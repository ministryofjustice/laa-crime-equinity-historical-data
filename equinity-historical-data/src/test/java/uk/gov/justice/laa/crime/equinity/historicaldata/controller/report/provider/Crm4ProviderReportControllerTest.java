package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report.provider;

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
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.StartDateConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm4ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm4ProviderReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4ProviderReportControllerTest {
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String DECISION_FROM = CURRENT_DATE.minusDays(1).toString();
    private static final String DECISION_TO = CURRENT_DATE.toString();
    private static final String PROVIDER_ACCOUNT = "123ABC";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm4ProviderReportRepository mockReportRepository;

    @Autowired
    Crm4ProviderReportController controller;

    @Test
    void generateProviderReportCrm4_WhenExistingDecisionDatesAndValidProviderAccountAreGivenThenReturnDTO() {
        Crm4ProviderReportModel report = Crm4ProviderReportModel.builder()
                .clientUfn("123456/123").usn(1234567L).providerAccount("1ABCD").firmName("XXXX").clientName("John Doe")
                .repOrderNumber("1234567").maatId("").prisonLaw("No").receivedDate(CURRENT_DATE)
                .decisionDate(CURRENT_DATE).decisionResult("Grant").expenditureType("Costs")
                .expertName("Some expert").quantity(4.0).rate(50.0).unit("Hour(s)").totalCost(200.0).additionalExpenditure(0.0)
                .totalAuthority(200.0).totalGranted(200.0).build();

        when(mockReportRepository.getReport(DECISION_FROM, DECISION_TO, PROVIDER_ACCOUNT)).thenReturn(List.of(report));

        // execute
        ResponseEntity<String> response = controller.generateProviderReportCrm4(DECISION_FROM, DECISION_TO, PROVIDER_ACCOUNT);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String expectedReport = "Client UFN,Usn,Provider Account,Firm Name,Client Name,"+
                "Rep Order Number,Maat ID,Prison Law,Date Received,Decision Date,Decision,Expenditure Type,Expert Name," +
                "Quantity,Rate,Unit,Total Cost,Additional Expenditure,Total Authority,Total Granted"+
                "\n123456/123,1234567,1ABCD,XXXX,John Doe,1234567,,No,"+ CURRENT_DATE + "," + CURRENT_DATE + ",Grant,Costs,Some expert," +
                "4.0,50.0,Hour(s),200.0,0.0,200.0,200.0\n";
        softly.assertThat(response.getBody()).isEqualTo(expectedReport);

        verify(mockReportRepository).getReport(DECISION_FROM, DECISION_TO, PROVIDER_ACCOUNT);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateProviderReportCrm4_WhenInvalidDecisionDateFromIsGivenThenThrowConstraintViolationException(String invalidDecisionFrom) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(invalidDecisionFrom, DECISION_TO, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionFrom: must match \"^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$\"");
    }

    @Test
    void generateProviderReportCrm4_WhenDecisionDateFromIsOver7YrsAgoThenThrowConstraintViolationException() {
        String decisionFrom7yrsAgo = CURRENT_DATE.minusYears(7).minusMonths(2).toString();

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(decisionFrom7yrsAgo, DECISION_TO, PROVIDER_ACCOUNT))
                .isInstanceOf(StartDateConstraintViolationException.class)
                .hasMessage("Start Date Constraint Violation Exception :: decision start date ["+ decisionFrom7yrsAgo + "] cannot be earlier than 7 years ago");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateProviderReportCrm4_WhenInvalidDecisionDateToIsGivenThenThrowConstraintViolationException(String invalidDate) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(DECISION_FROM, invalidDate, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionTo: must match \"^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$\"");
    }

    @Test
    void generateProviderReportCrm4_WhenInvalidDecisionDateRangeIsGivenThenThrowConstraintViolationException() {
        String decisionFrom = LocalDate.now().toString();
        String decisionTo = LocalDate.now().minusDays(1).toString(); // before decisionFrom

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(decisionFrom, decisionTo, PROVIDER_ACCOUNT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [" + decisionFrom +"] must not be after end date ["+ decisionTo+"]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "123*", "1234ABCD"})
    void generateProviderReportCrm4_WhenInvalidProviderAccountIsGivenThenThrowConstraintViolationException(String invalidProviderAccount) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(DECISION_FROM, DECISION_TO, invalidProviderAccount))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateProviderReportCrm4.providerAccount: must match \"([0-9A-Za-z]){4,6}\"");
    }

    @Test
    void generateProviderReportCrm4_WhenDecisionFromIsMissingThenThrowConstraintViolationException() {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(null, DECISION_TO, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionFrom: must not be null");
    }

    @Test
    void generateProviderReportCrm4_WhenDecisionToIsMissingThenThrowConstraintViolationException() {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(DECISION_FROM, null, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionTo: must not be null");
    }

    @Test
    void generateProviderReportCrm4_WhenProviderAccountIsMissingThenThrowConstraintViolationException() {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(DECISION_FROM, DECISION_TO, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.providerAccount: must not be null");
    }
}
