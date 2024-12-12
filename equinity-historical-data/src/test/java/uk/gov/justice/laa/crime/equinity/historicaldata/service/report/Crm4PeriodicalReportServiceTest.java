package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm4ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm4PeriodicalReportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4PeriodicalReportServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm4PeriodicalReportRepository mockReportRepository;

    @Autowired
    Crm4PeriodicalReportService reportService;

    @Test
    void getReportShouldReturnCSV() {

        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        when(mockReportRepository.getReport(startDate, endDate)).thenReturn(List.of(new Crm4PeriodicalReportModel(
                "123456/123", 1234567L, "1ABCD", "XXXX", "John Doe",
                "1234567", "", "No", LocalDate.of(2023, 3, 16),
                LocalDate.of(2023, 3, 16), "Grant", "Some expert",
                "tyjtjtjt", 4.0, 50.0, "Hour(s)", 200.0, 0.0,
                200.0, 200.0, "XXXX")
        ));


        String results = reportService.getReport(startDate, endDate);

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results).isEqualTo("Client UFN,Usn,Provider Account,Firm Name,Client Name," +
                "Rep Order Number,Maat ID,Prison Law,Date Received,Decision Date,Decision,Expenditure Type,Expert Name," +
                "Quantity,Rate,Unit,Total Cost,Additional Expenditure,Total Authority,Total Granted,Granting Caseworker\n" +
                "123456/123,1234567,1ABCD,XXXX,John Doe,1234567,,No,2023-03-16,2023-03-16,Grant,Some expert,tyjtjtjt," +
                "4.0,50.0,Hour(s),200.0,0.0,200.0,200.0,XXXX\n");
    }

    @Test
    void getReportShouldThrowExceptionWhenNoReportData() {

        String startDate = "1988-02-01";
        String endDate = "1988-02-02";

        when(mockReportRepository.getReport(startDate, endDate)).thenReturn(List.of());

        // execute
        softly.assertThatThrownBy(() -> reportService.getReport(startDate, endDate))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No data found for CRM4 Periodical Report between 1988-02-01 and 1988-02-02");

    }
}