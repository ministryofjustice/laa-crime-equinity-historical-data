package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.crm15;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15InvestmentDetailsModel {
    @JsonProperty("Investment_holder")
    public String investment_holder;
    @JsonProperty("The_investment")
    public String the_investment;
    @JsonProperty("investment")
    public String investment;
    @JsonProperty("Value")
    public double value;
}