package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.TaskSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormDetails;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class TaskSearchService {
    private final TaskRepository taskRepository;
    private final TaskSearchCriteria taskSearchCriteria;

    public List<Task> searchById(long taskId) {
        List<Task> tasksFound =  taskRepository.findById(taskId)
            .stream()
            .toList();

        if (tasksFound.isEmpty()) {
            throw new ResourceNotFoundException("Task with USN " + taskId + " not found");
        }

        return tasksFound;
    }

    public List<Task> searchAllByCriteria(@Nullable Integer taskTypeId, @Nullable LocalDate dateFrom, @Nullable LocalDate dateTo) {
        LocalDateTime dateTimeFrom = Objects.isNull(dateFrom) ? null :
                convertToDateTime(dateFrom);

        LocalDateTime dateTimeTo = Objects.isNull(dateTo) ? null :
                convertToDateTime(dateTo).plusDays(1);

        List<Task> tasksFound =  taskRepository.findAll(
                taskSearchCriteria.getSpecification(taskTypeId, dateTimeFrom, dateTimeTo)
            )
                .stream()
                .toList();

        if (tasksFound.isEmpty()) {
            throw new ResourceNotFoundException("No Tasks were found");
        }

        return tasksFound;
    }

    public JSONObject getXMLJsonObject(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + taskId + " not found"));

        // Collect and clean content
        String odfImageContentString = new String(task.exportCrmFile(), StandardCharsets.UTF_8)
                .replaceAll("\u0000", "")
                ;

        // Get form data
        JSONObject xmlJSONFormData = (JSONObject) XML.toJSONObject(odfImageContentString)
                .get("fd:formdata")
                ;
        // Cleanup form data by removing unused fields
        xmlJSONFormData.remove("printinfo");
        xmlJSONFormData.remove("schema");
        xmlJSONFormData.remove("signature_data");
        xmlJSONFormData.remove("version");
        xmlJSONFormData.remove("xmlns:fd");
        xmlJSONFormData.remove("familypath");
        xmlJSONFormData.remove("SectionStates");
        return xmlJSONFormData;
    }

    public Map<String, Object> getCrmFile(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + taskId + " not found"));

        // Collect and clean content
        String odfImageContentString = new String(task.exportCrmFile(), StandardCharsets.UTF_8)
            .replaceAll("\u0000", "")
        ;

        // Get form data
        JSONObject xmlJSONFormData = (JSONObject) XML.toJSONObject(odfImageContentString)
                .get("fd:formdata")
        ;
        // Cleanup form data by removing unused fields
        xmlJSONFormData.remove("printinfo");
        xmlJSONFormData.remove("schema");
        xmlJSONFormData.remove("signature_data");
        xmlJSONFormData.remove("version");
        xmlJSONFormData.remove("xmlns:fd");
        xmlJSONFormData.remove("familypath");
        xmlJSONFormData.remove("SectionStates");
        return xmlJSONFormData.toMap();
    }

    public Map<String, Object> getCrmFile_Old(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + taskId + " not found"));

        // Collect and clean content
        String odfImageContentString = new String(task.exportCrmFile(), StandardCharsets.UTF_8)
                .replaceAll("\u0000", "")
                ;

        // Get form data
        JSONObject xmlJSONFormData = (JSONObject) XML.toJSONObject(odfImageContentString)
                .get("fd:formdata")
                ;
        // Cleanup form data by removing unused fields
        xmlJSONFormData.remove("printinfo");
        xmlJSONFormData.remove("schema");
        xmlJSONFormData.remove("signature_data");
        xmlJSONFormData.remove("version");
        xmlJSONFormData.remove("xmlns:fd");
        xmlJSONFormData.remove("familypath");
        xmlJSONFormData.remove("SectionStates");
        System.out.println("xmlJSONFormData::"+xmlJSONFormData.toString());
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            CrmFormDetails root = om.readValue(xmlJSONFormData.toString(), CrmFormDetails.class);
            System.out.println("getSubmitter_user_id::"+root.getFielddataObject().getSubmitter_user_id());
            System.out.println("getSolicitorid::"+root.getFielddataObject().getSolicitorname());
            System.out.println("Fc_reject_reasons_text::"+root.getFielddataObject().getFc_processingoffice());
            System.out.println("getDate_received::"+root.getFielddataObject().getClient_dob());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return xmlJSONFormData.toMap();
    }

    public Map<String, Object> getCrmFileSchema(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with USN " + taskId + " not found"));

        // Collect and clean content
        String odfImageContentString = new String(task.exportCrmFile(), StandardCharsets.UTF_8)
                .replaceAll("\u0000", "");

        // Get form data
        JSONObject xmlJSONFormData = (JSONObject) XML.toJSONObject(odfImageContentString)
                .get("fd:formdata");
        // Cleanup form data by removing unused fields
        xmlJSONFormData.remove("printinfo");
        xmlJSONFormData.remove("fielddata");
        return xmlJSONFormData.toMap();
    }

    public LocalDateTime convertToDateTime(LocalDate dateToConvert) {
        return LocalDateTime.of(dateToConvert.getYear(), dateToConvert.getMonth(), dateToConvert.getDayOfMonth(), 0, 0, 0);
    }
}
