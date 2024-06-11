package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm7Model implements CrmFormModelInterface {
    @JsonProperty("fielddata")
    Crm7DetailsModel formDetails;
    @JsonProperty("read_only")
    private boolean read_only;
    @JsonProperty("attachfields")
    private String attachfields;
    @JsonProperty("targetpath")
    private String targetpath;

    @Override
    public CrmEvidenceFilesModel getEvidenceFiles() {
        return (CrmEvidenceFilesModel) List.of();
    }
}