package uk.gov.justice.laa.crime.equinity.historicaldata.service.report.provider;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm14ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14ProviderReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm14ProviderReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14ProviderReportServiceTest {

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm14ProviderReportRepository mockReportRepository;

    @Autowired
    Crm14ProviderReportService reportService;

    @Test
    void getReportData_WhenGivenReportCriteriaReturnsData() {
        Crm14ProviderReportModel report = new Crm14ProviderReportModel(
                1234567L, LocalDate.of(2023, 3, 16), "",
                LocalDate.of(2023, 3, 16), "Mr John Doe", "1ABCD",
                "Someone", "Crown Court", "Indictable", "Passed",
                LocalDate.of(2023, 3, 16), "XXXX", "Some charge");

        when(mockReportRepository.getReport(
                1, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01", "All",
                0, "2010-02-01", "2024-06-01"))
                .thenReturn(List.of(report));

        Crm14ProviderReportCriteriaDTO reportCriteria = new Crm14ProviderReportCriteriaDTO(
                1, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                "All"
        );

        List<Crm14ProviderReportModel> results = reportService.getReportData(reportCriteria);

        softly.assertThat(results).hasSize(1);
        softly.assertThat(results.get(0)).isEqualTo(report);
    }

    @Test
    void getReportData_WhenNoReportDataFoundThrowsException() {
        when(mockReportRepository.getReport(
                0, "2010-02-01", "2024-06-01",
                1, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01", "All",
                0, "2010-02-01", "2024-06-01"))
                .thenReturn(List.of());

        Crm14ProviderReportCriteriaDTO reportCriteria = new Crm14ProviderReportCriteriaDTO(
                1, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                "All"
        );

        softly.assertThatThrownBy(() -> reportService.getReportData(reportCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No data found for CRM14 Provider Report for inputs");
    }
}