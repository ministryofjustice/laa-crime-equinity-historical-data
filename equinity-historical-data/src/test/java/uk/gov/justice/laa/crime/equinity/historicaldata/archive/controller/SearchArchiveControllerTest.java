package uk.gov.justice.laa.crime.equinity.historicaldata.archive.controller;

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
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.service.SearchArchiveService;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchArchiveControllerTest {
    private List<String> testInvalidFormatDates;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    SearchArchiveService searchService;

    @Autowired
    SearchArchiveController controller;

    @BeforeAll
    void preTest() {
        testInvalidFormatDates = List.of(
                "123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"
        );

        given(searchService.searchAllByCriteria(any())).willReturn(new SearchResultDTO());
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }


    /**
     * USN input checks
     */
    @Test
    void doSearchArchiveByTest_WhenShorterUsnThenReturnConstraintViolationException() {
        String usnTest = "12";
        String expectedMessage = "size must be between";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                        usnTest, null,null, null, null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchArchiveByTest_WhenLongerUsnThenReturnConstraintViolationException() {
        String usnTest = "12345678901";
        String expectedMessage = "size must be between";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                        usnTest, null,null, null, null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchArchiveByTest_WhenNonDigitsGivenAsUsnThenReturnConstraintViolationException() {
        String usnTest = "notanumber";
        String expectedMessage = "must match";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                        usnTest, null,null, null, null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchArchiveByTest_WhenAlphaNumericUsnThenReturnConstraintViolationException() {
        String usnTest = "12alpha3";
        String expectedMessage = "must match";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                        usnTest, null,null, null, null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchArchiveByTest_WhenValidUsnThenReturnDTO() {
        String usnTest = "1234";

        ResponseEntity<SearchResultDTO> response = controller.doSearchArchiveBy(usnTest, null,null, null, null, null, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Date Format input checks
     **/
    @Test
    void doSearchArchiveByTest_WhenInvalidSubmittedDateFromIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                                null, null,null, null, dateToTest, null, null, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchArchiveByTest_WhenInvalidSubmittedDateToIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                                null, null,null, null, null, dateToTest, null, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchArchiveByTest_WhenInvalidClientDateOfBirthIsGivenThenReturnConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                                null, null,null, dateToTest, null, null, null, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchArchiveByTest_WhenInvalidSubmittedDateRangeIsGivenThenReturnConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";
        String expectedMessage = "must not be after end date";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                        null, null,null, null, startDate, endDate, null, null, null))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchArchiveByTest_WhenOnlyValidSubmittedDateFromIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchArchiveBy(null, null,null, null, dateToTest, null, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void doSearchArchiveByTest_WhenOnlyValidSubmittedDateToIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchArchiveBy(null, null,null, null, null, dateToTest, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void doSearchArchiveByTest_WhenOnlyValidSubmittedDateRangeIsGivenThenReturnDTO() {
        String dateToTestFrom = "2024-02-09";
        String dateToTestTo = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchArchiveBy(null, null,null, null, dateToTestFrom, dateToTestTo, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void doSearchArchiveByTest_WhenOnlyValidSubmittedDateRangeWithSameDatesIsGivenThenReturnDTO() {
        String dateToTestFrom = "2024-02-19";
        String dateToTestTo = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchArchiveBy(null, null,null, null, dateToTestFrom, dateToTestTo, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Provider Account input checks
     */
    @Test
    void doSearchArchiveByTest_WhenLongerProviderAccountIsGivenThenReturnConstraintViolationException() {
        String providerAccount = "0A0A0A1";
        String expectedMessage = "size must be between";

        softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                        null, null,null, null, null, null, providerAccount, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchArchiveByTest_WhenInvalidFormatProviderAccountIsGivenThenReturnConstraintViolationException() {
        List<String> providerAccounts = List.of(
                "012*3A", " 0123A", "Z0123 ", "      ", "123 AB", "ID>=12",
                "0A0A0A1", "0A0A0A12"
        );
        String expectedMessage = "must match";

        // execute
        providerAccounts.forEach(providerAccount ->
                softly.assertThatThrownBy(() -> controller.doSearchArchiveBy(
                                null, null,null, null, null, null, providerAccount, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchArchiveByTest_WhenValidProviderAccountIsGivenThenReturnDTO() {
        String providerAccount = "0A0z0A";
        ResponseEntity<SearchResultDTO> response = controller.doSearchArchiveBy(null,null, null, null, null, null, providerAccount, null, null);
        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        providerAccount = "0A0z0";
        response = controller.doSearchArchiveBy(null,null, null, null, null, null, providerAccount, null, null);
        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        providerAccount = "A0z0";
        response = controller.doSearchArchiveBy(null,null, null, null, null, null, providerAccount, null, null);
        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}