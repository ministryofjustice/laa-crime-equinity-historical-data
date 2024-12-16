package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm5Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm5UpperLimitReportService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.ReportInputsUtil;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm5UpperLimitReportController implements ReportCrm5Api {
    private final Crm5UpperLimitReportService reportService;

    @Override
    public ResponseEntity<String> generateReportCrm5(
            String decisionFrom, String decisionTo, String profileAcceptedTypes) {

        ReportInputsUtil.checkInputs(decisionFrom, decisionTo,
                profileAcceptedTypes, CRM_TYPE_5
        );

        log.info("eForm CRM5 report request received :: decision between [{}] and [{}]", decisionFrom, decisionTo);

        return ResponseEntity.ok(
            reportService.getReport(decisionFrom, decisionTo)
        );
    }
}
