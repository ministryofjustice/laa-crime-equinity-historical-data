package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Crm14DetailsModel extends Crm14DetailsExtendedModel implements CrmFileDetailsModelInterface {
    @JsonProperty("Esa_partner")
    public boolean esa_partner;
    @JsonProperty("Solicitor_phone_landline")
    public String solicitor_phone_landline;
    @JsonProperty("Partner_surname")
    public String partner_surname;
    @JsonProperty("Other_reason_to_be_represented")
    public boolean other_reason_to_be_represented;
    @JsonProperty("Total_file_size_bytes")
    public int total_file_size_bytes;
    @JsonProperty("Salary_1")
    public boolean salary_1;
    @JsonProperty("Gender")
    public String gender;
    @JsonProperty("Hearing_date")
    public Date hearing_date;
    @JsonProperty("Use_maat_inject")
    public boolean use_maat_inject;
    @JsonProperty("Reason_calc")
    public int reason_calc;
    @JsonProperty("Why_not_rep_same_solicitor_reasons")
    public String why_not_rep_same_solicitor_reasons;
    @JsonProperty("Under_18")
    public String under_18;
    @JsonProperty("Proceedings_already_concluded")
    public String proceedings_already_concluded;
    @JsonProperty("Fc_eligible_for_assessment_result_inject")
    public boolean fc_eligible_for_assessment_result_inject;
    @JsonProperty("Jsa")
    public boolean jsa;
    @JsonProperty("Originator_tl_passwordneverexpires")
    public int originator_tl_passwordneverexpires;
    @JsonProperty("Originator_tl_qn_answer_salt")
    public String originator_tl_qn_answer_salt;
    @JsonProperty("Subformfundingdecision_1-Overall_result_magsorcfs")
    public String subformfundingdecision_1_Overall_result_magsorcfs;
    @JsonProperty("Originator_tl_permanentlyremoved")
    public int originator_tl_permanentlyremoved;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_type")
    public String subformfundingdecision_3_Means_test_result_type;
    @JsonProperty("Other_case_court")
    public String other_case_court;
    @JsonProperty("Tax_credits_calc")
    public int tax_credits_calc;
    @JsonProperty("Datestamp_time")
    public String datestamp_time;
    @JsonProperty("Title_other")
    public String title_other;
    @JsonProperty("Lost_job_due_to_custody_date")
    public Date lost_job_due_to_custody_date;
    @JsonProperty("Childcare_costs")
    public String childcare_costs;
    @JsonProperty("Hide_crm15")
    public boolean hide_crm15;
    @JsonProperty("Employed")
    public int employed;
    @JsonProperty("Tlmessage")
    public String tlmessage;
    @JsonProperty("Child_benefit_crm15_paid_every")
    public String child_benefit_crm15_paid_every;
    @JsonProperty("Fc_non_means_tested")
    public boolean fc_non_means_tested;
    @JsonProperty("Originator_tl_usertype")
    public int originator_tl_usertype;
    @JsonProperty("Student_loan")
    public boolean student_loan;
    @JsonProperty("TLProjectUniqueName")
    public String tLProjectUniqueName;
    @JsonProperty("Partner_income_from_other_sources")
    public String partner_income_from_other_sources;
    @JsonProperty("Partner_ni")
    public String partner_ni;
    @JsonProperty("Partner_self_assessment_tax_received")
    public String partner_self_assessment_tax_received;
    @JsonProperty("Tltasktype")
    public int tltasktype;
    @JsonProperty("Witness_trace")
    public boolean witness_trace;
    @JsonProperty("Partner_date_of_birth")
    public Date partner_date_of_birth;
    @JsonProperty("Tlautonumber")
    public int tlautonumber;
    @JsonProperty("Subformfundingdecision_3-Overall_result_appealtocc")
    public String subformfundingdecision_3_Overall_result_appealtocc;
    @JsonProperty("Charged_with_adult")
    public String charged_with_adult;
    @JsonProperty("Dwp_check_result")
    public String dwp_check_result;
    @JsonProperty("Subformfundingdecision_2-Overall_result_type")
    public String subformfundingdecision_2_Overall_result_type;
    @JsonProperty("Legal_rep_declaration")
    public String legal_rep_declaration;
    @JsonProperty("Black_african")
    public boolean black_african;
    @JsonProperty("Subformfundingdecision_5-Subformisvisible")
    public boolean subformfundingdecision_5_Subformisvisible;
    @JsonProperty("How_pay_bills_family")
    public boolean how_pay_bills_family;
    @JsonProperty("TLRecoveryLoad")
    public boolean tLRecoveryLoad;
    @JsonProperty("Gypsy_or_traveller")
    public boolean gypsy_or_traveller;
    @JsonProperty("Court_name")
    public String court_name;
    @JsonProperty("Heard_in_magistrates_court")
    public int heard_in_magistrates_court;
    @JsonProperty("Other_income_maintenance")
    public boolean other_income_maintenance;
    @JsonProperty("Legal_aid_contribution_ref")
    public String legal_aid_contribution_ref;
    @JsonProperty("White_and_asian")
    public boolean white_and_asian;
    @JsonProperty("Remanded_in_custody")
    public int remanded_in_custody;
    @JsonProperty("Lose_livelihood_details")
    public String lose_livelihood_details;
    @JsonProperty("Originator_selfregistered")
    public int originator_selfregistered;
    @JsonProperty("Committal")
    public boolean committal;
    @JsonProperty("Board_and_lodgings_paid_every")
    public String board_and_lodgings_paid_every;
    @JsonProperty("Surname")
    public String surname;
    @JsonProperty("Fc_delivery")
    public int fc_delivery;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_appealtocc")
    public String subformfundingdecision_1_Means_test_result_appealtocc;
    @JsonProperty("Other_case_next_hearing")
    public Date other_case_next_hearing;
    @JsonProperty("Partner_jsa_crm15_paid_every")
    public String partner_jsa_crm15_paid_every;
    @JsonProperty("Email")
    public String email;
    @JsonProperty("Date_of_birth")
    public Date date_of_birth;
    @JsonProperty("Xsd_name")
    public String xsd_name;
    @JsonProperty("Solicitor_firm_dx")
    public String solicitor_firm_dx;
    @JsonProperty("Fc_route_to_nct_on_resubmission")
    public boolean fc_route_to_nct_on_resubmission;
    @JsonProperty("Ethnicity_calc")
    public int ethnicity_calc;
    @JsonProperty("Subformfundingdecision_1-Overall_result_non_means")
    public String subformfundingdecision_1_Overall_result_non_means;
    @JsonProperty("Partner_maintenance_payment_paid_every")
    public String partner_maintenance_payment_paid_every;
    @JsonProperty("Partner_other_rent")
    public boolean partner_other_rent;
    @JsonProperty("Solicitor_address_1")
    public String solicitor_address_1;
    @JsonProperty("Tlseqno")
    public int tlseqno;
    @JsonProperty("Partner_income_rental")
    public boolean partner_income_rental;
    @JsonProperty("Time_received")
    public String time_received;
    @JsonProperty("Household_outgoings_exceed_income")
    public String household_outgoings_exceed_income;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_cc")
    public String subformfundingdecision_5_Means_test_result_cc;
    @JsonProperty("Originator_routemethod")
    public int originator_routemethod;
    @JsonProperty("Mixed_other")
    public boolean mixed_other;
    @JsonProperty("Originator_supervisor")
    public String originator_supervisor;
    @JsonProperty("Partner_conflict_of_interest")
    public int partner_conflict_of_interest;
    @JsonProperty("Suspended_sentence_details")
    public String suspended_sentence_details;
    @JsonProperty("Home_address_search")
    public String home_address_search;
    @JsonProperty("Freezing_order_crm15")
    public String freezing_order_crm15;
    @JsonProperty("Court_name_display")
    public String court_name_display;
    @JsonProperty("Dwp_check_dob")
    public Date dwp_check_dob;
    @JsonProperty("Provider_user_id")
    public int provider_user_id;
    @JsonProperty("Interests_of_another")
    public boolean interests_of_another;
    @JsonProperty("Partner_universal_credit_crm15")
    public int partner_universal_credit_crm15;
    @JsonProperty("TLProjectName")
    public String tLProjectName;
    @JsonProperty("Datestamp_clientdob")
    public Date datestamp_clientdob;
    @JsonProperty("Home_address_type")
    public String home_address_type;
    @JsonProperty("Return_reason_details")
    public String return_reason_details;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_magsorcfs")
    public String subformfundingdecision_4_Means_test_result_magsorcfs;
    @JsonProperty("Private_company")
    public String private_company;
    @JsonProperty("Originator_zone")
    public String originator_zone;
    @JsonProperty("Submitter_language")
    public String submitter_language;
    @JsonProperty("Childcare_costs_paid_every")
    public String childcare_costs_paid_every;
    @JsonProperty("Incapacity_benefit_crm15_paid_every")
    public String incapacity_benefit_crm15_paid_every;
    @JsonProperty("Last_sent_datetime")
    public String last_sent_datetime;
    @JsonProperty("Legal_rep_laa_account")
    public String legal_rep_laa_account;
    @JsonProperty("Partner_child_benefit_crm15_paid_every")
    public String partner_child_benefit_crm15_paid_every;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_magsorcfs")
    public String subformfundingdecision_5_Means_test_result_magsorcfs;
    @JsonProperty("Receive_pension")
    public String receive_pension;
    @JsonProperty("Maintenance_payment_calc")
    public int maintenance_payment_calc;
    @JsonProperty("Partner_income_financial_support")
    public boolean partner_income_financial_support;
    @JsonProperty("Has_warnings")
    public boolean has_warnings;
    @JsonProperty("Court_originating")
    public String court_originating;
    @JsonProperty("Partner_wage_paid_every")
    public String partner_wage_paid_every;
    @JsonProperty("Datestamp_date")
    public Date datestamp_date;
    @JsonProperty("Other_benefits_paid_every")
    public String other_benefits_paid_every;
    @JsonProperty("Partner_maintenance_payment_calc")
    public int partner_maintenance_payment_calc;
    @JsonProperty("Other_rent")
    public boolean other_rent;
    @JsonProperty("Last_hmcts_stagefilter1_display")
    public String last_hmcts_stagefilter1_display;
    @JsonProperty("TlTaskLastUpdated")
    public Date tlTaskLastUpdated;
    @JsonProperty("Partner_interest_paid_every")
    public String partner_interest_paid_every;
    @JsonProperty("Other_income_friends_family")
    public boolean other_income_friends_family;
    @JsonProperty("Partner_calc")
    public int partner_calc;
    @JsonProperty("Maintenance_payment_paid_every")
    public String maintenance_payment_paid_every;
    @JsonProperty("Phone_landline")
    public String phone_landline;
    @JsonProperty("Dwp_check_dateofaward")
    public Date dwp_check_dateofaward;
    @JsonProperty("Universal_credit_paid_every")
    public String universal_credit_paid_every;
    @JsonProperty("Fc_form_is_read_only")
    public boolean fc_form_is_read_only;
    @JsonProperty("Partner_other_income_checkbox_count")
    public int partner_other_income_checkbox_count;
    @JsonProperty("Subformfundingdecision_4-Overall_result_cc")
    public String subformfundingdecision_4_Overall_result_cc;
    @JsonProperty("Partner_tax_credits_paid_every")
    public String partner_tax_credits_paid_every;
    @JsonProperty("Family_rent")
    public boolean family_rent;
    @JsonProperty("Additional_attachments")
    public boolean additional_attachments;
    @JsonProperty("Other_cases")
    public String other_cases;
    @JsonProperty("Originator_department")
    public String originator_department;
    @JsonProperty("Indian")
    public boolean indian;
    @JsonProperty("Solicitor_firm")
    public String solicitor_firm;
    @JsonProperty("Subformfundingdecision_5-Overall_result_magsorcfs")
    public String subformfundingdecision_5_Overall_result_magsorcfs;
    @JsonProperty("Maintenance_amount_paid_every")
    public String maintenance_amount_paid_every;
    @JsonProperty("How_pay_bills_homeless")
    public boolean how_pay_bills_homeless;
    @JsonProperty("Partner_other_benefits_crm15_details")
    public String partner_other_benefits_crm15_details;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_type")
    public String subformfundingdecision_2_Means_test_result_type;
    @JsonProperty("Partner_other_income_source_initialised_flag")
    public boolean partner_other_income_source_initialised_flag;
    @JsonProperty("Subformfundingdecision_3-Official_sign_fullname")
    public String subformfundingdecision_3_Official_sign_fullname;
    @JsonProperty("Arc_number")
    public String arc_number;
    @JsonProperty("Marital_status")
    public String marital_status;
    @JsonProperty("Cant_present_own_case")
    public boolean cant_present_own_case;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_magsorcfs")
    public String subformfundingdecision_1_Means_test_result_magsorcfs;
    @JsonProperty("Charges_brought")
    public List<Crm14ChargesBroughtModel> charges_brought;
    @JsonProperty("Partner_child_benefit_paid_every")
    public String partner_child_benefit_paid_every;
    @JsonProperty("Subformfundingdecision_4-Appropriate_officer_name")
    public String subformfundingdecision_4_Appropriate_officer_name;
    @JsonProperty("Welsh_corr_flag")
    public boolean welsh_corr_flag;
    @JsonProperty("Partner_usual_postcode")
    public String partner_usual_postcode;
    @JsonProperty("Datestamp_usn")
    public int datestamp_usn;
    @JsonProperty("Partner_other_case_charges")
    public String partner_other_case_charges;
    @JsonProperty("Total_pension_paid_every")
    public String total_pension_paid_every;
    @JsonProperty("Firm_office")
    public String firm_office;
    @JsonProperty("Tlnewmessage")
    public String tlnewmessage;
    @JsonProperty("Asian_other")
    public boolean asian_other;
    @JsonProperty("Subformfundingdecision_3-Overall_result_cc")
    public String subformfundingdecision_3_Overall_result_cc;
    @JsonProperty("Expert_cross_exam")
    public boolean expert_cross_exam;
    @JsonProperty("Partner_receiving_benefits_2")
    public String partner_receiving_benefits_2;
    @JsonProperty("Jsa_crm15_paid_every")
    public String jsa_crm15_paid_every;
    @JsonProperty("Food_bill_every")
    public String food_bill_every;
    @JsonProperty("Partner_national_savings_certificates")
    public String partner_national_savings_certificates;
    @JsonProperty("Last_hmcts_stagefilter1")
    public String last_hmcts_stagefilter1;
    @JsonProperty("Subformfundingdecision_2-Overall_result_cc")
    public String subformfundingdecision_2_Overall_result_cc;
    @JsonProperty("Last_hmcts_stagefilter2")
    public int last_hmcts_stagefilter2;
    @JsonProperty("Damage_reputation_details")
    public String damage_reputation_details;
    @JsonProperty("Wage_tax_calc")
    public int wage_tax_calc;
    @JsonProperty("Legal_rep_declaration_confirm")
    public boolean legal_rep_declaration_confirm;
    @JsonProperty("Last_sender_display")
    public String last_sender_display;
    @JsonProperty("Subformfundingdecision_1-Means_test_result_cc")
    public String subformfundingdecision_1_Means_test_result_cc;
    @JsonProperty("Subformfundingdecision_1-Overall_result_cc")
    public String subformfundingdecision_1_Overall_result_cc;
    @JsonProperty("Other_income_source_freetext")
    public String other_income_source_freetext;
    @JsonProperty("Partner_income_rent_from_family")
    public boolean partner_income_rent_from_family;
    @JsonProperty("Application_type")
    public String application_type;
    @JsonProperty("Receive_interest")
    public String receive_interest;
    @JsonProperty("Subformfundingdecision_4-Overall_result_appealtocc")
    public String subformfundingdecision_4_Overall_result_appealtocc;
    @JsonProperty("Prev_answers_no_other_income")
    public String prev_answers_no_other_income;
    @JsonProperty("Child_benefit_calc")
    public int child_benefit_calc;
    @JsonProperty("Subformfundingdecision_2-Justice_test_reasons")
    public String subformfundingdecision_2_Justice_test_reasons;
    @JsonProperty("Fc_return_inject_result")
    public String fc_return_inject_result;
    @JsonProperty("Partner_other_case_next_hearing")
    public Date partner_other_case_next_hearing;
    @JsonProperty("Premium_bonds")
    public String premium_bonds;
    @JsonProperty("Urn")
    public String urn;
    @JsonProperty("Esa")
    public boolean esa;
    @JsonProperty("Partner_child_benefit_crm15")
    public int partner_child_benefit_crm15;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_appealtocc")
    public String subformfundingdecision_5_Means_test_result_appealtocc;
    @JsonProperty("Last_sender_uniquename")
    public String last_sender_uniquename;
    @JsonProperty("Late_application_cc")
    public boolean late_application_cc;
    @JsonProperty("Home_or_rent_payment")
    public String home_or_rent_payment;
    @JsonProperty("Subformfundingdecision_3-Justice_test_reasons")
    public String subformfundingdecision_3_Justice_test_reasons;
    @JsonProperty("Contact_postcode")
    public String contact_postcode;
    @JsonProperty("Income_over_set_amount")
    public String income_over_set_amount;
    @JsonProperty("Partner_receive_maintenance_payments")
    public String partner_receive_maintenance_payments;
    @JsonProperty("Tax_credit_crm15_paid_every")
    public String tax_credit_crm15_paid_every;
    @JsonProperty("White_and_black_african")
    public boolean white_and_black_african;
    @JsonProperty("Dwp_check_surname")
    public String dwp_check_surname;
    @JsonProperty("Maintenance_paid_every")
    public String maintenance_paid_every;
    @JsonProperty("Council_tax_paid_every")
    public int council_tax_paid_every;
    @JsonProperty("Partner_paid_40_percent_tax")
    public String partner_paid_40_percent_tax;
    @JsonProperty("Own_property")
    public String own_property;
    @JsonProperty("Solicitor_address_2")
    public String solicitor_address_2;
    @JsonProperty("Partner_other_income_source")
    public String partner_other_income_source;
    @JsonProperty("Solicitor_address_3")
    public String solicitor_address_3;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_magsorcfs")
    public String subformfundingdecision_3_Means_test_result_magsorcfs;
    @JsonProperty("Usn")
    public int usn;
    @JsonProperty("Originator_notificationmethod")
    public int originator_notificationmethod;
    @JsonProperty("Dwp_check_nino")
    public String dwp_check_nino;
    @JsonProperty("Partner_total_pension_paid_every")
    public String partner_total_pension_paid_every;
    @JsonProperty("Salary_paid_into_account")
    public String salary_paid_into_account;
    @JsonProperty("Phone_work")
    public String phone_work;
    @JsonProperty("Solicitor_email")
    public String solicitor_email;
    @JsonProperty("Child_benefit_paid_every")
    public String child_benefit_paid_every;
    @JsonProperty("Partner_tax_credit_crm15_paid_every")
    public String partner_tax_credit_crm15_paid_every;
    @JsonProperty("Mortgage_rent_calc")
    public int mortgage_rent_calc;
    @JsonProperty("Partner_pensions_calc")
    public int partner_pensions_calc;
    @JsonProperty("Land_calc_2")
    public int land_calc_2;
    @JsonProperty("Partner_trust_fund")
    public String partner_trust_fund;
    @JsonProperty("Originator_jobtitle")
    public String originator_jobtitle;
    @JsonProperty("Prev_app_maat")
    public String prev_app_maat;
    @JsonProperty("Legal_rep_declaration_2")
    public String legal_rep_declaration_2;
    @JsonProperty("Lost_job_due_to_custody")
    public int lost_job_due_to_custody;
    @JsonProperty("Tlreadonly")
    public int tlreadonly;
    @JsonProperty("Marital_status_2")
    public String marital_status_2;
    @JsonProperty("Partner_maintenance_calc")
    public int partner_maintenance_calc;
    @JsonProperty("Provider_displayname")
    public String provider_displayname;
    @JsonProperty("Subformfundingdecision_4-Overall_result_type")
    public String subformfundingdecision_4_Overall_result_type;
    @JsonProperty("Forenames")
    public String forenames;
    @JsonProperty("Tax_credits_paid_every")
    public String tax_credits_paid_every;
    @JsonProperty("Partner_other_financial_support")
    public boolean partner_other_financial_support;
    @JsonProperty("Receiving_benefits_2")
    public String receiving_benefits_2;
    @JsonProperty("Legal_rep_sign_date")
    public Date legal_rep_sign_date;
    @JsonProperty("Subformfundingdecision_2-Overall_result_appealtocc")
    public String subformfundingdecision_2_Overall_result_appealtocc;
    @JsonProperty("Universal_credit_crm15_paid_every")
    public String universal_credit_crm15_paid_every;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_cc")
    public String subformfundingdecision_4_Means_test_result_cc;
    @JsonProperty("State_pension_partner")
    public boolean state_pension_partner;
    @JsonProperty("Other_reason_to_be_represented_details")
    public String other_reason_to_be_represented_details;
    @JsonProperty("User_signed_name")
    public String user_signed_name;
    @JsonProperty("Partner_salary_paid_into_account")
    public String partner_salary_paid_into_account;
    @JsonProperty("Pakistani")
    public boolean pakistani;
    @JsonProperty("Originator_tl_neverpermanentlyremove")
    public int originator_tl_neverpermanentlyremove;
    @JsonProperty("Partner_universal_credit_crm15_paid_every")
    public String partner_universal_credit_crm15_paid_every;
    @JsonProperty("Subformfundingdecision_2-Maat_number")
    public String subformfundingdecision_2_Maat_number;
    @JsonProperty("Subformfundingdecision_4-Means_test_result_appealtocc")
    public String subformfundingdecision_4_Means_test_result_appealtocc;
    @JsonProperty("Partner_universal_credit_paid_every")
    public String partner_universal_credit_paid_every;
    @JsonProperty("Subformfundingdecision_4-Subformisvisible")
    public boolean subformfundingdecision_4_Subformisvisible;
    @JsonProperty("Appeal_to_crown_court")
    public boolean appeal_to_crown_court;
    @JsonProperty("Other_financial_support")
    public boolean other_financial_support;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_appealtocc")
    public String subformfundingdecision_2_Means_test_result_appealtocc;
    @JsonProperty("Wage_paid_every")
    public String wage_paid_every;
    @JsonProperty("Partner_disablement_benefit_crm15_paid_every")
    public String partner_disablement_benefit_crm15_paid_every;
    @JsonProperty("Land_calc_partner")
    public int land_calc_partner;
    @JsonProperty("Subformfundingdecision_3-Appropriate_officer_name")
    public String subformfundingdecision_3_Appropriate_officer_name;
    @JsonProperty("Subformfundingdecision_4-Overall_result_non_means")
    public String subformfundingdecision_4_Overall_result_non_means;
    @JsonProperty("Maat_numbers")
    public String maat_numbers;
    @JsonProperty("Overall_results_for_notification")
    public String overall_results_for_notification;
    @JsonProperty("Priority_case_calc")
    public int priority_case_calc;
    @JsonProperty("Date_of_trial")
    public Date date_of_trial;
    @JsonProperty("Other_benefits_crm15_paid_every")
    public String other_benefits_crm15_paid_every;
    @JsonProperty("Has_errors")
    public boolean has_errors;
    @JsonProperty("Ni_number")
    public String ni_number;
    @JsonProperty("How_pay_bills_freetext")
    public String how_pay_bills_freetext;
    @JsonProperty("Fc_staging_inject_result")
    public String fc_staging_inject_result;
    @JsonProperty("Subformfundingdecision_2-Overall_result_magsorcfs")
    public String subformfundingdecision_2_Overall_result_magsorcfs;
    @JsonProperty("Codefendants_details")
    public String codefendants_details;
    @JsonProperty("Date_received")
    public Date date_received;
    @JsonProperty("Maat_number_count")
    public int maat_number_count;
    @JsonProperty("Have_home_address")
    public String have_home_address;
    @JsonProperty("How_pay_bills_initialised_flag")
    public boolean how_pay_bills_initialised_flag;
    @JsonProperty("Question_of_law_details")
    public String question_of_law_details;
    @JsonProperty("Subformfundingdecision_3-Case_number")
    public String subformfundingdecision_3_Case_number;
    @JsonProperty("Solicitor_postcode")
    public String solicitor_postcode;
    @JsonProperty("Partner_involved_in_case")
    public int partner_involved_in_case;
    @JsonProperty("Paid_40_percent_tax")
    public String paid_40_percent_tax;
    @JsonProperty("Other_income_source")
    public String other_income_source;
    @JsonProperty("Income_from_other_sources_calc")
    public int income_from_other_sources_calc;
    @JsonProperty("Jsa_partner")
    public boolean jsa_partner;
    @JsonProperty("Subformfundingdecision_3-Means_test_result_appealtocc")
    public String subformfundingdecision_3_Means_test_result_appealtocc;
    @JsonProperty("Partner_sign_date")
    public Date partner_sign_date;
    @JsonProperty("Provider_firm_id")
    public int provider_firm_id;
    @JsonProperty("Subformfundingdecision_2-Justice_test")
    public String subformfundingdecision_2_Justice_test;
    @JsonProperty("Partner_freezing_order_crm15")
    public String partner_freezing_order_crm15;
    @JsonProperty("Fc_current_user")
    public String fc_current_user;
    @JsonProperty("Evidence_hidden")
    public int evidence_hidden;
    @JsonProperty("Home_address_3")
    public String home_address_3;
    @JsonProperty("Partner_title_other")
    public String partner_title_other;
    @JsonProperty("Home_address_2")
    public String home_address_2;
    @JsonProperty("Home_postcode")
    public String home_postcode;
    @JsonProperty("Home_address_1")
    public String home_address_1;
    @JsonProperty("Board_and_lodgings_calc")
    public int board_and_lodgings_calc;
    @JsonProperty("Have_partner_copy")
    public int have_partner_copy;
    @JsonProperty("Contribute_legal_aid")
    public String contribute_legal_aid;
    @JsonProperty("Own_land_or_property")
    public String own_land_or_property;
    @JsonProperty("State_pension_crm15_paid_every")
    public String state_pension_crm15_paid_every;
    @JsonProperty("Subformfundingdecision_1-Subformisvisible")
    public boolean subformfundingdecision_1_Subformisvisible;
    @JsonProperty("Indictable_offence")
    public int indictable_offence;
    @JsonProperty("Originator_emailflag")
    public String originator_emailflag;
    @JsonProperty("Interests_of_another_details")
    public String interests_of_another_details;
    @JsonProperty("Black_other")
    public boolean black_other;
    @JsonProperty("Board_and_lodgings_landlord")
    public String board_and_lodgings_landlord;
    @JsonProperty("Subformfundingdecision_2-Official_sign_fullname")
    public String subformfundingdecision_2_Official_sign_fullname;
    @JsonProperty("Applicant_confirm_read")
    public boolean applicant_confirm_read;
    @JsonProperty("Partner_business_calc")
    public int partner_business_calc;
    @JsonProperty("Partner_usual_address_1")
    public String partner_usual_address_1;
    @JsonProperty("Freezing_order")
    public String freezing_order;
    @JsonProperty("Partner_usual_address_2")
    public String partner_usual_address_2;
    @JsonProperty("Partner_tax_liability_paid_every")
    public String partner_tax_liability_paid_every;
    @JsonProperty("Partner_usual_address_3")
    public String partner_usual_address_3;
    @JsonProperty("Board_lodgings_landlord_relationship")
    public String board_lodgings_landlord_relationship;
    @JsonProperty("Originator_tl_dtlastfailedloginattempt")
    public Date originator_tl_dtlastfailedloginattempt;
    @JsonProperty("Other_case_charges")
    public String other_case_charges;
    @JsonProperty("Partner_other_benefits_calc")
    public int partner_other_benefits_calc;
    @JsonProperty("Contact_address_search")
    public String contact_address_search;
    @JsonProperty("Partner_forenames")
    public String partner_forenames;
    @JsonProperty("Formtype")
    public String formtype;
    @JsonProperty("Other_income_rental")
    public boolean other_income_rental;
    @JsonProperty("Partner_student_loan")
    public boolean partner_student_loan;
    @JsonProperty("Other_income_checkbox_count")
    public int other_income_checkbox_count;
    @JsonProperty("Tlmultimessage")
    public String tlmultimessage;
    @JsonProperty("Lose_livelihood")
    public boolean lose_livelihood;
    @JsonProperty("Subformfundingdecision_1-Justice_test")
    public String subformfundingdecision_1_Justice_test;
    @JsonProperty("Subformfundingdecision_3-Justice_test")
    public String subformfundingdecision_3_Justice_test;
    @JsonProperty("Partner_salary_account")
    public String partner_salary_account;
    @JsonProperty("Dwp_check_partner_dob")
    public Date dwp_check_partner_dob;
    @JsonProperty("Other_income_student_grant")
    public boolean other_income_student_grant;
    @JsonProperty("Partner_state_pension_crm15_paid_every")
    public String partner_state_pension_crm15_paid_every;
    @JsonProperty("Why_not_rep_same_solicitor")
    public String why_not_rep_same_solicitor;
    @JsonProperty("State_pension")
    public boolean state_pension;
    @JsonProperty("Employment_calc")
    public int employment_calc;
    @JsonProperty("Childcare_costs_calc")
    public int childcare_costs_calc;
    @JsonProperty("User_signed_date")
    public Date user_signed_date;
    @JsonProperty("Total_amount_received_every")
    public String total_amount_received_every;
    @JsonProperty("Income_support_partner")
    public boolean income_support_partner;
    @JsonProperty("Tldateorig")
    public Date tldateorig;
    @JsonProperty("Fc_assessment_result_inject_result")
    public String fc_assessment_result_inject_result;
    @JsonProperty("Receive_benefits")
    public String receive_benefits;
    @JsonProperty("Subformfundingdecision_2-Means_test_result_magsorcfs")
    public String subformfundingdecision_2_Means_test_result_magsorcfs;
    @JsonProperty("Prev_app_maat_cifc")
    public String prev_app_maat_cifc;
    @JsonProperty("Subformfundingdecision_5-Means_test_result_type")
    public String subformfundingdecision_5_Means_test_result_type;
    @JsonProperty("Originator_tl_failedloginattemptcount")
    public int originator_tl_failedloginattemptcount;
    @JsonProperty("Solicitor_phone_mobile")
    public String solicitor_phone_mobile;
    @JsonProperty("Datestamp_clientname")
    public String datestamp_clientname;
    @JsonProperty("Indictable")
    public boolean indictable;
    @JsonProperty("Appeal_no_changes")
    public boolean appeal_no_changes;
    @JsonProperty("Partner_different_home")
    public String partner_different_home;
    @JsonProperty("Disabled_definition")
    public String disabled_definition;
    @JsonProperty("Business_calc")
    public int business_calc;
    @JsonProperty("Legal_rep_fullname")
    public String legal_rep_fullname;
    @JsonProperty("Partner_pensions_paid_every")
    public String partner_pensions_paid_every;
    @JsonProperty("Wage_tax")
    public String wage_tax;
    @JsonProperty("Expert_cross_exam_details")
    public String expert_cross_exam_details;
    @JsonProperty("Dwp_check_partner_nino")
    public String dwp_check_partner_nino;
    @JsonProperty("Subformfundingdecision_2-Appropriate_officer_name")
    public String subformfundingdecision_2_Appropriate_officer_name;
    @JsonProperty("Prefer_not_to_say_ethnicity")
    public boolean prefer_not_to_say_ethnicity;
    @JsonProperty("Last_nct_stagefilter2")
    public String last_nct_stagefilter2;
    @JsonProperty("Last_nct_stagefilter1")
    public String last_nct_stagefilter1;
    @JsonProperty("Tax_liability_paid_every")
    public String tax_liability_paid_every;
    @JsonProperty("Interest_paid_every")
    public String interest_paid_every;
    @JsonProperty("Subformfundingdecision_3-Overall_result_magsorcfs")
    public String subformfundingdecision_3_Overall_result_magsorcfs;
    @JsonProperty("Partner_total_pension_calc")
    public int partner_total_pension_calc;
    @JsonProperty("How_pay_bills")
    public String how_pay_bills;
    @JsonProperty("Total_file_size_mb")
    public double total_file_size_mb;
    @JsonProperty("Partner_premium_bonds")
    public String partner_premium_bonds;
    @JsonProperty("Originator_ntlogon")
    public String originator_ntlogon;
    @JsonProperty("Partner_involvement")
    public int partner_involvement;
    @JsonProperty("Disabled")
    public String disabled;
    @JsonProperty("Child_benefit_crm15")
    public int child_benefit_crm15;
    @JsonProperty("Originator_routeoffline")
    public int originator_routeoffline;
    @JsonProperty("Income_calc")
    public int income_calc;
    @JsonProperty("Partner_income_maintenance")
    public boolean partner_income_maintenance;
    @JsonProperty("Subformfundingdecision_5-Maat_number")
    public String subformfundingdecision_5_Maat_number;
    @JsonProperty("Partner_wage_calc")
    public int partner_wage_calc;
    @JsonProperty("Other_income_source_initialised_flag")
    public boolean other_income_source_initialised_flag;
    @JsonProperty("Subformfundingdecision_3-Maat_number")
    public String subformfundingdecision_3_Maat_number;
    @JsonProperty("Originator_dtlastupdated")
    public Date originator_dtlastupdated;
    @JsonProperty("Partner_income_from_other_source_calc")
    public int partner_income_from_other_source_calc;
    @JsonProperty("Income_from_other_sources")
    public String income_from_other_sources;
    @JsonProperty("Either_way")
    public boolean either_way;
    @JsonProperty("Irish")
    public boolean irish;
    @JsonProperty("Originator_emailaddress")
    public String originator_emailaddress;
    @JsonProperty("Self_assessment_tax_received")
    public String self_assessment_tax_received;
    @JsonProperty("Subformfundingdecision_5-Overall_result_non_means")
    public String subformfundingdecision_5_Overall_result_non_means;
    @JsonProperty("Subformfundingdecision_1-Maat_number")
    public String subformfundingdecision_1_Maat_number;
    @JsonProperty("Tax_credit_crm15")
    public int tax_credit_crm15;
    @JsonProperty("Originator_tl_mustchangepassword")
    public int originator_tl_mustchangepassword;
    @JsonProperty("Tlstatename")
    public String tlstatename;
    @JsonProperty("Other_income_crm15")
    public String other_income_crm15;
    @JsonProperty("Lose_liberty")
    public boolean lose_liberty;
    @JsonProperty("Partner_income_student_grant")
    public boolean partner_income_student_grant;
    @JsonProperty("Dwp_check_benefitchecktorepeat")
    public String dwp_check_benefitchecktorepeat;
    @JsonProperty("Pay_maintenance")
    public String pay_maintenance;
    @JsonProperty("Last_action")
    public String last_action;
    @JsonProperty("Subformfundingdecision_5-Official_sign_fullname")
    public String subformfundingdecision_5_Official_sign_fullname;
}