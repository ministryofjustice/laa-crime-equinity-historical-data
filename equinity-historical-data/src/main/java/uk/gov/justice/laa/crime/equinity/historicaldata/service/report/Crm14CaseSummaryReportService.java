package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import io.micrometer.core.annotation.Timed;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm14CaseSummaryReportRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Crm14CaseSummaryReportService {

    private final Crm14CaseSummaryReportRepository reportRepository;

    public static String getCSVFileName() {
        return String.format("Report_CRM14_CaseSummary_%s.csv", LocalDate.now());
    }

    @Transactional
    @Timed("laa_crime_equiniti_historic_data_report_crm14_get_data")
    public List<Crm14CaseSummaryReportModel> getReportData(Crm14CaseSummaryReportCriteriaDTO reportCriteria) throws ResourceNotFoundException {
        log.info("Collecting data for CRM14 Case Summary Report with :: {} ", reportCriteria);
        List<Crm14CaseSummaryReportModel> reportData = reportRepository.getReport(
                reportCriteria.filterByDecision(), reportCriteria.decisionFrom(), reportCriteria.decisionTo(),
                reportCriteria.filterBySubmit(), reportCriteria.submittedFrom(), reportCriteria.submittedTo(),
                reportCriteria.filterByCreation(), reportCriteria.createdFrom(), reportCriteria.createdTo(),
                reportCriteria.state(),
                reportCriteria.filterByLastSubmit(), reportCriteria.lastSubmittedFrom(), reportCriteria.lastSubmittedTo()
        );

        if (reportData.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No data found for CRM14 Case Summary Report for inputs %s ", reportCriteria)
            );
        }

        log.info("CRM14 Case Summary Report generated with {} records", reportData.size());
        return reportData;
    }
}
