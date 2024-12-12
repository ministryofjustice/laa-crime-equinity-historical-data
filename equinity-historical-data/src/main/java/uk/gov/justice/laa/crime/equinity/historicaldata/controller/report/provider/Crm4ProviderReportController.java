package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ProviderReportCrm4Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.provider.Crm4ProviderReportService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.ReportInputsUtil;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4ProviderReportController implements ProviderReportCrm4Api {
    private final Crm4ProviderReportService reportService;


    @Override
    public ResponseEntity<String> generateProviderReportCrm4(
            String decisionFrom, String decisionTo, String providerAccount) {

        ReportInputsUtil.checkDateRange(decisionFrom, decisionTo);

        log.info("eForm CRM4 Provider report request received :: decision between [{}] and [{}]", decisionFrom, decisionTo);

        return ResponseEntity.ok(
            reportService.getReport(decisionFrom, decisionTo, providerAccount)
        );
    }
}
