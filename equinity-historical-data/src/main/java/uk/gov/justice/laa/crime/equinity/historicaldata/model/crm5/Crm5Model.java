package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm5Model implements CrmFormModelInterface {
    @JsonProperty("fielddata")
    Crm5DetailsModel formDetails;
    @JsonProperty("read_only")
    private boolean read_only;
    @JsonProperty("attachfields")
    private String attachfields;
    @JsonProperty("targetpath")
    private String targetpath;

    @JsonProperty("linkedAttachments")
    private CrmEvidenceFilesModel evidenceFiles;
}