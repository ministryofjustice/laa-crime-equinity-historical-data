package uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input;

import jakarta.annotation.Nullable;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.ProfileAcceptedTypesUtil;

public record CrmFormDetailsCriteriaDTO(
        Long usn,
        Integer type,
        @Nullable String profileAcceptedTypes
        ) {
    public CrmFormDetailsCriteriaDTO {
        if (usn == null) {
            throw new InvalidDataAccessApiUsageException("Expected USN not be null");
        }

        ProfileAcceptedTypesUtil.checkTypeIsAcceptedByProfile(type, profileAcceptedTypes);
    }

    @Override
    public String toString() {
        return "CrmFormDetailsCriteriaDTO{" +
                "usn='" + usn + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
