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

    private final TaskImageFilesRepository taskImageFilesRepository;
    private final ObjectMapper jsonObjectMapper;
    private final CrmFormDetailsCriteria crmFormDetailsCriteria;

    private static Class<? extends CrmFormModelInterface> getCrmFormTypeMapClass(Integer type) throws NotEnoughSearchParametersException {
        return switch (type) {
            case CRM_TYPE_4 -> Crm4Model.class;
            case CRM_TYPE_5 -> Crm5Model.class;
            case CRM_TYPE_7 -> Crm7Model.class;
            default -> throw new NotEnoughSearchParametersException("A valid CRM From Type is required. Given type: " + type);
        };
    }

    public <T extends CrmFormModelInterface> T getCrmFormData(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) {
        JSONObject crmFileJsonObject = getCrmFileJson(crmFormDetailsCriteriaDTO);

        // Format sanity checks and conversions
        crmFileJsonObject.put("linkedAttachments", convertCrmFormLinkedAttachments(crmFileJsonObject));

        return convertCrmFileJsonToModel(crmFileJsonObject, crmFormDetailsCriteriaDTO);
    }

    public JSONObject getCrmFileJson(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) throws JSONException {
        TaskImageFilesModel task = (TaskImageFilesModel) taskImageFilesRepository.findOne(
                crmFormDetailsCriteria.getSpecification(crmFormDetailsCriteriaDTO)
            )
            .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + crmFormDetailsCriteriaDTO.usn() + " not found"));

        // Collect and clean content
        String crmFormFileContent = new String(task.getCrmFile(), StandardCharsets.UTF_8)
            .replaceAll("\u0000", "");

        return convertCrmFileContentToJson(crmFormFileContent);
    }

    private JSONObject convertCrmFileContentToJson(String crmImageFile) throws JSONException {
        // Extract eForm data content
        JSONObject crmFileJsonObject = (JSONObject) XML.toJSONObject(crmImageFile)
                .get("fd:formdata");

        // Cleanup eForm data by removing unused fields
        crmFileJsonObject.remove("printinfo");
        crmFileJsonObject.remove("schema");

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

        if (!crmFileJsonObject.has("linkedAttachments")) {
            linkedAttachments = new JSONObject();
            linkedAttachments.put("linkedAttachment", new JSONArray());
            return linkedAttachments;
        }

        linkedAttachments = (JSONObject) crmFileJsonObject.get("linkedAttachments");

        if (!linkedAttachments.has("linkedAttachment")) {
            linkedAttachments.put("linkedAttachment", new JSONArray());
            return linkedAttachments;
        }

        if (linkedAttachments.get("linkedAttachment") instanceof JSONObject) {
            log.warn("CRM eForm evidence files expected to be a list. Try converting into a list");

            JSONArray linkedAttachmentArray = new JSONArray();
            linkedAttachmentArray.put(linkedAttachments.get("linkedAttachment"));
            linkedAttachments.put("linkedAttachment", linkedAttachmentArray);
            return linkedAttachments;
        }

        return linkedAttachments;
    }

}

