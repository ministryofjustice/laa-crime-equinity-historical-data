package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Crm7DetailsModel implements CrmFileDetailsModelInterface {
    @JsonProperty("Usn") // in crm5 too
    public Long usn;

    // Summary
    @JsonProperty("Client_surname") // in crm5 too
    public String client_surname;
    @JsonProperty("Client_forename") // in crm5 too
    public String client_forename;
    @JsonProperty("Client_dob") // in CRM5 too
    public LocalDate client_dob;
    @JsonProperty("Client_ufn") // in crm5 too
    public String client_ufn;
    @JsonProperty("Maat_number")
    public String maat_number;
    @JsonProperty("Rep_order_no")
    public String rep_order_no;
    @JsonProperty("Rep_order_date")
    public LocalDate rep_order_date;
    @JsonProperty("Cp_rep_order_attach_method") // Post/DX
    public String cp_rep_order_attach_method;
    @JsonProperty("Sc_case_in_designated_area") // Yes/No
    public String sc_case_in_designated_area;
    @JsonProperty("TlStateName") // in CRM5 too
    public String tlStateName;
    @JsonProperty("Sc_outcome_code")
    public String sc_outcome_code;
    @JsonProperty("Sc_matter_type")
    public int sc_matter_type;
    // Summary - Equal Opportunities
    @JsonProperty("Sc_date_class_of_work")
    public LocalDate sc_date_class_of_work;
    @JsonProperty("Sc_no_of_defendants")
    public int sc_no_of_defendants;
    @JsonProperty("Sc_no_of_court_attendances")
    public int sc_no_of_court_attendances;
    @JsonProperty("Sc_court_identifier")
    public String sc_court_identifier;
    @JsonProperty("Sc_youth_court") // Yes/No
    public String sc_youth_court;
    // Summary - Office Use
    @JsonProperty("Sc_eo_1")
    public String sc_eo_1;
    @JsonProperty("Sc_eo_2")
    public String sc_eo_2;
    @JsonProperty("Sc_eo_3") // Y/N?
    public String sc_eo_3;
    @JsonProperty("Sc_prov_case_in_designated_area")
    public String sc_prov_case_in_designated_area;
    @JsonProperty("Tltaskstate") // in crm5 too
    public int tltaskstate;
    @JsonProperty("Sc_eo_code")
    public String sc_eo_code;
    @JsonProperty("Client_name") // in crm5 too
    public String client_name;
    @JsonProperty("Tlsubstate") // in crm5 too
    public String tlsubstate;
    @JsonProperty("Sc_court_indentifier")
    public String sc_court_indentifier;
    /** *** **/

    // Solicitor Details
    @JsonProperty("Firm_name") // in crm5 too
    public String firm_name;
    @JsonProperty("Firm_address") // in crm5 too
    public String firm_address;
    @JsonProperty("Firm_supplier_no") // in crm5 too
    public String firm_supplier_no;
    @JsonProperty("Firm_phone") // in crm5 too
    public String firm_phone;
    @JsonProperty("Contact_name") // in crm5 too
    public String contact_name;
    @JsonProperty("Solicitorname") // in crm5 too
    public String solicitorname;
    @JsonProperty("Solicitorid") // in crm5 too
    public String solicitorid;


    @JsonProperty("Firm_office") // in crm5 too
    public String firm_office;
    /** *** **/

    // Case Details
    @JsonProperty("Urn")
    public String urn;
    @JsonProperty("Cd_main_offence")
    public String cd_main_offence;
    @JsonProperty("Cd_offence_date")
    public LocalDate cd_offence_date;
    @JsonProperty("Cd_serious_fraud_case") // Yes/No
    public String cd_serious_fraud_case;
    @JsonProperty("Cd_indictable_only") // Yes/No
    public String cd_indictable_only;
    @JsonProperty("Cd_indictment_attach_method") // Post/DX
    public String cd_indictment_attach_method;
    @JsonProperty("Cd_wasted_cost_order") // Yes/No?
    public String cd_wasted_cost_order;
    @JsonProperty("Cd_wasted_costs_order_details")
    public String cd_wasted_costs_order_details;
    /** *** **/

    // Non Standard Fee Claim
    @JsonProperty("Reason_details") // in crm5 too
    public String reason_details;

    @JsonProperty("Nsfc_core_costs_exceeded")
    public boolean nsfc_core_costs_exceeded;
    @JsonProperty("Nsfc_enhanced_rates_claimed")
    public boolean nsfc_enhanced_rates_claimed;
    @JsonProperty("Nsfc_counsel_assigned")
    public boolean nsfc_counsel_assigned;
    @JsonProperty("Nsfc_extradition")
    public boolean nsfc_extradition;
    @JsonProperty("Nsfc_rep_order_withdrawn")
    public boolean nsfc_rep_order_withdrawn;
    @JsonProperty("Nsfc_other")
    public boolean nsfc_other;
    @JsonProperty("Nsfc_order_withdrawn_details")
    public String nsfc_order_withdrawn_details;
    /** *** **/

    // Disposals
    @JsonProperty("Cdsp_category")
    public String cdsp_category;

    @JsonProperty("Cdsp_category_1_type")
    public String cdsp_category_1_type;
    @JsonProperty("Cdsp_category_2_type")
    public String cdsp_category_2_type;
    @JsonProperty("Cdsp_category_3_type")
    public String cdsp_category_3_type;

    @JsonProperty("Cdsp_category_1_radio")
    public boolean cdsp_category_1_radio;
    @JsonProperty("Cdsp_category_2_radio")
    public boolean cdsp_category_2_radio;
    @JsonProperty("Cdsp_category_3_radio")
    public boolean cdsp_category_3_radio;

    @JsonProperty("Cdsp_other_details")
    public String cdsp_other_details;
    /** *** **/

    // Claim details
    @JsonProperty("Counsel_instructed_assigned") // Yes/No
    public String counsel_instructed_assigned;
    @JsonProperty("Counsel_instructed_unassigned") // Yes/No
    public String counsel_instructed_unassigned; // Yes/No
    @JsonProperty("Agent_instructed")
    public String agent_instructed;
    @JsonProperty("Cld_prosecution_pages")
    public int cld_prosecution_pages;
    @JsonProperty("Cld_defence_pages")
    public int cld_defence_pages;
    @JsonProperty("Cld_no_of_defence_witnesses")
    public int cld_no_of_defence_witnesses;
    @JsonProperty("Cld_supplemental_claim") // Yes/No
    public String cld_supplemental_claim;
    @JsonProperty("Cld_attach_method") // Post/DX
    public String cld_attach_method;
    @JsonProperty("Cld_taped_evidence")
    public String cld_taped_evidence;
    @JsonProperty("Cld_tape_running_time")
    public String cld_tape_running_time;

    @JsonProperty("Cld_remitted_to_mags") // Yes/No
    public String cld_remitted_to_mags;
    @JsonProperty("Cld_prev_claims_attach_method") // Post/DX
    public String cld_prev_claims_attach_method;
    /** *** **/

    // Pre Order of Work
    @JsonProperty("Pow_claiming") // Yes/No
    public String pow_claiming;
    @JsonProperty("Date_first_hearing")
    public LocalDate date_first_hearing;
    @JsonProperty("Date_received") // in crm5 too
    public LocalDate date_received;

    /** *** **/

    @JsonProperty("Schedule.Row")
    List<Crm7TimeSpentModel> schedule;

    // Claim of Costs
    // Time
    @JsonProperty("Total_travel_time_display")
    public String total_travel_time_display;
    @JsonProperty("Total_waiting_time_display")
    public String total_waiting_time_display;
    @JsonProperty("Total_attendance_time_display")
    public String total_attendance_time_display;
    @JsonProperty("Total_preparation_time_display")
    public String total_preparation_time_display;
    @JsonProperty("Total_advocacy_time_display")
    public String total_advocacy_time_display;
    // Costs
    @JsonProperty("Total_travel_costs")
    public float total_travel_costs;
    @JsonProperty("Total_waiting_costs")
    public float total_waiting_costs;
    @JsonProperty("Total_attendance_costs")
    public float total_attendance_costs;
    @JsonProperty("Total_preparation_costs")
    public float total_preparation_costs;
    @JsonProperty("Total_advocacy_costs")
    public float total_advocacy_costs;
    // Totals
    @JsonProperty("Total_basic_claim")
    public float total_basic_claim;
    @JsonProperty("Total_claim")
    public float total_claim;
    // Totals - Office Use
    @JsonProperty("Cw_total_basic_claim")
    public float cw_total_basic_claim;
    @JsonProperty("Cw_total_claim")
    public float cw_total_claim;
    // Communications
    @JsonProperty("Assessment_reasons")
    public String assessment_reasons;
    // Communications - Totals
    // Letters
    @JsonProperty("Cfc_no_of_letters")
    public int cfc_no_of_letters;
    @JsonProperty("Cfc_letter_rate")
    public float cfc_letter_rate;
    @JsonProperty("Cfc_letter_uplift")
    public float cfc_letter_uplift;
    @JsonProperty("Cfc_total_letter_cost")
    public float cfc_total_letter_cost;
    // Calls
    @JsonProperty("Cfc_no_of_calls")
    public int cfc_no_of_calls;
    @JsonProperty("Cfc_calls_rate")
    public float cfc_calls_rate;
    @JsonProperty("Cfc_cw_calls_uplift")
    public float cfc_cw_calls_uplift;
    @JsonProperty("Cfc_total_phone_costs")
    public float cfc_total_phone_costs;
    // Summary
    @JsonProperty("Cfc_total_costs")
    public float cfc_total_costs;
    @JsonProperty("Cfc_sol_core_costs")
    public float cfc_sol_core_costs;
    // Communications - Office
    // Letters
    @JsonProperty("Cfc_cw_no_of_letters")
    public int cfc_cw_no_of_letters;
    @JsonProperty("Cfc_cw_letters_rate")
    public float cfc_cw_letters_rate;
    @JsonProperty("Cfc_cw_letters_uplift")
    public float cfc_cw_letters_uplift;
    @JsonProperty("Cfc_cw_letters_costs")
    public float cfc_cw_letters_costs;
    // Calls
    @JsonProperty("Cfc_cw_no_of_calls")
    public int cfc_cw_no_of_calls;
    @JsonProperty("Cfc_cw_calls_rate")
    public float cfc_cw_calls_rate;
    @JsonProperty("Cfc_calls_uplift")
    public float cfc_calls_uplift;
    @JsonProperty("Cfc_cw_calls_costs")
    public float cfc_cw_calls_costs;
    // Summary
    @JsonProperty("Cfc_cw_total_costs")
    public float cfc_cw_total_costs;
    @JsonProperty("Cfc_cw_sol_core_costs")
    public float cfc_cw_sol_core_costs;


    @JsonProperty("Total_travel_time")
    public float total_travel_time;
    @JsonProperty("Total_waiting_time")
    public int total_waiting_time;
    @JsonProperty("Total_attendance_time")
    public float total_attendance_time;
    @JsonProperty("Total_preparation_time")
    public String total_preparation_time;
    @JsonProperty("Total_advocacy_time")
    public int total_advocacy_time;

    /** **** **/

    // Disbursements
    @JsonProperty("Cfc_disb_attach_method") // Post/DX
    public String cfc_disb_attach_method;
    // Totals
    @JsonProperty("Cfc_disb_total_net")
    public float cfc_disb_total_net;
    @JsonProperty("Cfc_disb_total_vat")
    public float cfc_disb_total_vat;
    @JsonProperty("Cfc_disb_grand_total")
    public float cfc_disb_grand_total;
    // Office
    @JsonProperty("Cfc_disb_total_net_ou")
    public float cfc_disb_total_net_ou;
    @JsonProperty("Cfc_disb_total_vat_ou")
    public float cfc_disb_total_vat_ou;
    @JsonProperty("Cfc_disb_grand_total_ou")
    public float cfc_disb_grand_total_ou;

    /** **** **/

    // Claim of Costs
    // Total
    @JsonProperty("Ct_profit_costs_vat_rate")
    public float ct_profit_costs_vat_rate;
    @JsonProperty("Ct_profit_costs_total")
    public String ct_profit_costs_total;
    @JsonProperty("Ct_travel_costs_vat_rate")
    public float ct_travel_costs_vat_rate;
    @JsonProperty("Ct_travel_costs_total")
    public float ct_travel_costs_total;
    @JsonProperty("Ct_waiting_costs_vat_rate")
    public float ct_waiting_costs_vat_rate;
    @JsonProperty("Ct_waiting_costs_total")
    public float ct_waiting_costs_total;
    @JsonProperty("Ct_total")
    public float ct_total;
    // Office
    @JsonProperty("Ctou_profit_costs_vat_rate")
    public float ctou_profit_costs_vat_rate;
    @JsonProperty("Ctou_profit_costs_total")
    public float ctou_profit_costs_total;
    @JsonProperty("Ctou_travel_costs_vat_rate")
    public float ctou_travel_costs_vat_rate;
    @JsonProperty("Ctou_travel_costs_total")
    public float ctou_travel_costs_total;
    @JsonProperty("Ctou_waiting_costs_vat_rate")
    public float ctou_waiting_costs_vat_rate;
    @JsonProperty("Ctou_waiting_costs_total")
    public float ctou_waiting_costs_total;
    @JsonProperty("Ct_total_cw")
    public float ct_total_cw;

    /** ***** **/

    // Copversheet
    @JsonProperty("Coversheet_printed")
    public boolean coversheet_printed;

    /** ***** **/

    // Case Information
    @JsonProperty("Relevant_case_info_details")
    public String relevant_case_info_details;
    @JsonProperty("Solicitor_sign_name") // in CRM5
    public String solicitor_sign_name;
    @JsonProperty("Solicitor_sign_date") // in crm5 too
    public LocalDate solicitor_sign_date;
    @JsonProperty("Additional_info")
    public String additional_info;

    /** ***** **/

    // Office Use Only
    // Decision
    @JsonProperty("Decision") // in CRM5 too
    public String decision;

    /** ***** **/

    // Unmapped
    @JsonProperty("Phone_call_rate_london")
    public float phone_call_rate_london;
    @JsonProperty("Phone_call_rate_non_london")
    public float phone_call_rate_non_london;
    @JsonProperty("Fc_current_user") // in CRM5
    public String fc_current_user;
    @JsonProperty("Fc_priority") // CRM5 too
    public int fc_priority;
    @JsonProperty("Fc_injected") // in CRM5 too
    public boolean fc_injected;
    @JsonProperty("Fc_selected_eformsreviewer") // in CRM5 too
    public String fc_selected_eformsreviewer;
    @JsonProperty("Fc_is_highcourt")
    public String fc_is_highcourt;
    @JsonProperty("Fc_processingoffice")
    public String fc_processingoffice;
    @JsonProperty("Fc_info_returned_flag") // in CRM5 too
    public boolean fc_info_returned_flag;
    @JsonProperty("Fc_referred") // in CRM5 too
    public boolean fc_referred;
    @JsonProperty("Fc_qa_required") // in CRM5 too
    public boolean fc_qa_required;
    @JsonProperty("Fc_rfi_options_selected") // in crm5 too
    public String fc_rfi_options_selected;
    @JsonProperty("Fc_integration_info_required")
    public boolean fc_integration_info_required;
    @JsonProperty("Fc_dummy") // in crm5 too
    public boolean fc_dummy;
    @JsonProperty("Fc_reject_reasons_selected") // in crm5 too
    public String fc_reject_reasons_selected;
    @JsonProperty("Fc_possible_pg_reasons") // in crm5 too
    public String fc_possible_pg_reasons;
    @JsonProperty("Fc_processingofficefax") // in crm5 too
    public String fc_processingofficefax;
    @JsonProperty("Fc_pg_options") // in crm5 too
    public String fc_pg_options;
    @JsonProperty("Fc_decided_by") // in crm5 too
    public String fc_decided_by;
    @JsonProperty("Fc_decision") // in crm5 too
    public String fc_decision;
    @JsonProperty("Fc_testing") // in crm5 too
    public boolean fc_testing;
    @JsonProperty("Fc_qa_method") // in crm5 too
    public String fc_qa_method;
    @JsonProperty("Fc_current_user_type") // in crm5 too
    public String fc_current_user_type;
    @JsonProperty("Fc_currentstage") // in crm5 too
    public String fc_currentstage;
    @JsonProperty("Fc_rfi_text") // in crm5 too
    public String fc_rfi_text;
    @JsonProperty("Fc_info_returned") // in crm5 too
    public boolean fc_info_returned;
    @JsonProperty("Fc_processingofficedx") // in crm5 too
    public String fc_processingofficedx;
    @JsonProperty("Fc_ok_to_autogrant") // in crm5 too
    public boolean fc_ok_to_autogrant;
    @JsonProperty("Fc_rfi_options") // in crm5 too
    public String fc_rfi_options;
    @JsonProperty("Fc_reject_reasons_text") // in crm5 too
    public String fc_reject_reasons_text;
    @JsonProperty("Fc_initial_processingoffice") // in crm5 too
    public String fc_initial_processingoffice;
    @JsonProperty("Fc_decision_explanation") // in crm5 too
    public String fc_decision_explanation;
    @JsonProperty("Fc_processingofficeaddress") // in crm5 too
    public String fc_processingofficeaddress;
    @JsonProperty("Fc_cw_requesting") // in crm5 too
    public String fc_cw_requesting;
    @JsonProperty("Fc_return_info_to_cw") // in crm5 too
    public boolean fc_return_info_to_cw;
    @JsonProperty("Fc_possible_reject_reasons") // in crm5 too
    public String fc_possible_reject_reasons;
    @JsonProperty("Cp_rbba_category")
    public String cp_rbba_category;
    @JsonProperty("Cp_rbba_category_cw")
    public float cp_rbba_category_cw;
    @JsonProperty("Cp_rbba_category_before_qc")
    public String cp_rbba_category_before_qc;
    @JsonProperty("Sd_address_postcode") // in CRM5 too
    public String sd_address_postcode;
    @JsonProperty("Sd_address_town")  // in CRM5 too
    public String sd_address_town;
    @JsonProperty("Sd_address_line_1") // in crm5 too
    public String sd_address_line_1;
    @JsonProperty("Sd_address_line_3") // in crm5 too
    public String sd_address_line_3;
    @JsonProperty("Sd_address_line_2") // in crm5 too
    public String sd_address_line_2;
    @JsonProperty("Sd_address_county") // in crm5 too
    public String sd_address_county;
    @JsonProperty("Originator_tl_passwordneverexpires") // CRM5 too
    public int originator_tl_passwordneverexpires;
    @JsonProperty("Originator_tl_qn_answer_salt") // CRM5 too
    public String originator_tl_qn_answer_salt;
    @JsonProperty("Originator_tl_permanentlyremoved") // CRM5 too
    public int originator_tl_permanentlyremoved;
    @JsonProperty("Originator_emailflag") // in CRM5 too
    public String originator_emailflag;
    @JsonProperty("Originator_tl_usertype") // in CRM5 too
    public int originator_tl_usertype;
    @JsonProperty("Originator_tl_failedloginattemptcount") // in CRM5 too
    public int originator_tl_failedloginattemptcount;
    @JsonProperty("Originator_selfregistered") // in CRM5 too
    public int originator_selfregistered;
    @JsonProperty("Originator_routemethod") // in crm5 too
    public int originator_routemethod;
    @JsonProperty("Originator_ntlogon") // in crm5 too
    public String originator_ntlogon;
    @JsonProperty("Originator_routeoffline") //  in crm5 too
    public int originator_routeoffline;
    @JsonProperty("Originator_supervisor") // in crm5 too
    public String originator_supervisor;
    @JsonProperty("Originator_dtlastupdated") // in crm5 too
    public LocalDateTime originator_dtlastupdated;
    @JsonProperty("Originator_emailaddress") // in crm5 too
    public String originator_emailaddress;
    @JsonProperty("Originator_tl_mustchangepassword") // in crm5 too
    public int originator_tl_mustchangepassword;
    @JsonProperty("Originator_zone") // in crm5 too
    public String originator_zone;
    @JsonProperty("Originator_tl_facebookid") // in crm5 too
    public String originator_tl_facebookid;
    @JsonProperty("Originator_lscareaoffice") // in crm5 too
    public String originator_lscareaoffice;
    @JsonProperty("Originator_lscemailaddress") // in crm5 too
    public String originator_lscemailaddress;
    @JsonProperty("Originator_department") // in crm5 too
    public String originator_department;
    @JsonProperty("Originator_tl_googleid") // in crm5 too
    public String originator_tl_googleid;
    @JsonProperty("Originator_type") // in crm5 too
    public int originator_type;
    @JsonProperty("Originator_dtlastaccessed") // in crm5 too
    public LocalDateTime originator_dtlastaccessed;
    @JsonProperty("Originator_tl_qn_answer_hash") // in crm5 too
    public String originator_tl_qn_answer_hash;
    @JsonProperty("Originator_displayname") // in crm5 too
    public String originator_displayname;
    @JsonProperty("Originator_notificationmethod") // in crm5 too
    public int originator_notificationmethod;
    @JsonProperty("Originator_jobtitle") // in crm5 too
    public String originator_jobtitle;
    @JsonProperty("Originator_uniquename") // in crm5 too
    public String originator_uniquename;
    @JsonProperty("Originator_id") // in crm5 too
    public int originator_id;
    @JsonProperty("Originator_tl_neverpermanentlyremove") // in crm5 too
    public int originator_tl_neverpermanentlyremove;
    @JsonProperty("Originator_firm") // in crm5 too
    public String originator_firm;
    @JsonProperty("Originator__dtadded") // in crm5 too
    public LocalDate originator__dtadded;
    @JsonProperty("Sol_cert")
    public boolean sol_cert;
    @JsonProperty("Provider_account") // CRM5 too
    public String provider_account;
    @JsonProperty("Provider_case_ref") // in CRM5 too
    public String provider_case_ref;
    @JsonProperty("Decision_original") // in CRM5 too
    public String decision_original;
    @JsonProperty("Tlmessage") // in CRM5 too
    public String tlmessage;
    @JsonProperty("Tlmultimessage") // in CRM5 too
    public String tlmultimessage;
    @JsonProperty("TLProjectUniqueName")
    public String tLProjectUniqueName;
    @JsonProperty("Tltasktype") // in CRM5 too
    public int tltasktype;
    @JsonProperty("Tlautonumber") // in CRM5 too
    public int tlautonumber;
    @JsonProperty("Tldateorig") // in CRM5 too
    public LocalDateTime tldateorig;
    @JsonProperty("TLRecoveryLoad")
    public boolean tLRecoveryLoad;
    @JsonProperty("Tlseqno") // in crm5 too
    public int tlseqno;
    @JsonProperty("TLProjectName")
    public String tLProjectName;
    @JsonProperty("TlTaskLastUpdated") // in crm5 too
    public LocalDateTime tlTaskLastUpdated;
    @JsonProperty("Tlnewmessage") // in crm5 too
    public String tlnewmessage;
    @JsonProperty("TLActionsOnLine") // in crm5 too
    public String tLActionsOnLine;
    @JsonProperty("Tltaskid") // in crm5 too
    public int tltaskid;
    @JsonProperty("Tlreadonly") // in crm5 too
    public int tlreadonly;
    @JsonProperty("Total_attendance_time_display_no_counsel")
    public String total_attendance_time_display_no_counsel;
    @JsonProperty("Total_attendance_costs_no_counsel")
    public float total_attendance_costs_no_counsel;
    @JsonProperty("Total_uplift")
    public float total_uplift;
    @JsonProperty("Total_attendance_time_display_counsel")
    public String total_attendance_time_display_counsel;
    @JsonProperty("Total_attendance_costs_counsel")
    public float total_attendance_costs_counsel;
    @JsonProperty("Total_attendance_time_no_counsel")
    public float total_attendance_time_no_counsel;
    @JsonProperty("Total_attendance_time_counsel")
    public float total_attendance_time_counsel;
    @JsonProperty("Cat1london")
    public float cat1london;
    @JsonProperty("Cat1national")
    public float cat1national;
    @JsonProperty("Cat2london")
    public float cat2london;
    @JsonProperty("Cat2national")
    public float cat2national;
    @JsonProperty("Cat3london")
    public float cat3london;
    @JsonProperty("Cat3national")
    public float cat3national;
    @JsonProperty("Calc")
    public int calc;
    @JsonProperty("Qc_decision") // in crm5 too
    public String qc_decision;
    @JsonProperty("Qc_override_reason") // in CRM5 too
    public String qc_override_reason;
    @JsonProperty("Ou_signed_auth") // in CRM5 too
    public String ou_signed_auth;
    @JsonProperty("Actioncode")
    public String actioncode;
    @JsonProperty("Attendance_rate_london")
    public float attendance_rate_london;
    @JsonProperty("Attendance_rate_non_london")
    public float attendance_rate_non_london;
    @JsonProperty("Attendance_rate_counsel_london")
    public float attendance_rate_counsel_london;
    @JsonProperty("Attendance_rate_counsel_non_london")
    public float attendance_rate_counsel_non_london;
    @JsonProperty("Attendance_rate_no_counsel_non_london")
    public float attendance_rate_no_counsel_non_london;
    @JsonProperty("Cw_total_attendance_costs")
    public float cw_total_attendance_costs;
    @JsonProperty("Cw_total_travel_costs")
    public float cw_total_travel_costs;
    @JsonProperty("Cw_total_attendance_costs_no_counsel")
    public float cw_total_attendance_costs_no_counsel;
    @JsonProperty("Cw_total_advocacy_costs")
    public float cw_total_advocacy_costs;
    @JsonProperty("Cw_total_preparation_costs")
    public float cw_total_preparation_costs;
    @JsonProperty("Cw_total_waiting_costs")
    public float cw_total_waiting_costs;
    @JsonProperty("Cw_total_attendance_costs_counsel")
    public float cw_total_attendance_costs_counsel;
    @JsonProperty("Referral_reason_frc_ia") // in crm5 too
    public String referral_reason_frc_ia;
    @JsonProperty("Referral_reason_sol") // in crm5 too
    public String referral_reason_sol;
    @JsonProperty("Time_received")  // in crm5 too (maybe change to Time)
    public String time_received;
    @JsonProperty("Lsc_accountoffice") // in crm5 too
    public String lsc_accountoffice;
    @JsonProperty("Lsc_region") // in crm5 too
    public String lsc_region;
    @JsonProperty("Lsc_case_ref") // in crm5 too
    public String lsc_case_ref;
    @JsonProperty("Date_submitted")
    public LocalDate date_submitted;
    @JsonProperty("Submitter_language") // in crm5 too
    public String submitter_language;
    @JsonProperty("Submitter_user_id") // in crm5 too
    public String submitter_user_id;
    @JsonProperty("Maat")
    public String maat;
    @JsonProperty("Travel_rate_non_london")
    public float travel_rate_non_london;
    @JsonProperty("Travel_rate_london")
    public float travel_rate_london;
    @JsonProperty("Waiting_rate_non_london")
    public float waiting_rate_non_london;
    @JsonProperty("Waiting_rate_london")
    public float waiting_rate_london;
    @JsonProperty("Advocacy_rate_non_london")
    public float advocacy_rate_non_london;
    @JsonProperty("Advocacy_rate_london")
    public float advocacy_rate_london;
    @JsonProperty("Preparation_rate_non_london")
    public float preparation_rate_non_london;
    @JsonProperty("Preparation_rate_london")
    public float preparation_rate_london;
    @JsonProperty("Letter_rate_non_london")
    public float letter_rate_non_london;
    @JsonProperty("Letter_rate_london")
    public float letter_rate_london;
    @JsonProperty("Fi_language") // in crm5 too
    public String fi_language;
    @JsonProperty("Fi_dtreceived") // in crm5 too
    public LocalDateTime fi_dtreceived;
    @JsonProperty("Fi_form_subtype") // in crm5 too
    public String fi_form_subtype;
    @JsonProperty("Form_type") // in crm5 too
    public String form_type;
    @JsonProperty("Form_version")
    public int form_version;
    @JsonProperty("Showrbbawarning")
    public boolean showrbbawarning;
    @JsonProperty("Hide") // in crm5 too
    public String hide;
    @JsonProperty("Full_grant_notes") // in crm5 too
    public String full_grant_notes;
    @JsonProperty("Caseworker") // in crm5 too
    public String caseworker;
    @JsonProperty("Decided_by_original") // in crm5 too
    public String decided_by_original;
    @JsonProperty("Prog")
    public String prog;
    @JsonProperty("Prog_prov")
    public String prog_prov;
    @JsonProperty("Emailto")
    public String emailto;
    @JsonProperty("Taskid")
    public long taskid;
    @JsonProperty("Unique_reference_number") // in crm5 too
    public String unique_reference_number;
    @JsonProperty("Rbba_category") // in crm5 too
    public String rbba_category;
    @JsonProperty("Statutory_charge") // in crm5 too
    public String statutory_charge;
    @JsonProperty("Printed")
    public boolean printed;
    @JsonProperty("Prom")
    public String prom;
    @JsonProperty("Prom_prov")
    public String prom_prov;
    @JsonProperty("Voucherrequired_count")
    public int voucherrequired_count;
    @JsonProperty("Match_carried_out") // in crm5 too
    public boolean match_carried_out;
}