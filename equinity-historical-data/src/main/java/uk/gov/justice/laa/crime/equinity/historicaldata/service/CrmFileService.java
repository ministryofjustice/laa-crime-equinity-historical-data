package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm5Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;

import java.nio.charset.StandardCharsets;



@Service
@RequiredArgsConstructor
@Slf4j
public class CrmFileService {
    private final TaskRepository taskRepository;
    private final ObjectMapper jsonObjectMapper;
    private final Crm5Mapper crm5Mapper;

//    public CrmFileService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//        this.jsonObjectMapper = new ObjectMapper(); // jsonObjectMapper;
//        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }

    public CRM5DetailsDTO getCrmFileData(long taskId) {
        Crm5Model crmFormDetails = null;
        JSONObject crmFileJsonObject = getCrmFormJson(taskId);

        try {
            crmFormDetails = jsonObjectMapper.readValue(crmFileJsonObject.toString(), Crm5Model.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        CRM5DetailsDTO cmr5DetailsDTO = crm5Mapper.getEntityFromModel(crmFormDetails.getFormDetails());
//        System.out.println("usn::"+cmr5DetailsDTO.getUsn());
//        System.out.println("getDetailsOfWorkCompleted::"+cmr5DetailsDTO.getDetailsOfWorkCompleted());
//        System.out.println("getfirm phone::"+cmr5DetailsDTO.getFirm().getFirmPhone());
//        System.out.println("getFirmContactName::"+cmr5DetailsDTO.getFirm().getFirmContactName());
//        System.out.println("getDateOfNextHearing::"+cmr5DetailsDTO.getProceedings().getDetailsOfProceedings().getDateOfNextHearing());
//        System.out.println("getDateOfNextHearing::"+cmr5DetailsDTO.getProceedings().getTypeOfProceedings().getPreCharge());
        return cmr5DetailsDTO;
    }

    private JSONObject getCrmFileJson(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + taskId + " not found"));

        // Collect and clean content
        String odfImageContentString = new String(task.exportCrmFile(), StandardCharsets.UTF_8)
                .replaceAll("\u0000", "");
        // Get form data
        return (JSONObject) XML.toJSONObject(odfImageContentString).get("fd:formdata");
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
