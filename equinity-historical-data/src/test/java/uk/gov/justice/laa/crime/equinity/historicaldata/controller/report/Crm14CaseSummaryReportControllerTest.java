package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm14CaseSummaryReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14CaseSummaryReportControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = "6";
    private static final String DENIED_PROFILE_TYPES = "2,9";
    private static final String STATE_DEFAULT = "All";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm14CaseSummaryReportRepository mockReportRepository;

    @Autowired
    private Crm14CaseSummaryReportController controller;

    @Autowired
    HttpServletResponse response;

    /**
     * Date Format input checks
     **/
    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateReportCrm14Test_WhenInvalidDateIsGivenThenReturnConstraintViolationException(String invalidDate) throws ConstraintViolationException {
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                        1, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm14.decisionFrom: must match")
                .hasMessageContaining("generateReportCrm14.decisionTo: must match")
                .hasMessageContaining("generateReportCrm14.submittedFrom: must match")
                .hasMessageContaining("generateReportCrm14.submittedTo: must match")
                .hasMessageContaining("generateReportCrm14.createdFrom: must match")
                .hasMessageContaining("generateReportCrm14.createdTo: must match")
                .hasMessageContaining("generateReportCrm14.lastSubmittedFrom: must match")
                .hasMessageContaining("generateReportCrm14.lastSubmittedTo: must match");
    }

    @Test
    void generateReportCrm14Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                        1, startDate, endDate,
                        0, startDate, endDate,
                        0, startDate, endDate,
                        0, startDate, endDate,
                        ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [2024-02-19] must not be after end date [2024-02-09]");
    }

    /**
     * Report data collection tests
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {ACCEPTED_PROFILE_TYPES})
    void generateReportCrm14Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO(String profileTypes) throws ConstraintViolationException {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        Crm14CaseSummaryReportModel report = new Crm14CaseSummaryReportModel(
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
        );

        when(mockReportRepository.getReport(1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate, STATE_DEFAULT,
                0, startDate, endDate)).thenReturn(List.of(report));

        // execute
        ResponseEntity<Void> result = controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                profileTypes, STATE_DEFAULT
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(result).isNull();
    }

    @Test
    void generateReportCrm14Test_WhenExistingDecisionDatesAndInvalidProfileAreGivenThenReturnUnauthorizedUserProfileException() {
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
