package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm14Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm14CaseSummaryReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm14CaseSummaryReportController implements ReportCrm14Api {
    private final Crm14CaseSummaryReportService reportService;
    private final HttpServletResponse response;


    @Override
    public ResponseEntity<Void> generateReportCrm14(Integer filterByDecision, String decisionFrom, String decisionTo, Integer filterBySubmit, String submittedFrom, String submittedTo, Integer filterByCreation, String createdFrom, String createdTo, Integer filterByLastSubmit, String lastSubmittedFrom, String lastSubmittedTo, String profileAcceptedTypes, String state) {
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

        try {

            CSVWriter writer = new CSVWriter(response.getWriter(), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            // Write header to CSV file
            writer.writeNext(Crm14CaseSummaryReportModel.exportHeaderToCSVArray());

            // Write data to CSV file
            for (Crm14CaseSummaryReportModel report : reportData) {
                writer.writeNext(report.exportToCSVArray());
            }

            writer.close();
        } catch (IOException e) {
            log.error("Something went wrong generating CRM14 report for given criteria [{}]. Error:: {}. StackTrace:: {}", criteria, e.getMessage(), e.getStackTrace());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        }

        response.setStatus(HttpStatus.OK.value());
        return null;
    }

    // TODO (EMP-000): consider moving this function to a CSV Writer helper class
    private static String getResponseHeaderFilename() {
        return String.format(
                "attachment; filename=\"%s\"",
                Crm14CaseSummaryReportService.getCSVFileName()
        );
    }
}
