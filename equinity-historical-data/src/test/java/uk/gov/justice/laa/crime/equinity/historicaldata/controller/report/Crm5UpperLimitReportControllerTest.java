package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

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
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm5UpperLimitReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm5UpperLimitReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm5UpperLimitReportControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = "4";
    private static final String DENIED_PROFILE_TYPES = "2,9";

    @MockBean
    Crm5UpperLimitReportRepository mockReportRepository;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    private Crm5UpperLimitReportController controller;

    /**
     * Date Format input checks
     **/
    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateReportCrm5Test_WhenInvalidDecisionDateFromIsGivenThenReturnConstraintViolationException(String invalidDate) {
        String validDate = "2050-01-01";

        softly.assertThatThrownBy(() -> controller.generateReportCrm5(invalidDate, validDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm5.decisionFrom: must match");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateReportCrm5Test_WhenInvalidDecisionDateToIsGivenThenReturnConstraintViolationException(String invalidDate) {
        String validDate = "2050-01-01";

        softly.assertThatThrownBy(() -> controller.generateReportCrm5(validDate, invalidDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm5.decisionTo: must match");
    }

    @Test
    void generateReportCrm5Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(startDate, endDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [2024-02-19] must not be after end date [2024-02-09]");
    }

    /**
     * Report data collection tests
     */
    @ParameterizedTest
    @NullSource // test when profileTypes = null
    @ValueSource(strings = {ACCEPTED_PROFILE_TYPES})
    void generateReportCrm5Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO(String profileTypes) {

        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

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

        when(mockReportRepository.getReport(startDate, endDate)).thenReturn(List.of(report));

        // execute
        ResponseEntity<String> response = controller.generateReportCrm5(startDate, endDate, profileTypes);

        softly.assertThat(response.getBody()).isNotEmpty();
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void generateReportCrm5Test_WhenExistingDecisionDatesAndInvalidProfileAreGivenThenReturnUnauthorizedUserProfileException() {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(startDate, endDate, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [4]");
    }

    @Test
    void generateReportCrm5Test_WhenNonExistingValidDecisionDatesAreGivenThenReturnResourceNotFoundException() {

        String startDate = "1988-02-01";
        String endDate = "1988-02-02";

        when(mockReportRepository.getReport(startDate, endDate)).thenReturn(List.of());

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(startDate, endDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No data found for CRM5 Upper Limit Report between 1988-02-01 and 1988-02-02");
    }
}
