package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.NotEnoughSearchParametersException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.Crm14Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7.Crm7Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.CrmFormDetailsCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j
public class CrmFileService {
    public static final int CRM_TYPE_4 = 1;
    public static final int CRM_TYPE_5 = 4;
    public static final int CRM_TYPE_7 = 5;
    public static final int CRM_TYPE_14 = 6;
    private static final String CHAR_NULL = "\u0000";
    private static final String CHAR_HYPHEN ="\u2010";
    private static final String CHAR_AND ="&(?!a)";
    private static final String CHAR_EMPTY = "";
    private static final String CRM_FORM_DATA = "fd:formdata";
    private static final String CRM_PRINT_INFO = "printinfo";
    private static final String CRM_SCHEMA = "schema";
    public static final String CRM_LINKED_EVIDENCE = "linkedAttachments";
    public static final String CRM_LINKED_EVIDENCE_FILES = "linkedAttachment";
    public static final String CRM_FORM_FIELD_DATA = "fielddata";
    public static final String CRM_ROW = "row";
    public static final String CRM4_ADDITIONAL_EXPENDITURE ="Ae";
    public static final String CRM4_AUTHORISED_ADDITIONAL_EXPENDITURE ="Ae_cw";
    public static final String CRM4_RELATED_SUBMISSIONS ="Relatedsubmissions";
    public static final String CRM_FURTHER_INFO_ATTACHMENT ="Attachments";
    public static final String CRM7_DISBURSEMENTS ="Db";
    public static final String CRM7_SCHEDULE ="Schedule";
    public static final String CRM7_SCHEDULE_CW ="Schedule_cw";
    public static final String CRM14_CHARGES_BROUGHT = "Charges_brought";
    public static final String CRM14_MESSAGE_HISTORY ="Messagehistory";
    public static final String CRM15_BUSINESS_DETAILS = "Business_details";
    public static final String CRM15_PARTNER_BUSINESS_DETAILS = "Partner_business_details";
    public static final String CRM15_LAND_PROPERTY_DETAILS = "Land_and_property_table";
    public static final String CRM15_CAR_REG_DETAILS = "Car_reg_table";
    public static final String CRM15_INVESTMENT_DETAILS ="Investments_table";
    public static final String CRM15_SAVINGS_CERT_DETAILS ="Savings_certificates";
    public static final String CRM15_BANK_ACCOUNTS_DETAILS ="Bank_accounts";
    public static final String CRM15_EMPLOYMENT_DETAILS ="Employment_details";
    public static final String CRM15_PARTNER_EMPLOYMENT_DETAILS ="Partner_employment_details";
    public static final String CRM15_NEW_ATTACHMENTS ="Tblnewattachments";
    public static final String HAS_CRM15_SECTION ="crm15Section";
    public static final String CONTAINS_CRM15 ="crm15";


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

    @Timed("laa_crime_equiniti_historic_data_view_crmForm_details")
    public <T extends CrmFormModelInterface> T getCrmFormData(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) {
        JSONObject crmFileJsonObject = getCrmFileJson(crmFormDetailsCriteriaDTO);

        formattingSanityCheckConversions(crmFormDetailsCriteriaDTO, crmFileJsonObject);

        return convertCrmFileJsonToModel(crmFileJsonObject, crmFormDetailsCriteriaDTO);
    }

    /**
     *  Format sanity checks and conversions
     */
    private void formattingSanityCheckConversions(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO, JSONObject crmFileJsonObject) {
        switch (crmFormDetailsCriteriaDTO.type()) {
            case CRM_TYPE_4:
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM4_ADDITIONAL_EXPENDITURE, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM4_AUTHORISED_ADDITIONAL_EXPENDITURE, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM4_RELATED_SUBMISSIONS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM_FURTHER_INFO_ATTACHMENT, CRM_ROW);
            case CRM_TYPE_5:
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM_FURTHER_INFO_ATTACHMENT, CRM_ROW);
            break;
            case CRM_TYPE_7:
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM7_SCHEDULE, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM7_SCHEDULE_CW, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM7_DISBURSEMENTS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM_FURTHER_INFO_ATTACHMENT, CRM_ROW);
            break;
            case CRM_TYPE_14:
                sectionsCheck(crmFileJsonObject);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM14_CHARGES_BROUGHT, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_BUSINESS_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_PARTNER_BUSINESS_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_CAR_REG_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_LAND_PROPERTY_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_INVESTMENT_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_SAVINGS_CERT_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_BANK_ACCOUNTS_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_EMPLOYMENT_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_PARTNER_EMPLOYMENT_DETAILS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM15_NEW_ATTACHMENTS, CRM_ROW);
                convertCrmFormObjectToArray(crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA), CRM14_MESSAGE_HISTORY, CRM_ROW);
            break;
        }

        convertCrmFormObjectToArray(crmFileJsonObject, CRM_LINKED_EVIDENCE, CRM_LINKED_EVIDENCE_FILES);
    }

    public JSONObject getCrmFileJson(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) throws JSONException {
        TaskImageFilesModel task = (TaskImageFilesModel) taskImageFilesRepository.findOne(
                crmFormDetailsCriteria.getSpecification(crmFormDetailsCriteriaDTO)
            )
            .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + crmFormDetailsCriteriaDTO.usn() + " not found"));

        // Collect and clean content
        String crmFormFileContent = new String(task.getCrmFile(), StandardCharsets.UTF_8)
            .replaceAll(CHAR_NULL, CHAR_EMPTY);
         crmFormFileContent = crmFormFileContent.replaceAll(CHAR_HYPHEN,CHAR_EMPTY); // replacing hyphen unicode with empty space to remove malformed xml error
         crmFormFileContent = crmFormFileContent.replaceAll(CHAR_AND,CHAR_EMPTY);
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


    public void sectionsCheck(JSONObject crmFileJsonObject) {
        JSONArray sectionsArray  = (JSONArray)((JSONObject)(crmFileJsonObject.getJSONObject("SectionStates"))).get("SectionName");
        boolean hasCrm15Section =false;
       if (sectionsArray != null) {
            for (int i=0;i<sectionsArray.length();i++){
                if (sectionsArray.getString(i).contains(CONTAINS_CRM15)){
                    hasCrm15Section =true;
                    break;
                }
            }
        }
        crmFileJsonObject.getJSONObject(CRM_FORM_FIELD_DATA).put(HAS_CRM15_SECTION, hasCrm15Section);
    }

    public void convertCrmFormObjectToArray(JSONObject crmFileJsonObject, String jsonParentKey, String jsonChildKey) {
        JSONObject jsonParentObject;
        if (!crmFileJsonObject.has(jsonParentKey)) {
            jsonParentObject = new JSONObject();
            jsonParentObject.put(jsonChildKey, new JSONArray());
            crmFileJsonObject.put(jsonParentKey,jsonParentObject);
            return;
        }
        jsonParentObject = (JSONObject) crmFileJsonObject.get(jsonParentKey);

        if (!jsonParentObject.has(jsonChildKey)) {
            jsonParentObject.put(jsonChildKey, new JSONArray());
            crmFileJsonObject.put(jsonParentKey,jsonParentObject);
            return;
        }
        if (jsonParentObject.get(jsonChildKey) instanceof JSONObject) {
            log.info("CRM form [{}] :: [{}] to be a list. Try converting into a list", jsonParentKey, jsonChildKey);

            JSONArray toArray = new JSONArray();
            toArray.put(jsonParentObject.get(jsonChildKey));
            jsonParentObject.put(jsonChildKey, toArray);
            crmFileJsonObject.put(jsonParentKey,jsonParentObject);
        }
    }
}