package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report.provider;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ProviderReportCrm14Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm14ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14ReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CsvWriterService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.provider.Crm14ProviderReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm14ProviderReportController implements ProviderReportCrm14Api {
    private final Crm14ProviderReportService reportService;
    private final HttpServletResponse response;
    private final CsvWriterService csvService;

    @Override
    public ResponseEntity<Void> generateProviderReportCrm14(Integer filterByDecision, String decisionFrom, String decisionTo,
                                                            Integer filterBySubmit, String submittedFrom, String submittedTo,
                                                            Integer filterByCreation, String createdFrom, String createdTo,
                                                            Integer filterByLastSubmit, String lastSubmittedFrom, String lastSubmittedTo,
                                                            String profileAcceptedTypes, String state) {
        Crm14ReportCriteriaDTO criteria;
        List<Crm14ProviderReportModel> reportData;

        try {
            criteria = new Crm14ReportCriteriaDTO(
                    filterByDecision, decisionFrom, decisionTo,
                    filterBySubmit, submittedFrom, submittedTo,
                    filterByCreation, createdFrom, createdTo,
                    filterByLastSubmit, lastSubmittedFrom, lastSubmittedTo,
                    state, profileAcceptedTypes
            );

            log.info("eForm CRM14 Provider report download request received :: [{}]", criteria);
            reportData = reportService.getReportData(criteria);
        } catch (UnauthorizedUserProfileException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw e;
        } catch (ResourceNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            throw e;
        }

        csvService.setupResponseHeaders(Crm14ProviderReportService.getCSVFileName());

        try {
            CSVWriter writer = csvService.open();

            // Write header to CSV file
            csvService.writeLine(writer, Crm14ProviderReportModel.CSV_HEADER_ARRAY);

            // Write data to CSV file
            for (Crm14ProviderReportModel report : reportData) {
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
