package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm14Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm14CaseSummaryReportService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm14CaseSummaryReportController implements ReportCrm14Api {
    private final Crm14CaseSummaryReportService reportService;

    @Override
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

        String logMessage = String.format("eForm CRM14 report request received :: [%s]", criteria);
        log.info(logMessage);
        // TODO (EMP-182): This is only to count how many requests are received. Review to replace once other metric systems are introduced
        Sentry.captureMessage(logMessage, SentryLevel.INFO);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, getResponseHeaderFilename())
            .body(reportService.getReport(criteria).getBytes());
    }

    private static String getResponseHeaderFilename() {
        return String.format(
            "attachment; filename=\"%s\"",
            Crm14CaseSummaryReportService.getCSVFileName()
        );
    }
}
