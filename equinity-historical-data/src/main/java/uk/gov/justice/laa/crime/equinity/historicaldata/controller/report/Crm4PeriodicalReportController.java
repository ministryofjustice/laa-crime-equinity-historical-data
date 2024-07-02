package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.ReportCrm4Api;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm4PeriodicalReportService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4PeriodicalReportController implements ReportCrm4Api {
    private final Crm4PeriodicalReportService reportService;


    @Override
    public ResponseEntity<String> generateReportCrm4(
            String decisionFrom, String decisionTo, String profileAcceptedTypes) {
        checkRequestInputs(decisionFrom, decisionTo, profileAcceptedTypes);

        return ResponseEntity.ok(
            reportService.getReport(decisionFrom, decisionTo)
        );
    }

    private void checkRequestInputs(String decisionFrom, String decisionTo, String profileAcceptedTypes) throws DateRangeConstraintViolationException, ResourceNotFoundException {
        LocalDate dateDecisionFrom = DateUtil.convertStringToLocalDate(decisionFrom);
        LocalDate dateDecisionTo = DateUtil.convertStringToLocalDate(decisionTo);
        DateUtil.checkDateRangeIsValid(dateDecisionFrom, dateDecisionTo);

        checkValidProfileAcceptedTypes(profileAcceptedTypes);
    }

    private void checkValidProfileAcceptedTypes(@Nullable String types) throws ResourceNotFoundException {
        if (types == null) return;

        if (!types.contains(String.valueOf(CRM_TYPE_4))) {
            throw new ResourceNotFoundException("No data found. User profile does not have privileges to access CRM4 data");
        }
    }
}
