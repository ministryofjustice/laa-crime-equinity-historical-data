package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * This class was created to contain all fields.
 * Lombok is not compiling if we put all fields in CRM14DetailsModel.
 */
@Data
@NoArgsConstructor
public class Crm14AdditionalDetails {

    @JsonProperty("stocks")
    public int stocks;
    @JsonProperty("Partner_stocks")
    public int partner_stocks;
    @JsonProperty("Joint_stocks")
    public int joint_stocks;
    @JsonProperty("Shares")
    public int shares;
    @JsonProperty("Partner_shares")
    public int partner_shares;
    @JsonProperty("Joint_shares")
    public int joint_shares;
    @JsonProperty("Peps")
    public int peps;
    @JsonProperty("Partner_peps")
    public int partner_peps;
    @JsonProperty("Joint_peps")
    public int joint_peps;
    @JsonProperty("Share_isa")
    public int share_isa;
    @JsonProperty("Partner_share_isa")
    public int partner_share_isa;
    @JsonProperty("Joint_share_isa")
    public int joint_share_isa;
    @JsonProperty("Unit_trust")
    public int unit_trust;
    @JsonProperty("Partner_unit_trust")
    public int partner_unit_trust;
    @JsonProperty("Joint_unit_trust")
    public int joint_unit_trust;
    @JsonProperty("Investment_bonds")
    public int investment_bonds;
    @JsonProperty("Partner_investment_bonds")
    public int partner_investment_bonds;
    @JsonProperty("Joint_investment_bonds")
    public int joint_investment_bonds;
    @JsonProperty("Other_lump_sum")
    public int other_lump_sum;
    @JsonProperty("Partner_other_lump_sum")
    public int partner_other_lump_sum;
    @JsonProperty("Joint_other_lump_sum")
    public int joint_other_lump_sum;
    @JsonProperty("National_savings_certificates_total_value")
    public double national_savings_certificates_total_value;
    @JsonProperty("Trust_fund_amount")
    public double trust_fund_amount;
    @JsonProperty("Trust_fund_dividend")
    public double trust_fund_dividend;
    @JsonProperty("Partner_Trust_fund_amount")
    public double partner_Trust_fund_amount;
    @JsonProperty("Partner_Trust_fund_dividend")
    public double partner_Trust_fund_dividend;
    @JsonProperty("Premium_bonds_holder_no")
    public int premium_bonds_holder_no;
    @JsonProperty("Partner_premium_bonds_holder_no")
    public int partner_premium_bonds_holder_no;
    @JsonProperty("Premium_bonds_total_value")
    public double premium_bonds_total_value;
    @JsonProperty("Bank_accounts_crm15")
    public int bank_accounts_crm15;
    @JsonProperty("Partner_bank_accounts_crm15")
    public int partner_bank_accounts_crm15;
    @JsonProperty("Joint_bank_accounts_crm15")
    public int joint_bank_accounts_crm15;
    @JsonProperty("Building_society_account_crm15")
    public int building_society_account_crm15;
    @JsonProperty("Partner_building_society_account_crm15")
    public int partner_building_society_account_crm15;
    @JsonProperty("Joint_building_society_account_crm15")
    public int joint_building_society_account_crm15;
    @JsonProperty("Cash_isa_crm15")
    public int cash_isa_crm15;
    @JsonProperty("Partner_cash_isa_crm15")
    public int partner_cash_isa_crm15;
    @JsonProperty("Joint_cash_isa_crm15")
    public int joint_cash_isa_crm15;
    @JsonProperty("National_savings_crm15")
    public int national_savings_crm15;
    @JsonProperty("Partner_national_savings_crm15")
    public int partner_national_savings_crm15;
    @JsonProperty("Joint_national_savings_crm15")
    public int joint_national_savings_crm15;
    @JsonProperty("Other_cash_investments_crm15")
    public int other_cash_investments_crm15;
    @JsonProperty("Partner_other_cash_investments_crm15")
    public int partner_other_cash_investments_crm15;
    @JsonProperty("Joint_other_cash_investments_crm15")
    public int joint_other_cash_investments_crm15;
    @JsonProperty("Employers_crm15")
    public int employers_crm15;
    @JsonProperty("Non_cash_benefit")
    public String non_cash_benefit;
    @JsonProperty("Partner_non_cash_benefit")
    public String partner_non_cash_benefit;
    @JsonProperty("Non_cash_benefit_value")
    public double non_cash_benefit_value;
    @JsonProperty("Partner_non_cash_benefit_value")
    public double partner_non_cash_benefit_value;
    @JsonProperty("State_pension_crm15")
    public double state_pension_crm15 ;
    @JsonProperty("Partner_state_pension_crm15")
    public double partner_state_pension_crm15 ;
    @JsonProperty("Partner_universal_credit_crm15")
    public double partner_universal_credit_crm15 ;
    @JsonProperty("Incapacity_benefit_crm15")
    public double incapacity_benefit_crm15 ;
    @JsonProperty("Partner_incapacity_benefit_crm15")
    public double partner_incapacity_benefit_crm15 ;
    @JsonProperty("Disablement_benefit_crm15")
    public double disablement_benefit_crm15 ;
    @JsonProperty("Partner_disablement_benefit_crm15")
    public double partner_disablement_benefit_crm15 ;
    @JsonProperty("Jsa_crm15")
    public double jsa_crm15 ;
    @JsonProperty("Partner_jsa_crm15")
    public double partner_jsa_crm15 ;
    @JsonProperty("Other_benefits_crm15")
    public double other_benefits_crm15 ;
    @JsonProperty("Partner_other_benefits_crm15")
    public double partner_other_benefits_crm15 ;
    @JsonProperty("Total_pension")
    public double total_pension ;
    @JsonProperty("Partner_total_pension")
    public double partner_total_pension ;
    @JsonProperty("Year_1")
    public int year_1;
    @JsonProperty("Year_2_to_4")
    public int year_2_to_4;
    @JsonProperty("Year_5_to_7")
    public int year_5_to_7;
    @JsonProperty("Year_8_to_10")
    public int year_8_to_10;
    @JsonProperty("Year_11_to_12")
    public int year_11_to_12;
    @JsonProperty("Year_13_to_15")
    public int year_13_to_15;
    @JsonProperty("Year_16_to_18")
    public int year_16_to_18;
    @JsonProperty("Partner_receive_maintenance_payments")
    private String partner_receive_maintenance_payments;
    @JsonProperty("Partner_maintenance")
    public double partner_maintenance;
    @JsonProperty("Partner_maintenance_payment_paid_every")
    private String partner_maintenance_payment_paid_every;
    @JsonProperty("Interest")
    public double interest;
    @JsonProperty("Partner_receive_interest")
    private String partner_receive_interest;
    @JsonProperty("Partner_interest")
    public double partner_interest;
    @JsonProperty("Partner_interest_paid_every")
    private String partner_interest_paid_every;
    @JsonProperty("Total_amount_received")
    public double total_amount_received;
    @JsonProperty("Partner_student_loan")
    private boolean partner_student_loan;
    @JsonProperty("Partner_family_rent")
    private boolean partner_family_rent;
    @JsonProperty("Partner_other_rent")
    private boolean partner_other_rent;
    @JsonProperty("Partner_other_financial_support")
    private boolean partner_other_financial_support;
    @JsonProperty("Partner_other_income_source_freetext")
    private String partner_other_income_source_freetext;
    @JsonProperty("Partner_total_amount_received")
    public double partner_total_amount_received;
    @JsonProperty("Partner_total_amount_received_every")
    private String partner_total_amount_received_every;

