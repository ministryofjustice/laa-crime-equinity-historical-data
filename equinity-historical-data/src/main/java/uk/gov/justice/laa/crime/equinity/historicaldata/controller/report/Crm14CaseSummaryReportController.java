package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm14Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm14CaseSummaryReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
// TODO (EMP-000): if download version can not be added to openapi specification, then remove the report implementation
public class Crm14CaseSummaryReportController implements ReportCrm14Api {
    private final Crm14CaseSummaryReportService reportService;

    /**
     * GET /api/internal/v1/equinity/report/crm14/ : CRM14 Summary Report
     *
     * @param filterByDecision  (required)
     * @param decisionFrom  (required)
     * @param decisionTo  (required)
     * @param filterBySubmit  (required)
     * @param submittedFrom  (required)
     * @param submittedTo  (required)
     * @param filterByCreation  (required)
     * @param createdFrom  (required)
     * @param createdTo  (required)
     * @param filterByLastSubmit  (required)
     * @param lastSubmittedFrom  (required)
     * @param lastSubmittedTo  (required)
     * @param state  (optional, default to All)
     * @param profileAcceptedTypes  (optional)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/internal/v1/equinity/report/crm14/download/"
    )
    public void generateReportCrm14(
            @NotNull @Min(0) @Max(1)  @Valid @RequestParam(value = "filterByDecision", required = true)
            Integer filterByDecision,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "decisionFrom", required = true)
            String decisionFrom,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "decisionTo", required = true)
            String decisionTo,
            @NotNull @Min(0) @Max(1)  @Valid @RequestParam(value = "filterBySubmit", required = true)
            Integer filterBySubmit,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "submittedFrom", required = true)
            String submittedFrom,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "submittedTo", required = true)
            String submittedTo,
            @NotNull @Min(0) @Max(1)  @Valid @RequestParam(value = "filterByCreation", required = true)
            Integer filterByCreation,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "createdFrom", required = true)
            String createdFrom,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "createdTo", required = true)
            String createdTo,
            @NotNull @Min(0) @Max(1)  @Valid @RequestParam(value = "filterByLastSubmit", required = true)
            Integer filterByLastSubmit,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "lastSubmittedFrom", required = true)
            String lastSubmittedFrom,
            @NotNull @Pattern(regexp = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")  @Valid @RequestParam(value = "lastSubmittedTo", required = true)
            String lastSubmittedTo,
            @Size(min = 2, max = 40)  @Valid @RequestParam(value = "state", required = false, defaultValue = "All")
            String state,
            @RequestHeader(value="profileAcceptedTypes", required = false)
            String profileAcceptedTypes,
            HttpServletResponse response
    ) throws IOException {
        Crm14CaseSummaryReportCriteriaDTO criteria;
        List<Crm14CaseSummaryReportModel> reportData;

        try {
            criteria = new Crm14CaseSummaryReportCriteriaDTO(
                    filterByDecision, decisionFrom, decisionTo,
                    filterBySubmit, submittedFrom, submittedTo,
                    filterByCreation, createdFrom, createdTo,
                    filterByLastSubmit, lastSubmittedFrom, lastSubmittedTo,
                    state, profileAcceptedTypes
            );

            log.info("eForm CRM14 report download request received :: [{}]", criteria);
            reportData = reportService.getReportData(criteria);
        } catch (UnauthorizedUserProfileException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw e;
        } catch (ResourceNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            throw e;
        }

        // TODO (EMP-000): consider moving this logic to a CSV Writer helper class
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,  getResponseHeaderFilename());

        CSVWriter writer = new CSVWriter(response.getWriter(), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        // Write header to CSV file
        writer.writeNext(Crm14CaseSummaryReportModel.exportHeaderToCSVArray());

        // Write data to CSV file
        for (Crm14CaseSummaryReportModel report : reportData) {
            writer.writeNext(report.exportToCSVArray());
        }

        writer.close();
    }

    // TODO (EMP-000): consider moving this function to a CSV Writer helper class
    private static String getResponseHeaderFilename() {
        return String.format(
                "attachment; filename=\"%s\"",
                Crm14CaseSummaryReportService.getCSVFileName()
        );
    }

    @Override
    @Deprecated
    // TODO (EMP-000) consider removing this version (or find a way to add it to openapi specification)
    public ResponseEntity<byte[]> generateReportCrm14(
            Integer filterByDecision, String decisionFrom, String decisionTo,
            Integer filterBySubmit, String submittedFrom, String submittedTo,
            Integer filterByCreation, String createdFrom, String createdTo,
            Integer filterByLastSubmit, String lastSubmittedFrom, String lastSubmittedTo,
            String profileAcceptedTypes, String state) {

        Crm14CaseSummaryReportCriteriaDTO criteria = new Crm14CaseSummaryReportCriteriaDTO(
                filterByDecision, decisionFrom, decisionTo,
                filterBySubmit, submittedFrom, submittedTo,
                filterByCreation, createdFrom, createdTo,
                filterByLastSubmit, lastSubmittedFrom, lastSubmittedTo,
                state, profileAcceptedTypes
        );

        log.info("eForm CRM14 report request received :: [{}]", criteria);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, getResponseHeaderFilename())
                .body(reportService.getReport(criteria).getBytes());
    }
}
