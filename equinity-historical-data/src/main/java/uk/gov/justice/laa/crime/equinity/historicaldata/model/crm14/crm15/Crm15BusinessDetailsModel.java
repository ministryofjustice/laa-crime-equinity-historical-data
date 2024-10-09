package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.crm15;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15BusinessDetailsModel {
    @JsonProperty("Drawings_every")
    public String drawings_every;
    @JsonProperty("Trading_address_3")
    public String trading_address_3;
    @JsonProperty("Trading_address_2")
    public String trading_address_2;
    @JsonProperty("Trading_address_1")
    public String trading_address_1;
    @JsonProperty("Profit_every")
    public String profit_every;
    @JsonProperty("In_business_with_other")
    public String in_business_with_other;
    @JsonProperty("In_business_with_name")
    public String in_business_with_name;
    @JsonProperty("Business_type")
    public String business_type;
    @JsonProperty("Business_other_employees")
    public String business_other_employees;
    @JsonProperty("Turnover")
    public int turnover;
    @JsonProperty("Profit")
    public int profit;
    @JsonProperty("Business_nature")
    public String business_nature;
    @JsonProperty("Business_details_display")
    public String business_details_display;
    @JsonProperty("Trading_address_postcode")
    public String trading_address_postcode;
    @JsonProperty("Directors_share_income_display")
    public String directors_share_income_display;
    @JsonProperty("Trading_name")
    public String trading_name;
    @JsonProperty("Turnover_every")
    public String turnover_every;
    @JsonProperty("Previous_financials_display")
    public String previous_financials_display;
    @JsonProperty("Share_sales")
    public int share_sales;
    @JsonProperty("Remuneration")
    public int remuneration;
    @JsonProperty("Trading_details_display")
    public String trading_details_display;
    @JsonProperty("Percent_of_profits")
    public double percent_of_profits;
    @JsonProperty("Drawings")
    public Float drawings;
    @JsonProperty("Business_no_of_employees")
    public int business_no_of_employees;
    @JsonProperty("Business_trading_date")
    public Date business_trading_date;

}