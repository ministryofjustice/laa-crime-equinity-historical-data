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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.TaskSearchService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("equinity/tasks")
public class TaskController {
    private final TaskSearchService taskService;
    private final CrmFileService crmFileService;

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

    @GetMapping(value= "/{usn}/crm-file")
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
    public ResponseEntity<Map<String, Object>> getCrmFileByUsn(@PathVariable("usn") long taskId) {
        Map<String, Object> crmFileContents = crmFileService.getCrmFormJson(taskId).toMap();
        return new ResponseEntity<>(crmFileContents, HttpStatus.OK);
    }

    @GetMapping(value= "/{usn}/crm-schema")
    @Operation(description = "Search a specific Task by ID (USN) for its OFD Image Schema file")
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
    public ResponseEntity<Map<String, Object>> getCrmFileSchemaByUsn(@PathVariable("usn") long taskId) {
        Map<String, Object> crmFileSchema = crmFileService.getCrmFileSchema(taskId).toMap();
        return new ResponseEntity<>(crmFileSchema, HttpStatus.OK);
    }
}
