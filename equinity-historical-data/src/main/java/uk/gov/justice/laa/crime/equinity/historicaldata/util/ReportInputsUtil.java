package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;

import java.time.LocalDate;

@UtilityClass
public class ReportInputsUtil {
    public static void checkInputs(String dateFrom, String dateTo,
                                   String profileAcceptedTypes, int requiredType) throws DateRangeConstraintViolationException, UnauthorizedUserProfileException {
        checkDateRange(dateFrom, dateTo);
        checkTypeIsAcceptedByProfile(requiredType, profileAcceptedTypes);
    }

    public static void checkDateRange(String dateFrom, String dateTo) throws DateRangeConstraintViolationException {
        LocalDate checkDateFrom = DateUtil.convertStringToLocalDate(dateFrom);
        LocalDate  checkDateTo = DateUtil.convertStringToLocalDate(dateTo);
        DateUtil.checkDateRangeIsValid(checkDateFrom, checkDateTo);
    }

    public static void checkTypeIsAcceptedByProfile(Integer requiredType, @Nullable String types) throws UnauthorizedUserProfileException {
        if (types == null) return;

        if (!types.contains(String.valueOf(requiredType))) {
            throw new UnauthorizedUserProfileException(
                    String.format("Unauthorized. User profile does not have privileges to access requested report type [%d] ", requiredType)
            );
        }
    }
}
