package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
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
    public String getReport(Crm14CaseSummaryReportCriteriaDTO reportCriteria) throws ResourceNotFoundException {
        log.info("Generating CRM14 Case Summary Report for :: {} ", reportCriteria);
        List< Crm14CaseSummaryReportModel> report = reportRepository.getReport(
            reportCriteria.filterByDecision(), reportCriteria.decisionFrom(), reportCriteria.decisionTo(),
            reportCriteria.filterBySubmit(), reportCriteria.submittedFrom(), reportCriteria.submittedTo(),
            reportCriteria.filterByCreation(), reportCriteria.createdFrom(), reportCriteria.createdTo(),
            reportCriteria.state(),
            reportCriteria.filterByLastSubmit(), reportCriteria.lastSubmittedFrom(), reportCriteria.lastSubmittedTo()
        );


        if (report.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("No data found for CRM14 Case Summary Report for inputs %s ", reportCriteria)
            );
        }

        log.info("CRM14 Case Summary Report generated with {} records", report.size());
        String reportInCSV = exportToCSV(report);
        log.info("CRM14 Case Summary Report exported to CSV format");
        return reportInCSV;
    }

    private String exportToCSV(List<Crm14CaseSummaryReportModel> report) {
        return report.stream()
                .map(Crm14CaseSummaryReportModel::exportToCSV)
                .reduce(Crm14CaseSummaryReportModel.exportHeaderToCSV(), String::concat);
    }
}
