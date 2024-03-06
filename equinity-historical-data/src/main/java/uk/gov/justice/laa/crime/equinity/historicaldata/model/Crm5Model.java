package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Crm5Model {
    @JsonProperty("fielddata")
    Crm5DetailsModel formDetails;
    @JsonProperty("read_only")
    private boolean read_only;
    @JsonProperty("attachfields")
    private String attachfields;
    @JsonProperty("targetpath")
    private String targetpath;
//    @JsonProperty("linkedAttachments")
//    LinkedAttachments LinkedAttachmentsObject;
}