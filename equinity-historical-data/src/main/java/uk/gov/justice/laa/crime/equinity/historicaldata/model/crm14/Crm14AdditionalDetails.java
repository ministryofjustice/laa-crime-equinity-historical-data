package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.crm15.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14AttachmentModel;

import java.util.Date;
import java.util.List;

/**
 * This class was created to contain all fields.
 * Lombok is not compiling if we put all fields in CRM14DetailsModel.
 */
@Data
@NoArgsConstructor
public class Crm14AdditionalDetails {

    @JsonProperty("Stocks")
    public Integer stocks;
    @JsonProperty("Partner_stocks")
    public Integer partner_stocks;
    @JsonProperty("Joint_stocks")
    public Integer joint_stocks;
    @JsonProperty("Shares")
    public Integer shares;
    @JsonProperty("Partner_shares")
    public Integer partner_shares;
    @JsonProperty("Joint_shares")
    public Integer joint_shares;
    @JsonProperty("Peps")
    public Integer peps;
    @JsonProperty("Partner_peps")
    public Integer partner_peps;
    @JsonProperty("Joint_peps")
    public Integer joint_peps;
    @JsonProperty("Share_isa")
    public Integer share_isa;
    @JsonProperty("Partner_share_isa")
    public Integer partner_share_isa;
    @JsonProperty("Joint_share_isa")
    public Integer joint_share_isa;
    @JsonProperty("Unit_trust")
    public Integer unit_trust;
    @JsonProperty("Partner_unit_trust")
    public Integer partner_unit_trust;
    @JsonProperty("Joint_unit_trust")
    public Integer joint_unit_trust;
    @JsonProperty("Investment_bonds")
    public Integer investment_bonds;
    @JsonProperty("Partner_investment_bonds")
    public Integer partner_investment_bonds;
    @JsonProperty("Joint_investment_bonds")
    public Integer joint_investment_bonds;
    @JsonProperty("Other_lump_sum")
    public Integer other_lump_sum;
    @JsonProperty("Partner_other_lump_sum")
    public Integer partner_other_lump_sum;
    @JsonProperty("Joint_other_lump_sum")
    public Integer joint_other_lump_sum;
    @JsonProperty("National_savings_certificates_total_value")
    public Double national_savings_certificates_total_value;
    @JsonProperty("Trust_fund_amount")
    public Double trust_fund_amount;
    @JsonProperty("Trust_fund_dividend")
    public Double trust_fund_dividend;
    @JsonProperty("Partner_Trust_fund_amount")
    public Double partner_Trust_fund_amount;
    @JsonProperty("Partner_Trust_fund_dividend")
    public Double partner_Trust_fund_dividend;
    @JsonProperty("Premium_bonds_holder_no")
    public Long premium_bonds_holder_no;
    @JsonProperty("Partner_premium_bonds_holder_no")
    public Long partner_premium_bonds_holder_no;
    @JsonProperty("Premium_bonds_total_value")
    public Double premium_bonds_total_value;
    @JsonProperty("Bank_accounts_crm15")
    public Integer bank_accounts_crm15;
    @JsonProperty("Partner_bank_accounts_crm15")
    public Integer partner_bank_accounts_crm15;
    @JsonProperty("Joint_bank_accounts_crm15")
    public Integer joint_bank_accounts_crm15;
    @JsonProperty("Building_society_account_crm15")
    public Integer building_society_account_crm15;
    @JsonProperty("Partner_building_society_account_crm15")
    public Integer partner_building_society_account_crm15;
    @JsonProperty("Joint_building_society_account_crm15")
    public Integer joint_building_society_account_crm15;
    @JsonProperty("Cash_isa_crm15")
    public Integer cash_isa_crm15;
    @JsonProperty("Partner_cash_isa_crm15")
    public Integer partner_cash_isa_crm15;
    @JsonProperty("Joint_cash_isa_crm15")
    public Integer joint_cash_isa_crm15;
    @JsonProperty("National_savings_crm15")
    public Integer national_savings_crm15;
    @JsonProperty("Partner_national_savings_crm15")
    public Integer partner_national_savings_crm15;
    @JsonProperty("Joint_national_savings_crm15")
    public Integer joint_national_savings_crm15;
    @JsonProperty("Other_cash_investments_crm15")
    public Integer other_cash_investments_crm15;
    @JsonProperty("Partner_other_cash_investments_crm15")
    public Integer partner_other_cash_investments_crm15;
    @JsonProperty("Joint_other_cash_investments_crm15")
    public Integer joint_other_cash_investments_crm15;
    @JsonProperty("Employers_crm15")
    public int employers_crm15;
    @JsonProperty("Partner_employer_crm15")
    public int partner_employer_crm15;
    @JsonProperty("Non_cash_benefit")
    public String non_cash_benefit;
    @JsonProperty("Partner_non_cash_benefit")
    public String partner_non_cash_benefit;
    @JsonProperty("Non_cash_benefit_value")
    public Double non_cash_benefit_value;
    @JsonProperty("Partner_non_cash_benefit_value")
    public Double partner_non_cash_benefit_value;
    @JsonProperty("State_pension_crm15")
    public Double state_pension_crm15 ;
    @JsonProperty("Partner_state_pension_crm15")
    public Double partner_state_pension_crm15 ;
    @JsonProperty("Partner_universal_credit_crm15")
    public Double partner_universal_credit_crm15 ;
    @JsonProperty("Incapacity_benefit_crm15")
    public Double incapacity_benefit_crm15 ;
    @JsonProperty("Partner_incapacity_benefit_crm15")
    public Double partner_incapacity_benefit_crm15 ;
    @JsonProperty("Disablement_benefit_crm15")
    public Double disablement_benefit_crm15 ;
    @JsonProperty("Partner_disablement_benefit_crm15")
    public Double partner_disablement_benefit_crm15 ;
    @JsonProperty("Jsa_crm15")
    public Double jsa_crm15 ;
    @JsonProperty("Partner_jsa_crm15")
    public Double partner_jsa_crm15 ;
    @JsonProperty("Other_benefits_crm15")
    public Double other_benefits_crm15 ;
    @JsonProperty("Partner_other_benefits_crm15")
    public Double partner_other_benefits_crm15 ;
    @JsonProperty("Total_pension")
    public Double total_pension ;
    @JsonProperty("Partner_total_pension")
    public Double partner_total_pension ;
    @JsonProperty("Year_1")
    public Integer year_1;
    @JsonProperty("Year_2_to_4")
    public Integer year_2_to_4;
    @JsonProperty("Year_5_to_7")
    public Integer year_5_to_7;
    @JsonProperty("Year_8_to_10")
    public Integer year_8_to_10;
    @JsonProperty("Year_11_to_12")
    public Integer year_11_to_12;
    @JsonProperty("Year_13_to_15")
    public Integer year_13_to_15;
    @JsonProperty("Year_16_to_18")
    public Integer year_16_to_18;
    @JsonProperty("Partner_receive_maintenance_payments")
    private String partner_receive_maintenance_payments;
    @JsonProperty("Partner_maintenance")
    public Double partner_maintenance;
    @JsonProperty("Partner_maintenance_payment_paid_every")
    private String partner_maintenance_payment_paid_every;
    @JsonProperty("Interest")
    public Double interest;
    @JsonProperty("Partner_receive_interest")
    private String partner_receive_interest;
    @JsonProperty("Partner_interest")
    public Double partner_interest;
    @JsonProperty("Partner_interest_paid_every")
    private String partner_interest_paid_every;
    @JsonProperty("Total_amount_received")
    public Double total_amount_received;
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
    public Double partner_total_amount_received;
    @JsonProperty("Partner_total_amount_received_every")
    private String partner_total_amount_received_every;

