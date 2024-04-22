package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal/v1/equinity/crm-file")
public class CrmFileController {
    private final CrmFileService crmFileService;

    @GetMapping(value= "/{usn}")
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
    public ResponseEntity<Map<String, Object>> getCrmFileByUsn(@PathVariable("usn") Long taskId) {
        Map<String, Object> crmFileContents = crmFileService.getCrmFormJson(taskId).toMap();
        return new ResponseEntity<>(crmFileContents, HttpStatus.OK);
    }

}
