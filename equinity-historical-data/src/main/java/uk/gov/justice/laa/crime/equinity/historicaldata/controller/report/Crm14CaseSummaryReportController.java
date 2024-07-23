package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<String> generateReportCrm14(
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


        return ResponseEntity.ok(reportService.getReport(criteria));
    }
}
