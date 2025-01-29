package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.StartDateConstraintViolationException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.DateRange.SUBMITTED;
import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.getMinStartDate;

@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateUtilTest {

    private static final LocalDate MIN_START_DATE = getMinStartDate();

    @InjectSoftAssertions
    private SoftAssertions softly;

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2024-02-29T00:00:00", "2024-02-29T09:10:21"})
    void doSearchByTest_WhenInvalidDateFormatIsGivenThenReturnConstraintViolationException(String invalidDate) {
        String expectedMessage = "could not be parsed";

        // execute
        softly.assertThatThrownBy(() -> DateUtil.convertStringToLocalDate(invalidDate))
                .isInstanceOf(DateTimeParseException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-02-31", "2023-02-30", "2023-02-29", "2023-02-28"})
    void convertStringToLocalDate_WhenOutOfBoundDateValidFormatThenReturnsDateLastDayOfMonth(String outOfBoundDate) {
        String expectedFeb = "2023-02-28";

        LocalDate convertedDate = DateUtil.convertStringToLocalDate(outOfBoundDate);

        softly.assertThat(convertedDate).isNotNull();
        softly.assertThat(convertedDate).isEqualTo(expectedFeb);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2024-02-31", "2024-02-30", "2024-02-29"})
    void convertStringToLocalDate_WhenOutOfBoundDateValidFormatThenReturnsDateLastDayOfMonthForLeapYr(String outOfBoundDate) {
        String expectedLeap = "2024-02-29";

        LocalDate convertedDate = DateUtil.convertStringToLocalDate(outOfBoundDate);
        softly.assertThat(convertedDate).isNotNull();
        softly.assertThat(convertedDate).isEqualTo(expectedLeap);
    }

    @Test
    void convertStringToLocalDate_WhenOutOfBoundDateValidFormatThenReturnsDateLastDayOfMonthForSep() {
        String dateToTestSep = "2024-09-31";
        String expectedSep = "2024-09-30";

        LocalDate convertedDate = DateUtil.convertStringToLocalDate(dateToTestSep);

        softly.assertThat(convertedDate).isNotNull();
        softly.assertThat(convertedDate).isEqualTo(expectedSep);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2024-02-29T00:00:00", "2024-02-29T09:10:21"})
    void convertStringToDate_WhenValidFormatThenReturnsSimpleDate(String validDate) throws Exception {
        String expectedLeap = "2024-02-29";
        String expectedLeapLong = "2024-02-29T00:00:00";

        Date convertedDate = DateUtil.convertStringToSimpleDate(validDate);

        softly.assertThat(convertedDate).isNotNull();
        softly.assertThat(convertedDate).isEqualTo(expectedLeap);
        softly.assertThat(convertedDate).isEqualTo(expectedLeapLong);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2024-02-30T00:00:00", "2024-02-30T11:03:10", "2024-03-01T00:00:00", "2024-03-01T09:00:57"})
    void convertStringToDate_WhenOutOfBoundDateValidFormatThenReturnsSimpleDateRolledOver(String outOfBoundDate) throws Exception {
        String expectedLeap = "2024-03-01";
        String expectedLeapLong = "2024-03-01T00:00:00";

        Date convertedDate = DateUtil.convertStringToSimpleDate(outOfBoundDate);

        softly.assertThat(convertedDate).isNotNull();
        softly.assertThat(convertedDate).isEqualTo(expectedLeap);
        softly.assertThat(convertedDate).isEqualTo(expectedLeapLong);
    }

    @Test
    void convertStringToDate_WhenNoDateOnlyTimeInvalidFormatThenReturnsNull() throws Exception {
        Date convertedDate = DateUtil.convertStringToSimpleDate("00:00:00");

        softly.assertThat(convertedDate).isNull();
    }

    @Test
    void checkDateRangeIsValid_AcceptsNullStartDate() {
        DateUtil.checkDateRangeIsValid(DateUtil.DateRange.DECISION, null, LocalDate.of(2024, 1, 1));
    }

    @Test
    void checkDateRangeIsValid_AcceptsNullEndDate() {
        DateUtil.checkDateRangeIsValid(DateUtil.DateRange.DECISION, LocalDate.of(2024, 1, 1), null);
    }

    @Test
    void checkDateRangeIsValid_WhenInvalidDateRangeIsGivenThenThrowsException() {
        softly.assertThatThrownBy(() -> DateUtil.checkDateRangeIsValid(DateUtil.DateRange.DECISION,
                        LocalDate.of(2024, 1, 1), LocalDate.of(2023, 12, 1)))
                .isInstanceOf(DateRangeConstraintViolationException.class)
                .hasMessage("Date Range Constraint Violation Exception :: decision start date [2024-01-01] must not be after end date [2023-12-01]");
    }

    @Test
    void checkStartDateWithinLimit_AcceptsStartDateWithin7yrs() {
        LocalDate startDate = LocalDate.now().minusYears(1);
        DateUtil.checkStartDateWithinLimit(SUBMITTED, startDate);
    }

    @Test
    void checkStartDateWithinLimit_AcceptsNullStartDate() {
        DateUtil.checkStartDateWithinLimit(SUBMITTED, null);
    }

    @Test
    void checkStartDateWithinLimit_AcceptsMinStartDate() {
        DateUtil.checkStartDateWithinLimit(SUBMITTED, MIN_START_DATE);
    }

    @Test
    void checkStartDateWithinLimit_WhenStartDateIsBeforeMinStartDateThenThrowsException() {
        LocalDate oldStartDate = MIN_START_DATE.minusDays(1);
        softly.assertThatThrownBy(() -> DateUtil.checkStartDateWithinLimit(SUBMITTED, oldStartDate))
                .isInstanceOf(StartDateConstraintViolationException.class)
                .hasMessageContaining("Start Date Constraint Violation Exception :: submitted start date ["
                        + oldStartDate + "] cannot be earlier than " + MIN_START_DATE);
    }

    @Test
    void convertDateToLocalDate_WhenGivenDateShouldReturnLocalDate() {
        LocalDate result = DateUtil.convertDateToLocalDate(new Date());

        softly.assertThat(result).isNotNull();
        softly.assertThat(result).isEqualTo(LocalDate.now());
    }

    @Test
    void getMinimumStartDate_ShouldReturnDate() {
        LocalDate result = getMinStartDate();

        softly.assertThat(result).isNotNull();
    }
}
