package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm14Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CsvWriterService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm14CaseSummaryReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm14CaseSummaryReportController implements ReportCrm14Api {
    private final Crm14CaseSummaryReportService reportService;
    private final HttpServletResponse response;
    private final CsvWriterService csvService;


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

        csvService.setupResponseHeaders(Crm14CaseSummaryReportService.getCSVFileName());

        try {
            CSVWriter writer = csvService.open();

            // Write header to CSV file
            csvService.writeLine(writer, Crm14CaseSummaryReportModel.exportHeaderToCSVArray());

            // Write data to CSV file
            for (Crm14CaseSummaryReportModel report : reportData) {
                csvService.writeLine(writer, report.exportToCSVArray());
            }

            csvService.close(writer);
        } catch (IOException e) {
            log.error("Something went wrong generating CRM14 report for given criteria [{}]. Error:: {}. StackTrace:: {}", criteria, e.getMessage(), e.getStackTrace());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        }

        response.setStatus(HttpStatus.OK.value());
        return null;
    }
}
