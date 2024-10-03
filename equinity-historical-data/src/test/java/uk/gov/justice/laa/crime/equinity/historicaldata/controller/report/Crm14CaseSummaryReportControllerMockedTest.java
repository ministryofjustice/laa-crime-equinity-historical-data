package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm14CaseSummaryReportService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14CaseSummaryReportControllerMockedTest {
    private static final String STATE_DEFAULT = "All";
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);
    private static final String DENIED_PROFILE_TYPES = "2,9";


    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    private Crm14CaseSummaryReportService service;

    @Autowired
    @InjectMocks
    private Crm14CaseSummaryReportController controller;

    @Autowired
    HttpServletResponse response;

    List<Crm14CaseSummaryReportModel> mockedReport;


    @BeforeAll
    void setup() {
        // with results
        mockedReport = List.of(
            new Crm14CaseSummaryReportModel(
                2040533L,
                LocalDate.of(2016, 9, 12),
                "6620929",
                LocalDate.of(2019, 5, 17),
                "Mr WILL TURNER",
                "0K919G",
                "Elizabeth Swan",
                "HMCTS / CAT",
                "CAT 1",
                "19990",
                "North Of Caribbean (Tortuga) Magistrates' Court",
                "Sparrow; Cpt. Jack",
                "Completed",null,"Summary-Only",null,"NOT_NEEDED","Passed","Yes",
                "Magistrates' Court or CFS: Passed","Magistrates' Court or CFS: Granted",
                LocalDate.of(2013, 3, 28),
                "BLACK PEARL SOLICITORS",
                "New application",
                "Y","N",2,"Y",
                1,
                LocalDate.of(2022, 7, 7),
                null,null,null,
                null,null,null,null,
                null,null,null
            )
        );

        when(service.getReportData(any())).thenReturn(mockedReport);
    }


    /**
     * Report data collection tests
     */

    @Test
    void generateReportCrm14Test_WhenValidProfileAreGivenThenReturnSuccessHttpStatus() {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        // execute
        ResponseEntity<Void> result = controller.generateReportCrm14(
            1, startDate, endDate,
            0, startDate, endDate,
            0, startDate, endDate,
            0, startDate, endDate,
            ACCEPTED_PROFILE_TYPES, STATE_DEFAULT
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(result).isNull();
    }

    @Test
    void generateReportCrm14Test_WhenNoProfileIsGivenThenReturnSuccessHttpStatus() {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        // execute
        ResponseEntity<Void> result = controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                null, STATE_DEFAULT
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(result).isNull();
    }

    @Test
    void generateReportCrm14Test_WhenInvalidProfileIsGivenThenReturnUnauthorizedUserProfileException() {
            String startDate = "2010-02-01";
            String endDate = "2024-06-01";

            // execute
            softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                    1, startDate, endDate,
                    0, startDate, endDate,
                    0, startDate, endDate,
                    0, startDate, endDate,
                    DENIED_PROFILE_TYPES, STATE_DEFAULT))
                .isInstanceOf(UnauthorizedUserProfileException.class);
    }
}
