package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.DateRangeConstraintViolationException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;

import java.time.LocalDate;

@UtilityClass
public class ReportInputsUtil {
    public static void checkInputs(String dateFrom, String dateTo,
                                   String profileAcceptedTypes, int requiredType) throws DateRangeConstraintViolationException, ResourceNotFoundException {
        checkDateRange(dateFrom, dateTo);
        checkTypeIsAcceptedByProfile(String.valueOf(requiredType), profileAcceptedTypes);
    }

    private static void checkDateRange(String dateFrom, String dateTo) throws DateRangeConstraintViolationException {
        LocalDate checkDateFrom = DateUtil.convertStringToLocalDate(dateFrom);
        LocalDate  checkDateTo = DateUtil.convertStringToLocalDate(dateTo);
        DateUtil.checkDateRangeIsValid(checkDateFrom, checkDateTo);
    }

    private static void checkTypeIsAcceptedByProfile(String requiredType, @Nullable String types) throws ResourceNotFoundException {
        if (types == null) return;

        if (!types.contains(String.valueOf(requiredType))) {
            throw new ResourceNotFoundException("No data found. User profile does not have privileges to access requested data");
        }
    }
}
