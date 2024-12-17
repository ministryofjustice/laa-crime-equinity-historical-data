package uk.gov.justice.laa.crime.equinity.historicaldata.service.report;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14CaseSummaryReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm14CaseSummaryReportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14CaseSummaryReportServiceMockTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm14CaseSummaryReportRepository reportRepository;

    @Autowired
    Crm14CaseSummaryReportService reportService;

    List<Crm14CaseSummaryReportModel> mockedResponse;

    @BeforeAll
    void setup() {
        mockedResponse = new ArrayList<>();

        mockedResponse.add(
            new Crm14CaseSummaryReportModel(
                5012603L,
                LocalDate.of(2017, 5, 6),
                "7000011",
                LocalDate.of(2017, 5, 6),
                "Miss Lois Lane",
                "0D182J",
                "Clark Kent",
                "HMCTS / CAT",
                "CAT 1",
                "19990",
                "Metropolis Youth Court",
                "Wayne, Mr. Bruce",
                "Completed",
                null,
                "Summary-Only",
                null,
                "NOT_NEEDED",
                "Passed",
                "Yes",
                "Magistrates' Court or CFS: Passed",
                "Magistrates' Court or CFS: Granted",
                LocalDate.of(2017, 5, 9),
                "LUTHOR CORP LLP",
                "New application",
                "Y",
                "N",
                1,
                "Y",
                1,
                LocalDate.of(2017, 5, 6),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );

        mockedResponse.add(
            new Crm14CaseSummaryReportModel(
                4004444L,
                LocalDate.of(2017, 5, 6),
                "4004004",
                LocalDate.of(2017, 5, 30),
                "Mr Reed Richards",
                "0D182J",
                "Silver Surfer",
                "HMCTS / CAT",
                "CAT 1",
                "19990",
                "Galactic Magistrates' Court",
                "Von Doom, Mr. Victor",
                "Completed",
                null,
                "Summary-Only",
                LocalDate.of(2017, 6, 1),
                "NOT_NEEDED",
                "Passed",
                "Yes",
                "Magistrates' Court or CFS: Passed",
                "Magistrates' Court or CFS: Granted",
                LocalDate.of(2017, 6, 4),
                "LESS THAN 4 SOLICITORS LTD",
                "New application",
                "N",
                "N",
                1,
                "Y",
                2,
                LocalDate.of(2017, 6, 1),
                LocalDate.of(2017, 6, 1),
                LocalDate.of(2017, 6, 1),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );
    }

    @BeforeEach
    void setupTest() {
        when(reportRepository.getReport(any(), any(), any(), any(), any(), any(),any(), any(),any(),any(), any(),any(),any()))
                .thenReturn(mockedResponse);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getReportDataWhenCalledMockedThenShouldReturnList() {
        Crm14CaseSummaryReportCriteriaDTO criteria = new Crm14CaseSummaryReportCriteriaDTO(
                1, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                0, "2010-02-01", "2024-06-01",
                "All", null
        );
        List<Crm14CaseSummaryReportModel> results = reportService.getReportData(criteria);

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results.get(0).getUsn()).isEqualTo(5012603L);
        softly.assertThat(results.get(1).getUsn()).isEqualTo(4004444L);
    }
}
