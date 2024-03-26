package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.SearchService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchArchiveControllerInputsTest {
    private List<String> testInvalidFormatDates;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    SearchService searchService;

    @Autowired
    SearchController controller;

    @BeforeAll
    void preTest() {
        testInvalidFormatDates = List.of(
            "123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"
        );

        given(searchService.searchAllByCriteria(any())).willReturn(new SearchDTO());
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    /**
     * USN input checks
     */
    @Test
    void doSearchByTest_WhenShorterUsnThenReturnConstraintViolationException() {
        String usnTest = "12";
        String expectedMessage = "size must be between";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenLongerUsnThenReturnConstraintViolationException() {
        String usnTest = "12345678901";
        String expectedMessage = "size must be between";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenNonDigitsGivenAsUsnThenReturnConstraintViolationException() {
        String usnTest = "notanumber";
        String expectedMessage = "must match";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenAlphaNumericUsnThenReturnConstraintViolationException() {
        String usnTest = "12alpha3";
        String expectedMessage = "must match";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenValidUsnThenReturnDTO() {
        String usnTest = "1234";

        ResponseEntity<SearchDTO> response = controller.doSearchBy(usnTest, null, null, null, null, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Date Format input checks
     **/
    @Test
    void doSearchByTest_WhenInvalidSubmittedDateFromIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
            softly.assertThatThrownBy(() -> controller.doSearchBy(
                   null, null, null, dateToTest, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchByTest_WhenInvalidSubmittedDateToIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
            softly.assertThatThrownBy(() -> controller.doSearchBy(
                        null, null, null, null, dateToTest, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchByTest_WhenInvalidClientDateOfBirthIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
            softly.assertThatThrownBy(() -> controller.doSearchBy(
                        null, null, dateToTest, null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchByTest_WhenInvalidSubmittedDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";
        String expectedMessage = "must not be after end date";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                null, null, null, startDate, endDate, null, null, null))
            .isInstanceOf(DateRangeConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateFromIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchDTO> response = controller.doSearchBy(null, null, null, dateToTest, null, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateToIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchDTO> response = controller.doSearchBy(null, null, null, null, dateToTest, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateRangeIsGivenThenReturnDTO() {
        String dateToTestFrom = "2024-02-09";
        String dateToTestTo = "2024-02-19";

        // execute
        ResponseEntity<SearchDTO> response = controller.doSearchBy(null, null, null, dateToTestFrom, dateToTestTo, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateRangeWithSameDatesIsGivenThenReturnDTO() {
        String dateToTestFrom = "2024-02-19";
        String dateToTestTo = "2024-02-19";

        // execute
        ResponseEntity<SearchDTO> response = controller.doSearchBy(null, null, null, dateToTestFrom, dateToTestTo, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Provider Account input checks
     */
    @Test
    void doSearchByTest_WhenShorterProviderAccountIsGivenThenReturnConstraintViolationException() {
        String providerAccount = "0A0A0";
        String expectedMessage = "size must be between";

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        null, null, null, null, null, providerAccount, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenLongerProviderAccountIsGivenThenReturnConstraintViolationException() {
        String providerAccount = "0A0A0A1";
        String expectedMessage = "size must be between";

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        null, null, null, null, null, providerAccount, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenInvalidFormatProviderAccountIsGivenThenReturnConstraintViolationException() {
        List<String> providerAccounts = List.of(
            "012*3A", " 0123A", "Z0123 ", "      ", "123 AB", "ID>=12",
            "0A0A0A1", "0A0A0A12"
        );
        String expectedMessage = "must match";

        // execute
        providerAccounts.forEach(providerAccount ->
            softly.assertThatThrownBy(() -> controller.doSearchBy(
                    null, null, null, null, null, providerAccount, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchByTest_WhenValidProviderAccountIsGivenThenReturnDTO() {
        String providerAccount = "0A0z0A";

        // execute
        ResponseEntity<SearchDTO> response = controller.doSearchBy(null, null, null, null, null, providerAccount, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}