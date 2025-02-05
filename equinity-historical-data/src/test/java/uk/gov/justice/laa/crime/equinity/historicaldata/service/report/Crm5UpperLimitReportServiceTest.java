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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm5UpperLimitReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm5UpperLimitReportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm5UpperLimitReportServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm5UpperLimitReportRepository mockReportRepository;

    @Autowired
    Crm5UpperLimitReportService reportService;

    @BeforeAll
    void setup() {
        List<Crm5UpperLimitReportModel> expectedResponse = new ArrayList<>();
        Crm5UpperLimitReportModel report = new Crm5UpperLimitReportModel(
                5001600L,
                "0D182J",
                "ABELS",
                "Joe modo",
                "031022/777",
                238,
                239,
                LocalDate.of(2023, 3, 16),
                LocalDate.of(2023, 3, 16),
                "POOL-Mock",
                "Appeal",
                "Advice"
        );
        expectedResponse.add(report);

        when(mockReportRepository.getReport("2010-02-01", "2024-06-01")).thenReturn(expectedResponse);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getReportShouldReturnCSV() {
        String results = reportService.getReport("2010-02-01", "2024-06-01");

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results).isEqualTo("""
                Usn,Provider Account,Firm Name,Client Name,LAA Case Ref,New Limit \
                Request,Upper Limit Extended,Date Received,Decision Date,User,Review,Level Of Work
                5001600,0D182J,ABELS,Joe modo,031022/777,238.0,239.0,2023-03-16,2023-03-16,POOL-Mock,Appeal,Advice
                """);
    }
}
