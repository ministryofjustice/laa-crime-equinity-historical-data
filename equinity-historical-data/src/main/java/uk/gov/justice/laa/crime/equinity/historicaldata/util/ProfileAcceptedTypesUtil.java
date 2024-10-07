package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;

@UtilityClass
public class ProfileAcceptedTypesUtil {
    public static void checkTypeIsAcceptedByProfile(Integer requiredType, @Nullable String types) throws UnauthorizedUserProfileException {
        if (types == null) return;

        for (String profile : types.split(",")) {
            if (profile.equals(requiredType.toString())) return;
        }

        throw new UnauthorizedUserProfileException(
                String.format("Unauthorized. User profile does not have privileges to access requested report type [%d] ", requiredType)
        );
    }
}
