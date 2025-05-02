package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ProviderReportCrm4Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.provider.Crm4ProviderReportService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.AppUtil;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.ReportInputsUtil;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;
import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.DateRange.DECISION;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4ProviderReportController implements ProviderReportCrm4Api {
    private final Crm4ProviderReportService reportService;
    private final AppUtil appUtil;

    @Override
    public ResponseEntity<String> generateProviderReportCrm4(
            String decisionFrom, String decisionTo, String providerAccount, String profileAcceptedTypes) {

        ReportInputsUtil.checkInputs(decisionFrom, decisionTo,
                profileAcceptedTypes, CRM_TYPE_4, appUtil.applySevenYearsLimit());

        log.info("eForm CRM4 Provider report request received :: decision date between [{}] and [{}], provider account = [{}]", decisionFrom, decisionTo, providerAccount);

        return ResponseEntity.ok(
            reportService.getReport(decisionFrom, decisionTo, providerAccount)
        );
    }
}
