package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class CrmFurtherInfoAttachmentsModel {
    @JsonProperty("Delete")
    public String delete;
    @JsonProperty("Dtreceived")
    public Date dtreceived;
    @JsonProperty("Personname")
    public String personname;
    @JsonProperty("Description")
    public String description;
    @JsonProperty("Retrieve")
    public String retrieve;
    @JsonProperty("Personid")
    public String personid;
    @JsonProperty("Originalfilename")
    public String originalfilename;
    @JsonProperty("Name")
    public String name;
    public String fileKey;
}