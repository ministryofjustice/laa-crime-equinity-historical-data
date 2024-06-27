package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm4PeriodicalReportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4PeriodicalReportServiceMockTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm4PeriodicalReportRepository reportRepository;

    @Autowired
    Crm4PeriodicalReportService reportService;

    @BeforeAll
    void setup() {
        List<Crm4PeriodicalReportModel> expectedResponse = new ArrayList<>();
        Crm4PeriodicalReportModel report = new Crm4PeriodicalReportModel(
                "031022/777",
                5001600L,
                "0D182J",
                "ABELS",
                "Joe modo",
                "78543657",
                "",
                "No",
                LocalDate.of(2023, 3, 16),
                LocalDate.of(2023, 3, 16),
                "Grant",
                "a Psychiatrist",
                "tyjtjtjt",
                4.0,
                50.0,
                "Hour(s)",
                200.0,
                0.0,
                200.0,
                200.0,
                "Sym-G"
        );
        expectedResponse.add(report);

        when(reportRepository.getReport(any(), any()))
                .thenReturn(expectedResponse);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getReportWhenCalledMockedThenShouldReturnCSV() {
        String results = reportService.getReport("2010-02-01", "2024-06-01");

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results).startsWith(Crm4PeriodicalReportModel.exportHeaderToCSV());
        softly.assertThat(results).contains("5001600");
    }
}