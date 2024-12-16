package uk.gov.justice.laa.crime.equinity.historicaldata.service.report.provider;

import io.micrometer.core.annotation.Timed;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm4ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm4ProviderReportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Crm4ProviderReportService {
    private final Crm4ProviderReportRepository reportRepository;

    @Transactional
    @Timed("laa_crime_equiniti_historic_data_provider_report_crm4_generate")
    public String getReport(String startDate, String endDate, String providerAccount) throws ResourceNotFoundException {
        log.info("Generating CRM4 Provider Report between :: {} and {}, provider account = {}", startDate, endDate, providerAccount);
        List<Crm4ProviderReportModel> report = reportRepository.getReport(startDate, endDate, providerAccount);

        if (report.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("No data found for CRM4 Provider Report between %s and %s, provider account = %s", startDate, endDate, providerAccount)
            );
        }

        log.info("CRM4 Provider report generated with {} records", report.size());
        return exportToCSV(report);
    }

    private String exportToCSV(List<Crm4ProviderReportModel> report) {
        return report.stream()
                .map(Crm4ProviderReportModel::exportToCSV)
                .reduce(Crm4ProviderReportModel.CSV_HEADER, String::concat);
    }
}
