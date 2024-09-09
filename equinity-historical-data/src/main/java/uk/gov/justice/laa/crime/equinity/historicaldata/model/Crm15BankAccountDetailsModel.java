package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15BankAccountDetailsModel {
    @JsonProperty("Account_holder")
    public String account_holder;
    @JsonProperty("Bank")
    public String bank;
    @JsonProperty("Sort_code_brancg")
    public String sort_code_brancg;
    @JsonProperty("Account_type")
    public String account_type;
    @JsonProperty("Account_number")
    public String account_number;
    @JsonProperty("Overdrawn")
    public boolean overdrawn;
    @JsonProperty("Balance")
    public double balance;
}