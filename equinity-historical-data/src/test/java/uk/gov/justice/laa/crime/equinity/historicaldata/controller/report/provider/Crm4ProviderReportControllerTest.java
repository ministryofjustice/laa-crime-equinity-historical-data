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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm4ProviderReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider.Crm4ProviderReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4ProviderReportControllerTest {
    private static final String PROVIDER_ACCOUNT = "123ABC";
    private static final String VALID_DATE = "2050-01-01";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    Crm4ProviderReportRepository mockReportRepository;

    @Autowired
    Crm4ProviderReportController controller;

    @Test
    void generateProviderReportCrm4_WhenExistingDecisionDatesAndValidProviderAccountAreGivenThenReturnDTO() {
        String decisionFrom = "2010-02-01";
        String decisionTo = "2024-06-01";

        Crm4ProviderReportModel report = Crm4ProviderReportModel.builder()
                .clientUfn("123456/123").usn(1234567L).providerAccount("1ABCD").firmName("XXXX").clientName("John Doe")
                .repOrderNumber("1234567").maatId("").prisonLaw("No").receivedDate(LocalDate.of(2023, 3, 16))
                .decisionDate(LocalDate.of(2023, 3, 16)).decisionResult("Grant").expenditureType("Costs")
                .expertName("Some expert").quantity(4.0).rate(50.0).unit("Hour(s)").totalCost(200.0).additionalExpenditure(0.0)
                .totalAuthority(200.0).totalGranted(200.0).build();

        when(mockReportRepository.getReport(decisionFrom, decisionTo, PROVIDER_ACCOUNT)).thenReturn(List.of(report));

        // execute
        ResponseEntity<String> response = controller.generateProviderReportCrm4(decisionFrom, decisionTo, PROVIDER_ACCOUNT);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String expectedReport = """
                Client UFN,Usn,Provider Account,Firm Name,Client Name,\
                Rep Order Number,Maat ID,Prison Law,Date Received,Decision Date,Decision,Expenditure Type,Expert Name,\
                Quantity,Rate,Unit,Total Cost,Additional Expenditure,Total Authority,Total Granted
                123456/123,1234567,1ABCD,XXXX,John Doe,1234567,,No,2023-03-16,2023-03-16,Grant,Costs,Some expert,\
                4.0,50.0,Hour(s),200.0,0.0,200.0,200.0
                """;
        softly.assertThat(response.getBody()).isEqualTo(expectedReport);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateProviderReportCrm4_WhenInvalidDecisionDateFromIsGivenThenThrowConstraintViolationException(String invalidDecisionFrom) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(invalidDecisionFrom, VALID_DATE, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionFrom: must match \"^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$\"");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"})
    void generateProviderReportCrm4_WhenInvalidDecisionDateToIsGivenThenThrowConstraintViolationException(String invalidDate) {
        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(VALID_DATE, invalidDate, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionTo: must match \"^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$\"");
    }

    @Test
    void generateProviderReportCrm4_WhenInvalidDecisionDateRangeIsGivenThenThrowConstraintViolationException() {
        String decisionFrom = "2024-02-19";
        String decisionTo = "2024-02-09";

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(decisionFrom, decisionTo, PROVIDER_ACCOUNT))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: [Decision] start date [2024-02-19] must not be after end date [2024-02-09]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "123*", "1234ABCD"})
    void generateProviderReportCrm4_WhenInvalidProviderAccountIsGivenThenThrowConstraintViolationException(String invalidProviderAccount) {
        String decisionFrom = "2024-02-19";
        String decisionTo = "2024-02-09";

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(decisionFrom, decisionTo, invalidProviderAccount))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("generateProviderReportCrm4.providerAccount: must match \"([0-9A-Za-z]){4,6}\"");
    }

    @Test
    void generateProviderReportCrm4_WhenDecisionFromIsMissingThenThrowConstraintViolationException() {
        String decisionTo = "2024-02-09";

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(null, decisionTo, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionFrom: must not be null");
    }

    @Test
    void generateProviderReportCrm4_WhenDecisionToIsMissingThenThrowConstraintViolationException() {
        String decisionFrom = "2024-02-19";

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(decisionFrom, null, PROVIDER_ACCOUNT))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.decisionTo: must not be null");
    }

    @Test
    void generateProviderReportCrm4_WhenProviderAccountIsMissingThenThrowConstraintViolationException() {
        String decisionFrom = "2024-02-19";
        String decisionTo = "2024-02-09";

        softly.assertThatThrownBy(() -> controller.generateProviderReportCrm4(decisionFrom, decisionTo, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("generateProviderReportCrm4.providerAccount: must not be null");
    }
}
