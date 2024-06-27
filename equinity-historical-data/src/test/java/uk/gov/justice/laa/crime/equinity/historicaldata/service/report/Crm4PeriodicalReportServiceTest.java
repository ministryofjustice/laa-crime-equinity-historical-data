package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class Crm4PeriodicalReportServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    Crm4PeriodicalReportService reportService;

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void callCrm4PeriodicalReportRepoProcedure() {
        String results = reportService.getReport("2010-02-01", "2024-06-01");

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results).startsWith(Crm4PeriodicalReportModel.exportHeaderToCSV());
    }
}