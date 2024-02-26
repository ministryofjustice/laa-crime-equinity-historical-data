package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.TaskSearchCriteria;

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
