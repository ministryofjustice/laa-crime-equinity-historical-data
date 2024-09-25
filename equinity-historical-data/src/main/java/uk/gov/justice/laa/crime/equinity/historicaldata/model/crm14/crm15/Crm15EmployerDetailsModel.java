package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.crm15;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15EmployerDetailsModel {
    @JsonProperty("Salary_paid_every")
    public String salary_paid_every;
    @JsonProperty("Salary")
    public double salary;
    @JsonProperty("Salary_tax")
    public int salary_tax;
    @JsonProperty("Employers_postcode")
    public String employers_postcode;
    @JsonProperty("Other_deduction_paid_every")
    public String other_deduction_paid_every;
    @JsonProperty("Name_and_job_title_display")
    public String name_and_job_title_display;
    @JsonProperty("Deductions_display")
    public String deductions_display;
    @JsonProperty("Salary_display")
    public String salary_display;
    @JsonProperty("Employers_address_2")
    public String employers_address_2;
    @JsonProperty("Employers_address_3")
    public String employers_address_3;
    @JsonProperty("Income_tax")
    public double income_tax;
    @JsonProperty("National_insurance")
    public double national_insurance;
    @JsonProperty("Job_title")
    public String job_title;
    @JsonProperty("Income_tax_paid_every")
    public String income_tax_paid_every;
    @JsonProperty("Employers_name")
    public String employers_name;
    @JsonProperty("Employers_address_1")
    public String employers_address_1;
    @JsonProperty("National_insurance_paid_every")
    public String national_insurance_paid_every;
    @JsonProperty("Other_deduction_details")
    public String other_deduction_details;
    @JsonProperty("Address_display")
    public String address_display;
}