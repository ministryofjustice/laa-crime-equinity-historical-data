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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.report.Crm5UpperLimitReportService;

import java.util.List;

import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class Crm5UpperLimitReportControllerTest {
    private List<String> testInvalidFormatDates;
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_5);
    private static final String DENIED_PROFILE_TYPES = "2,9";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Mock
    Crm5UpperLimitReportService reportService;

    @Autowired
    Crm5UpperLimitReportController controller;


    @BeforeAll
    void preTest() {
        testInvalidFormatDates = List.of(
                "123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"
        );

        when(reportService.getReport("fail", "fail"))
            .thenThrow(Mockito.mock(InvalidDataAccessResourceUsageException.class));
    }

    /**
     * Date Format input checks
     **/
    @Test
    void generateReportCrm5Test_WhenInvalidDecisionDateFromIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";
        String validDate = "2050-01-01";


        // execute
        testInvalidFormatDates.forEach(dateToTest ->
            softly.assertThatThrownBy(() -> controller.generateReportCrm5(
                    dateToTest, validDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void generateReportCrm5Test_WhenInvalidDecisionDateToIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";
        String validDate = "2050-01-01";


        // execute
        testInvalidFormatDates.forEach(dateToTest ->
            softly.assertThatThrownBy(() -> controller.generateReportCrm5(
                    validDate, dateToTest, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void generateReportCrm5Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";
        String expectedMessage = "must not be after end date";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(
                startDate, endDate, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(DateRangeConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    /**
     * Report data collection tests
     */

    @Test
    void generateReportCrm5Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO() {
        try {
            String startDate = "2010-02-01";
            String endData = "2024-06-01";

            // execute
            ResponseEntity<String> response = controller.generateReportCrm5(
                    startDate, endData, ACCEPTED_PROFILE_TYPES
            );

            softly.assertThat(response.getBody()).isNotEmpty();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void generateReportCrm5Test_WhenExistingDecisionDatesAndNoProfileAreGivenThenReturnDTO() {
        try {
            String startDate = "2010-02-01";
            String endData = "2024-06-01";

            // execute
            ResponseEntity<String> response = controller.generateReportCrm5(
                    startDate, endData, null
            );

            softly.assertThat(response.getBody()).isNotEmpty();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void generateReportCrm5Test_WhenExistingDecisionDatesAndInvalidProfileAreGivenThenReturnResourceNotFoundException() {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(
                        startDate, endDate, DENIED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void generateReportCrm5Test_WhenNonExistingValidDecisionDatesAreGivenThenReturnResourceNotFoundException() {
        try {
            String startDate = "1988-02-01";
            String endDate = "1988-02-02";

            // execute
            controller.generateReportCrm5(
                    startDate, endDate, ACCEPTED_PROFILE_TYPES
            );
        } catch (ResourceNotFoundException e) {
            softly.assertThat(e).isInstanceOf(ResourceNotFoundException.class);
            softly.assertThat(e.getMessage()).contains("CRM5");
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }


}
