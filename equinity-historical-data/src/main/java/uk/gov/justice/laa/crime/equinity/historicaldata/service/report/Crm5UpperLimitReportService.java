package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import io.micrometer.core.annotation.Timed;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm5UpperLimitReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm5UpperLimitReportRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class Crm5UpperLimitReportService {
    private final Crm5UpperLimitReportRepository reportRepository;

    @Transactional
    @Timed("laa_crime_equiniti_historic_data_report_crm5_generate")
    public String getReport(String startDate, String endDate) throws ResourceNotFoundException {
        log.info("Generating CRM5 Upper Limit Report between :: {} and {}", startDate, endDate);
        List< Crm5UpperLimitReportModel> report = reportRepository.getReport(startDate, endDate);

        if (report.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("No data found for CRM5 Upper Limit Report between %s and %s",
                        startDate, endDate)
            );
        }

        log.info("CRM5 Upper Limit Report generated with {} records", report.size());
        return exportToCSV(report);
    }

    private String exportToCSV(List<Crm5UpperLimitReportModel> report) {
        return report.stream()
                .map(Crm5UpperLimitReportModel::exportToCSV)
                .reduce(Crm5UpperLimitReportModel.exportHeaderToCSV(), String::concat);
    }
}
