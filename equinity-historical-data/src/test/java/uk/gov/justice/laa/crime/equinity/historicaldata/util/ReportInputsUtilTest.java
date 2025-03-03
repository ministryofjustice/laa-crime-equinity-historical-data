package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;

@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportInputsUtilTest {

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Test
    void checkInputs_AcceptsValidInputs() {
        ReportInputsUtil.checkInputs("2024-01-01",  "2024-01-31", null,  1, false);
    }

    @Test
    void checkInputs_WhenInvalidDateRangeIsGivenThenThrowsException() {
        softly.assertThatThrownBy(() -> ReportInputsUtil.checkInputs("2024-01-31",  "2024-01-01", null,  1, false))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [2024-01-31] must not be after end date [2024-01-01]");
    }

    @Test
    void checkInputs_WhenInvalidProfileIsGivenThenThrowsException() {
        softly.assertThatThrownBy(() -> ReportInputsUtil.checkInputs("2024-01-31",  "2024-01-01", "1,4",  5, false))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [2024-01-31] must not be after end date [2024-01-01]");
    }
}