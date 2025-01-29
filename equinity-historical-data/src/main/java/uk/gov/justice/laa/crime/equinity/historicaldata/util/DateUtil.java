package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.StartDateConstraintViolationException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@UtilityClass
@ConfigurationPropertiesScan
@Slf4j
public class DateUtil {
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String INVALID_FORMAT_NO_DATE_ONLY_ZERO_TIME = "00:00:00";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)
            .withLocale(Locale.UK);
    private static final int SEVEN_YEARS = 7;

    public enum DateRange {
        CREATED("created"),
        DECISION("decision"),
        LAST_SUBMITTED("lastSubmitted"),
        SUBMITTED("submitted");

        private final String value;

        DateRange(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public static LocalDate convertDateToLocalDate(Date dateToConvert) throws DateTimeParseException {
        if (Objects.isNull(dateToConvert)) return null;

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate convertStringToLocalDate(String dateToConvert) throws DateTimeParseException {
        if (Objects.isNull(dateToConvert)) return null;

        return LocalDate.parse(dateToConvert, dateTimeFormatter);
    }

    public static void checkDateRangeIsValid(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(startDate)) return; // By default, accept open date ranges

        if (Objects.isNull(endDate)) return; // By default, accept open date ranges

        if (startDate.isAfter(endDate))
            throw new DateRangeConstraintViolationException(dateRange, startDate, endDate);
    }

    public static void checkStartDateWithinLimit(DateRange dateRange, LocalDate startDate) {
        LocalDate minStartDate = getMinStartDate();
        if (Objects.nonNull(startDate) && startDate.isBefore(minStartDate)) {
            throw new StartDateConstraintViolationException(dateRange, startDate, minStartDate);
        }
    }

    public static Date convertStringToSimpleDate(String dateToConvert) throws ParseException {
        if (Objects.isNull(dateToConvert)) return null;

        if (dateToConvert.equals(INVALID_FORMAT_NO_DATE_ONLY_ZERO_TIME)) return null;

        return new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.UK).parse(dateToConvert);
    }

    public static String calculateTimeDifference(String startDateTime, String endDateTime, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date startDate = dateFormat.parse(startDateTime);
            Date endDate = dateFormat.parse(endDateTime);
            long timeDiff = endDate.getTime() - startDate.getTime();
            return DurationFormatUtils
                    .formatDuration(timeDiff, TIME_FORMAT, true);
        } catch (ParseException e) {
            log.error("error parsing endDatetime :: endDateTime={} :: {}", endDateTime, e.getMessage());
            return null;
        }
    }

    public static LocalDate getMinStartDate() {
        // set minimum start date to seven yrs ago
        return LocalDate.now().minusYears(SEVEN_YEARS);
    }
}
