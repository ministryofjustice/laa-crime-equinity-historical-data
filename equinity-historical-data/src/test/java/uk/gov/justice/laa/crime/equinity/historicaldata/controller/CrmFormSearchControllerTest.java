package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
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
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFormSearchService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFormSearchControllerTest {

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    CrmFormSearchService searchService;

    @Autowired
    CrmFormSearchController controller;

    private static final String ACCEPTED_TYPES_DEFAULT = "1";

    private static final List<String> INVALID_DATE_FORMATS = List.of(
            "123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"
    );

    private SearchResultDTO searchResultDTO;

    @BeforeEach
    void preTest() {
        searchResultDTO = new SearchResultDTO();
        given(searchService.searchAllByCriteria(any())).willReturn(searchResultDTO);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    /**
     * USN input checks
     */
    @Test
    void doSearchByTest_WhenShorterUsnThenThrowConstraintViolationException() {
        String usnTest = "12";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usnTest, null, null, null, null, null,
                        null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("size must be between");
    }

    @Test
    void doSearchByTest_WhenLongerUsnThenThrowConstraintViolationException() {
        String usnTest = "12345678901";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usnTest, null, null, null, null, null,
                        null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("size must be between");
    }

    @Test
    void doSearchByTest_WhenNonDigitsGivenAsUsnThenThrowConstraintViolationException() {
        String usnTest = "notanumber";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usnTest, null, null, null, null, null,
                        null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must match");
    }

    @Test
    void doSearchByTest_WhenAlphaNumericUsnThenThrowConstraintViolationException() {
        String usnTest = "12alpha3";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usnTest, null, null, null, null, null,
                        null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must match");
    }

    @Test
    void doSearchByTest_WhenValidUsnThenReturnDTO() {
        String usnTest = "1234";

        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(
                ACCEPTED_TYPES_DEFAULT, usnTest, null, null, null, null, null,
                null, null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    /**
     * Type input checks
     */
    @ParameterizedTest
    @ValueSource(strings = {"1", "23", "467", "0", "999", "000", "001", "023", "0001", "0023", "0467", "00000313"})
    void doSearchByTest_WhenValidTypeStringValueIsGivenThenReturnDTO(String type) {
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(
                ACCEPTED_TYPES_DEFAULT, null, Integer.valueOf(type), null, null,
                null, null, null,
                null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 23, 467, 0, 999})
    void doSearchByTest_WhenValidTypeIntegerValueIsGivenThenReturnDTOn(int type) {
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(
                ACCEPTED_TYPES_DEFAULT, null, type, null, null,
                null, null, null,
                null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @ParameterizedTest
    @ValueSource(ints = {1234, 1000, 1001, 9990, -1, -9991})
    void doSearchByTest_WhenTypeIsGivenOutOfRangeValueThenThrowConstraintViolationException(int type) {
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, null, type, null, null, null, null,
                        null, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must be")
                .hasMessageContaining("than or equal to");
    }

    /**
     * Date Format input checks
     **/
    @Test
    void doSearchByTest_WhenInvalidSubmittedDateFromIsGivenThenThrowConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        INVALID_DATE_FORMATS.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> controller.doSearchBy(
                                ACCEPTED_TYPES_DEFAULT, null, null, null, null, dateToTest, null,
                                null, null, null, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchByTest_WhenInvalidSubmittedDateToIsGivenThenThrowConstraintViolationException() {
        String expectedMessage = "must match";

        // execute
        INVALID_DATE_FORMATS.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> controller.doSearchBy(
                                ACCEPTED_TYPES_DEFAULT, null, null, null, null, null, dateToTest,
                                null, null, null, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void doSearchByTest_WhenInvalidClientDateOfBirthIsGivenThenThrowConstraintViolationException() {
        INVALID_DATE_FORMATS.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> controller.doSearchBy(
                                ACCEPTED_TYPES_DEFAULT, null, null, null, dateToTest, null, null,
                                null, null, null, null, null))
                        .isInstanceOf(ConstraintViolationException.class)
                        .hasMessageContaining("must match")
        );
    }

    @Test
    void doSearchByTest_WhenInvalidSubmittedDateRangeIsGivenThenThrowConstraintViolationException() {
        String startDate = "2024-02-19";
        String endDate = "2024-02-09";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, null, null, null, null, startDate, endDate,
                        null, null, null, null, null))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessageContaining("must not be after end date");
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateFromIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(ACCEPTED_TYPES_DEFAULT, null, null, null, null, dateToTest, null,
                null, null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateToIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(ACCEPTED_TYPES_DEFAULT, null, null, null, null, null, dateToTest,
                null, null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateRangeIsGivenThenReturnDTO() {
        String dateToTestFrom = "2024-02-09";
        String dateToTestTo = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(ACCEPTED_TYPES_DEFAULT, null, null, null, null, dateToTestFrom, dateToTestTo,
                null, null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @Test
    void doSearchByTest_WhenOnlyValidSubmittedDateRangeWithSameDatesIsGivenThenReturnDTO() {
        String dateToTestFrom = "2024-02-19";
        String dateToTestTo = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(ACCEPTED_TYPES_DEFAULT, null, null, null, null, dateToTestFrom, dateToTestTo,
                null, null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @Test
    void doSearchByTest_WhenValidClientDateOfBirthIsGivenThenReturnDTO() {
        String dateToTest = "2024-02-19";

        // execute
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(ACCEPTED_TYPES_DEFAULT, null, null, null, dateToTest, null, null,
                null, null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    /**
     * Provider Account input checks
     */
    @Test
    void doSearchArchiveByTest_WhenShorterProviderAccountIsGivenThenThrowConstraintViolationException() {
        String providerAccount = "0A0";

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, null, null, null, null, null, null,
                        providerAccount, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("size must be between");
    }

    @Test
    void doSearchByTest_WhenLongerProviderAccountIsGivenThenThrowConstraintViolationException() {
        String providerAccount = "0A0A0A1";

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, null, null, null, null, null, null,
                        providerAccount, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("size must be between");
    }

    @ParameterizedTest
    @ValueSource(strings = {"012*3A", " 0123A", "Z0123 ", "      ", "123 AB", "ID>=12", "0A0A0A1", "0A0A0A12"})
    void doSearchByTest_WhenInvalidFormatProviderAccountIsGivenThenThrowConstraintViolationException(String providerAccount) {
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, null, null, null, null, null, null,
                        providerAccount, null, null, null, null))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must match");

    }

    @ParameterizedTest
    @ValueSource(strings = {"0A0z0A", "0A0z0", "A0z0"})
    void doSearchByTest_WhenValidProviderAccountIsGivenThenReturnDTO(String providerAccount) {
        ResponseEntity<SearchResultDTO> response = controller.doSearchBy(
                ACCEPTED_TYPES_DEFAULT, null, null, null, null,
                null, null, providerAccount,
                null, null, null, null);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    /**
     * Sort input checks
     */
    @ParameterizedTest
    @ValueSource(strings = {"originatedDate", "submittedDate"})
    void doSearchArchiveByTest_WhenValidSortIsGivenThenReturnDTO(String sort) {
        String usn = "1234";
        String order = "asc";

        ResponseEntity<SearchResultDTO> response =  controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usn, null, null, null, null, null,
                        null, null, null, sort, order);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @Test
    void doSearchArchiveByTest_WhenInvalidSortIsGivenThenThrowConstraintViolationException() {
        String usn = "1234";
        String sort = "someField";
        String order = "desc";

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usn, null, null, null, null, null,
                        null, null, null, sort, order))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("sort: must match \"(originatedDate|submittedDate)\"");
    }

    /**
     * Order input checks
     */
    @ParameterizedTest
    @ValueSource(strings = {"asc", "desc"})
    void doSearchArchiveByTest_WhenValidOrderIsGivenThenReturnDTO(String order) {
        String usn = "1234";
        String sort = "originatedDate";

        ResponseEntity<SearchResultDTO> response =  controller.doSearchBy(
                ACCEPTED_TYPES_DEFAULT, usn, null, null, null, null, null,
                null, null, null, sort, order);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isEqualTo(searchResultDTO);
    }

    @Test
    void doSearchArchiveByTest_WhenInvalidOrderIsGivenThenThrowConstraintViolationException() {
        String usn = "1234";
        String sort = "originatedDate";
        String order = "someOrder";

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, usn, null, null, null, null, null,
                        null, null, null, sort, order))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("order: size must be between 3 and 4")
                .hasMessageContaining("order: must match \"(asc|desc)\"");
    }

    @Test
    void doSearchByTest_WhenNoSearchParametersThenThrowNotEnoughSearchParametersException() {

        softly.assertThatThrownBy(() -> controller.doSearchBy(
                        ACCEPTED_TYPES_DEFAULT, null, null, null, null, null, null,
                        null, null, null, null, null))
                .isInstanceOf(NotEnoughSearchParametersException.class)
                .hasMessageContaining("Not enough search parameters. Search criteria needs at least 1 input");
    }
}