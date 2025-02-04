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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.Crm14ReportCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm14CaseSummaryReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14CaseSummaryReportServiceTest {
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String START_DATE = CURRENT_DATE.minusDays(1).toString();
    private static final String END_DATE = CURRENT_DATE.toString();
    private static final String STATE = "All";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm14CaseSummaryReportRepository reportRepository;

    @Autowired
    Crm14CaseSummaryReportService reportService;

    @BeforeAll
    void setup() {
        List<Crm14CaseSummaryReportModel> mockedResponse = List.of(
                new Crm14CaseSummaryReportModel(5012603L, CURRENT_DATE,
                        "7000011", CURRENT_DATE, "Miss Lois Lane",
                        "0D182J", "Clark Kent", "HMCTS / CAT", "CAT 1",
                        "19990", "Metropolis Youth Court", "Wayne, Mr. Bruce", "Completed",
                        null, "Summary-Only", null, "NOT_NEEDED",
                        "Passed", "Yes", "Magistrates' Court or CFS: Passed",
                        "Magistrates' Court or CFS: Granted", CURRENT_DATE,
                        "LUTHOR CORP LLP", "New application", "Y", "N",
                        1, "Y", 1, CURRENT_DATE,
                        null, null, null, null, null,
                        null, null, null, null, null),

                new Crm14CaseSummaryReportModel(4004444L, CURRENT_DATE, "4004004",
                        CURRENT_DATE, "Mr Reed Richards", "0D182J",
                        "Silver Surfer", "HMCTS / CAT", "CAT 1", "19990",
                        "Galactic Magistrates' Court", "Von Doom, Mr. Victor", "Completed", null,
                        "Summary-Only", CURRENT_DATE, "NOT_NEEDED",
                        "Passed", "Yes", "Magistrates' Court or CFS: Passed", "Magistrates' Court or CFS: Granted",
                        CURRENT_DATE, "LESS THAN 4 SOLICITORS LTD", "New application",
                        "N", "N", 1, "Y", 2,
                        CURRENT_DATE, CURRENT_DATE,CURRENT_DATE, null, null, null,
                        null, null, null, null, null)
        );

        when(reportRepository.getReport(
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE))
                .thenReturn(mockedResponse);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getReportDataShouldReturnList() {
        Crm14ReportCriteriaDTO criteria = new Crm14ReportCriteriaDTO(
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                STATE, "0D182J", null, true
        );

        List<Crm14CaseSummaryReportModel> results = reportService.getReportData(criteria);

        softly.assertThat(results).hasSize(2);
        softly.assertThat(results.get(0).getUsn()).isEqualTo(5012603L);
        softly.assertThat(results.get(1).getUsn()).isEqualTo(4004444L);

        verify(reportRepository).getReport(
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE);
    }
}
