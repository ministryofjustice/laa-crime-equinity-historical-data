package uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input;

import jakarta.validation.constraints.NotNull;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.NotEnoughSearchParametersException;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.ReportInputsUtil;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.DateRange.*;

public record Crm14ProviderReportCriteriaDTO(
        @NotNull Integer filterByDecision,
        @NotNull String decisionFrom, @NotNull String decisionTo,
        @NotNull Integer filterBySubmit,
        @NotNull String submittedFrom, @NotNull String submittedTo,
        @NotNull Integer filterByCreation,
        @NotNull String createdFrom, @NotNull  String createdTo,
        @NotNull Integer filterByLastSubmit,
        @NotNull String lastSubmittedFrom, @NotNull String lastSubmittedTo,
        @NotNull String state
        ) {
    public Crm14ProviderReportCriteriaDTO {
        boolean isdateRangeChecked = false;

        if (filterByDecision > 0) {
            ReportInputsUtil.checkDateRange(DECISION, decisionFrom, decisionTo);
            isdateRangeChecked = true;
        }

        if (filterBySubmit > 0) {
            ReportInputsUtil.checkDateRange(SUBMITTED, submittedFrom, submittedTo);
            isdateRangeChecked = true;
        }

        if (filterByCreation > 0) {
            ReportInputsUtil.checkDateRange(CREATED, createdFrom, createdTo);
            isdateRangeChecked = true;
        }

        if (filterByLastSubmit > 0) {
            ReportInputsUtil.checkDateRange(LAST_SUBMITTED, lastSubmittedFrom, lastSubmittedTo);
            isdateRangeChecked = true;
        }

        if (!isdateRangeChecked) {
            throw new NotEnoughSearchParametersException("Not enough inputs to generate report. Please specify at least 1 date range and turn on the corresponding filter flag");
        }

    }


    @Override
    public String toString() {
        return "Crm14ProviderReportCriteriaDTO{" +
                "filterByDecision='" + filterByDecision + "'" +
                ", decisionFrom='" + decisionFrom + "'" +
                ", decisionTo='" + decisionTo + "'" +
                ", filterBySubmit='" + filterBySubmit + "'" +
                ", submittedFrom='" + submittedFrom + "'" +
                ", submittedTo='" + submittedTo + "'" +
                ", filterByCreation='" + filterByCreation + "'" +
                ", createdFrom='" + createdFrom + "'" +
                ", createdTo='" + createdTo + "'" +
                ", filterByLastSubmit='" + filterByLastSubmit + "'" +
                ", lastSubmittedFrom='" + lastSubmittedFrom + "'" +
                ", lastSubmittedTo='" + lastSubmittedTo + "'" +
                ", state='" + state + "'" +
            '}';
    }
}
