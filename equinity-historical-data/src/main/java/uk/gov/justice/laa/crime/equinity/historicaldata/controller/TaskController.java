package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.TasksApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.model.TaskDetails;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.TaskSearchService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("equinity/tasks")
public class TaskController implements TasksApi {
    private final TaskSearchService taskService;

    @GetMapping(value="/{usn}")
    @Operation(description = "Search Task by ID (USN)")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400",
        description = "Bad request.",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)
        )
    )
    @ApiResponse(responseCode = "500",
        description = "Server Error.",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)
        )
    )
    public ResponseEntity<List<Task>> searchById(@PathVariable("usn") long taskId) {
        List<Task> tasks = taskService.searchById(taskId);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value="/")
    @Operation(description = "Search Tasks dynamically by given criteria (optional)")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400",
        description = "Bad request.",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)
        )
    )
    @ApiResponse(responseCode = "500",
        description = "Server Error.",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)
        )
    )
    public ResponseEntity<List<Task>> searchByType(
            @Nullable @RequestParam("typeId") Integer taskTypeId,
            @Nullable @RequestParam("originatedFrom")LocalDate dateFrom,
            @Nullable @RequestParam("originatedTo") LocalDate dateTo) {
        List<Task> tasks = taskService.searchAllByCriteria(taskTypeId, dateFrom, dateTo);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value="/{usn}/odf-image")
    @Operation(description = "Search a specific Task by ID (USN) for its OFD Image file")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400",
        description = "Bad request.",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)
        )
    )
    @ApiResponse(responseCode = "500",
        description = "Server Error.",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)
        )
    )
    public ResponseEntity<Map<String, Object>> getOdfImageByTaskId(@PathVariable("usn") long taskId) {
        Map<String, Object> ofdImageFileContents = taskService.getOfdImage(taskId);
        return new ResponseEntity<>(ofdImageFileContents, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDetails> getTask(BigDecimal id) {
        TaskDetails returnTask = new TaskDetails();
        returnTask.setTypeId(BigDecimal.valueOf(1));
        returnTask.setStateId(BigDecimal.valueOf(2));
        return new ResponseEntity<>(returnTask, HttpStatus.OK);
    }
}
