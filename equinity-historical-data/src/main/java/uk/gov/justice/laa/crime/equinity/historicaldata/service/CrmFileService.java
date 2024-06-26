package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.NotEnoughSearchParametersException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j
public class CrmFileService {
    public static final int CRM_TYPE_4 = 1;
    public static final int CRM_TYPE_5 = 4;
    public static final int CRM_TYPE_7 = 5;
    public static final int CRM_TYPE_14 = 6;
    public static final int CRM_TYPE_15 = 7;
    private static final String CHAR_NULL = "\u0000";
    private static final String CHAR_EMPTY = "";
    private static final String CRM_FORM_DATA = "fd:formdata";
    private static final String CRM_PRINT_INFO = "printinfo";
    private static final String CRM_SCHEMA = "schema";
    private static final String CRM_LINKED_EVIDENCE = "linkedAttachments";
    private static final String CRM_LINKED_EVIDENCE_FILES = "linkedAttachment";
    private static final String CRM14_CHARGES_BROUGHT = "Charges_brought";
    private static final String CRM14_ROW = "row";
    private static final String CRM_FORM_FIELD_DATA = "fielddata";
    private static final String CRM15_BUSINESS_DETAILS = "Business_details";
    private static final String CRM15_PARTNER_BUSINESS_DETAILS = "Partner_business_details";
    private static final String CRM15_LAND_PROPERTY_DETAILS = "Land_and_property_table";


    private final TaskImageFilesRepository taskImageFilesRepository;
    private final ObjectMapper jsonObjectMapper;
    private final CrmFormDetailsCriteria crmFormDetailsCriteria;

    private static Class<? extends CrmFormModelInterface> getCrmFormTypeMapClass(Integer type) throws NotEnoughSearchParametersException {
        return switch (type) {
            case CRM_TYPE_4 -> Crm4Model.class;
            case CRM_TYPE_5 -> Crm5Model.class;
            case CRM_TYPE_7 -> Crm7Model.class;
            case CRM_TYPE_14 -> Crm14Model.class;
            default -> throw new NotEnoughSearchParametersException("A valid CRM From Type is required. Given type: " + type);
        };
    }

    public <T extends CrmFormModelInterface> T getCrmFormData(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) {
        JSONObject crmFileJsonObject = getCrmFileJson(crmFormDetailsCriteriaDTO);
        // Format sanity checks and conversions
        if (crmFormDetailsCriteriaDTO.type() == CRM_TYPE_14){
            convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM14_CHARGES_BROUGHT, CRM14_ROW);
            convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_BUSINESS_DETAILS, CRM14_ROW);
            convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_PARTNER_BUSINESS_DETAILS, CRM14_ROW);
            convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_LAND_PROPERTY_DETAILS, CRM14_ROW);
        }
        crmFileJsonObject.put(CRM_LINKED_EVIDENCE, convertCrmFormLinkedAttachments(crmFileJsonObject));

        return convertCrmFileJsonToModel(crmFileJsonObject, crmFormDetailsCriteriaDTO);
    }

    public JSONObject getCrmFileJson(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) throws JSONException {
        TaskImageFilesModel task = (TaskImageFilesModel) taskImageFilesRepository.findOne(
                crmFormDetailsCriteria.getSpecification(crmFormDetailsCriteriaDTO)
            )
            .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + crmFormDetailsCriteriaDTO.usn() + " not found"));

        // Collect and clean content
        String crmFormFileContent = new String(task.getCrmFile(), StandardCharsets.UTF_8)
            .replaceAll(CHAR_NULL, CHAR_EMPTY);

        return convertCrmFileContentToJson(crmFormFileContent);
    }

    private JSONObject convertCrmFileContentToJson(String crmImageFile) throws JSONException {
        // Extract eForm data content
        JSONObject crmFileJsonObject = XML.toJSONObject(crmImageFile)
                .getJSONObject(CRM_FORM_DATA);

        // Cleanup eForm data by removing unused fields
        crmFileJsonObject.remove(CRM_PRINT_INFO);
        crmFileJsonObject.remove(CRM_SCHEMA);

        return crmFileJsonObject;
    }

    public <T extends CrmFormModelInterface> T convertCrmFileJsonToModel(JSONObject crmFileJsonObject, CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) throws NotEnoughSearchParametersException, JSONException {
        CrmFormModelInterface crmFormData;

        try {
            crmFormData = jsonObjectMapper.readValue(
                crmFileJsonObject.toString(),
                getCrmFormTypeMapClass(crmFormDetailsCriteriaDTO.type())
            );
        } catch (JsonProcessingException e) {
            throw new JSONException(e);
        }

        return (T) crmFormData;
    }

    public JSONObject convertCrmFormLinkedAttachments(JSONObject crmFileJsonObject) {
        JSONObject linkedAttachments;

        if (!crmFileJsonObject.has(CRM_LINKED_EVIDENCE)) {
            linkedAttachments = new JSONObject();
            linkedAttachments.put(CRM_LINKED_EVIDENCE_FILES, new JSONArray());
            return linkedAttachments;
        }

        linkedAttachments = (JSONObject) crmFileJsonObject.get(CRM_LINKED_EVIDENCE);

        if (!linkedAttachments.has(CRM_LINKED_EVIDENCE_FILES)) {
            linkedAttachments.put(CRM_LINKED_EVIDENCE_FILES, new JSONArray());
            return linkedAttachments;
        }

        if (linkedAttachments.get(CRM_LINKED_EVIDENCE_FILES) instanceof JSONObject) {
            log.warn("CRM eForm evidence files expected to be a list. Try converting into a list");

            JSONArray linkedAttachmentArray = new JSONArray();
            linkedAttachmentArray.put(linkedAttachments.get(CRM_LINKED_EVIDENCE_FILES));
            linkedAttachments.put(CRM_LINKED_EVIDENCE_FILES, linkedAttachmentArray);
            return linkedAttachments;
        }

        return linkedAttachments;
    }

    public void convertCrmFormObjectToArray(JSONObject crmFileJsonObject, String jsonParentKey, String jsonChildKey) {
        JSONObject jsonParentObject;
        if (!crmFileJsonObject.has(jsonParentKey)) {
            jsonParentObject = new JSONObject();
            jsonParentObject.put(jsonParentKey, new JSONArray());
            return;
        }
        jsonParentObject = (JSONObject) crmFileJsonObject.get(jsonParentKey);

        if (!jsonParentObject.has(jsonChildKey)) {
            jsonParentObject.put(jsonChildKey, new JSONArray());
            return;
        }
        if (jsonParentObject.get(jsonChildKey) instanceof JSONObject) {
            log.warn("CRM form "+ jsonParentKey + " Charges Brought to be a list. Try converting into a list");
            JSONArray toArray = new JSONArray();
            toArray.put(jsonParentObject.get(jsonChildKey));
            jsonParentObject.put(jsonChildKey, toArray);
        }
    }
}
