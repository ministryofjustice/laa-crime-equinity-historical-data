package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report.provider;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.NotEnoughSearchParametersException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.StartDateConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm14ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm14ProviderReportRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14ProviderReportControllerTest {
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String START_DATE = CURRENT_DATE.minusDays(1).toString();
    private static final String END_DATE = CURRENT_DATE.toString();
    private static final String PROVIDER_ACCOUNT = "123ABC";
    private static final String STATE = "All";
    private static final LocalDate SEVEN_YEARS_AGO = DateUtil.getDateSevenYearsAgo();

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm14ProviderReportRepository mockReportRepository;

    @Autowired
    HttpServletResponse response;

    @Autowired
    Crm14ProviderReportController controller;

    @Test
    void generateProviderReportCrm14Test_WhenExistingDecisionDatesGivenThenReturnDTO() {
        Crm14ProviderReportModel report = new Crm14ProviderReportModel(
                1234567L, CURRENT_DATE, "", CURRENT_DATE, "Mr John Doe", "1ABCD",
                "Someone", "Crown Court", "Indictable", "Passed",
                CURRENT_DATE, "XXXX", "Some charge");

        when(mockReportRepository.getReport(PROVIDER_ACCOUNT,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE)).thenReturn(List.of(report));

        // execute
        ResponseEntity<Void> result = controller.generateProviderReportCrm14(
                PROVIDER_ACCOUNT,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(result).isNull();

        verify(mockReportRepository).getReport(PROVIDER_ACCOUNT,
                1, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE);
    }

    @Test
    void generateProviderReportCrm4_WhenProviderAccountIsMissingThenThrowConstraintViolationException() {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        null,
                        1, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm14.providerAccount: must not be null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateProviderReportCrm14Test_WhenInvalidDateIsGivenThenReturnConstraintViolationException(String invalidDate) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        1, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        STATE))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateProviderReportCrm14.decisionFrom: must match")
                .hasMessageContaining("generateProviderReportCrm14.decisionTo: must match")
                .hasMessageContaining("generateProviderReportCrm14.submittedFrom: must match")
                .hasMessageContaining("generateProviderReportCrm14.submittedTo: must match")
                .hasMessageContaining("generateProviderReportCrm14.createdFrom: must match")
                .hasMessageContaining("generateProviderReportCrm14.createdTo: must match")
                .hasMessageContaining("generateProviderReportCrm14.lastSubmittedFrom: must match")
                .hasMessageContaining("generateProviderReportCrm14.lastSubmittedTo: must match");
    }

    @Test
    void generateReportCrm14Test_WhenDecisionDateFromIsOver7YrsAgoThenThrowConstraintViolationException() {
        String decisionFrom = SEVEN_YEARS_AGO.minusMonths(2).toString();
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        1, decisionFrom, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(StartDateConstraintViolationException.class)
                .hasMessage("Start Date Constraint Violation Exception :: decision start date [" + decisionFrom + "] cannot be earlier than [" + SEVEN_YEARS_AGO + "]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidDecisionDateRangeIsGivenThenThrowConstraintViolationException() {
        String decisionFrom = CURRENT_DATE.toString();
        String decisionTo = CURRENT_DATE.minusDays(1).toString(); // before decisionFrom

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        1, decisionFrom, decisionTo,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [" + decisionFrom + "] must not be after end date [" + decisionTo + "]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidSubmittedDateRangeIsGivenThenThrowConstraintViolationException() {
        String submittedFrom = CURRENT_DATE.toString();
        String submittedTo = CURRENT_DATE.minusDays(1).toString(); // before submittedFrom

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        0, START_DATE, END_DATE,
                        1, submittedFrom, submittedTo,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: submitted start date ["+ submittedFrom + "] must not be after end date [" + submittedTo + "]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidCreationDateRangeIsGivenThenThrowConstraintViolationException() {
        String createdFrom = CURRENT_DATE.toString();
        String createdTo = CURRENT_DATE.minusDays(1).toString(); // before createdFrom

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        1, createdFrom, createdTo,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: created start date [" + createdFrom + "] must not be after end date [" + createdTo + "]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidLastSubmittedDateRangeIsGivenThenThrowConstraintViolationException() {
        String lastSubmittedFrom = CURRENT_DATE.toString();
        String lastSubmittedTo = CURRENT_DATE.minusDays(1).toString(); // before lastSubmittedFrom

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        1, lastSubmittedFrom, lastSubmittedTo,
                        STATE))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: lastSubmitted start date [" + lastSubmittedFrom + "] must not be after end date [" + lastSubmittedTo + "]");
    }

    @Test
    void generateProviderReportCrm14_WhenNoFilterFlagIsSetThenThrowDateRangeConstraintViolationException() {
        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(NotEnoughSearchParametersException.class)
                .hasMessage("Not enough inputs to generate report. Please specify at least 1 date range and turn on the corresponding filter flag");
    }

    @Test
    void generateProviderReportCrm14_WhenNoReportDataThenThrowResourceNotFoundException() {
        when(mockReportRepository.getReport(PROVIDER_ACCOUNT,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE)).thenReturn(List.of());

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        PROVIDER_ACCOUNT,
                        0, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        1, START_DATE, END_DATE,
                        0, START_DATE, END_DATE,
                        STATE))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No data found for CRM14 Provider Report for inputs");

        verify(mockReportRepository).getReport(PROVIDER_ACCOUNT,
                0, START_DATE, END_DATE,
                0, START_DATE, END_DATE,
                1, START_DATE, END_DATE, STATE,
                0, START_DATE, END_DATE);
    }
}
