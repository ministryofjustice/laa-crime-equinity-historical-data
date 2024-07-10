package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm4Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm4PeriodicalReportService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.ReportInputsUtil;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4PeriodicalReportController implements ReportCrm4Api {
    private final Crm4PeriodicalReportService reportService;


    @Override
    public ResponseEntity<String> generateReportCrm4(
            String decisionFrom, String decisionTo, String profileAcceptedTypes) {
        ReportInputsUtil.checkInputs(decisionFrom, decisionTo,
                profileAcceptedTypes, CRM_TYPE_4
        );

        return ResponseEntity.ok(
            reportService.getReport(decisionFrom, decisionTo)
        );
    }
}
