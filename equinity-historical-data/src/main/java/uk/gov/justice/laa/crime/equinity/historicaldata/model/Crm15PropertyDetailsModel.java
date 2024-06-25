package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15PropertyDetailsModel {
    @JsonProperty("Percent_owned")
    public double percent_owned;
    @JsonProperty("Partner_percent_owned")
    public double partner_percent_owned;
    @JsonProperty("Residential_property_bedrooms")
    public int residential_property_bedrooms;
    @JsonProperty("Commercial_property_used_for")
    public String commercial_property_used_for;
    @JsonProperty("Property_not_home_address_3")
    public String property_not_home_address_3;
    @JsonProperty("Property_not_home_address_2")
    public String property_not_home_address_2;
    @JsonProperty("Estimated_market_value")
    public int estimated_market_value;
    @JsonProperty("Property_not_home_address_1")
    public String property_not_home_address_1;
    @JsonProperty("Other_owners_name_2")
    public String other_owners_name_2;
    @JsonProperty("Partner_usual_home_address")
    public boolean partner_usual_home_address;
    @JsonProperty("Other_owners_name_1")
    public String other_owners_name_1;
    @JsonProperty("Other_owners_relationship_1")
    public String other_owners_relationship_1;
    @JsonProperty("Address_listed_below")
    public boolean address_listed_below;
    @JsonProperty("Other_owners_relationship_2")
    public String other_owners_relationship_2;
    @JsonProperty("Residential_commercial_land_details_display")
    public String residential_commercial_land_details_display;
    @JsonProperty("Mortgage_to_pay")
    public String mortgage_to_pay;
    @JsonProperty("Residential_property_type")
    public String residential_property_type;
    @JsonProperty("L_and_p_address_indicator_display")
    public String l_and_p_address_indicator_display;
    @JsonProperty("Other_owners_relationship_other_1")
    public String other_owners_relationship_other_1;
    @JsonProperty("Other_property_type")
    public String other_property_type;
    @JsonProperty("Land_use")
    public String land_use;
    @JsonProperty("Usual_home_address")
    public boolean usual_home_address;
    @JsonProperty("Other_owners_relationship_other_2")
    public String other_owners_relationship_other_2;
    @JsonProperty("Ownership_details_display")
    public String ownership_details_display;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Mortgage_display")
    public String mortgage_display;
    @JsonProperty("L_and_p_address_display")
    public String l_and_p_address_display;
    @JsonProperty("Property_not_home_postcode")
    public String property_not_home_postcode;

}