    @JsonProperty("Have_partner")
    public int have_partner;
    @JsonProperty("Partner_conflict_of_interest")
    public int partner_conflict_of_interest;
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
    public double partner_child_benefit_crm15;
    @JsonProperty("Partner_paid_40_percent_tax")
    public String partner_paid_40_percent_tax;
    @JsonProperty("Partner_other_income_source")
    public String partner_other_income_source;
    @JsonProperty("Partner_total_pension_paid_every")
    public int partner_total_pension_paid_every;
    @JsonProperty("Partner_tax_credit_crm15_paid_every")
    public String partner_tax_credit_crm15_paid_every;
    @JsonProperty("Partner_pensions_calc")
    public int partner_pensions_calc;
    @JsonProperty("Partner_trust_fund")
    public String partner_trust_fund;
    @JsonProperty("Partner_maintenance_calc")
    public int partner_maintenance_calc;
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
    @JsonProperty("Partner_sign_fullname")
    public String partner_sign_fullname;
    @JsonProperty("Partner_confirm_read")
    public boolean partner_confirm_read;
    @JsonProperty("No_of_partner_accounts_calc")
    public int no_of_partner_accounts_calc;
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
    @JsonProperty("Partner_arc")
    public String partner_arc;
    @JsonProperty("Partner_employed")
    public String partner_employed;
    @JsonProperty("Why_partner_no_sign")
    public String why_partner_no_sign;
    @JsonProperty("Partner_child_benefit_calc")
    public int partner_child_benefit_calc;
    @JsonProperty("Partner_tax_credit_crm15")
    public double partner_tax_credit_crm15;
    @JsonProperty("Partner_maintenance_payment")
    public int partner_maintenance_payment;
    @JsonProperty("Partner_receive_pension")
    public String partner_receive_pension;
    @JsonProperty("Partner_wage_tax_calc")
    public int partner_wage_tax_calc;
    @JsonProperty("partner_self_employed_no_of_businesses")
    public int partner_self_employed_no_of_businesses;
    @JsonProperty("partner_business_partnerships_no_of")
    public int partner_business_partnerships_no_of;
    @JsonProperty("partner_private_companies_no_of")
    public int partner_private_companies_no_of;
    @JsonProperty("tax_liability")
    public double tax_liability;
    @JsonProperty("partner_tax_liability")
    public double partner_tax_liability;


