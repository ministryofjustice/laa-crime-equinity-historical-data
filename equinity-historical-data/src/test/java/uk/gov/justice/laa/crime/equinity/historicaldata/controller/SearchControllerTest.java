package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.model.CrmFormsDTO;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchControllerTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    SearchController controller;

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
                usnTest, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenLongerUsnThenReturnConstraintViolationException() {
        String usnTest = "12345678901";
        String expectedMessage = "size must be between";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenNonDigitsGivenAsUsnThenReturnConstraintViolationException() {
        String usnTest = "notanumber";
        String expectedMessage = "must match";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenAlphaNumericUsnThenReturnConstraintViolationException() {
        String usnTest = "12alpha3";
        String expectedMessage = "must match";

        // execute
        softly.assertThatThrownBy(() -> controller.doSearchBy(
                usnTest, null, null, null, null))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void doSearchByTest_WhenValidUsnThenReturnDTO() {
        String usnTest = "1234";

        ResponseEntity<CrmFormsDTO> response = controller.doSearchBy(usnTest, null, null, null, null);

        softly.assertThat(response).isInstanceOf(ResponseEntity.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody()).isInstanceOf(CrmFormsDTO.class);
    }
}