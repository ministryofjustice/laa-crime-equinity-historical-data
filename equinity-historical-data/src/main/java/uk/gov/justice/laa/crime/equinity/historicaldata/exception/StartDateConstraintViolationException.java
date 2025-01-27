package uk.gov.justice.laa.crime.equinity.historicaldata.exception;

import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;

public class StartDateConstraintViolationException extends RuntimeException {

    public StartDateConstraintViolationException(DateUtil.DateRange dateRange, LocalDate startDate, int yearsLimit) {
        super(String.format("Start Date Constraint Violation Exception :: %s start date [%s] cannot be earlier than %d years ago",
                dateRange, startDate, yearsLimit));
    }
}
