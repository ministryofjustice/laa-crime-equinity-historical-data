package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm4Model implements CrmFormModelInterface {
    @JsonProperty("fielddata")
    Crm4DetailsModel formDetails;
    @JsonProperty("read_only")
    private boolean read_only;
    @JsonProperty("targetpath")
    private String targetpath;

    @JsonProperty("linkedAttachments")
    private CrmEvidenceFilesModel evidenceFiles;
}