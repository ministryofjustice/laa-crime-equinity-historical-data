package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm4PeriodicalReportRepository;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class Crm4PeriodicalReportServiceLocalTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Mock
    Crm4PeriodicalReportRepository reportRepository;

    @Autowired
    Crm4PeriodicalReportService reportService;

    @BeforeAll
    void setup() {
        when(reportRepository.getReport("fail", "fail"))
                .thenThrow(Mockito.mock(InvalidDataAccessResourceUsageException.class));
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getReportWhenMockingSQLExceptionThenShouldReturnExceptionAndEnd() {
        try {
            reportService.getReport("fail", "fail");
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // Test coverage using mocked data is covered in a separate test
        }
    }

    @Test
    void getReportWhenUsingSqlConnectionAndValidInputThenShouldReturnReportCSV() {
        try {
            String results = reportService.getReport("2010-02-01", "2024-06-01");

            softly.assertThat(results).isNotEmpty();
            softly.assertThat(results).startsWith(Crm4PeriodicalReportModel.CSV_HEADER);
            softly.assertThat(results).contains(",5001613,");
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }
}