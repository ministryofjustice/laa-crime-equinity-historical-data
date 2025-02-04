package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.DateRange;

import java.time.LocalDate;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.DateRange.DECISION;

@UtilityClass
public class ReportInputsUtil {
    public static void checkInputs(String dateFrom, String dateTo,
                                   String profileAcceptedTypes, int requiredType, boolean applySevenYearsLimit) throws DateRangeConstraintViolationException, UnauthorizedUserProfileException {
        checkDateRange(DECISION, dateFrom, dateTo, applySevenYearsLimit);
        ProfileAcceptedTypesUtil.checkTypeIsAcceptedByProfile(requiredType, profileAcceptedTypes);
    }

    public static void checkDateRange(DateRange dateRange, String dateFrom, String dateTo, boolean applySevenYearsLimit) throws DateRangeConstraintViolationException {
        LocalDate checkDateFrom = DateUtil.convertStringToLocalDate(dateFrom);
        LocalDate checkDateTo = DateUtil.convertStringToLocalDate(dateTo);
        if (applySevenYearsLimit) {
            DateUtil.checkStartDateWithin7yrs(dateRange, checkDateFrom);
        }
        DateUtil.checkDateRangeIsValid(dateRange, checkDateFrom, checkDateTo);
    }
}

