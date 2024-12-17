package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public enum DateRange {
        CREATED("Created"),
        DECISION("Decision"),
        LAST_SUBMITTED("Last submitted"),
        SUBMITTED("Submitted");

        private final String value;

        DateRange(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public static LocalDate convertStringToLocalDate(String dateToConvert) throws DateTimeParseException {
        if (Objects.isNull(dateToConvert)) return null;

        return LocalDate.parse(dateToConvert, dateTimeFormatter);
    }

    public static void checkDateRangeIsValid(LocalDate startDate, LocalDate endDate, DateRange dateRange) {
        if (Objects.isNull(startDate)) return; // By default, accept open date ranges

        if (Objects.isNull(endDate)) return; // By default, accept open date ranges

        if (startDate.isAfter(endDate))
            throw new DateRangeConstraintViolationException(startDate, endDate, dateRange);
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
}