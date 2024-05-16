package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Crm4ExpenditureModel{
    @JsonProperty("Description")
    public String description;
    @JsonProperty("Rate")
    public int rate;
    @JsonProperty("Total")
    public int total;
    @JsonProperty("Quantity")
    public int quantity;
    @JsonProperty("Justification")
    public String justification;
}