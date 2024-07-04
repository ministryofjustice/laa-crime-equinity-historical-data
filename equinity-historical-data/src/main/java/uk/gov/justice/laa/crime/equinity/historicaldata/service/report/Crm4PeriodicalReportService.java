package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm4PeriodicalReportRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class Crm4PeriodicalReportService {
    private final Crm4PeriodicalReportRepository reportRepository;

    @Transactional
    public String getReport(String startDate, String endDate) throws ResourceNotFoundException {
        log.info("Generating CRM4 Periodical Report between :: {} and {}", startDate, endDate);
        List<Crm4PeriodicalReportModel> report = reportRepository.getReport(startDate, endDate);

        if (report.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("No data found for CRM4 Periodical Report between %s and %s",
                        startDate, endDate)
            );
        }

        log.info("CRM4 Periodical report generated with {} records", report.size());
        return exportToCSV(report);
    }

    private String exportToCSV(List<Crm4PeriodicalReportModel> report) {
        return report.stream()
                .map(Crm4PeriodicalReportModel::exportToCSV)
                .reduce(Crm4PeriodicalReportModel.exportHeaderToCSV(), String::concat);
    }
}
