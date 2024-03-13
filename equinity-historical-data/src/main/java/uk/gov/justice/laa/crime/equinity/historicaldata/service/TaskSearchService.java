package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.TaskSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    public LocalDateTime convertToDateTime(LocalDate dateToConvert) {
        return LocalDateTime.of(dateToConvert.getYear(), dateToConvert.getMonth(), dateToConvert.getDayOfMonth(), 0, 0, 0);
    }
}
