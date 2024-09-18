package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm14CaseSummaryReportService;

import java.util.List;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class Crm14CaseSummaryReportControllerTest {
    private static final String STATE_DEFAULT = "All";
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);
    private static final String DENIED_PROFILE_TYPES = "2,9";
    private List<String> testInvalidFormatDates;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Mock
    private Crm14CaseSummaryReportService reportService;

    @Autowired
    private Crm14CaseSummaryReportController controller;


    @BeforeAll
    void preTest() {
        testInvalidFormatDates = List.of(
                "123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"
        );
    }

    /**
     * Date Format input checks
     **/

    @Test
    void generateReportCrm14Test_WhenInvalidDateIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";
        String validDate = "2050-01-01";

        // execute
        testInvalidFormatDates.forEach(dateToTest -> {
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                            1, dateToTest, dateToTest,
                            0, dateToTest, dateToTest,
                            0, dateToTest, dateToTest,
                            0, dateToTest, dateToTest,
                            ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                    .isInstanceOf(ConstraintViolationException.class)
                    .hasMessageContaining(expectedMessage);
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                            1, dateToTest, validDate,
                            0, validDate, validDate,
                            0, validDate, validDate,
                            0, validDate, validDate,
                            ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                    .isInstanceOf(ConstraintViolationException.class)
                    .hasMessageContaining(expectedMessage);
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                            1, validDate, dateToTest,
                            0, validDate, validDate,
                            0, validDate, validDate,
                            0, validDate, validDate,
                            ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                    .isInstanceOf(ConstraintViolationException.class)
                    .hasMessageContaining(expectedMessage);
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                                1, validDate, validDate,
                                0, dateToTest, validDate,
                                0, validDate, validDate,
                                0, validDate, validDate,
                                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage);
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                                1, validDate, validDate,
                                0, validDate, dateToTest,
                                0, validDate, validDate,
                                0, validDate, validDate,
                                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage);
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                                1, validDate, validDate,
                                0, dateToTest, validDate,
                                0, validDate, validDate,
                                0, validDate, validDate,
                                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage);
                softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                                1, validDate, validDate,
                                0, validDate, validDate,
                                0, dateToTest, validDate,
                                0, validDate, dateToTest,
                                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage);
            }
        );
    }

    @Test
    void generateReportCrm14Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";
        String expectedMessage = "must not be after end date";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
            .isInstanceOf(DateRangeConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    /**
     * Report data collection tests
     */

    @Test
    void generateReportCrm14Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO() {
        try {
            String startDate = "2010-02-01";
            String endDate = "2024-06-01";

            // execute
            ResponseEntity<byte[]> response = controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT
            );

            softly.assertThat(response.getBody()).isNotEmpty();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void generateReportCrm14Test_WhenExistingDecisionDatesAndNoProfileAreGivenThenReturnDTO() {
        try {
            String startDate = "2010-02-01";
            String endDate = "2024-06-01";

            // execute
            ResponseEntity<byte[]> response = controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                null, STATE_DEFAULT
            );

            softly.assertThat(response.getBody()).isNotEmpty();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
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

    @Test
    void generateReportCrm14Test_WhenValidDecisionRangeWithNoDataIsGivenThenReturnResourceNotFoundException() {
        try {
            String startDate = "1988-02-01";
            String endDate = "1988-02-02";

            // execute
            controller.generateReportCrm14(
                    1, startDate, endDate,
                    0, startDate, endDate,
                    0, startDate, endDate,
                    0, startDate, endDate,
                    ACCEPTED_PROFILE_TYPES, STATE_DEFAULT
            );
        } catch (ResourceNotFoundException e) {
            softly.assertThat(e).isInstanceOf(ResourceNotFoundException.class);
            softly.assertThat(e.getMessage()).contains("CRM14");
            softly.assertThat(e.getMessage()).contains("No data found");
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }
}
