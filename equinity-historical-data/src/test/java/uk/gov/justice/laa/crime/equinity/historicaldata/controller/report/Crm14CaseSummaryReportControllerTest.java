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
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.StartDateConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.Crm14CaseSummaryReportRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CsvWriterService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14CaseSummaryReportControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String START_DATE = CURRENT_DATE.minusDays(1).toString();
    private static final String END_DATE = CURRENT_DATE.toString();
    private static final String DENIED_PROFILE_TYPES = "2,9";
    private static final String STATE_DEFAULT = "All";
    private static final LocalDate SEVEN_YEARS_AGO = DateUtil.getDateSevenYearsAgo();

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm14CaseSummaryReportRepository mockReportRepository;

    @MockBean
    private CsvWriterService csvService;

    @Autowired
    private Crm14CaseSummaryReportController controller;

    @Autowired
    HttpServletResponse response;

    /**
     * Report data collection tests
     */
    @ParameterizedTest
    @NullSource // test when profileTypes = null
    @ValueSource(strings = "6")
    void generateReportCrm14Test_WhenExistingDecisionDatesAndValidProfileAreGivenThenReturnDTO(String profileTypes) throws ConstraintViolationException {
        Crm14CaseSummaryReportModel report = new Crm14CaseSummaryReportModel(4004444L, CURRENT_DATE,
                "4004004", CURRENT_DATE, "Mr Reed Richards", "0D182J",
                "Silver Surfer", "HMCTS / CAT", "CAT 1", "19990", "Galactic Magistrates' Court",
                "Von Doom, Mr. Victor", "Completed", null, "Summary-Only",
                CURRENT_DATE, "NOT_NEEDED", "Passed", "Yes",
                "Magistrates' Court or CFS: Passed", "Magistrates' Court or CFS: Granted", CURRENT_DATE,
                "LESS THAN 4 SOLICITORS LTD", "New application", "N", "N", 1, "Y",
                2, CURRENT_DATE, CURRENT_DATE, CURRENT_DATE,
                null, null, null, null, null, null, null, null
        );

        when(mockReportRepository.getReport(
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE_DEFAULT,
                0, START_DATE, END_DATE)).thenReturn(List.of(report));

        // execute
        ResponseEntity<Void> result = controller.generateReportCrm14(
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                profileTypes, STATE_DEFAULT
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(result).isNull();

        verify(mockReportRepository).getReport(
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE_DEFAULT,
                0, START_DATE, END_DATE);
    }

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
    void generateReportCrm14Test_WhenDecisionDateFromIsOver7ysAgoThenThrowConstraintViolationException() {
        String decisionFrom = SEVEN_YEARS_AGO.minusMonths(2).toString();
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                        1, decisionFrom, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                .isInstanceOf(StartDateConstraintViolationException.class)
                .hasMessage("Start Date Constraint Violation Exception :: decision start date ["
                        + decisionFrom + "] cannot be earlier than [" + SEVEN_YEARS_AGO + "]");
    }

    @Test
    void generateReportCrm14Test_WhenInvalidDecisionDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = CURRENT_DATE.toString();
        String endDate = CURRENT_DATE.minusDays(1).toString(); // before decisionFrom

        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                        1, startDate, endDate,
                        0, startDate, endDate,
                        0, startDate, endDate,
                        0, startDate, endDate,
                        ACCEPTED_PROFILE_TYPES, STATE_DEFAULT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [" + startDate + "] must not be after end date [" + endDate + "]");
    }

    @Test
    void generateReportCrm14Test_WhenExistingDecisionDatesAndInvalidProfileAreGivenThenReturnUnauthorizedUserProfileException() {
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                        1, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        DENIED_PROFILE_TYPES, STATE_DEFAULT))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [6]");
    }

    @Test
    void generateReportCrm14Test_WhenNoReportDataThenThrowResourceNotFoundException() {
        when(mockReportRepository.getReport(
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE_DEFAULT,
                0, START_DATE, END_DATE)).thenReturn(List.of());


        // execute
        softly.assertThatThrownBy(() -> controller.generateReportCrm14(
                        0, START_DATE, END_DATE,
                        1, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        ACCEPTED_PROFILE_TYPES, STATE_DEFAULT
                )).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No data found for CRM14 Case Summary Report for inputs");

        verify(mockReportRepository).getReport(
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE_DEFAULT,
                0, START_DATE, END_DATE);
    }


    @Test
    void generateReportCrm14Test_GivenValidProfilesWhenUnexpectedCSVWriterErrorThenServerErrorHttpCode() throws Exception {

        when(mockReportRepository.getReport(
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE, STATE_DEFAULT,
                0, START_DATE, END_DATE)).thenReturn(List.of(new Crm14CaseSummaryReportModel()));

        doThrow(IOException.class).when(csvService).close(any());

        // execute
        ResponseEntity<Void> result = controller.generateReportCrm14(
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        softly.assertThat(result).isNull();

        verify(mockReportRepository).getReport(
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE, STATE_DEFAULT,
                0, START_DATE, END_DATE);
    }
}
