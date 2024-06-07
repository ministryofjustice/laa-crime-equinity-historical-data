package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Crm14ExtensionCrm15AnnexModel {
    @JsonProperty("Hide_crm15")
    public boolean hide_crm15;
    @JsonProperty("Child_benefit_crm15_paid_every")
    public String child_benefit_crm15_paid_every;
    @JsonProperty("Freezing_order_crm15")
    public String freezing_order_crm15;
    @JsonProperty("Incapacity_benefit_crm15_paid_every")
    public String incapacity_benefit_crm15_paid_every;
    @JsonProperty("Jsa_crm15_paid_every")
    public String jsa_crm15_paid_every;
    @JsonProperty("Tax_credit_crm15_paid_every")
    public String tax_credit_crm15_paid_every;
    @JsonProperty("Universal_credit_crm15_paid_every")
    public String universal_credit_crm15_paid_every;
    @JsonProperty("Other_benefits_crm15_paid_every")
    public String other_benefits_crm15_paid_every;
    @JsonProperty("State_pension_crm15_paid_every")
    public String state_pension_crm15_paid_every;
    @JsonProperty("Child_benefit_crm15")
    public int child_benefit_crm15;
    @JsonProperty("Tax_credit_crm15")
    public int tax_credit_crm15;
    @JsonProperty("Other_income_crm15")
    public String other_income_crm15;
    @JsonProperty("How_pay_bills_crm15")
    public String how_pay_bills_crm15;
    @JsonProperty("Disablement_benefit_crm15_paid_every")
    public String disablement_benefit_crm15_paid_every;
    @JsonProperty("Universal_credit_crm15")
    public int universal_credit_crm15;
    @JsonProperty("Other_benefits_crm15_details")
    public String other_benefits_crm15_details;
}