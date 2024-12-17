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
                                   String profileAcceptedTypes, int requiredType) throws DateRangeConstraintViolationException, UnauthorizedUserProfileException {
        checkDateRange(dateFrom, dateTo, DECISION);
        ProfileAcceptedTypesUtil.checkTypeIsAcceptedByProfile(requiredType, profileAcceptedTypes);
    }

    public static void checkDateRange(String dateFrom, String dateTo, DateRange dateRange) throws DateRangeConstraintViolationException {
        LocalDate checkDateFrom = DateUtil.convertStringToLocalDate(dateFrom);
        LocalDate checkDateTo = DateUtil.convertStringToLocalDate(dateTo);
        DateUtil.checkDateRangeIsValid(dateRange, checkDateFrom, checkDateTo);
    }
}
