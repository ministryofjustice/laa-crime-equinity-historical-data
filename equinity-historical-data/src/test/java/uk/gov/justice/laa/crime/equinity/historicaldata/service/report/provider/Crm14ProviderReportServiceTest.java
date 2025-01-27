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
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14ReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm14ProviderReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14ProviderReportServiceTest {
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String START_DATE = CURRENT_DATE.minusDays(1).toString();
    private static final String END_DATE = CURRENT_DATE.toString();
    private static final String PROVIDER_ACCOUNT = "123ABC";
    private static final String STATE = "All";

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
                CURRENT_DATE, "Mr John Doe", "1ABCD",
                "Someone", "Crown Court", "Indictable", "Passed",
                CURRENT_DATE, "XXXX", "Some charge");

        when(mockReportRepository.getReport(
                PROVIDER_ACCOUNT,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE))
                .thenReturn(List.of(report));

        Crm14ReportCriteriaDTO reportCriteria = new Crm14ReportCriteriaDTO(
                1,  START_DATE, END_DATE,
                0,  START_DATE, END_DATE,
                0,  START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                STATE, PROVIDER_ACCOUNT, null
        );

        List<Crm14ProviderReportModel> results = reportService.getReportData(reportCriteria);

        softly.assertThat(results).hasSize(1);
        softly.assertThat(results.get(0)).isEqualTo(report);

        verify(mockReportRepository).getReport(PROVIDER_ACCOUNT,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE);
    }

    @Test
    void getReportData_WhenNoReportDataFoundThrowsException() {
        when(mockReportRepository.getReport(
                PROVIDER_ACCOUNT,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE))
                .thenReturn(List.of());

        Crm14ReportCriteriaDTO reportCriteria = new Crm14ReportCriteriaDTO(
                0,  START_DATE, END_DATE,
                1, START_DATE, END_DATE,
                0,  START_DATE, END_DATE,
                0,  START_DATE, END_DATE,
                "All", PROVIDER_ACCOUNT, null
        );

        softly.assertThatThrownBy(() -> reportService.getReportData(reportCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No data found for CRM14 Provider Report for inputs");

        verify(mockReportRepository).getReport(PROVIDER_ACCOUNT,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE);
    }
}
