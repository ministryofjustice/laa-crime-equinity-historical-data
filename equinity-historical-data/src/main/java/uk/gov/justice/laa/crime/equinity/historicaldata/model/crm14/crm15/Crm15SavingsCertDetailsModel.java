package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.crm15;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15SavingsCertDetailsModel {
    @JsonProperty("Customer_number")
    public String customer_number;
    @JsonProperty("Cert_number")
    public String cert_number;
    @JsonProperty("You_or_partner")
    public String you_or_partner;
}