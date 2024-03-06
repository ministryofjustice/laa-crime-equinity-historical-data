package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
public class CrmFileService {
    private final TaskRepository taskRepository;
    private final ObjectMapper jsonObjectMapper; //' = new ObjectMapper();

    public Crm5Model getCrmFileData(long taskId) {
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Crm5Model crmFormDetails = null;
        JSONObject crmFileJsonObject = getCrmFormJson(taskId);
//        ObjectMapper om = new ObjectMapper();
        try {
            crmFormDetails = jsonObjectMapper.readValue(crmFileJsonObject.toString(), Crm5Model.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        Crm5FormDetailsModel crm5Details = crm5Mapper.fromJsonToDto(crmFormDetails);
//        System.out.println("USN**::"+crm5Details.getUsn());

        return crmFormDetails;
    }

    private JSONObject getCrmFileJson(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + taskId + " not found"));

        // Collect and clean content
        String odfImageContentString = new String(task.exportCrmFile(), StandardCharsets.UTF_8)
                .replaceAll("\u0000", "")
        ;

        // Get form data
        return (JSONObject) XML.toJSONObject(odfImageContentString)
                .get("fd:formdata");
    }

    // TODO (): make this private, once we have all CRM Form Files available for data-mapping
    public JSONObject getCrmFormJson(long taskId) {
        JSONObject xmlJSONFormData = getCrmFileJson(taskId);
        // Cleanup form data by removing unused fields
        xmlJSONFormData.remove("printinfo");
        xmlJSONFormData.remove("schema");
        return xmlJSONFormData;
    }

    // TODO (): make this private, once we have all CRM Form Files available for data-mapping
    public JSONObject getCrmFileSchema(long taskId) {
        // Get form data
        JSONObject xmlJSONFormData = getCrmFileJson(taskId);
        // Cleanup form data by removing unused fields
        xmlJSONFormData.remove("printinfo");
        xmlJSONFormData.remove("fielddata");
        return xmlJSONFormData;
    }
}
