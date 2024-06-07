package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Crm14PartnerDetailsModel {
    @JsonProperty("Esa_partner")
    public boolean esa_partner;
    @JsonProperty("Partner_surname")
    public String partner_surname;
    @JsonProperty("Partner_income_from_other_sources")
    public String partner_income_from_other_sources;
    @JsonProperty("Partner_ni")
    public String partner_ni;
    @JsonProperty("Partner_self_assessment_tax_received")
    public String partner_self_assessment_tax_received;
    @JsonProperty("Partner_date_of_birth")
    public Date partner_date_of_birth;
    @JsonProperty("Partner_jsa_crm15_paid_every")
    public String partner_jsa_crm15_paid_every;
    @JsonProperty("Partner_maintenance_payment_paid_every")
    public String partner_maintenance_payment_paid_every;
    @JsonProperty("Partner_other_rent")
    public boolean partner_other_rent;
    @JsonProperty("Partner_income_rental")
    public boolean partner_income_rental;
    @JsonProperty("Partner_child_benefit_crm15_paid_every")
    public String partner_child_benefit_crm15_paid_every;
    @JsonProperty("Partner_income_financial_support")
    public boolean partner_income_financial_support;
    @JsonProperty("Partner_wage_paid_every")
    public String partner_wage_paid_every;
    @JsonProperty("Partner_maintenance_payment_calc")
    public int partner_maintenance_payment_calc;
    @JsonProperty("Partner_interest_paid_every")
    public String partner_interest_paid_every;
    @JsonProperty("Partner_calc")
    public int partner_calc;
    @JsonProperty("Partner_other_income_checkbox_count")
    public int partner_other_income_checkbox_count;
    @JsonProperty("Partner_tax_credits_paid_every")
    public String partner_tax_credits_paid_every;
    @JsonProperty("Partner_other_benefits_crm15_details")
    public String partner_other_benefits_crm15_details;
    @JsonProperty("Partner_other_income_source_initialised_flag")
    public boolean partner_other_income_source_initialised_flag;
    @JsonProperty("Partner_child_benefit_paid_every")
    public String partner_child_benefit_paid_every;
    @JsonProperty("Partner_usual_postcode")
    public String partner_usual_postcode;
    @JsonProperty("Partner_other_case_charges")
    public String partner_other_case_charges;
    @JsonProperty("Partner_receiving_benefits_2")
    public String partner_receiving_benefits_2;
    @JsonProperty("Partner_national_savings_certificates")
    public String partner_national_savings_certificates;
    @JsonProperty("Partner_income_rent_from_family")
    public boolean partner_income_rent_from_family;
    @JsonProperty("Partner_other_case_next_hearing")
    public Date partner_other_case_next_hearing;
    @JsonProperty("Partner_child_benefit_crm15")
    public int partner_child_benefit_crm15;
    @JsonProperty("Partner_receive_maintenance_payments")
    public String partner_receive_maintenance_payments;
    @JsonProperty("Partner_paid_40_percent_tax")
    public String partner_paid_40_percent_tax;
    @JsonProperty("Partner_other_income_source")
    public String partner_other_income_source;
    @JsonProperty("Partner_total_pension_paid_every")
    public String partner_total_pension_paid_every;
    @JsonProperty("Partner_tax_credit_crm15_paid_every")
    public String partner_tax_credit_crm15_paid_every;
    @JsonProperty("Partner_pensions_calc")
    public int partner_pensions_calc;
    @JsonProperty("Partner_trust_fund")
    public String partner_trust_fund;
    @JsonProperty("Partner_maintenance_calc")
    public int partner_maintenance_calc;
    @JsonProperty("Partner_other_financial_support")
    public boolean partner_other_financial_support;
    @JsonProperty("State_pension_partner")
    public boolean state_pension_partner;
    @JsonProperty("Partner_salary_paid_into_account")
    public String partner_salary_paid_into_account;
    @JsonProperty("Partner_universal_credit_crm15_paid_every")
    public String partner_universal_credit_crm15_paid_every;
    @JsonProperty("Partner_universal_credit_paid_every")
    public String partner_universal_credit_paid_every;
    @JsonProperty("Partner_disablement_benefit_crm15_paid_every")
    public String partner_disablement_benefit_crm15_paid_every;
    @JsonProperty("Land_calc_partner")
    public int land_calc_partner;
    @JsonProperty("Partner_involved_in_case")
    public int partner_involved_in_case;
    @JsonProperty("Jsa_partner")
    public boolean jsa_partner;
    @JsonProperty("Partner_sign_date")
    public Date partner_sign_date;
    @JsonProperty("Partner_freezing_order_crm15")
    public String partner_freezing_order_crm15;
    @JsonProperty("Partner_title_other")
    public String partner_title_other;
    @JsonProperty("Have_partner_copy")
    public int have_partner_copy;
    @JsonProperty("Partner_business_calc")
    public int partner_business_calc;
    @JsonProperty("Partner_usual_address_1")
    public String partner_usual_address_1;
    @JsonProperty("Partner_usual_address_2")
    public String partner_usual_address_2;
    @JsonProperty("Partner_tax_liability_paid_every")
    public String partner_tax_liability_paid_every;
    @JsonProperty("Partner_usual_address_3")
    public String partner_usual_address_3;
    @JsonProperty("Partner_other_benefits_calc")
    public int partner_other_benefits_calc;
    @JsonProperty("Partner_forenames")
    public String partner_forenames;
    @JsonProperty("Partner_student_loan")
    public boolean partner_student_loan;
    @JsonProperty("Partner_salary_account")
    public String partner_salary_account;
    @JsonProperty("Dwp_check_partner_dob")
    public Date dwp_check_partner_dob;
    @JsonProperty("Partner_state_pension_crm15_paid_every")
    public String partner_state_pension_crm15_paid_every;
    @JsonProperty("Income_support_partner")
    public boolean income_support_partner;
    @JsonProperty("Partner_different_home")
    public String partner_different_home;
    @JsonProperty("Partner_pensions_paid_every")
    public String partner_pensions_paid_every;
    @JsonProperty("Dwp_check_partner_nino")
    public String dwp_check_partner_nino;
    @JsonProperty("Partner_total_pension_calc")
    public int partner_total_pension_calc;
    @JsonProperty("Partner_premium_bonds")
    public String partner_premium_bonds;
    @JsonProperty("Partner_involvement")
    public int partner_involvement;
    @JsonProperty("Partner_income_maintenance")
    public boolean partner_income_maintenance;
    @JsonProperty("Partner_wage_calc")
    public int partner_wage_calc;
    @JsonProperty("Partner_income_from_other_source_calc")
    public int partner_income_from_other_source_calc;
    @JsonProperty("Partner_income_student_grant")
    public boolean partner_income_student_grant;
    @JsonProperty("Partner_family_rent")
    public boolean partner_family_rent;
    @JsonProperty("Partner_title")
    public String partner_title;
    @JsonProperty("Dwp_check_partner_surname")
    public String dwp_check_partner_surname;
    @JsonProperty("Partner_incapacity_benefit_crm15_paid_every")
    public String partner_incapacity_benefit_crm15_paid_every;
    @JsonProperty("Partner_tax_credits_calc")
    public int partner_tax_credits_calc;
    @JsonProperty("Partner_needed_calc")
    public int partner_needed_calc;
    @JsonProperty("Partner_other_names")
    public String partner_other_names;
    @JsonProperty("Partner_other_income_source_freetext")
    public String partner_other_income_source_freetext;
    @JsonProperty("Dwp_check_partner_dateofaward")
    public Date dwp_check_partner_dateofaward;
    @JsonProperty("Partner_other_benefits_paid_every")
    public String partner_other_benefits_paid_every;
    @JsonProperty("Partner_usual_address_search")
    public String partner_usual_address_search;
    @JsonProperty("Partner_maintenance_paid_every")
    public String partner_maintenance_paid_every;
    @JsonProperty("Partner_wage_tax")
    public String partner_wage_tax;
    @JsonProperty("Partner_receive_interest")
    public String partner_receive_interest;
    @JsonProperty("Partner_sign_fullname")
    public String partner_sign_fullname;
    @JsonProperty("Partner_confirm_read")
    public boolean partner_confirm_read;
    @JsonProperty("No_of_partner_accounts_calc")
    public int no_of_partner_accounts_calc;
    @JsonProperty("Partner_non_cash_benefit")
    public String partner_non_cash_benefit;
    @JsonProperty("Partner_other_case_court")
    public String partner_other_case_court;
    @JsonProperty("Partner_own_property")
    public String partner_own_property;
    @JsonProperty("Partner_salary_1")
    public boolean partner_salary_1;
    @JsonProperty("Partner_other_income_calc")
    public int partner_other_income_calc;
    @JsonProperty("Partner_private_company")
    public String partner_private_company;
    @JsonProperty("Partner_income_friends_family")
    public boolean partner_income_friends_family;
    @JsonProperty("Partner_universal_credit_calc")
    public int partner_universal_credit_calc;
    @JsonProperty("Partner_other_benefits_crm15_paid_every")
    public String partner_other_benefits_crm15_paid_every;
    @JsonProperty("Partner_other_income_crm15")
    public String partner_other_income_crm15;
    @JsonProperty("Partner_other_income_paid_every")
    public String partner_other_income_paid_every;
    @JsonProperty("Partner_total_amount_received_every")
    public String partner_total_amount_received_every;
    @JsonProperty("Partner_arc")
    public String partner_arc;
    @JsonProperty("Partner_employed")
    public String partner_employed;
    @JsonProperty("Have_partner")
    public int have_partner;
    @JsonProperty("Why_partner_no_sign")
    public String why_partner_no_sign;
    @JsonProperty("Partner_child_benefit_calc")
    public int partner_child_benefit_calc;
    @JsonProperty("Partner_tax_credit_crm15")
    public int partner_tax_credit_crm15;
    @JsonProperty("Partner_maintenance_payment")
    public int partner_maintenance_payment;
    @JsonProperty("Partner_receive_pension")
    public String partner_receive_pension;
    @JsonProperty("Partner_wage_tax_calc")
    public int partner_wage_tax_calc;
}