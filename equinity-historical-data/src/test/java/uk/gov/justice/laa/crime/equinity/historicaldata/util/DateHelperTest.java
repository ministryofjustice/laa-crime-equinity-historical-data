package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateHelperTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void convertStringToLocalDate_WhenOutOfBoundDateValidFormatThenReturnsDateLastDayOfMonth() {
        List<String> testDatesFeb = List.of(
            "2024-02-31", "2024-02-30"
        );
        String expectedFeb = "2024-02-29";

        testDatesFeb.forEach(dateToTest -> {
                LocalDate convertedDate = DateHelper.convertStringToLocalDate(dateToTest);
                assert convertedDate != null;
                softly.assertThat(convertedDate.toString()).isEqualTo(expectedFeb);
            }
        );

        String dateToTestSep = "2024-09-31";
        String expectedSep = "2024-09-30";
        LocalDate convertedDate = DateHelper.convertStringToLocalDate(dateToTestSep);
        assert convertedDate != null;
        softly.assertThat(convertedDate.toString()).isEqualTo(expectedSep);
    }
}