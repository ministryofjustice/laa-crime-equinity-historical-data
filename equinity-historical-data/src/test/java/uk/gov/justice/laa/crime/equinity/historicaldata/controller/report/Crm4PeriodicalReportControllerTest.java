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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm4PeriodicalReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4PeriodicalReportControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_4);
    private static final String DENIED_PROFILE_TYPES = "2,9";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm4PeriodicalReportRepository mockReportRepository;

    @Autowired
    Crm4PeriodicalReportController controller;

    /**
     * Report data collection tests
     */
    @ParameterizedTest
    @NullSource // test when profileTypes = null
    @ValueSource(strings = "1")
    void generateReportCrm4Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO(String profileTypes) {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        Crm4PeriodicalReportModel report = Crm4PeriodicalReportModel.builder()
                .clientUfn("031022/777").usn(5001600L).providerAccount("0D182J").firmName("ABELS").clientName("Joe modo")
                .repOrderNumber("78543657").maatId("").prisonLaw("No").receivedDate(LocalDate.of(2023, 3, 16))
                .decisionDate(LocalDate.of(2023, 3, 16)).decisionResult("Grant").expenditureType("a Psychiatrist")
                .expertName("tyjtjtjt").quantity(4.0).rate(50.0).unit("Hour(s)").totalCost(200.0).additionalExpenditure(0.0)
                .totalAuthority(200.0).totalGranted(200.0).grantingCaseworker("Sym-G").build();

        when(mockReportRepository.getReport(startDate, endDate)).thenReturn(List.of(report));

        // execute
        ResponseEntity<String> response = controller.generateReportCrm4(startDate, endDate, profileTypes);

        softly.assertThat(response.getBody()).isNotEmpty();
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Date Format input checks
     **/
    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateReportCrm4Test_WhenInvalidDecisionDateFromIsGivenThenReturnConstraintViolationException(String invalidDate) {
        String validDate = "2050-01-01";

        softly.assertThatThrownBy(() -> controller.generateReportCrm4(invalidDate, validDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm4.decisionFrom: must match");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateReportCrm4Test_WhenInvalidDecisionDateToIsGivenThenReturnConstraintViolationException(String invalidDate) {
        String validDate = "2050-01-01";

        softly.assertThatThrownBy(() -> controller.generateReportCrm4(validDate, invalidDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateReportCrm4.decisionTo: must match");
    }

    @Test
    void generateReportCrm4Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm4(startDate, endDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [2024-02-19] must not be after end date [2024-02-09]");
    }

    @Test
    void generateReportCrm4Test_WhenExistingDecisionDatesAndInvalidProfileAreGivenThenReturnUnauthorizedUserProfileException() {
        String startDate = "2010-02-01";
        String endDate = "2024-06-01";

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm4(startDate, endDate, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [1]");
    }

    @Test
    void generateReportCrm4Test_WhenNonExistingValidDecisionDatesAreGivenThenReturnResourceNotFoundException() {
        String startDate = "1988-02-01";
        String endDate = "1988-02-02";

        when(mockReportRepository.getReport(startDate, endDate)).thenReturn(List.of());

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm4(startDate, endDate, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No data found for CRM4 Periodical Report between 1988-02-01 and 1988-02-02");
    }
}
