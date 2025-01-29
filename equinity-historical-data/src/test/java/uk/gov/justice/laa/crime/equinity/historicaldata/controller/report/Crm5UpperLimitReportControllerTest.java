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
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.StartDateConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm5UpperLimitReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm5UpperLimitReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;
import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.getMinStartDate;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm5UpperLimitReportControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_5);
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String DECISION_FROM = CURRENT_DATE.minusDays(1).toString();
    private static final String DECISION_TO = CURRENT_DATE.toString();
    private static final String DENIED_PROFILE_TYPES = "2,9";
    private static final LocalDate MIN_START_DATE = getMinStartDate();

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
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(invalidDate, DECISION_TO, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm5.decisionFrom: must match");
    }

    @Test
    void generateReportCrm5Test_WhenDecisionDateFromIsBeforeMinStartDateThenThrowConstraintViolationException() {
        String decisionFrom = MIN_START_DATE.minusMonths(2).toString();

        softly.assertThatThrownBy(() -> controller.generateReportCrm5(decisionFrom, DECISION_TO, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(StartDateConstraintViolationException.class)
                .hasMessage("Start Date Constraint Violation Exception :: decision start date ["
                        + decisionFrom + "] cannot be earlier than " + MIN_START_DATE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateReportCrm5Test_WhenInvalidDecisionDateToIsGivenThenReturnConstraintViolationException(String invalidDate) {
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(DECISION_FROM, invalidDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm5.decisionTo: must match");
    }


    @Test
    void generateReportCrm5Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String decisionFrom = CURRENT_DATE.toString();
        String decisionTo = CURRENT_DATE.minusDays(1).toString(); // before decisionFrom

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(decisionFrom, decisionTo, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [" + decisionFrom + "] must not be after end date [" + decisionTo + "]");
    }

    /**
     * Report data collection tests
     */
    @ParameterizedTest
    @NullSource // test when profileTypes = null
    @ValueSource(strings = "4")
    void generateReportCrm5Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO(String profileTypes) {
        Crm5UpperLimitReportModel report = new Crm5UpperLimitReportModel(
                5001600L,
                "0D182J",
                "ABELS",
                "Joe modo",
                "031022/777",
                238,
                239,
                CURRENT_DATE,
                CURRENT_DATE,
                "POOL-Mock",
                "Appeal",
                "Advice"
        );

        when(mockReportRepository.getReport(DECISION_FROM, DECISION_TO)).thenReturn(List.of(report));

        // execute
        ResponseEntity<String> response = controller.generateReportCrm5(DECISION_FROM, DECISION_TO, profileTypes);

        softly.assertThat(response.getBody()).isNotEmpty();
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(mockReportRepository).getReport(DECISION_FROM, DECISION_TO);
    }

    @Test
    void generateReportCrm5Test_WhenExistingDecisionDatesAndInvalidProfileAreGivenThenReturnUnauthorizedUserProfileException() {
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(DECISION_FROM, DECISION_TO, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [4]");
    }

    @Test
    void generateReportCrm5Test_WhenNonExistingValidDecisionDatesAreGivenThenReturnResourceNotFoundException() {
        when(mockReportRepository.getReport(DECISION_FROM, DECISION_TO)).thenReturn(List.of());

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm5(DECISION_FROM, DECISION_TO, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No data found for CRM5 Upper Limit Report between " + DECISION_FROM + " and " + DECISION_TO);

        verify(mockReportRepository).getReport(DECISION_FROM, DECISION_TO);
    }
}
