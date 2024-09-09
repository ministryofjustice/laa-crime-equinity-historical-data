package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Crm14MessageModel {
    @JsonProperty("Sender_display")
    public String sender_display;
    @JsonProperty("Message")
    public String message;
    @JsonProperty("Sender_uniquename")
    public String sender_uniquename;
    @JsonProperty("Datetime")
    public String datetime;
}