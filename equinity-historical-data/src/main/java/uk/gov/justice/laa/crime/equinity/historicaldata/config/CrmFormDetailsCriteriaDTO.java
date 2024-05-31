package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;
import org.springframework.dao.InvalidDataAccessApiUsageException;

public record CrmFormDetailsCriteriaDTO(
        Long usn,
        Integer type,
        @Nullable String profileAcceptedTypes
        ) {
    public CrmFormDetailsCriteriaDTO {
        if (usn == null || type == null) {
            throw new InvalidDataAccessApiUsageException("Expected USN and Crm eFrom type not be null");
        }
    }
//
    @Override
    public String toString() {
        return "CrmFormDetailsCriteriaDTO{" +
                "usn='" + usn + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
