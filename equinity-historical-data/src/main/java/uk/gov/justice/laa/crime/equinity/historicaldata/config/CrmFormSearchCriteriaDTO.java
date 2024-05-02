package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.NotEnoughSearchParametersException;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;

public record CrmFormSearchCriteriaDTO(
        @Nullable String usn, @Nullable Integer type,
        @Nullable String client, @Nullable String clientDoB,
        @Nullable String submittedFrom, @Nullable String submittedTo,
        @Nullable String providerAccount,
        @Nullable Integer page, @Nullable Integer pageSize
        ) {
    public CrmFormSearchCriteriaDTO {
        LocalDate dateSubmittedFrom = DateUtil.convertStringToLocalDate(submittedFrom);
        LocalDate dateSubmittedTo = DateUtil.convertStringToLocalDate(submittedTo);
        DateUtil.checkDateRangeIsValid(dateSubmittedFrom, dateSubmittedTo);

        if (isNullOrBlank(usn) && (type == null)
                && isNullOrBlank(client) && isNullOrBlank(clientDoB)
                && isNullOrBlank(submittedFrom) && isNullOrBlank(submittedTo)
                && isNullOrBlank(providerAccount)) {
            throw new NotEnoughSearchParametersException("Not enough search parameters. Search criteria needs at least 1 input");
        }
    }

    private boolean isNullOrBlank(String input) {
        return input == null || input.isBlank();
    }

    @Override
    public String toString() {
        return "CrmFormSearchCriteriaDTO{" +
                "usn='" + usn + '\'' +
                ", type='" + type + '\'' +
                ", client='" + client + '\'' +
                ", clientDoB='" + clientDoB + '\'' +
                ", submittedFrom='" + submittedFrom + '\'' +
                ", submittedTo='" + submittedTo + '\'' +
                ", providerAccount='" + providerAccount + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
