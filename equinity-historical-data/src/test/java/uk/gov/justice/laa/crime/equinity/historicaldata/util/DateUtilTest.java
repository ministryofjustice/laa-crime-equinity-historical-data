package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateUtilTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    private List<String> testInvalidFormatDates;

    @BeforeAll
    void preTest() {
        testInvalidFormatDates = List.of(
                "123", "12-12-23", "12-12-2023", "10/11/2024", "2024/03/12", "2024-13-01", "2024-12-32", "2024-12-1", "2024-1-12"
        );
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void doSearchByTest_WhenShorterUsnThenReturnConstraintViolationException() {
        String expectedMessage = "could not be parsed";

        // execute
        testInvalidFormatDates.forEach(dateToTest ->
                softly.assertThatThrownBy(() -> DateUtil.convertStringToLocalDate(dateToTest))
                        .isInstanceOf(DateTimeParseException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Test
    void convertStringToLocalDate_WhenOutOfBoundDateValidFormatThenReturnsDateLastDayOfMonth() {
        List<String> testDatesFeb = List.of(
            "2024-02-31", "2024-02-30"
        );
        String expectedFeb = "2024-02-29";

        testDatesFeb.forEach(dateToTest -> {
                LocalDate convertedDate = DateUtil.convertStringToLocalDate(dateToTest);
                assert convertedDate != null;
                softly.assertThat(convertedDate.toString()).isEqualTo(expectedFeb);
            }
        );

        String dateToTestSep = "2024-09-31";
        String expectedSep = "2024-09-30";
        LocalDate convertedDate = DateUtil.convertStringToLocalDate(dateToTestSep);
        assert convertedDate != null;
        softly.assertThat(convertedDate.toString()).isEqualTo(expectedSep);
    }
}