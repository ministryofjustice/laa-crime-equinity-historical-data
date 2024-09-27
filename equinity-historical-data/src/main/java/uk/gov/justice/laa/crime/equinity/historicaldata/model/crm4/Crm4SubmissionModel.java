package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class Crm4SubmissionModel {
    @JsonProperty("Usn")
    public int usn;
    @JsonProperty("Clientname")
    public String clientname;
    @JsonProperty("State")
    public String state;
    @JsonProperty("Formtype")
    public String formtype;
    @JsonProperty("Datesubmitted")
    public Date datesubmitted;
    @JsonProperty("Decision")
    public String decision;
}