    @JsonProperty("Have_partner")
    public Integer have_partner;
    @JsonProperty("Partner_conflict_of_interest")
    public Integer partner_conflict_of_interest;
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
    public String partner_date_of_birth;
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
    public Integer partner_maintenance_payment_calc;
    @JsonProperty("Partner_calc")
    public Integer partner_calc;
    @JsonProperty("Partner_other_income_checkbox_count")
    public Integer partner_other_income_checkbox_count;
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
    public Double partner_child_benefit_crm15;
    @JsonProperty("Partner_paid_40_percent_tax")
    public String partner_paid_40_percent_tax;
    @JsonProperty("Partner_other_income_source")
    public String partner_other_income_source;
    @JsonProperty("Partner_total_pension_paid_every")
    public Integer partner_total_pension_paid_every;
    @JsonProperty("Partner_tax_credit_crm15_paid_every")
    public String partner_tax_credit_crm15_paid_every;
    @JsonProperty("Partner_pensions_calc")
    public Integer partner_pensions_calc;
    @JsonProperty("Partner_trust_fund")
    public String partner_trust_fund;
    @JsonProperty("Partner_maintenance_calc")
    public Integer partner_maintenance_calc;
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
    public Integer land_calc_partner;
    @JsonProperty("Partner_involved_in_case")
    public String partner_involved_in_case;
    @JsonProperty("Jsa_partner")
    public boolean jsa_partner;
    @JsonProperty("Partner_sign_date")
    public Date partner_sign_date;
    @JsonProperty("Partner_freezing_order_crm15")
    public String partner_freezing_order_crm15;
    @JsonProperty("Partner_title_other")
    public String partner_title_other;
    @JsonProperty("Have_partner_copy")
    public Integer have_partner_copy;
    @JsonProperty("Partner_business_calc")
    public Integer partner_business_calc;
    @JsonProperty("Partner_usual_address_1")
    public String partner_usual_address_1;
    @JsonProperty("Partner_usual_address_2")
    public String partner_usual_address_2;
    @JsonProperty("Partner_tax_liability_paid_every")
    public String partner_tax_liability_paid_every;
    @JsonProperty("Partner_usual_address_3")
    public String partner_usual_address_3;
    @JsonProperty("Partner_other_benefits_calc")
    public Integer partner_other_benefits_calc;
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
    public Integer partner_total_pension_calc;
    @JsonProperty("Partner_premium_bonds")
    public String partner_premium_bonds;
    @JsonProperty("Partner_involvement")
    public String partner_involvement;
    @JsonProperty("Partner_income_maintenance")
    public boolean partner_income_maintenance;
    @JsonProperty("Partner_wage_calc")
    public Integer partner_wage_calc;
    @JsonProperty("Partner_income_from_other_source_calc")
    public Integer partner_income_from_other_source_calc;
    @JsonProperty("Partner_income_student_grant")
    public boolean partner_income_student_grant;
    @JsonProperty("Partner_title")
    public String partner_title;
    @JsonProperty("Dwp_check_partner_surname")
    public String dwp_check_partner_surname;
    @JsonProperty("Partner_incapacity_benefit_crm15_paid_every")
    public String partner_incapacity_benefit_crm15_paid_every;
    @JsonProperty("Partner_tax_credits_calc")
    public Integer partner_tax_credits_calc;
    @JsonProperty("Partner_needed_calc")
    public Integer partner_needed_calc;
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
    public Integer no_of_partner_accounts_calc;
    @JsonProperty("Partner_other_case_court")
    public String partner_other_case_court;
    @JsonProperty("Partner_own_property")
    public String partner_own_property;
    @JsonProperty("Partner_salary_1")
    public boolean partner_salary_1;
    @JsonProperty("Partner_other_income_calc")
    public Integer partner_other_income_calc;
    @JsonProperty("Partner_private_company")
    public String partner_private_company;
    @JsonProperty("Partner_income_friends_family")
    public boolean partner_income_friends_family;
    @JsonProperty("Partner_universal_credit_calc")
    public Integer partner_universal_credit_calc;
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
    public Integer partner_child_benefit_calc;
    @JsonProperty("Partner_tax_credit_crm15")
    public Double partner_tax_credit_crm15;
    @JsonProperty("Partner_maintenance_payment")
    public Integer partner_maintenance_payment;
    @JsonProperty("Partner_receive_pension")
    public String partner_receive_pension;
    @JsonProperty("Partner_wage_tax_calc")
    public Integer partner_wage_tax_calc;
    @JsonProperty("Partner_self_employed_no_of_businesses")
    public Integer partner_self_employed_no_of_businesses;
    @JsonProperty("Partner_business_partnerships_no_of")
    public Integer partner_business_partnerships_no_of;
    @JsonProperty("Partner_private_companies_no_of")
    public Integer partner_private_companies_no_of;
    @JsonProperty("Partner_wage_amount")
    public float partner_wage_amount;
    @JsonProperty("Partner_child_benefit")
    public float partner_child_benefit;
    @JsonProperty("Partner_tax_credits_amount")
    public float partner_tax_credits_amount;
    @JsonProperty("Partner_universal_credit")
    public float partner_universal_credit;
    @JsonProperty("Partner_other_benefits")
    public float partner_other_benefits;
    @JsonProperty("Partner_pensions")
    public float partner_pensions;
    @JsonProperty("Partner_other_income")
    public float partner_other_income;
    @JsonProperty("Subformfundingdecision_1-Overall_result_magsorcfs")
    public String subformfundingdecision_1_overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_type")
    public String subformfundingdecision_3_means_test_result_type;
    @JsonProperty("Subformfundingdecision_3-Overall_result_appealtocc")
    public String subformfundingdecision_3_overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Overall_result_type")
    public String subformfundingdecision_2_overall_result_type;
    @JsonProperty("Subformfundingdecision_5-Subformisvisible")
    public boolean subformfundingdecision_5_subformisvisible;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_appealtocc")
    public String subformfundingdecision_1_means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_1-Overall_result_non_means")
    public String subformfundingdecision_1_overall_result_non_means;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_cc")
    public String subformfundingdecision_5_means_test_result_cc;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_magsorcfs")
    public String subformfundingdecision_4_means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_magsorcfs")
    public String subformfundingdecision_5_means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_4-Overall_result_cc")
    public String subformfundingdecision_4_overall_result_cc;
    @JsonProperty("Subformfundingdecision_5-Overall_result_magsorcfs")
    public String subformfundingdecision_5_overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_type")
    public String subformfundingdecision_2_means_test_result_type;
    @JsonProperty("Subformfundingdecision_3-Official_sign_fullname")
    public String subformfundingdecision_3_official_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_magsorcfs")
    public String subformfundingdecision_1_means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_4-Appropriate_officer_name")
    public String subformfundingdecision_4_appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_3-Overall_result_cc")
    public String subformfundingdecision_3_overall_result_cc;
    @JsonProperty("Subformfundingdecision_2-Overall_result_cc")
    public String subformfundingdecision_2_overall_result_cc;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_cc")
    public String subformfundingdecision_1_means_test_result_cc;
    @JsonProperty("Subformfundingdecision_1-Overall_result_cc")
    public String subformfundingdecision_1_overall_result_cc;
    @JsonProperty("Subformfundingdecision_4-Overall_result_appealtocc")
    public String subformfundingdecision_4_overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Justice_test_reasons")
    public String subformfundingdecision_2_justice_test_reasons;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_appealtocc")
    public String subformfundingdecision_5_means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_3-Justice_test_reasons")
    public String subformfundingdecision_3_justice_test_reasons;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_magsorcfs")
    public String subformfundingdecision_3_means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_4-Overall_result_type")
    public String subformfundingdecision_4_overall_result_type;
    @JsonProperty("Subformfundingdecision_2-Overall_result_appealtocc")
    public String subformfundingdecision_2_overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_cc")
    public String subformfundingdecision_4_means_test_result_cc;
    @JsonProperty("Subformfundingdecision_2-Maat_number")
    public String subformfundingdecision_2_maat_number;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_appealtocc")
    public String subformfundingdecision_4_means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_4-Subformisvisible")
    public boolean subformfundingdecision_4_subformisvisible;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_appealtocc")
    public String subformfundingdecision_2_means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_3-Appropriate_officer_name")
    public String subformfundingdecision_3_appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_4-Overall_result_non_means")
    public String subformfundingdecision_4_overall_result_non_means;
    @JsonProperty("Subformfundingdecision_2-Overall_result_magsorcfs")
    public String subformfundingdecision_2_overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_3-Case_number")
    public String subformfundingdecision_3_case_number;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_appealtocc")
    public String subformfundingdecision_3_means_test_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Justice_test")
    public String subformfundingdecision_2_justice_test;
    @JsonProperty("Subformfundingdecision_1-Subformisvisible")
    public boolean subformfundingdecision_1_subformisvisible;
    @JsonProperty("Subformfundingdecision_2-Official_sign_fullname")
    public String subformfundingdecision_2_official_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Justice_test")
    public String subformfundingdecision_1_justice_test;
    @JsonProperty("Subformfundingdecision_3-Justice_test")
    public String subformfundingdecision_3_justice_test;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_magsorcfs")
    public String subformfundingdecision_2_means_test_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_type")
    public String subformfundingdecision_5_means_test_result_type;
    @JsonProperty("Subformfundingdecision_2-Appropriate_officer_name")
    public String subformfundingdecision_2_appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_3-Overall_result_magsorcfs")
    public String subformfundingdecision_3_overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_5-Maat_number")
    public String subformfundingdecision_5_maat_number;
    @JsonProperty("Subformfundingdecision_3-Maat_number")
    public String subformfundingdecision_3_maat_number;
    @JsonProperty("Subformfundingdecision_5-Overall_result_non_means")
    public String subformfundingdecision_5_overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Maat_number")
    public String subformfundingdecision_1_maat_number;
    @JsonProperty("Subformfundingdecision_5-Official_sign_fullname")
    public String subformfundingdecision_5_official_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_type")
    public String subformfundingdecision_1_means_test_result_type;
    @JsonProperty("Subformfundingdecision_2-Case_number")
    public String subformfundingdecision_2_case_number;
    @JsonProperty("Subformfundingdecision_4-Case_number")
    public String subformfundingdecision_4_case_number;
    @JsonProperty("Subformfundingdecision_4-Justice_test_reasons")
    public String subformfundingdecision_4_justice_test_reasons;
    @JsonProperty("Subformfundingdecision_3-Subformisvisible")
    public boolean subformfundingdecision_3_subformisvisible;
    @JsonProperty("Subformfundingdecision_3-Overall_result_non_means")
    public String subformfundingdecision_3_overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Overall_result_appealtocc")
    public String subformfundingdecision_1_overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_5-Justice_test_reasons")
    public String subformfundingdecision_5_justice_test_reasons;
    @JsonProperty("Subformfundingdecision_1-Overall_result_type")
    public String subformfundingdecision_1_overall_result_type;
    @JsonProperty("Subformfundingdecision_5-Justice_test")
    public String subformfundingdecision_5_justice_test;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_type")
    public String subformfundingdecision_4_means_test_result_type;
    @JsonProperty("Subformfundingdecision_1-Official_sign_fullname")
    public String subformfundingdecision_1_official_sign_fullname;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_cc")
    public String subformfundingdecision_3_means_test_result_cc;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_cc")
    public String subformfundingdecision_2_means_test_result_cc;
    @JsonProperty("Subformfundingdecision_5-Overall_result_cc")
    public String subformfundingdecision_5_overall_result_cc;
    @JsonProperty("Subformfundingdecision_5-Overall_result_appealtocc")
    public String subformfundingdecision_5_overall_result_appealtocc;
    @JsonProperty("Subformfundingdecision_2-Subformisvisible")
    public boolean subformfundingdecision_2_subformisvisible;
    @JsonProperty("Subformfundingdecision_4-Justice_test")
    public String subformfundingdecision_4_justice_test;
    @JsonProperty("Subformfundingdecision_1-Case_number")
    public String subformfundingdecision_1_case_number;
    @JsonProperty("Subformfundingdecision_1-Appropriate_officer_sign_date")
    public Date subformfundingdecision_1_appropriate_officer_sign_date;
    @JsonProperty("Subformfundingdecision_2-Appropriate_officer_sign_date")
    public Date subformfundingdecision_2_appropriate_officer_sign_date;
    @JsonProperty("Subformfundingdecision_3-Appropriate_officer_sign_date")
    public Date subformfundingdecision_3_appropriate_officer_sign_date;
    @JsonProperty("Subformfundingdecision_4-Appropriate_officer_sign_date")
    public Date subformfundingdecision_4_appropriate_officer_sign_date;
    @JsonProperty("Subformfundingdecision_5-Appropriate_officer_sign_date")
    public Date subformfundingdecision_5_appropriate_officer_sign_date;
    @JsonProperty("Subformfundingdecision_1-Official_sign_date")
    public Date subformfundingdecision_1_official_sign_date;
    @JsonProperty("Subformfundingdecision_2-Official_sign_date")
    public Date subformfundingdecision_2_official_sign_date;
    @JsonProperty("Subformfundingdecision_3-Official_sign_date")
    public Date subformfundingdecision_3_official_sign_date;
    @JsonProperty("Subformfundingdecision_4-Official_sign_date")
    public Date subformfundingdecision_4_official_sign_date;
    @JsonProperty("Subformfundingdecision_5-Official_sign_date")
    public Date subformfundingdecision_5_official_sign_date;
    @JsonProperty("Subformfundingdecision_4-Maat_number")
    public String subformfundingdecision_4_maat_number;
    @JsonProperty("Subformfundingdecision_5-Appropriate_officer_name")
    public String subformfundingdecision_5_appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_3-Overall_result_type")
    public String subformfundingdecision_3_overall_result_type;
    @JsonProperty("Subformfundingdecision_2-Overall_result_non_means")
    public String subformfundingdecision_2_overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Appropriate_officer_name")
    public String subformfundingdecision_1_appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_4-Overall_result_magsorcfs")
    public String subformfundingdecision_4_overall_result_magsorcfs;
    @JsonProperty("Subformfundingdecision_1-Justice_test_reasons")
    public String subformfundingdecision_1_justice_test_reasons;
    @JsonProperty("Subformfundingdecision_5-Overall_result_type")
    public String subformfundingdecision_5_overall_result_type;
    @JsonProperty("Subformfundingdecision_4-Official_sign_fullname")
    public String subformfundingdecision_4_official_sign_fullname;
    @JsonProperty("Subformfundingdecision_5-Case_number")
    public String subformfundingdecision_5_case_number;
    @JsonProperty("Injection_count")
    public Integer injection_count;
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

    @JsonProperty("Messagehistory")
    public Crm14MessageHistoryModel messagehistory;

}