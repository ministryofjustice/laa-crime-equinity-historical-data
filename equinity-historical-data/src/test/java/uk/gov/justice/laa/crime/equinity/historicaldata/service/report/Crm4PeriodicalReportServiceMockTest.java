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
        Crm4PeriodicalReportModel report = Crm4PeriodicalReportModel.builder()
                .clientUfn("031022/777").usn(5001600L).providerAccount("0D182J").firmName("ABELS").clientName("Joe modo")
                .repOrderNumber("78543657").maatId("").prisonLaw("No").receivedDate(LocalDate.of(2023, 3, 16))
                .decisionDate(LocalDate.of(2023, 3, 16)).decisionResult("Grant").expenditureType("a Psychiatrist")
                .expertName("tyjtjtjt").quantity(4.0).rate(50.0).unit("Hour(s)").totalCost(200.0).additionalExpenditure(0.0)
                .totalAuthority(200.0).totalGranted(200.0).grantingCaseworker("Sym-G").build();

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
        softly.assertThat(results).contains(",5001600,");
    }
}