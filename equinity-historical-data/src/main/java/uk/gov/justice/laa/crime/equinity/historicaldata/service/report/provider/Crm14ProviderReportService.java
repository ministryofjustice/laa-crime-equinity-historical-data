package uk.gov.justice.laa.crime.equinity.historicaldata.service.report.provider;

import io.micrometer.core.annotation.Timed;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm14ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14ReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm14ProviderReportRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Crm14ProviderReportService {

    private final Crm14ProviderReportRepository reportRepository;

    public static String getCSVFileName() {
        return String.format("Provider_Report_CRM14_%s.csv", LocalDate.now());
    }

    @Transactional
    @Timed("laa_crime_equiniti_historic_data_provider_report_crm14_get_data")
    public List<Crm14ProviderReportModel> getReportData(Crm14ReportCriteriaDTO reportCriteria) throws ResourceNotFoundException {
        log.info("Collecting data for CRM14 Provider Report with :: {} ", reportCriteria);
        List<Crm14ProviderReportModel> reportData = reportRepository.getReport(
                reportCriteria.providerAccount(),
                reportCriteria.filterByDecision(), reportCriteria.decisionFrom(), reportCriteria.decisionTo(),
                reportCriteria.filterBySubmit(), reportCriteria.submittedFrom(), reportCriteria.submittedTo(),
                reportCriteria.filterByCreation(), reportCriteria.createdFrom(), reportCriteria.createdTo(),
                reportCriteria.state(),
                reportCriteria.filterByLastSubmit(), reportCriteria.lastSubmittedFrom(), reportCriteria.lastSubmittedTo()
        );

        if (reportData.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No data found for CRM14 Provider Report for inputs %s ", reportCriteria)
            );
        }

        log.info("CRM14 Provider Report generated with {} records", reportData.size());
        return reportData;
    }
}
