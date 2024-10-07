package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Crm4ExpenditureModel{
    @JsonProperty("Description")
    public String description;
    @JsonProperty("Rate")
    public Float rate;
    @JsonProperty("Total")
    public Float total;
    @JsonProperty("Quantity")
    public int quantity;
    @JsonProperty("Justification")
    public String justification;
}