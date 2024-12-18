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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm14ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm14ProviderReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14ProviderReportControllerTest {
    private static final String STATE_DEFAULT = "All";
    private static final String VALID_START_DATE = "2024-06-01";
    private static final String VALID_END_DATE = "2024-06-30";
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);

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
                1234567L, LocalDate.of(2023, 3, 16), "",
                LocalDate.of(2023, 3, 16), "Mr John Doe", "1ABCD",
                "Someone", "Crown Court", "Indictable", "Passed",
                LocalDate.of(2023, 3, 16), "XXXX", "Some charge");

        when(mockReportRepository.getReport(1, VALID_START_DATE, VALID_END_DATE,
                0, VALID_START_DATE, VALID_END_DATE,
                0, VALID_START_DATE, VALID_END_DATE, STATE_DEFAULT,
                0, VALID_START_DATE, VALID_END_DATE)).thenReturn(List.of(report));

        // execute
        ResponseEntity<Void> result = controller.generateProviderReportCrm14(
                1, VALID_START_DATE, VALID_END_DATE,
                0, VALID_START_DATE, VALID_END_DATE,
                0, VALID_START_DATE, VALID_END_DATE,
                0, VALID_START_DATE, VALID_END_DATE,
                STATE_DEFAULT
        );

        softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(result).isNull();
    }

    /**
     * Date Format input checks
     **/
    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateProviderReportCrm14Test_WhenInvalidDateIsGivenThenReturnConstraintViolationException(String invalidDate) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        1, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        0, invalidDate, invalidDate,
                        STATE_DEFAULT))
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
    void generateProviderReportCrm14_WhenInvalidDecisionDateRangeIsGivenThenThrowConstraintViolationException() {
        String startDate = "2024-07-05";
        String endDate = "2024-06-30";

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        1, startDate, endDate,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        STATE_DEFAULT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: \"decision\" start date [2024-07-05] must not be after end date [2024-06-30]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidSubmittedDateRangeIsGivenThenThrowConstraintViolationException() {
        String startDate = "2024-07-05";
        String endDate = "2024-06-30";

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        0, VALID_START_DATE, VALID_END_DATE,
                        1, startDate, endDate,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        STATE_DEFAULT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: \"submitted\" start date [2024-07-05] must not be after end date [2024-06-30]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidCreationDateRangeIsGivenThenThrowConstraintViolationException() {
        String startDate = "2024-07-05";
        String endDate = "2024-06-30";

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        1, startDate, endDate,
                        0, VALID_START_DATE, VALID_END_DATE,
                        STATE_DEFAULT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: \"created\" start date [2024-07-05] must not be after end date [2024-06-30]");
    }

    @Test
    void generateProviderReportCrm14_WhenInvalidLastSubmittedDateRangeIsGivenThenThrowConstraintViolationException() {
        String startDate = "2024-07-05";
        String endDate = "2024-06-30";

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        1, startDate, endDate,
                        STATE_DEFAULT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: \"lastSubmitted\" start date [2024-07-05] must not be after end date [2024-06-30]");
    }

    @Test
    void generateReportCrm14_WhenNoFilterFlagIsSetThenThrowDateRangeConstraintViolationException() {
        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        STATE_DEFAULT))
                .isInstanceOf(NotEnoughSearchParametersException.class)
                .hasMessage("Not enough inputs to generate report. Please specify at least 1 date range and turn on the corresponding filter flag");
    }

    @Test
    void generateProviderReportCrm14_WhenExistingDecisionDatesGivenThenReturnDTO2() {
        when(mockReportRepository.getReport(0, VALID_START_DATE, VALID_END_DATE,
                0, VALID_START_DATE, VALID_END_DATE,
                1, VALID_START_DATE, VALID_END_DATE, STATE_DEFAULT,
                0, VALID_START_DATE, VALID_END_DATE)).thenThrow(new ResourceNotFoundException("error"));

        // execute
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm14(
                        0, VALID_START_DATE, VALID_END_DATE,
                        1, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        0, VALID_START_DATE, VALID_END_DATE,
                        STATE_DEFAULT))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No data found for CRM14 Provider Report for inputs");
    }
}