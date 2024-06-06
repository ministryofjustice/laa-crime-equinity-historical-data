package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Crm14DetailsExtendedModel{

    @JsonProperty("Bangladeshi")
    public boolean bangladeshi;
    @JsonProperty("Originator_tl_facebookid")
    public String originator_tl_facebookid;
    @JsonProperty("Originator_lscareaoffice")
    public String originator_lscareaoffice;
    @JsonProperty("Dwp_check_partner_surname")
    public String dwp_check_partner_surname;
    @JsonProperty("Pensions_paid_every")
    public String pensions_paid_every;
    @JsonProperty("Provider_sign")
    public String provider_sign;
    @JsonProperty("Legal_aid_contribution_paid_every")
    public String legal_aid_contribution_paid_every;
    @JsonProperty("Partner_family_rent")
    public boolean partner_family_rent;
    @JsonProperty("Partner_title")
    public String partner_title;
    @JsonProperty("Cant_present_own_case_details")
    public String cant_present_own_case_details;
    @JsonProperty("Universal_credit_calc")
    public int universal_credit_calc;
    @JsonProperty("Black_caribbean")
    public boolean black_caribbean;
    @JsonProperty("Partner_incapacity_benefit_crm15_paid_every")
    public String partner_incapacity_benefit_crm15_paid_every;
    @JsonProperty("British")
    public boolean british;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_type")
    public String subformfundingdecision_1_Means_test_result_type;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Partner_tax_credits_calc")
    public int partner_tax_credits_calc;
    @JsonProperty("Solicitor_title")
    public String solicitor_title;
    @JsonProperty("Originator_lscemailaddress")
    public String originator_lscemailaddress;
    @JsonProperty("Partner_needed_calc")
    public int partner_needed_calc;
    @JsonProperty("Contact_address_1")
    public String contact_address_1;
    @JsonProperty("Other_income_rent_from_family")
    public boolean other_income_rent_from_family;
    @JsonProperty("Subformfundingdecision_2-Case_number")
    public String subformfundingdecision_2_Case_number;
    @JsonProperty("Tlsubstate")
    public String tlsubstate;
    @JsonProperty("Income_support")
    public boolean income_support;
    @JsonProperty("Fc_submission_count")
    public int fc_submission_count;
    @JsonProperty("Fc_testing")
    public boolean fc_testing;
    @JsonProperty("Subformfundingdecision_4-Case_number")
    public String subformfundingdecision_4_Case_number;
    @JsonProperty("Contact_address_2")
    public String contact_address_2;
    @JsonProperty("Contact_address_3")
    public String contact_address_3;
    @JsonProperty("Partner_other_names")
    public String partner_other_names;
    @JsonProperty("Maintenance_payment")
    public int maintenance_payment;
    @JsonProperty("Proceedings_already_concluded_notes")
    public String proceedings_already_concluded_notes;
    @JsonProperty("Subformfundingdecision_4-Justice_test_reasons")
    public String subformfundingdecision_4_Justice_test_reasons;
    @JsonProperty("Provider_uniquename")
    public String provider_uniquename;
    @JsonProperty("Subformfundingdecision_3-Subformisvisible")
    public boolean subformfundingdecision_3_Subformisvisible;
    @JsonProperty("Partner_other_income_source_freetext")
    public String partner_other_income_source_freetext;
    @JsonProperty("Dwp_check_partner_dateofaward")
    public Date dwp_check_partner_dateofaward;
    @JsonProperty("Wage_calc")
    public int wage_calc;
    @JsonProperty("Partner_other_benefits_paid_every")
    public String partner_other_benefits_paid_every;
    @JsonProperty("Partner_usual_address_search")
    public String partner_usual_address_search;
    @JsonProperty("Who_dwp_checked")
    public String who_dwp_checked;
    @JsonProperty("Household_outgoings_exceed_income_how")
    public String household_outgoings_exceed_income_how;
    @JsonProperty("Subformfundingdecision_3-Overall_result_non_means")
    public String subformfundingdecision_3_Overall_result_non_means;
    @JsonProperty("Partner_maintenance_paid_every")
    public String partner_maintenance_paid_every;
    @JsonProperty("Originator_tl_googleid")
    public String originator_tl_googleid;
    @JsonProperty("Return_reason")
    public String return_reason;
    @JsonProperty("National_savings_certificates")
    public String national_savings_certificates;
    @JsonProperty("Age_calc")
    public int age_calc;
    @JsonProperty("Partner_wage_tax")
    public String partner_wage_tax;
    @JsonProperty("Transitioned_from_offline")
    public boolean transitioned_from_offline;
    @JsonProperty("Solicitor_fax")
    public String solicitor_fax;
    @JsonProperty("Offline")
    public boolean offline;
    @JsonProperty("Council_tax_calc")
    public int council_tax_calc;
    @JsonProperty("Trust_fund")
    public String trust_fund;
    @JsonProperty("Other_ethnicity")
    public boolean other_ethnicity;
    @JsonProperty("Maintenance_calc")
    public int maintenance_calc;
    @JsonProperty("Hearing_date_imminent")
    public boolean hearing_date_imminent;
    @JsonProperty("Own_car")
    public String own_car;
    @JsonProperty("Originator_type")
    public int originator_type;
    @JsonProperty("Subformfundingdecision_1-Overall_result_appealtocc")
    public String subformfundingdecision_1_Overall_result_appealtocc;
    @JsonProperty("Non_cash_benefit")
    public String non_cash_benefit;
    @JsonProperty("Partner_receive_interest")
    public String partner_receive_interest;
    @JsonProperty("Fc_eligible_for_staging_inject")
    public boolean fc_eligible_for_staging_inject;
    @JsonProperty("Subformfundingdecision_5-Justice_test_reasons")
    public String subformfundingdecision_5_Justice_test_reasons;
    @JsonProperty("Privacy_agree")
    public boolean privacy_agree;
    @JsonProperty("Witness_trace_details")
    public String witness_trace_details;
    @JsonProperty("Partner_sign_fullname")
    public String partner_sign_fullname;
    @JsonProperty("Subformfundingdecision_1-Overall_result_type")
    public String subformfundingdecision_1_Overall_result_type;
    @JsonProperty("Subformfundingdecision_5-Justice_test")
    public String subformfundingdecision_5_Justice_test;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_type")
    public String subformfundingdecision_4_Means_test_result_type;
    @JsonProperty("Fc_current_user_type")
    public String fc_current_user_type;
    @JsonProperty("How_pay_bills_crm15")
    public String how_pay_bills_crm15;
    @JsonProperty("Subformfundingdecision_1-Official_sign_fullname")
    public String subformfundingdecision_1_Official_sign_fullname;
    @JsonProperty("Fc_currentstage")
    public String fc_currentstage;
    @JsonProperty("Salary_account")
    public String salary_account;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_cc")
    public String subformfundingdecision_3_Means_test_result_cc;
    @JsonProperty("Originator_dtlastaccessed")
    public Date originator_dtlastaccessed;
    @JsonProperty("Disablement_benefit_crm15_paid_every")
    public String disablement_benefit_crm15_paid_every;
    @JsonProperty("Receive_maintenance_payments")
    public String receive_maintenance_payments;
    @JsonProperty("TLActionsOnLine")
    public String tLActionsOnLine;
    @JsonProperty("Originator_tl_qn_answer_hash")
    public String originator_tl_qn_answer_hash;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_cc")
    public String subformfundingdecision_2_Means_test_result_cc;
    @JsonProperty("Originator_displayname")
    public String originator_displayname;
    @JsonProperty("Fc_maat_id_injected")
    public boolean fc_maat_id_injected;
    @JsonProperty("Partner_confirm_read")
    public boolean partner_confirm_read;
    @JsonProperty("How_pay_bills_checkbox_count")
    public int how_pay_bills_checkbox_count;
    @JsonProperty("How_pay_bills_custody")
    public boolean how_pay_bills_custody;
    @JsonProperty("Mortgage_rent_paid_every")
    public String mortgage_rent_paid_every;
    @JsonProperty("Suspended_sentence")
    public boolean suspended_sentence;
    @JsonProperty("Damage_reputation")
    public boolean damage_reputation;
    @JsonProperty("No_of_partner_accounts_calc")
    public int no_of_partner_accounts_calc;
    @JsonProperty("Any_codefendants")
    public String any_codefendants;
    @JsonProperty("Solicitor_title_other")
    public String solicitor_title_other;
    @JsonProperty("Other_names")
    public String other_names;
    @JsonProperty("Partner_non_cash_benefit")
    public String partner_non_cash_benefit;
    @JsonProperty("Subformfundingdecision_5-Overall_result_cc")
    public String subformfundingdecision_5_Overall_result_cc;
    @JsonProperty("Partner_other_case_court")
    public String partner_other_case_court;
    @JsonProperty("Partner_own_property")
    public String partner_own_property;
    @JsonProperty("Subformfundingdecision_5-Overall_result_appealtocc")
    public String subformfundingdecision_5_Overall_result_appealtocc;
    @JsonProperty("Partner_salary_1")
    public boolean partner_salary_1;
    @JsonProperty("Offence_type")
    public String offence_type;
    @JsonProperty("Fc_route_to_nct_direct_queue")
    public boolean fc_route_to_nct_direct_queue;
    @JsonProperty("Chinese")
    public boolean chinese;
    @JsonProperty("Tltaskid")
    public int tltaskid;
    @JsonProperty("Tltaskstate")
    public int tltaskstate;
    @JsonProperty("Partner_other_income_calc")
    public int partner_other_income_calc;
    @JsonProperty("Remanded_in_custody_date")
    public Date remanded_in_custody_date;
    @JsonProperty("Partner_universal_credit_calc")
    public int partner_universal_credit_calc;
    @JsonProperty("Custody")
    public boolean custody;
    @JsonProperty("No_of_joint_accounts_calc")
    public int no_of_joint_accounts_calc;
    @JsonProperty("Subformfundingdecision_2-Subformisvisible")
    public boolean subformfundingdecision_2_Subformisvisible;
    @JsonProperty("Subformfundingdecision_4-Justice_test")
    public String subformfundingdecision_4_Justice_test;
    @JsonProperty("Question_of_law")
    public boolean question_of_law;
    @JsonProperty("Savings_or_investments")
    public String savings_or_investments;
    @JsonProperty("Subformfundingdecision_1-Case_number")
    public String subformfundingdecision_1_Case_number;
    @JsonProperty("Phone_mobile")
    public String phone_mobile;
    @JsonProperty("Other_income_paid_every")
    public String other_income_paid_every;
    @JsonProperty("No_of_accounts_calc")
    public int no_of_accounts_calc;
    @JsonProperty("Land_calc")
    public int land_calc;
    @JsonProperty("Partner_private_company")
    public String partner_private_company;
    @JsonProperty("Universal_credit_crm15")
    public int universal_credit_crm15;
    @JsonProperty("Relationship_to_home_owner")
    public String relationship_to_home_owner;
    @JsonProperty("Partner_income_friends_family")
    public boolean partner_income_friends_family;
    @JsonProperty("Print_signed")
    public boolean print_signed;
    @JsonProperty("Total_pension_calc")
    public int total_pension_calc;
    @JsonProperty("Partner_other_benefits_crm15_paid_every")
    public String partner_other_benefits_crm15_paid_every;
    @JsonProperty("How_pay_bills_friend_sofa")
    public boolean how_pay_bills_friend_sofa;
    @JsonProperty("Originator_uniquename")
    public String originator_uniquename;
    @JsonProperty("Priority")
    public String priority;
    @JsonProperty("Subformfundingdecision_4-Maat_number")
    public String subformfundingdecision_4_Maat_number;
    @JsonProperty("Youth")
    public boolean youth;
    @JsonProperty("Partner_other_income_crm15")
    public String partner_other_income_crm15;
    @JsonProperty("Originator_id")
    public int originator_id;
    @JsonProperty("Partner_other_income_paid_every")
    public String partner_other_income_paid_every;
    @JsonProperty("Partner_total_amount_received_every")
    public String partner_total_amount_received_every;
    @JsonProperty("Lose_liberty_details")
    public String lose_liberty_details;
    @JsonProperty("Business_calc_2")
    public int business_calc_2;
    @JsonProperty("Legal_rep_sign")
    public String legal_rep_sign;
    @JsonProperty("White_other")
    public boolean white_other;
    @JsonProperty("Subformfundingdecision_5-Appropriate_officer_name")
    public String subformfundingdecision_5_Appropriate_officer_name;
    @JsonProperty("Court_originating_display")
    public String court_originating_display;
    @JsonProperty("Partner_arc")
    public String partner_arc;
    @JsonProperty("Do_you_have_proof")
    public String do_you_have_proof;
    @JsonProperty("Subformfundingdecision_3-Overall_result_type")
    public String subformfundingdecision_3_Overall_result_type;
    @JsonProperty("Other_benefits_calc")
    public int other_benefits_calc;
    @JsonProperty("Subformfundingdecision_2-Overall_result_non_means")
    public String subformfundingdecision_2_Overall_result_non_means;
    @JsonProperty("Partner_employed")
    public String partner_employed;
    @JsonProperty("Vulnerable")
    public boolean vulnerable;
    @JsonProperty("Subformfundingdecision_1-Appropriate_officer_name")
    public String subformfundingdecision_1_Appropriate_officer_name;
    @JsonProperty("Maintenance_amount_calc")
    public int maintenance_amount_calc;
    @JsonProperty("Subformfundingdecision_4-Overall_result_magsorcfs")
    public String subformfundingdecision_4_Overall_result_magsorcfs;
    @JsonProperty("Originator_firm")
    public String originator_firm;
    @JsonProperty("Firm_administrator_email")
    public String firm_administrator_email;
    @JsonProperty("Trial_in_crown_court")
    public boolean trial_in_crown_court;
    @JsonProperty("Means_tested")
    public String means_tested;
    @JsonProperty("White_and_black_caribbean")
    public boolean white_and_black_caribbean;
    @JsonProperty("Subformfundingdecision_1-Justice_test_reasons")
    public String subformfundingdecision_1_Justice_test_reasons;
    @JsonProperty("Have_partner")
    public int have_partner;
    @JsonProperty("Fc_caseworker_completing_funding_decision_manually")
    public boolean fc_caseworker_completing_funding_decision_manually;
    @JsonProperty("What_contact_address")
    public String what_contact_address;
    @JsonProperty("Pensions_calc")
    public int pensions_calc;
    @JsonProperty("Other_income_financial_support")
    public boolean other_income_financial_support;
    @JsonProperty("Subformfundingdecision_5-Overall_result_type")
    public String subformfundingdecision_5_Overall_result_type;
    @JsonProperty("Originator__dtadded")
    public Date originator__dtadded;
    @JsonProperty("Fc_eligible_for_return_inject")
    public boolean fc_eligible_for_return_inject;
    @JsonProperty("Why_partner_no_sign")
    public String why_partner_no_sign;
    @JsonProperty("Partner_child_benefit_calc")
    public int partner_child_benefit_calc;
    @JsonProperty("Case_type_calc")
    public int case_type_calc;
    @JsonProperty("Partner_tax_credit_crm15")
    public int partner_tax_credit_crm15;
    @JsonProperty("Partner_maintenance_payment")
    public int partner_maintenance_payment;
    @JsonProperty("Subformfundingdecision_4-Official_sign_fullname")
    public String subformfundingdecision_4_Official_sign_fullname;
    @JsonProperty("Other_income_calc")
    public int other_income_calc;
    @JsonProperty("Employment_ceased_3_months")
    public int employment_ceased_3_months;
    @JsonProperty("Partner_receive_pension")
    public String partner_receive_pension;
    @JsonProperty("Summary")
    public boolean summary;
    @JsonProperty("Other_benefits_crm15_details")
    public String other_benefits_crm15_details;
    @JsonProperty("Partner_wage_tax_calc")
    public int partner_wage_tax_calc;
    @JsonProperty("Subformfundingdecision_5-Case_number")
    public String subformfundingdecision_5_Case_number;

}