package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Crm4AuthorisedExpenditureModel {
    @JsonProperty("Description")
    public String description;
    @JsonProperty("Rate_cw")
    public float rate_cw;
    @JsonProperty("Rate")
    public float rate;
    @JsonProperty("Total")
    public int total;
    @JsonProperty("Quantity")
    public int quantity;
    @JsonProperty("Justification")
    public String justification;
    @JsonProperty("Total_cw")
    public int total_cw;
    @JsonProperty("Quantity_cw")
    public int quantity_cw;
}