    @JsonProperty("Subformfundingdecision_1-Overall_result_magsorcfs")
    public String subformfundingdecision_1_Overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_type")
    public String subformfundingdecision_3_Means_test_result_type;
    @JsonProperty("Subformfundingdecision_3-Overall_result_appealtocc")
    public String subformfundingdecision_3_Overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Overall_result_type")
    public String subformfundingdecision_2_Overall_result_type;
    @JsonProperty("Subformfundingdecision_5-Subformisvisible")
    public boolean subformfundingdecision_5_Subformisvisible;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_appealtocc")
    public String subformfundingdecision_1_Means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_1-Overall_result_non_means")
    public String subformfundingdecision_1_Overall_result_non_means;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_cc")
    public String subformfundingdecision_5_Means_test_result_cc;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_magsorcfs")
    public String subformfundingdecision_4_Means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_magsorcfs")
    public String subformfundingdecision_5_Means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_4-Overall_result_cc")
    public String subformfundingdecision_4_Overall_result_cc;
    @JsonProperty("Subformfundingdecision_5-Overall_result_magsorcfs")
    public String subformfundingdecision_5_Overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_type")
    public String subformfundingdecision_2_Means_test_result_type;
    @JsonProperty("Subformfundingdecision_3-Official_sign_fullname")
    public String subformfundingdecision_3_Official_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_magsorcfs")
    public String subformfundingdecision_1_Means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_4-Appropriate_officer_name")
    public String subformfundingdecision_4_Appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_3-Overall_result_cc")
    public String subformfundingdecision_3_Overall_result_cc;
    @JsonProperty("Subformfundingdecision_2-Overall_result_cc")
    public String subformfundingdecision_2_Overall_result_cc;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_cc")
    public String subformfundingdecision_1_Means_test_result_cc;
    @JsonProperty("Subformfundingdecision_1-Overall_result_cc")
    public String subformfundingdecision_1_Overall_result_cc;
    @JsonProperty("Subformfundingdecision_4-Overall_result_appealtocc")
    public String subformfundingdecision_4_Overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Justice_test_reasons")
    public String subformfundingdecision_2_Justice_test_reasons;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_appealtocc")
    public String subformfundingdecision_5_Means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_3-Justice_test_reasons")
    public String subformfundingdecision_3_Justice_test_reasons;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_magsorcfs")
    public String subformfundingdecision_3_Means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_4-Overall_result_type")
    public String subformfundingdecision_4_Overall_result_type;
    @JsonProperty("Subformfundingdecision_2-Overall_result_appealtocc")
    public String subformfundingdecision_2_Overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_cc")
    public String subformfundingdecision_4_Means_test_result_cc;
    @JsonProperty("Subformfundingdecision_2-Maat_number")
    public String subformfundingdecision_2_Maat_number;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_appealtocc")
    public String subformfundingdecision_4_Means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_4-Subformisvisible")
    public boolean subformfundingdecision_4_Subformisvisible;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_appealtocc")
    public String subformfundingdecision_2_Means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_3-Appropriate_officer_name")
    public String subformfundingdecision_3_Appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_4-Overall_result_non_means")
    public String subformfundingdecision_4_Overall_result_non_means;
    @JsonProperty("Subformfundingdecision_2-Overall_result_magsorcfs")
    public String subformfundingdecision_2_Overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_3-Case_number")
    public String subformfundingdecision_3_Case_number;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_appealtocc")
    public String subformfundingdecision_3_Means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Justice_test")
    public String subformfundingdecision_2_Justice_test;
    @JsonProperty("Subformfundingdecision_1-Subformisvisible")
    public boolean subformfundingdecision_1_Subformisvisible;
    @JsonProperty("Subformfundingdecision_2-Official_sign_fullname")
    public String subformfundingdecision_2_Official_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Justice_test")
    public String subformfundingdecision_1_Justice_test;
    @JsonProperty("Subformfundingdecision_3-Justice_test")
    public String subformfundingdecision_3_Justice_test;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_magsorcfs")
    public String subformfundingdecision_2_Means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_type")
    public String subformfundingdecision_5_Means_test_result_type;
    @JsonProperty("Subformfundingdecision_2-Appropriate_officer_name")
    public String subformfundingdecision_2_Appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_3-Overall_result_magsorcfs")
    public String subformfundingdecision_3_Overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_5-Maat_number")
    public String subformfundingdecision_5_Maat_number;
    @JsonProperty("Subformfundingdecision_3-Maat_number")
    public String subformfundingdecision_3_Maat_number;
    @JsonProperty("Subformfundingdecision_5-Overall_result_non_means")
    public String subformfundingdecision_5_Overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Maat_number")
    public String subformfundingdecision_1_Maat_number;
    @JsonProperty("Subformfundingdecision_5-Official_sign_fullname")
    public String subformfundingdecision_5_Official_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_type")
    public String subformfundingdecision_1_Means_test_result_type;
    @JsonProperty("Subformfundingdecision_2-Case_number")
    public String subformfundingdecision_2_Case_number;
    @JsonProperty("Subformfundingdecision_4-Case_number")
    public String subformfundingdecision_4_Case_number;
    @JsonProperty("Subformfundingdecision_4-Justice_test_reasons")
    public String subformfundingdecision_4_Justice_test_reasons;
    @JsonProperty("Subformfundingdecision_3-Subformisvisible")
    public boolean subformfundingdecision_3_Subformisvisible;
    @JsonProperty("Subformfundingdecision_3-Overall_result_non_means")
    public String subformfundingdecision_3_Overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Overall_result_appealtocc")
    public String subformfundingdecision_1_Overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_5-Justice_test_reasons")
    public String subformfundingdecision_5_Justice_test_reasons;
    @JsonProperty("Subformfundingdecision_1-Overall_result_type")
    public String subformfundingdecision_1_Overall_result_type;
    @JsonProperty("Subformfundingdecision_5-Justice_test")
    public String subformfundingdecision_5_Justice_test;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_type")
    public String subformfundingdecision_4_Means_test_result_type;
    @JsonProperty("Subformfundingdecision_1-Official_sign_fullname")
    public String subformfundingdecision_1_Official_sign_fullname;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_cc")
    public String subformfundingdecision_3_Means_test_result_cc;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_cc")
    public String subformfundingdecision_2_Means_test_result_cc;
    @JsonProperty("Subformfundingdecision_5-Overall_result_cc")
    public String subformfundingdecision_5_Overall_result_cc;
    @JsonProperty("Subformfundingdecision_5-Overall_result_appealtocc")
    public String subformfundingdecision_5_Overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Subformisvisible")
    public boolean subformfundingdecision_2_Subformisvisible;
    @JsonProperty("Subformfundingdecision_4-Justice_test")
    public String subformfundingdecision_4_Justice_test;
    @JsonProperty("Subformfundingdecision_1-Case_number")
    public String subformfundingdecision_1_Case_number;
    @JsonProperty("Subformfundingdecision_4-Maat_number")
    public String subformfundingdecision_4_Maat_number;
    @JsonProperty("Subformfundingdecision_5-Appropriate_officer_name")
    public String subformfundingdecision_5_Appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_3-Overall_result_type")
    public String subformfundingdecision_3_Overall_result_type;
    @JsonProperty("Subformfundingdecision_2-Overall_result_non_means")
    public String subformfundingdecision_2_Overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Appropriate_officer_name")
    public String subformfundingdecision_1_Appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_4-Overall_result_magsorcfs")
    public String subformfundingdecision_4_Overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_1-Justice_test_reasons")
    public String subformfundingdecision_1_Justice_test_reasons;
    @JsonProperty("Subformfundingdecision_5-Overall_result_type")
    public String subformfundingdecision_5_Overall_result_type;
    @JsonProperty("Subformfundingdecision_4-Official_sign_fullname")
    public String subformfundingdecision_4_Official_sign_fullname;
    @JsonProperty("Subformfundingdecision_5-Case_number")
    public String subformfundingdecision_5_Case_number;
    @JsonProperty("Employment_details")
    public Crm15EmployersModel employment_details;
    @JsonProperty("Partner_employment_details")
    public Crm15EmployersModel partner_employment_details;
    @JsonProperty("Business_details")
    public Crm15BusinessesModel business_details;
    @JsonProperty("Partner_business_details")
    public Crm15BusinessesModel partner_business_details;
    @JsonProperty("Land_and_property_table")
    public Crm15PropertyModel land_and_property_table;
    @JsonProperty("Savings_certificates")
    public Crm15SavingsCertificateModel savings_certificates;
    @JsonProperty("Car_reg_table")
    public Crm15CarRegModel car_reg_table;
    @JsonProperty("Investments_table")
    public Crm15InvestmentsModel investments_table;
    @JsonProperty("Bank_accounts")
    public Crm15BankAccountsModel bank_accounts;
    @JsonProperty("Tblnewattachments")
    public Crm15TblNewAttachmentsModel all_new_attachments;

    public List<Crm14AttachmentModel> processedAttachments;
}