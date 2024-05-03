package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Crm7DetailsModel implements CrmFileDetailsModelInterface {
    @JsonProperty("Proceedings_counsel_instructed")
    public String proceedings_counsel_instructed;
    @JsonProperty("Fc_current_user")
    public String fc_current_user;
    @JsonProperty("Client_total_savings")
    public int client_total_savings;
    @JsonProperty("Client_address_line3")
    public String client_address_line3;
    @JsonProperty("Proceedings_prisonlaw")
    public String proceedings_prisonlaw;
    @JsonProperty("Solicitor_sign_name")
    public String solicitor_sign_name;
    @JsonProperty("Ctd_mileage_cost")
    public int ctd_mileage_cost;
    @JsonProperty("Sd_address_postcode")
    public String sd_address_postcode;
    @JsonProperty("Aa_first_sol_no_claim")
    public boolean aa_first_sol_no_claim;
    @JsonProperty("Originator_tl_passwordneverexpires")
    public int originator_tl_passwordneverexpires;
    @JsonProperty("Proceedings_crowncourtappeal")
    public String proceedings_crowncourtappeal;
    @JsonProperty("Fc_priority")
    public int fc_priority;
    @JsonProperty("Originator_tl_qn_answer_salt")
    public String originator_tl_qn_answer_salt;
    @JsonProperty("Provider_account")
    public String provider_account;
    @JsonProperty("Originator_tl_permanentlyremoved")
    public int originator_tl_permanentlyremoved;
    @JsonProperty("Id_attendance_allowance")
    public int id_attendance_allowance;
    @JsonProperty("Appealed_prev_decision_details")
    public String appealed_prev_decision_details;
    @JsonProperty("TlStateName")
    public String tlStateName;
    @JsonProperty("Originator_emailflag")
    public String originator_emailflag;
    @JsonProperty("Id_dependants_16_plus")
    public int id_dependants_16_plus;
    @JsonProperty("Sd_address_town")
    public String sd_address_town;
    @JsonProperty("Aa_transfer_from_solicitor")
    public boolean aa_transfer_from_solicitor;
    @JsonProperty("Fc_injected")
    public boolean fc_injected;
    @JsonProperty("Decision_original")
    public String decision_original;
    @JsonProperty("Tlmessage")
    public String tlmessage;
    @JsonProperty("Advocacy_threshold_income")
    public int advocacy_threshold_income;
    @JsonProperty("Originator_tl_dtlastfailedloginattempt")
    public Date originator_tl_dtlastfailedloginattempt;
    @JsonProperty("Fc_selected_eformsreviewer")
    public String fc_selected_eformsreviewer;
    @JsonProperty("Proceedings_precharge")
    public String proceedings_precharge;
    @JsonProperty("Client_address_line2")
    public String client_address_line2;
    @JsonProperty("Client_address_line1")
    public String client_address_line1;
    @JsonProperty("Originator_tl_usertype")
    public int originator_tl_usertype;
    @JsonProperty("Ac_travel_time_partfilled")
    public int ac_travel_time_partfilled;
    @JsonProperty("Tlmultimessage")
    public String tlmultimessage;
    @JsonProperty("Ctd_letters")
    public int ctd_letters;
    @JsonProperty("Tltasktype")
    public int tltasktype;
    @JsonProperty("Tlautonumber")
    public int tlautonumber;
    @JsonProperty("Ac_advocacy_time_cost")
    public int ac_advocacy_time_cost;
    @JsonProperty("Fc_processingoffice")
    public String fc_processingoffice;
    @JsonProperty("Ctd_advocacy_costs")
    public int ctd_advocacy_costs;
    @JsonProperty("Ctd_letters_partfilled")
    public int ctd_letters_partfilled;
    @JsonProperty("Tldateorig")
    public Date tldateorig;
    @JsonProperty("Provider_case_ref")
    public String provider_case_ref;
    @JsonProperty("Client_partner_savings")
    public int client_partner_savings;
    @JsonProperty("Advocacy_threshold_inc")
    public int advocacy_threshold_inc;
    @JsonProperty("Caf_court_funding_details")
    public String caf_court_funding_details;
    @JsonProperty("Id_ni_contributions")
    public int id_ni_contributions;
    @JsonProperty("Originator_tl_failedloginattemptcount")
    public int originator_tl_failedloginattemptcount;
    @JsonProperty("Fc_info_returned_flag")
    public boolean fc_info_returned_flag;
    @JsonProperty("Qc_override_reason")
    public String qc_override_reason;
    @JsonProperty("Decision")
    public String decision;
    @JsonProperty("Client_dob")
    public Date client_dob;
    @JsonProperty("Fc_referred")
    public boolean fc_referred;
    @JsonProperty("Appealed_prev_decision")
    public String appealed_prev_decision;
    @JsonProperty("Aa_gap_in_time")
    public boolean aa_gap_in_time;
    @JsonProperty("Ou_signed_auth")
    public String ou_signed_auth;
    @JsonProperty("Fc_qa_required")
    public boolean fc_qa_required;
    @JsonProperty("Originator_selfregistered")
    public int originator_selfregistered;
    @JsonProperty("Aa_within_6_months")
    public String aa_within_6_months;
    @JsonProperty("Id_dependants_under_16")
    public int id_dependants_under_16;
    @JsonProperty("Ctd_mileage_partfilled")
    public int ctd_mileage_partfilled;
    @JsonProperty("Client_forename")
    public String client_forename;
    @JsonProperty("Fc_rfi_options_selected")
    public String fc_rfi_options_selected;
    @JsonProperty("Client_state_benefit")
    public String client_state_benefit;
    @JsonProperty("Ac_waiting_time_cost")
    public int ac_waiting_time_cost;
    @JsonProperty("Show_client_under_18_question")
    public boolean show_client_under_18_question;
    @JsonProperty("Ac_phone_calls")
    public int ac_phone_calls;
    @JsonProperty("Referral_reason_frc_ia")
    public String referral_reason_frc_ia;
    @JsonProperty("Client_address_county")
    public String client_address_county;
    @JsonProperty("Fd_additional_work")
    public String fd_additional_work;
    @JsonProperty("Appeals_and_review")
    public boolean appeals_and_review;
    @JsonProperty("Ctd_phone_calls")
    public int ctd_phone_calls;
    @JsonProperty("Tlseqno")
    public int tlseqno;
    @JsonProperty("Time_received")
    public String time_received;
    @JsonProperty("Lsc_accountoffice")
    public String lsc_accountoffice;
    @JsonProperty("Contact_name")
    public String contact_name;
    @JsonProperty("Originator_routemethod")
    public int originator_routemethod;
    @JsonProperty("Ac_travel_time_cost")
    public int ac_travel_time_cost;
    @JsonProperty("Originator_ntlogon")
    public String originator_ntlogon;
    @JsonProperty("Advocacy_threshold_0")
    public int advocacy_threshold_0;
    @JsonProperty("Advocacy_threshold_2")
    public int advocacy_threshold_2;
    @JsonProperty("Originator_routeoffline")
    public int originator_routeoffline;
    @JsonProperty("Originator_supervisor")
    public String originator_supervisor;
    @JsonProperty("Advocacy_threshold_1")
    public int advocacy_threshold_1;
    @JsonProperty("Lsc_region")
    public String lsc_region;
    @JsonProperty("Ctd_other_cost")
    public int ctd_other_cost;
    @JsonProperty("Ac_preparation_time_partfilled")
    public int ac_preparation_time_partfilled;
    @JsonProperty("Id_partner_weekly_income")
    public int id_partner_weekly_income;
    @JsonProperty("Firm_phone")
    public String firm_phone;
    @JsonProperty("Caf_client_has_cop_rep_order")
    public String caf_client_has_cop_rep_order;
    @JsonProperty("Ac_waiting_time")
    public String ac_waiting_time;
    @JsonProperty("Ac_phone_calls_partfilled")
    public int ac_phone_calls_partfilled;
    @JsonProperty("Fc_dummy")
    public boolean fc_dummy;
    @JsonProperty("Qc_decision")
    public String qc_decision;
    @JsonProperty("Ctd_attendance_time")
    public String ctd_attendance_time;
    @JsonProperty("Originator_dtlastupdated")
    public Date originator_dtlastupdated;
    @JsonProperty("Prev_app_ref")
    public String prev_app_ref;
    @JsonProperty("Ctd_mileage_miles")
    public int ctd_mileage_miles;
    @JsonProperty("Ccrc")
    public boolean ccrc;
    @JsonProperty("Originator_emailaddress")
    public String originator_emailaddress;
    @JsonProperty("Ch_case_history")
    public String ch_case_history;
    @JsonProperty("Fc_reject_reasons_selected")
    public String fc_reject_reasons_selected;
    @JsonProperty("Fd_details_of_work")
    public String fd_details_of_work;
    @JsonProperty("Originator_tl_mustchangepassword")
    public int originator_tl_mustchangepassword;
    @JsonProperty("Fc_possible_pg_reasons")
    public String fc_possible_pg_reasons;
    @JsonProperty("Originator_zone")
    public String originator_zone;
    @JsonProperty("Submitter_language")
    public String submitter_language;
    @JsonProperty("Urgency_reason")
    public String urgency_reason;
    @JsonProperty("Client_marital_status")
    public String client_marital_status;
    @JsonProperty("Ac_attendance_time_cost")
    public int ac_attendance_time_cost;
    @JsonProperty("Ac_waiting_time_partfilled")
    public int ac_waiting_time_partfilled;
    @JsonProperty("Criminal_proceeding")
    public boolean criminal_proceeding;
    @JsonProperty("Originator_tl_facebookid")
    public String originator_tl_facebookid;
    @JsonProperty("Lsc_case_ref")
    public String lsc_case_ref;
    @JsonProperty("Originator_lscareaoffice")
    public String originator_lscareaoffice;
    @JsonProperty("Ac_mileage")
    public int ac_mileage;
    @JsonProperty("Fc_processingofficefax")
    public String fc_processingofficefax;
    @JsonProperty("Advocacy_threshold_base")
    public int advocacy_threshold_base;
    @JsonProperty("Fi_language")
    public String fi_language;
    @JsonProperty("Prison_law")
    public boolean prison_law;
    @JsonProperty("Id_client_weekly_income")
    public int id_client_weekly_income;
    @JsonProperty("Fc_pg_options")
    public String fc_pg_options;
    @JsonProperty("TlTaskLastUpdated")
    public Date tlTaskLastUpdated;
    @JsonProperty("Urgent")
    public String urgent;
    @JsonProperty("Form_type")
    public String form_type;
    @JsonProperty("Fc_decided_by")
    public String fc_decided_by;
    @JsonProperty("Caf_for_expert_report_only")
    public String caf_for_expert_report_only;
    @JsonProperty("Ctd_travel_time_partfilled")
    public int ctd_travel_time_partfilled;
    @JsonProperty("Originator_lscemailaddress")
    public String originator_lscemailaddress;
    @JsonProperty("Advice_threshold_base")
    public int advice_threshold_base;
    @JsonProperty("Id_partner_deductions")
    public int id_partner_deductions;
    @JsonProperty("Fc_decision")
    public String fc_decision;
    @JsonProperty("Tlsubstate")
    public String tlsubstate;
    @JsonProperty("Originator_department")
    public String originator_department;
    @JsonProperty("Aa_cds_provided_same_matter")
    public String aa_cds_provided_same_matter;
    @JsonProperty("Fc_testing")
    public boolean fc_testing;
    @JsonProperty("Id_total_weekly_income")
    public int id_total_weekly_income;
    @JsonProperty("Ctd_waiting_time")
    public String ctd_waiting_time;
    @JsonProperty("Ou_new_limit_request")
    public int ou_new_limit_request;
    @JsonProperty("Ctd_preparation_time")
    public String ctd_preparation_time;
    @JsonProperty("Ctd_waiting_time_cost")
    public int ctd_waiting_time_cost;
    @JsonProperty("Ac_letters_cost")
    public int ac_letters_cost;
    @JsonProperty("Ac_attendance_time")
    public String ac_attendance_time;
    @JsonProperty("Fc_qa_method")
    public String fc_qa_method;
    @JsonProperty("Firm_office")
    public String firm_office;
    @JsonProperty("Advice_threshold_inc")
    public int advice_threshold_inc;
    @JsonProperty("Proceedings_nexthearingdate")
    public Date proceedings_nexthearingdate;
    @JsonProperty("Tlnewmessage")
    public String tlnewmessage;
    @JsonProperty("Solicitor_sign_forename")
    public String solicitor_sign_forename;
    @JsonProperty("Reason_details")
    public String reason_details;
    @JsonProperty("Ctd_phone_calls_partfilled")
    public int ctd_phone_calls_partfilled;
    @JsonProperty("Ac_advocacy_time_partfilled")
    public int ac_advocacy_time_partfilled;
    @JsonProperty("Ctd_travel_time")
    public String ctd_travel_time;
    @JsonProperty("Originator_tl_googleid")
    public String originator_tl_googleid;
    @JsonProperty("Id_total_deductions")
    public int id_total_deductions;
    @JsonProperty("Ctd_phone_calls_cost")
    public int ctd_phone_calls_cost;
    @JsonProperty("Client_ufn")
    public String client_ufn;
    @JsonProperty("Originator_type")
    public int originator_type;
    @JsonProperty("Id_dependants_16_plus_cost")
    public int id_dependants_16_plus_cost;
    @JsonProperty("Ac_mileage_partfilled")
    public int ac_mileage_partfilled;
    @JsonProperty("Client_dependants")
    public int client_dependants;
    @JsonProperty("Certification_sol_name")
    public String certification_sol_name;
    @JsonProperty("Fi_dtreceived")
    public Date fi_dtreceived;
    @JsonProperty("Hide")
    public String hide;
    @JsonProperty("Ctd_waiting_time_partfilled")
    public int ctd_waiting_time_partfilled;
    @JsonProperty("Fi_form_subtype")
    public int fi_form_subtype;
    @JsonProperty("Solicitorname")
    public String solicitorname;
    @JsonProperty("Fc_current_user_type")
    public String fc_current_user_type;
    @JsonProperty("Client_savings")
    public int client_savings;
    @JsonProperty("Advice_threshold_0")
    public int advice_threshold_0;
    @JsonProperty("Ac_other_cost")
    public int ac_other_cost;
    @JsonProperty("Ch_additional_info")
    public String ch_additional_info;
    @JsonProperty("Full_grant_notes")
    public String full_grant_notes;
    @JsonProperty("Advice_threshold_2")
    public int advice_threshold_2;
    @JsonProperty("Advice_threshold_1")
    public int advice_threshold_1;
    @JsonProperty("Caseworker")
    public String caseworker;
    @JsonProperty("Firm_address")
    public String firm_address;
    @JsonProperty("Fc_currentstage")
    public String fc_currentstage;
    @JsonProperty("Originator_dtlastaccessed")
    public Date originator_dtlastaccessed;
    @JsonProperty("Ctd_preparation_costs")
    public int ctd_preparation_costs;
    @JsonProperty("TLActionsOnLine")
    public String tLActionsOnLine;
    @JsonProperty("Originator_tl_qn_answer_hash")
    public String originator_tl_qn_answer_hash;
    @JsonProperty("Id_income_tax")
    public int id_income_tax;
    @JsonProperty("Client_address_town")
    public String client_address_town;
    @JsonProperty("Client_name")
    public String client_name;
    @JsonProperty("Originator_displayname")
    public String originator_displayname;
    @JsonProperty("Ac_letters_partfilled")
    public int ac_letters_partfilled;
    @JsonProperty("Decided_by_original")
    public String decided_by_original;
    @JsonProperty("Ac_attendance_time_partfilled")
    public int ac_attendance_time_partfilled;
    @JsonProperty("Advice_threshold_income")
    public int advice_threshold_income;
    @JsonProperty("Sd_address_line_1")
    public String sd_address_line_1;
    @JsonProperty("Statement_of_case")
    public String statement_of_case;
    @JsonProperty("Sd_address_line_3")
    public String sd_address_line_3;
    @JsonProperty("Sd_address_line_2")
    public String sd_address_line_2;
    @JsonProperty("Ctd_advocacy_time")
    public String ctd_advocacy_time;
    @JsonProperty("Unique_reference_number")
    public String unique_reference_number;
    @JsonProperty("Fc_rfi_text")
    public String fc_rfi_text;
    @JsonProperty("Rbba_category")
    public String rbba_category;
    @JsonProperty("Solicitor_sign_date")
    public Date solicitor_sign_date;
    @JsonProperty("Usn")
    public int usn;
    @JsonProperty("Aa_cds_provided_same_matter_reason")
    public String aa_cds_provided_same_matter_reason;
    @JsonProperty("Fc_info_returned")
    public boolean fc_info_returned;
    @JsonProperty("Originator_notificationmethod")
    public int originator_notificationmethod;
    @JsonProperty("Cds5_auto_reject")
    public int cds5_auto_reject;
    @JsonProperty("Ctd_advocacy_partfilled")
    public int ctd_advocacy_partfilled;
    @JsonProperty("Tltaskid")
    public int tltaskid;
    @JsonProperty("Tltaskstate")
    public int tltaskstate;
    @JsonProperty("Ctd_total_costs")
    public int ctd_total_costs;
    @JsonProperty("Statutory_charge")
    public String statutory_charge;
    @JsonProperty("Originator_jobtitle")
    public String originator_jobtitle;
    @JsonProperty("Id_client_state_benefit")
    public String id_client_state_benefit;
    @JsonProperty("Client_surname")
    public String client_surname;
    @JsonProperty("New_limit_request")
    public int new_limit_request;
    @JsonProperty("Ac_total_costs")
    public int ac_total_costs;
    @JsonProperty("Tlreadonly")
    public int tlreadonly;
    @JsonProperty("Ctd_preparation_partfilled")
    public int ctd_preparation_partfilled;
    @JsonProperty("Firm_supplier_no")
    public String firm_supplier_no;
    @JsonProperty("Fc_processingofficedx")
    public String fc_processingofficedx;
    @JsonProperty("Fc_ok_to_autogrant")
    public boolean fc_ok_to_autogrant;
    @JsonProperty("Class_calc")
    public int class_calc;
    @JsonProperty("Ctd_attendance_cost")
    public int ctd_attendance_cost;
    @JsonProperty("Originator_uniquename")
    public String originator_uniquename;
    @JsonProperty("Originator_id")
    public int originator_id;
    @JsonProperty("Originator_tl_neverpermanentlyremove")
    public int originator_tl_neverpermanentlyremove;
    @JsonProperty("Ac_letters")
    public int ac_letters;
    @JsonProperty("Additional_text")
    public String additional_text;
    @JsonProperty("Ac_preparation_time")
    public String ac_preparation_time;
    @JsonProperty("Aa_given_at_police_station")
    public boolean aa_given_at_police_station;
    @JsonProperty("Total_partfilled")
    public int total_partfilled;
    @JsonProperty("Referral_reason_sol")
    public String referral_reason_sol;
    @JsonProperty("Ac_preparation_time_cost")
    public int ac_preparation_time_cost;
    @JsonProperty("Criminal_investigation")
    public boolean criminal_investigation;
    @JsonProperty("Level_of_work")
    public String level_of_work;
    @JsonProperty("Prev_app_made")
    public String prev_app_made;
    @JsonProperty("Client_under_18")
    public String client_under_18;
    @JsonProperty("Ctd_attendance_partfilled")
    public int ctd_attendance_partfilled;
    @JsonProperty("Id_total_weekly_disp_income")
    public int id_total_weekly_disp_income;
    @JsonProperty("Ac_mileage_cost")
    public int ac_mileage_cost;
    @JsonProperty("Summary_case_history")
    public String summary_case_history;
    @JsonProperty("Ac_advocacy_time")
    public String ac_advocacy_time;
    @JsonProperty("Originator_firm")
    public String originator_firm;
    @JsonProperty("Firm_name")
    public String firm_name;
    @JsonProperty("Fc_rfi_options")
    public String fc_rfi_options;
    @JsonProperty("Fc_reject_reasons_text")
    public String fc_reject_reasons_text;
    @JsonProperty("Fc_initial_processingoffice")
    public String fc_initial_processingoffice;
    @JsonProperty("Fc_decision_explanation")
    public String fc_decision_explanation;
    @JsonProperty("Caf_approached_for_funding")
    public String caf_approached_for_funding;
    @JsonProperty("Fc_processingofficeaddress")
    public String fc_processingofficeaddress;
    @JsonProperty("Client_nfa")
    public boolean client_nfa;
    @JsonProperty("Client_address_search")
    public String client_address_search;
    @JsonProperty("Aa_criteria")
    public String aa_criteria;
    @JsonProperty("Date_received")
    public Date date_received;
    @JsonProperty("Description_of_case")
    public String description_of_case;
    @JsonProperty("Cds5_auto_reject_reason")
    public String cds5_auto_reject_reason;
    @JsonProperty("Prev_app")
    public String prev_app;
    @JsonProperty("Ac_travel_time")
    public String ac_travel_time;
    @JsonProperty("Ctd_letters_cost")
    public int ctd_letters_cost;
    @JsonProperty("Ou_upper_limit_extended")
    public int ou_upper_limit_extended;
    @JsonProperty("Ctd_travel_time_costs")
    public int ctd_travel_time_costs;
    @JsonProperty("Certification_sol_date")
    public Date certification_sol_date;
    @JsonProperty("Fc_cw_requesting")
    public String fc_cw_requesting;
    @JsonProperty("Originator__dtadded")
    public Date originator__dtadded;
    @JsonProperty("Fc_return_info_to_cw")
    public boolean fc_return_info_to_cw;
    @JsonProperty("Id_dependants_under_16_cost")
    public int id_dependants_under_16_cost;
    @JsonProperty("Client_address_postcode")
    public String client_address_postcode;
    @JsonProperty("Match_carried_out")
    public boolean match_carried_out;
    @JsonProperty("Fc_possible_reject_reasons")
    public String fc_possible_reject_reasons;
    @JsonProperty("Solicitorid")
    public String solicitorid;
    @JsonProperty("Ac_phone_calls_cost")
    public int ac_phone_calls_cost;
    @JsonProperty("Sd_address_county")
    public String sd_address_county;
    @JsonProperty("Submitter_user_id")
    public String submitter_user_id;
}