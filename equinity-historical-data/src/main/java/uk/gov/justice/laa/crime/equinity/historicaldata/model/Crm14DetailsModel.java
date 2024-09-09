package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class Crm14DetailsModel extends Crm14AdditionalDetails implements CrmFormDetailsModelInterface {
    @JsonProperty("Solicitor_phone_landline")
    public String solicitor_phone_landline;
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
    @JsonProperty("Charged_with_adult")
    public String charged_with_adult;
    @JsonProperty("Date_of_separation")
    public String date_of_separation;
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
    @JsonProperty("Originator_tl_permanentlyremoved")
    public int originator_tl_permanentlyremoved;
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
    @JsonProperty("Employed")
    public int employed;
    @JsonProperty("Tlmessage")
    public String tlmessage;
    @JsonProperty("Fc_non_means_tested")
    public boolean fc_non_means_tested;
    @JsonProperty("Originator_tl_usertype")
    public int originator_tl_usertype;
    @JsonProperty("Student_loan")
    public boolean student_loan;
    @JsonProperty("TLProjectUniqueName")
    public String tLProjectUniqueName;
    @JsonProperty("Tltasktype")
    public int tltasktype;
    @JsonProperty("Witness_trace")
    public boolean witness_trace;
    @JsonProperty("Tlautonumber")
    public int tlautonumber;
    @JsonProperty("Dwp_check_result")
    public String dwp_check_result;
    @JsonProperty("Legal_rep_declaration")
    public String legal_rep_declaration;
    @JsonProperty("Black_african")
    public boolean black_african;
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
    @JsonProperty("Other_case_next_hearing")
    public Date other_case_next_hearing;
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
    @JsonProperty("Solicitor_address_1")
    public String solicitor_address_1;
    @JsonProperty("Tlseqno")
    public int tlseqno;
    @JsonProperty("Time_received")
    public String time_received;
    @JsonProperty("Household_outgoings_exceed_income")
    public String household_outgoings_exceed_income;
    @JsonProperty("Originator_routemethod")
    public int originator_routemethod;
    @JsonProperty("Mixed_other")
    public boolean mixed_other;
    @JsonProperty("Originator_supervisor")
    public String originator_supervisor;
    @JsonProperty("Suspended_sentence_details")
    public String suspended_sentence_details;
    @JsonProperty("Home_address_search")
    public String home_address_search;
    @JsonProperty("Court_name_display")
    public String court_name_display;
    @JsonProperty("Dwp_check_dob")
    public Date dwp_check_dob;
    @JsonProperty("Provider_user_id")
    public int provider_user_id;
    @JsonProperty("Interests_of_another")
    public boolean interests_of_another;
    @JsonProperty("TLProjectName")
    public String tLProjectName;
    @JsonProperty("Datestamp_clientdob")
    public Date datestamp_clientdob;
    @JsonProperty("Home_address_type")
    public String home_address_type;
    @JsonProperty("Return_reason_details")
    public String return_reason_details;
    @JsonProperty("Private_company")
    public String private_company;
    @JsonProperty("Originator_zone")
    public String originator_zone;
    @JsonProperty("Submitter_language")
    public String submitter_language;
    @JsonProperty("Childcare_costs_paid_every")
    public String childcare_costs_paid_every;
    @JsonProperty("Last_sent_datetime")
    public String last_sent_datetime;
    @JsonProperty("Legal_rep_laa_account")
    public String legal_rep_laa_account;
    @JsonProperty("Receive_pension")
    public String receive_pension;
    @JsonProperty("Maintenance_payment_calc")
    public int maintenance_payment_calc;
    @JsonProperty("Has_warnings")
    public boolean has_warnings;
    @JsonProperty("Court_originating")
    public String court_originating;
    @JsonProperty("Datestamp_date")
    public Date datestamp_date;
    @JsonProperty("Other_benefits_paid_every")
    public String other_benefits_paid_every;
    @JsonProperty("Other_rent")
    public boolean other_rent;
    @JsonProperty("Last_hmcts_stagefilter1_display")
    public String last_hmcts_stagefilter1_display;
    @JsonProperty("TlTaskLastUpdated")
    public Date tlTaskLastUpdated;
    @JsonProperty("Other_income_friends_family")
    public boolean other_income_friends_family;
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
    @JsonProperty("Maintenance_amount_paid_every")
    public String maintenance_amount_paid_every;
    @JsonProperty("How_pay_bills_homeless")
    public boolean how_pay_bills_homeless;
    @JsonProperty("Arc_number")
    public String arc_number;
    @JsonProperty("Marital_status")
    public String marital_status;
    @JsonProperty("Cant_present_own_case")
    public boolean cant_present_own_case;
    @JsonProperty("Charges_brought")
    public Crm14ChargesBroughtModel charges_brought;
    @JsonProperty("Welsh_corr_flag")
    public boolean welsh_corr_flag;
    @JsonProperty("Datestamp_usn")
    public int datestamp_usn;
    @JsonProperty("Total_pension_paid_every")
    public int total_pension_paid_every;
    @JsonProperty("Firm_office")
    public String firm_office;
    @JsonProperty("Tlnewmessage")
    public String tlnewmessage;
    @JsonProperty("Asian_other")
    public boolean asian_other;
    @JsonProperty("Expert_cross_exam")
    public boolean expert_cross_exam;
    @JsonProperty("Food_bill_every")
    public String food_bill_every;
    @JsonProperty("Last_hmcts_stagefilter1")
    public String last_hmcts_stagefilter1;
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
    @JsonProperty("Other_income_source_freetext")
    public String other_income_source_freetext;
    @JsonProperty("Application_type")
    public String application_type;
    @JsonProperty("Receive_interest")
    public String receive_interest;
    @JsonProperty("Prev_answers_no_other_income")
    public String prev_answers_no_other_income;
    @JsonProperty("Child_benefit_calc")
    public int child_benefit_calc;
    @JsonProperty("Fc_return_inject_result")
    public String fc_return_inject_result;
    @JsonProperty("Premium_bonds")
    public String premium_bonds;
    @JsonProperty("Urn")
    public String urn;
    @JsonProperty("Esa")
    public boolean esa;
    @JsonProperty("Last_sender_uniquename")
    public String last_sender_uniquename;
    @JsonProperty("Late_application_cc")
    public boolean late_application_cc;
    @JsonProperty("Home_or_rent_payment")
    public String home_or_rent_payment;
    @JsonProperty("Contact_postcode")
    public String contact_postcode;
    @JsonProperty("Income_over_set_amount")
    public String income_over_set_amount;
    @JsonProperty("White_and_black_african")
    public boolean white_and_black_african;
    @JsonProperty("Dwp_check_surname")
    public String dwp_check_surname;
    @JsonProperty("Maintenance_paid_every")
    public String maintenance_paid_every;
    @JsonProperty("Council_tax_paid_every")
    public int council_tax_paid_every;
    @JsonProperty("Own_property")
    public String own_property;
    @JsonProperty("Solicitor_address_2")
    public String solicitor_address_2;
    @JsonProperty("Solicitor_address_3")
    public String solicitor_address_3;
    @JsonProperty("Usn")
    public int usn;
    @JsonProperty("Originator_notificationmethod")
    public int originator_notificationmethod;
    @JsonProperty("Dwp_check_nino")
    public String dwp_check_nino;
    @JsonProperty("Salary_paid_into_account")
    public String salary_paid_into_account;
    @JsonProperty("Phone_work")
    public String phone_work;
    @JsonProperty("Solicitor_email")
    public String solicitor_email;
    @JsonProperty("Child_benefit_paid_every")
    public String child_benefit_paid_every;
    @JsonProperty("Mortgage_rent_calc")
    public int mortgage_rent_calc;
    @JsonProperty("Mortgage_rent")
    public double mortgage_rent;
    @JsonProperty("Council_tax")
    public double council_tax;
    @JsonProperty("Board_and_lodgings")
    public double board_and_lodgings;
    @JsonProperty("Food_bill")
    public double food_bill;
    @JsonProperty("Childcare_costs_amount")
    public double childcare_costs_amount;
    @JsonProperty("Maintenance_amount")
    public double maintenance_amount;
    @JsonProperty("Legal_aid_contribution")
    public double legal_aid_contribution;
    @JsonProperty("Land_calc_2")
    public int land_calc_2;
    @JsonProperty("Originator_jobtitle")
    public String originator_jobtitle;
    @JsonProperty("Prev_app_usn")
    public String prev_app_usn;
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
    @JsonProperty("Provider_displayname")
    public String provider_displayname;
    @JsonProperty("Forenames")
    public String forenames;
    @JsonProperty("Tax_credits_paid_every")
    public String tax_credits_paid_every;
    @JsonProperty("Receiving_benefits_2")
    public String receiving_benefits_2;
    @JsonProperty("Legal_rep_sign_date")
    public Date legal_rep_sign_date;
    @JsonProperty("Other_reason_to_be_represented_details")
    public String other_reason_to_be_represented_details;
    @JsonProperty("User_signed_name")
    public String user_signed_name;
    @JsonProperty("Pakistani")
    public boolean pakistani;
    @JsonProperty("Originator_tl_neverpermanentlyremove")
    public int originator_tl_neverpermanentlyremove;
    @JsonProperty("Appeal_to_crown_court")
    public boolean appeal_to_crown_court;
    @JsonProperty("Other_financial_support")
    public boolean other_financial_support;
    @JsonProperty("Wage_paid_every")
    public String wage_paid_every;
    @JsonProperty("Maat_numbers")
    public String maat_numbers;
    @JsonProperty("Overall_results_for_notification")
    public String overall_results_for_notification;
    @JsonProperty("Priority_case_calc")
    public int priority_case_calc;
    @JsonProperty("Date_of_trial")
    public Date date_of_trial;
    @JsonProperty("Appeal_lodged_date")
    public Date appeal_lodged_date;
    @JsonProperty("Has_errors")
    public boolean has_errors;
    @JsonProperty("Ni_number")
    public String ni_number;
    @JsonProperty("How_pay_bills_freetext")
    public String how_pay_bills_freetext;
    @JsonProperty("Fc_staging_inject_result")
    public String fc_staging_inject_result;
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
    @JsonProperty("Solicitor_postcode")
    public String solicitor_postcode;
    @JsonProperty("Paid_40_percent_tax")
    public String paid_40_percent_tax;
    @JsonProperty("Other_income_source")
    public String other_income_source;
    @JsonProperty("Income_from_other_sources_calc")
    public int income_from_other_sources_calc;
    @JsonProperty("Provider_firm_id")
    public int provider_firm_id;
    @JsonProperty("Fc_current_user")
    public String fc_current_user;
    @JsonProperty("Evidence_hidden")
    public int evidence_hidden;
    @JsonProperty("Home_address_3")
    public String home_address_3;
    @JsonProperty("Home_address_2")
    public String home_address_2;
    @JsonProperty("Home_postcode")
    public String home_postcode;
    @JsonProperty("Home_address_1")
    public String home_address_1;
    @JsonProperty("Board_and_lodgings_calc")
    public int board_and_lodgings_calc;
    @JsonProperty("Contribute_legal_aid")
    public String contribute_legal_aid;
    @JsonProperty("Own_land_or_property")
    public String own_land_or_property;
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
    @JsonProperty("Applicant_confirm_read")
    public boolean applicant_confirm_read;
    @JsonProperty("Freezing_order")
    public String freezing_order;
    @JsonProperty("Board_lodgings_landlord_relationship")
    public String board_lodgings_landlord_relationship;
    @JsonProperty("Originator_tl_dtlastfailedloginattempt")
    public Date originator_tl_dtlastfailedloginattempt;
    @JsonProperty("Other_case_charges")
    public String other_case_charges;
    @JsonProperty("Contact_address_search")
    public String contact_address_search;
    @JsonProperty("Formtype")
    public String formtype;
    @JsonProperty("Other_income_rental")
    public boolean other_income_rental;
    @JsonProperty("Other_income_checkbox_count")
    public int other_income_checkbox_count;
    @JsonProperty("Tlmultimessage")
    public String tlmultimessage;
    @JsonProperty("Lose_livelihood")
    public boolean lose_livelihood;
    @JsonProperty("Other_income_student_grant")
    public boolean other_income_student_grant;
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
    @JsonProperty("Tldateorig")
    public Date tldateorig;
    @JsonProperty("Fc_assessment_result_inject_result")
    public String fc_assessment_result_inject_result;
    @JsonProperty("Receive_benefits")
    public String receive_benefits;
    @JsonProperty("Prev_app_maat_cifc")
    public String prev_app_maat_cifc;
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
    @JsonProperty("Disabled_definition")
    public String disabled_definition;
    @JsonProperty("Business_calc")
    public int business_calc;
    @JsonProperty("Legal_rep_fullname")
    public String legal_rep_fullname;
    @JsonProperty("Wage_tax")
    public String wage_tax;
    @JsonProperty("Expert_cross_exam_details")
    public String expert_cross_exam_details;
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
    @JsonProperty("How_pay_bills")
    public String how_pay_bills;
    @JsonProperty("Total_file_size_mb")
    public double total_file_size_mb;
    @JsonProperty("Originator_ntlogon")
    public String originator_ntlogon;
    @JsonProperty("Disabled")
    public String disabled;
    @JsonProperty("Originator_routeoffline")
    public int originator_routeoffline;
    @JsonProperty("Income_calc")
    public int income_calc;
    @JsonProperty("Other_income_source_initialised_flag")
    public boolean other_income_source_initialised_flag;
    @JsonProperty("Originator_dtlastupdated")
    public Date originator_dtlastupdated;
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
    @JsonProperty("Originator_tl_mustchangepassword")
    public int originator_tl_mustchangepassword;
    @JsonProperty("Tlstatename")
    public String tlstatename;
    @JsonProperty("Lose_liberty")
    public boolean lose_liberty;
    @JsonProperty("Dwp_check_benefitchecktorepeat")
    public String dwp_check_benefitchecktorepeat;
    @JsonProperty("Pay_maintenance")
    public String pay_maintenance;
    @JsonProperty("Last_action")
    public String last_action;
    @JsonProperty("Bangladeshi")
    public boolean bangladeshi;
    @JsonProperty("Originator_tl_facebookid")
    public String originator_tl_facebookid;
    @JsonProperty("Originator_lscareaoffice")
    public String originator_lscareaoffice;
    @JsonProperty("Pensions_paid_every")
    public String pensions_paid_every;
    @JsonProperty("Provider_sign")
    public String provider_sign;
    @JsonProperty("Legal_aid_contribution_paid_every")
    public String legal_aid_contribution_paid_every;
    @JsonProperty("Cant_present_own_case_details")
    public String cant_present_own_case_details;
    @JsonProperty("Universal_credit_calc")
    public int universal_credit_calc;
    @JsonProperty("Black_caribbean")
    public boolean black_caribbean;
    @JsonProperty("British")
    public boolean british;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Solicitor_title")
    public String solicitor_title;
    @JsonProperty("Originator_lscemailaddress")
    public String originator_lscemailaddress;
    @JsonProperty("Contact_address_1")
    public String contact_address_1;
    @JsonProperty("Other_income_rent_from_family")
    public boolean other_income_rent_from_family;
    @JsonProperty("Tlsubstate")
    public String tlsubstate;
    @JsonProperty("Income_support")
    public boolean income_support;
    @JsonProperty("Fc_submission_count")
    public int fc_submission_count;
    @JsonProperty("Fc_testing")
    public boolean fc_testing;
    @JsonProperty("Contact_address_2")
    public String contact_address_2;
    @JsonProperty("Contact_address_3")
    public String contact_address_3;
    @JsonProperty("Maintenance_payment")
    public int maintenance_payment;
    @JsonProperty("Proceedings_already_concluded_notes")
    public String proceedings_already_concluded_notes;
    @JsonProperty("Provider_uniquename")
    public String provider_uniquename;
    @JsonProperty("Wage_calc")
    public int wage_calc;
    @JsonProperty("Who_dwp_checked")
    public String who_dwp_checked;
    @JsonProperty("Household_outgoings_exceed_income_how")
    public String household_outgoings_exceed_income_how;
    @JsonProperty("Originator_tl_googleid")
    public String originator_tl_googleid;
    @JsonProperty("Return_reason")
    public String return_reason;
    @JsonProperty("National_savings_certificates")
    public String national_savings_certificates;
    @JsonProperty("Age_calc")
    public int age_calc;
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
    @JsonProperty("Non_cash_benefit")
    public String non_cash_benefit;
    @JsonProperty("Fc_eligible_for_staging_inject")
    public boolean fc_eligible_for_staging_inject;
    @JsonProperty("Privacy_agree")
    public boolean privacy_agree;
    @JsonProperty("Witness_trace_details")
    public String witness_trace_details;
    @JsonProperty("Fc_current_user_type")
    public String fc_current_user_type;
    @JsonProperty("Fc_currentstage")
    public String fc_currentstage;
    @JsonProperty("Salary_account")
    public String salary_account;
    @JsonProperty("Originator_dtlastaccessed")
    public Date originator_dtlastaccessed;
    @JsonProperty("Receive_maintenance_payments")
    public String receive_maintenance_payments;
    @JsonProperty("TLActionsOnLine")
    public String tLActionsOnLine;
    @JsonProperty("Originator_tl_qn_answer_hash")
    public String originator_tl_qn_answer_hash;
    @JsonProperty("Originator_displayname")
    public String originator_displayname;
    @JsonProperty("Fc_maat_id_injected")
    public boolean fc_maat_id_injected;
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
    @JsonProperty("Any_codefendants")
    public String any_codefendants;
    @JsonProperty("Solicitor_title_other")
    public String solicitor_title_other;
    @JsonProperty("Other_names")
    public String other_names;
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
    @JsonProperty("Remanded_in_custody_date")
    public Date remanded_in_custody_date;
    @JsonProperty("Custody")
    public boolean custody;
    @JsonProperty("No_of_joint_accounts_calc")
    public int no_of_joint_accounts_calc;
    @JsonProperty("Question_of_law")
    public boolean question_of_law;
    @JsonProperty("Savings_or_investments")
    public String savings_or_investments;
    @JsonProperty("Phone_mobile")
    public String phone_mobile;
    @JsonProperty("Other_income_paid_every")
    public String other_income_paid_every;
    @JsonProperty("No_of_accounts_calc")
    public int no_of_accounts_calc;
    @JsonProperty("Land_calc")
    public int land_calc;
    @JsonProperty("Relationship_to_home_owner")
    public String relationship_to_home_owner;
    @JsonProperty("Print_signed")
    public boolean print_signed;
    @JsonProperty("Total_pension_calc")
    public int total_pension_calc;
    @JsonProperty("How_pay_bills_friend_sofa")
    public boolean how_pay_bills_friend_sofa;
    @JsonProperty("Originator_uniquename")
    public String originator_uniquename;
    @JsonProperty("Priority")
    public String priority;
    @JsonProperty("Youth")
    public boolean youth;
    @JsonProperty("Originator_id")
    public int originator_id;
    @JsonProperty("Lose_liberty_details")
    public String lose_liberty_details;
    @JsonProperty("Business_calc_2")
    public int business_calc_2;
    @JsonProperty("Legal_rep_sign")
    public String legal_rep_sign;
    @JsonProperty("White_other")
    public boolean white_other;
    @JsonProperty("Court_originating_display")
    public String court_originating_display;
    @JsonProperty("Do_you_have_proof")
    public String do_you_have_proof;
    @JsonProperty("Other_benefits_calc")
    public int other_benefits_calc;
    @JsonProperty("Vulnerable")
    public boolean vulnerable;
    @JsonProperty("Maintenance_amount_calc")
    public int maintenance_amount_calc;
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
    @JsonProperty("Fc_caseworker_completing_funding_decision_manually")
    public boolean fc_caseworker_completing_funding_decision_manually;
    @JsonProperty("What_contact_address")
    public String what_contact_address;
    @JsonProperty("Pensions_calc")
    public int pensions_calc;
    @JsonProperty("Other_income_financial_support")
    public boolean other_income_financial_support;
    @JsonProperty("Originator__dtadded")
    public Date originator__dtadded;
    @JsonProperty("Fc_eligible_for_return_inject")
    public boolean fc_eligible_for_return_inject;
    @JsonProperty("Case_type_calc")
    public int case_type_calc;
    @JsonProperty("Other_income_calc")
    public int other_income_calc;
    @JsonProperty("Employment_ceased_3_months")
    public int employment_ceased_3_months;
    @JsonProperty("Summary")
    public boolean summary;
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
    public double child_benefit_crm15;
    @JsonProperty("Tax_credit_crm15")
    public double tax_credit_crm15;
    @JsonProperty("Other_income_crm15")
    public String other_income_crm15;
    @JsonProperty("How_pay_bills_crm15")
    public String how_pay_bills_crm15;
    @JsonProperty("Disablement_benefit_crm15_paid_every")
    public String disablement_benefit_crm15_paid_every;
    @JsonProperty("Universal_credit_crm15")
    public double universal_credit_crm15;
    @JsonProperty("Other_benefits_crm15_details")
    public String other_benefits_crm15_details;
    @JsonProperty("Self_employed_no_of_businesses")
    public int self_employed_no_of_businesses;
    @JsonProperty("Business_partnerships_no_of")
    public int business_partnerships_no_of;
    @JsonProperty("Private_companies_no_of")
    public int private_companies_no_of;
    @JsonProperty("Properties_residential")
    public int properties_residential;
    @JsonProperty("Properties_commercial")
    public int properties_commercial;
    @JsonProperty("Pieces_of_land")
    public int pieces_of_land;
    @JsonProperty("Partner_properties_residential")
    public int partner_properties_residential;
    @JsonProperty("Partner_properties_commercial")
    public int partner_properties_commercial;
    @JsonProperty("Partner_pieces_of_land")
    public int partner_pieces_of_land;
    @JsonProperty("Joint_properties_residential")
    public int joint_properties_residential;
    @JsonProperty("Joint_properties_commercial")
    public int joint_properties_commercial;
    @JsonProperty("Joint_pieces_of_land")
    public int joint_pieces_of_land;
    public List<Crm14FundDecisionModel> fundingDecisions;

    public boolean hasCrm15() {
        if (StringUtils.isNotEmpty(this.getPrivate_company()) && this.getPrivate_company().equalsIgnoreCase("Yes"))
            return true;
        else
            return StringUtils.isNotEmpty(this.getPartner_private_company()) && this.getPartner_private_company().equalsIgnoreCase("Yes");
    }

    public List<Crm14FundDecisionModel> getFundingDecisions() {
        if (!this.isSubformfundingdecision_1_subformisvisible()) {
            return null;
        }
        List<Crm14FundDecisionModel> allDecisions = new ArrayList<Crm14FundDecisionModel>();
        for (int i = 1; i <= 5; i++) {
            if (this.isSubformfundingdecision_1_subformisvisible()) {
                Method instanceMethod;
                Crm14FundDecisionModel decision = new Crm14FundDecisionModel();
                try {
                    instanceMethod = this.getClass().getMethod("isSubformfundingdecision_" + i + "_subformisvisible");
                    boolean visible = (boolean) instanceMethod.invoke(this);
                    if (visible) {
                        decision.setSubFormVisible(visible);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_maat_number");
                        String maatNumber = (String) instanceMethod.invoke(this);
                        decision.setMaatNumber(maatNumber);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_case_number");
                        String caseNumber = (String) instanceMethod.invoke(this);
                        decision.setCaseNumber(caseNumber);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_justice_test");
                        String justiceTest = (String) instanceMethod.invoke(this);
                        decision.setJusticeTest(justiceTest);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_justice_test_reasons");
                        String justiceTestReason = (String) instanceMethod.invoke(this);
                        decision.setJusticeTestReasons(justiceTestReason);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_means_test_result_type");
                        String meansResultType = (String) instanceMethod.invoke(this);
                        decision.setMeansTestResultType(meansResultType);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_official_sign_fullname");
                        String officialSignFullName = (String) instanceMethod.invoke(this);
                        decision.setOfficialSignFullName(officialSignFullName);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_appropriate_officer_name");
                        String officerName = (String) instanceMethod.invoke(this);
                        decision.setAppropriateOfficerName(officerName);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_overall_result_magsorcfs");
                        String resultMagsorcfs = (String) instanceMethod.invoke(this);
                        decision.setOverallResultMagsorcfs(resultMagsorcfs);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_appropriate_officer_sign_date");
                        Date officerSignDate = (Date) instanceMethod.invoke(this);
                        decision.setAppropriateOfficerSignDate(officerSignDate);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_means_test_result_appealtocc");
                        String resultAppealToCc = (String) instanceMethod.invoke(this);
                        decision.setMeansTestResultAppealToCc(resultAppealToCc);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_overall_result_non_means");
                        String resultNonMeans = (String) instanceMethod.invoke(this);
                        decision.setOverallResultNonMeans(resultNonMeans);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_means_test_result_magsorcfs");
                        String meansTestresultMagsorcfs = (String) instanceMethod.invoke(this);
                        decision.setMeansTestResultMagsorcfs(meansTestresultMagsorcfs);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_means_test_result_cc");
                        String meansTestResultCc = (String) instanceMethod.invoke(this);
                        decision.setMeansTestResultCc(meansTestResultCc);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_overall_result_appealtocc");
                        String overallResultAppealToCc = (String) instanceMethod.invoke(this);
                        decision.setOverallResultAppealToCc(overallResultAppealToCc);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_overall_result_type");
                        String overallResultType = (String) instanceMethod.invoke(this);
                        decision.setOverallResultType(overallResultType);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_overall_result_cc");
                        String overallResultCc = (String) instanceMethod.invoke(this);
                        decision.setOverallResultCc(overallResultCc);
                        instanceMethod = this.getClass().getMethod("getSubformfundingdecision_" + i + "_official_sign_date");
                        Date officialSignDate = (Date) instanceMethod.invoke(this);
                        decision.setOfficialSignDate(officialSignDate);
                        allDecisions.add(decision);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    log.error("eForm CRM14 unable to call funding decision Method:: urn={} :: {}", urn, e.getMessage());
                }

            }
        }
        return allDecisions;
    }
}