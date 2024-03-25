package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record CrmFormSearchCriteriaDTO(
        @Nullable String usn, @Nullable String client, @Nullable String clientDoB,
        @Nullable LocalDate submittedFrom, @Nullable LocalDate submittedTo,
        @Nullable String providerAccount
) {}
