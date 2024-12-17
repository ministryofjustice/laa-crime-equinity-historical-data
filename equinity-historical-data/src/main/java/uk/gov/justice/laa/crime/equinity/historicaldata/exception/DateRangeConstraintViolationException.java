package uk.gov.justice.laa.crime.equinity.historicaldata.exception;

import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.DateRange;

import java.time.LocalDate;

public class DateRangeConstraintViolationException extends RuntimeException {

    public DateRangeConstraintViolationException(LocalDate startDate, LocalDate endDate, DateRange dateRange) {
        super(String.format(
            "Date Range Constraint Violation Exception :: [%s] start date [%s] must not be after end date [%s]",
            dateRange, startDate, endDate)
        );
    }
}
