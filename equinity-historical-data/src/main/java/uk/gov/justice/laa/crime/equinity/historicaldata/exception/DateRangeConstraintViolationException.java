package uk.gov.justice.laa.crime.equinity.historicaldata.exception;


import java.time.LocalDate;

public class DateRangeConstraintViolationException extends RuntimeException {

    public DateRangeConstraintViolationException(LocalDate startDate, LocalDate endDate) {
        super(String.format(
            "Date Range Constraint Violation Exception :: start date [%s] must not be after end date [%s]",
            startDate, endDate)
        );
    }
}
