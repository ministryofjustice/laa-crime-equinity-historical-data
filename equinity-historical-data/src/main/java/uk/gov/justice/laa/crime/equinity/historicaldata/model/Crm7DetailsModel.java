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
    @JsonProperty("Ctou_profit_costs_total")
    public float ctou_profit_costs_total;
    @JsonProperty("Cfc_disb_grand_total_ou")
    public float cfc_disb_grand_total_ou;
    @JsonProperty("Ctou_travel_costs_total")
    public float ctou_travel_costs_total;


    @JsonProperty("Client_name") // in crm5 too
    public String client_name;
    @JsonProperty("Tlsubstate") // in crm5 too
    public String tlsubstate;
    @JsonProperty("Sc_court_indentifier")
    public int sc_court_indentifier;
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



    @JsonProperty("Phone_call_rate_london")
    public float phone_call_rate_london;
    @JsonProperty("Fc_current_user") // in CRM5
    public String fc_current_user;
    @JsonProperty("Cp_rbba_category")
    public String cp_rbba_category;
    @JsonProperty("Cld_taped_evidence")
    public String cld_taped_evidence;
    @JsonProperty("Cfc_cw_sol_core_costs")
    public float cfc_cw_sol_core_costs;
    @JsonProperty("Total_attendance_costs")
    public float total_attendance_costs;
    @JsonProperty("Cfc_cw_letters_costs")
    public float cfc_cw_letters_costs;
    @JsonProperty("Nsfc_extradition")
    public boolean Nsfc_extradition;
    @JsonProperty("Solicitor_sign_name") // in CRM5
    public String solicitor_sign_name;
    @JsonProperty("Date_first_hearing")
    public LocalDate Date_first_hearing;
    @JsonProperty("Sd_address_postcode") // in CRM5 too
    public String sd_address_postcode;
    @JsonProperty("Cld_attach_method")
    public String cld_attach_method;

    @JsonProperty("Cfc_total_costs")
    public float cfc_total_costs;
    @JsonProperty("Originator_tl_passwordneverexpires") // CRM5 too
    public int originator_tl_passwordneverexpires;
    @JsonProperty("Sol_cert")
    public boolean sol_cert;
    @JsonProperty("Fc_priority") // CRM5 too
    public int fc_priority;
    @JsonProperty("Originator_tl_qn_answer_salt") // CRM5 too
    public String originator_tl_qn_answer_salt;
    @JsonProperty("Provider_account") // CRM5 too
    public String provider_account;
    @JsonProperty("Originator_tl_permanentlyremoved") // CRM5 too
    public int originator_tl_permanentlyremoved;
    @JsonProperty("Cfc_cw_letters_rate")
    public float cfc_cw_letters_rate;
    @JsonProperty("Prom_prov")
    public String prom_prov;
    @JsonProperty("Additional_info")
    public String additional_info;

    @JsonProperty("Counsel_instructed_unassigned")
    public String counsel_instructed_unassigned;
    @JsonProperty("Originator_emailflag") // in CRM5 too
    public String originator_emailflag;
    @JsonProperty("Sd_address_town")  // in CRM5 too
    public String sd_address_town;
    @JsonProperty("Total_advocacy_time")
    public int total_advocacy_time;
    @JsonProperty("Cfc_disb_grand_total")
    public float cfc_disb_grand_total;
    @JsonProperty("Fc_injected") // in CRM5 too
    public boolean fc_injected;
    @JsonProperty("Cfc_cw_no_of_letters")
    public int cfc_cw_no_of_letters;
    @JsonProperty("Decision_original") // in CRM5 too
    public String decision_original;
    @JsonProperty("Tlmessage") // in CRM5 too
    public String tlmessage;
    @JsonProperty("Cat3london")
    public float cat3london;
    @JsonProperty("Total_waiting_costs")
    public float total_waiting_costs;

    @JsonProperty("Total_attendance_time_display_no_counsel")
    public String total_attendance_time_display_no_counsel;
    @JsonProperty("Fc_selected_eformsreviewer") // in CRM5 too
    public String fc_selected_eformsreviewer;
    @JsonProperty("Total_advocacy_time_display")
    public String total_advocacy_time_display;
    @JsonProperty("Originator_tl_usertype") // in CRM5 too
    public int originator_tl_usertype;
    @JsonProperty("Tlmultimessage") // in CRM5 too
    public String tlmultimessage;
    @JsonProperty("TLProjectUniqueName")
    public String tLProjectUniqueName;
    @JsonProperty("Tltasktype") // in CRM5 too
    public int tltasktype;
    @JsonProperty("Tlautonumber") // in CRM5 too
    public int tlautonumber;
    @JsonProperty("Total_preparation_time_display")
    public String total_preparation_time_display;
    @JsonProperty("Fc_is_highcourt")
    public String fc_is_highcourt;
    @JsonProperty("Fc_processingoffice")
    public String fc_processingoffice;
    @JsonProperty("Cw_total_basic_claim")
    public float cw_total_basic_claim;
    @JsonProperty("Agent_instructed")
    public String agent_instructed;
    @JsonProperty("Cat2national")
    public float cat2national;
    @JsonProperty("Tldateorig") // in CRM5 too
    public LocalDateTime tldateorig;
    @JsonProperty("Phone_call_rate_non_london")
    public float phone_call_rate_non_london;
    @JsonProperty("Provider_case_ref") // in CRM5 too
    public String provider_case_ref;
    @JsonProperty("Cd_wasted_costs_order_details")
    public String cd_wasted_costs_order_details;

    @JsonProperty("Cfc_disb_total_net")
    public float cfc_disb_total_net;
    @JsonProperty("Cfc_cw_letters_uplift")
    public float cfc_cw_letters_uplift;
    @JsonProperty("TLRecoveryLoad")
    public boolean tLRecoveryLoad;
    @JsonProperty("Originator_tl_failedloginattemptcount") // in CRM5 too
    public int originator_tl_failedloginattemptcount;
    @JsonProperty("Calc")
    public int calc;
    @JsonProperty("Cfc_letter_rate")
    public float cfc_letter_rate;
    @JsonProperty("Fc_info_returned_flag") // in CRM5 too
    public boolean fc_info_returned_flag;
    @JsonProperty("Cfc_cw_calls_costs")
    public float cfc_cw_calls_costs;
    @JsonProperty("Qc_override_reason") // in CRM5 too
    public String qc_override_reason;
    @JsonProperty("Decision") // in CRM5 too
    public String decision;

    @JsonProperty("Fc_referred") // in CRM5 too
    public boolean fc_referred;
    @JsonProperty("Cfc_sol_core_costs")
    public float cfc_sol_core_costs;
    @JsonProperty("Cld_remitted_to_mags") // Yes/No?
    public String cld_remitted_to_mags;
    @JsonProperty("Ct_travel_costs_total")
    public float ct_travel_costs_total;
    @JsonProperty("Ou_signed_auth") // in CRM5 too
    public String ou_signed_auth;
    @JsonProperty("Fc_qa_required") // in CRM5 too
    public boolean fc_qa_required;
    @JsonProperty("Total_waiting_time")
    public int total_waiting_time;
    @JsonProperty("Originator_selfregistered") // in CRM5 too
    public int originator_selfregistered;
    @JsonProperty("Ctou_waiting_costs_vat_rate")
    public float Ctou_waiting_costs_vat_rate;
    @JsonProperty("Actioncode")
    public String actioncode;
    @JsonProperty("Cd_indictable_only") // Yes/No?
    public String cd_indictable_only;
    @JsonProperty("Total_claim")
    public float total_claim;
    @JsonProperty("Attendance_rate_london")
    public float attendance_rate_london;

    @JsonProperty("Cp_rbba_category_cw")
    public float pp_rbba_category_cw;
    @JsonProperty("Total_waiting_time_display")
    public String total_waiting_time_display;
    @JsonProperty("Total_travel_costs")
    public float total_travel_costs;
    @JsonProperty("Total_attendance_costs_no_counsel")
    public float total_attendance_costs_no_counsel;
    @JsonProperty("Counsel_instructed_assigned") // Yes/No?
    public String counsel_instructed_assigned;

    @JsonProperty("Fc_rfi_options_selected") // in crm5 too
    public String fc_rfi_options_selected;
    @JsonProperty("Coversheet_printed")
    public boolean coversheet_printed;
    @JsonProperty("Cfc_no_of_letters")
    public int cfc_no_of_letters;
    @JsonProperty("Cw_total_attendance_costs")
    public float cw_total_attendance_costs;
    @JsonProperty("Referral_reason_frc_ia") // in crm5 too
    public String referral_reason_frc_ia;
    @JsonProperty("Tlseqno") // in crm5 too
    public int tlseqno;
    @JsonProperty("Cat2london")
    public float cat2london;
    @JsonProperty("Time_received")  // in crm5 too (maybe change to Time)
    public String time_received;
    @JsonProperty("Lsc_accountoffice") // in crm5 too
    public String lsc_accountoffice;

    @JsonProperty("Originator_routemethod") // in crm5 too
    public int originator_routemethod;
    @JsonProperty("Cd_indictment_attach_method") // Post/DX?
    public String cd_indictment_attach_method;
    @JsonProperty("Date_submitted")
    public LocalDate date_submitted;
    @JsonProperty("Cw_total_travel_costs")
    public float cw_total_travel_costs;
    @JsonProperty("Originator_ntlogon") // in crm5 too
    public String originator_ntlogon;
    @JsonProperty("Originator_routeoffline") //  in crm5 too
    public int originator_routeoffline;
    @JsonProperty("Originator_supervisor") // in crm5 too
    public String originator_supervisor;
    @JsonProperty("Lsc_region") // in crm5 too
    public String lsc_region;
    @JsonProperty("Total_attendance_time_display")
    public String total_attendance_time_display;
    @JsonProperty("Pow_claiming") // Yes/No?
    public String pow_claiming;
    @JsonProperty("Cfc_calls_uplift")
    public float cfc_calls_uplift;
    @JsonProperty("Cfc_disb_total_vat_ou")
    public float cfc_disb_total_vat_ou;
    @JsonProperty("Fc_integration_info_required")
    public boolean fc_integration_info_required;

    @JsonProperty("Fc_dummy") // in crm5 too
    public boolean fc_dummy;
    @JsonProperty("Qc_decision") // in crm5 too
    public String qc_decision;
    @JsonProperty("Originator_dtlastupdated") // in crm5 too
    public LocalDateTime originator_dtlastupdated;
    @JsonProperty("Cfc_no_of_calls")
    public int cfc_no_of_calls;
    @JsonProperty("Originator_emailaddress") // in crm5 too
    public String originator_emailaddress;
    @JsonProperty("TLProjectName")
    public String tLProjectName;

    @JsonProperty("Fc_reject_reasons_selected") // in crm5 too
    public String fc_reject_reasons_selected;
    @JsonProperty("Cd_wasted_cost_order") // Yes/No?
    public String cd_wasted_cost_order;
    @JsonProperty("Nsfc_core_costs_exceeded")
    public boolean nsfc_core_costs_exceeded;
    @JsonProperty("Originator_tl_mustchangepassword") // in crm5 too
    public int originator_tl_mustchangepassword;
    @JsonProperty("Total_basic_claim")
    public float total_basic_claim;
    @JsonProperty("Total_uplift")
    public float total_uplift;
    @JsonProperty("Fc_possible_pg_reasons") // in crm5 too
    public String fc_possible_pg_reasons;
    @JsonProperty("Originator_zone") // in crm5 too
    public String originator_zone;
    @JsonProperty("Submitter_language") // in crm5 too
    public String submitter_language;
    @JsonProperty("Maat")
    public String maat;
    @JsonProperty("Cw_total_attendance_costs_no_counsel")
    public float cw_total_attendance_costs_no_counsel;
    @JsonProperty("Cdsp_category_2_radio")
    public boolean cdsp_category_2_radio;
    @JsonProperty("Cw_total_claim")
    public float cw_total_claim;
    @JsonProperty("Originator_tl_facebookid") // in crm5 too
    public String originator_tl_facebookid;
    @JsonProperty("Lsc_case_ref") // in crm5 too
    public String lsc_case_ref;
    @JsonProperty("Cat1london")
    public float cat1london;
    @JsonProperty("Travel_rate_non_london")
    public float travel_rate_non_london;
    @JsonProperty("Originator_lscareaoffice") // in crm5 too
    public String originator_lscareaoffice;
    @JsonProperty("Waiting_rate_non_london")
    public float waiting_rate_non_london;
    @JsonProperty("Nsfc_order_withdrawn_details")
    public String nsfc_order_withdrawn_details;
    @JsonProperty("Fc_processingofficefax") // in crm5 too
    public String fc_processingofficefax;
    @JsonProperty("Ct_profit_costs_total")
    public String ct_profit_costs_total;
    @JsonProperty("Fi_language") // in crm5 too
    public String fi_language;

    @JsonProperty("Ctou_profit_costs_vat_rate")
    public float ctou_profit_costs_vat_rate;
    @JsonProperty("Fc_pg_options") // in crm5 too
    public String fc_pg_options;
    @JsonProperty("TlTaskLastUpdated") // in crm5 too
    public LocalDateTime tlTaskLastUpdated;
    @JsonProperty("Nsfc_counsel_assigned")
    public boolean nsfc_counsel_assigned;
    @JsonProperty("Ct_total")
    public float ct_total;
    @JsonProperty("Ct_total_cw")
    public float ct_total_cw;
    @JsonProperty("Form_type") // in crm5 too
    public String form_type;
    @JsonProperty("Fc_decided_by") // in crm5 too
    public String fc_decided_by;
    @JsonProperty("Cdsp_category_1_radio")
    public boolean cdsp_category_1_radio;
    @JsonProperty("Attendance_rate_non_london")
    public float attendance_rate_non_london;
    @JsonProperty("Originator_lscemailaddress") // in crm5 too
    public String originator_lscemailaddress;
    @JsonProperty("Fc_decision") // in crm5 too
    public String fc_decision;
    @JsonProperty("Cld_defence_pages")
    public int cld_defence_pages;

    @JsonProperty("Total_attendance_time_display_counsel")
    public String total_attendance_time_display_counsel;

    @JsonProperty("Schedule.Row")
    List<Crm7TimeSpentModel> schedule;

    @JsonProperty("Originator_department") // in crm5 too
    public String originator_department;
    @JsonProperty("Nsfc_rep_order_withdrawn")
    public boolean nsfc_rep_order_withdrawn;
    @JsonProperty("Fc_testing") // in crm5 too
    public boolean fc_testing;
    @JsonProperty("Travel_rate_london")
    public float travel_rate_london;

    @JsonProperty("Assessment_reasons")
    public String assessment_reasons;
    @JsonProperty("Fc_qa_method") // in crm5 too
    public String fc_qa_method;
    @JsonProperty("Cfc_cw_calls_rate")
    public float cfc_cw_calls_rate;
    @JsonProperty("Cw_total_advocacy_costs")
    public float cw_total_advocacy_costs;

    @JsonProperty("Cat3national")
    public float cat3national;

    @JsonProperty("Advocacy_rate_non_london")
    public float advocacy_rate_non_london;
    @JsonProperty("Tlnewmessage") // in crm5 too
    public String tlnewmessage;
    @JsonProperty("Preparation_rate_non_london")
    public float preparation_rate_non_london;
    @JsonProperty("Reason_details") // in crm5 too
    public String reason_details;
    @JsonProperty("Nsfc_enhanced_rates_claimed")
    public boolean nsfc_enhanced_rates_claimed;
    @JsonProperty("Cfc_letter_uplift")
    public float Cfc_letter_uplift;
    @JsonProperty("Ctou_waiting_costs_total")
    public float ctou_waiting_costs_total;

    @JsonProperty("Relevant_case_info_details")
    public String relevant_case_info_details;
    @JsonProperty("Originator_tl_googleid") // in crm5 too
    public String originator_tl_googleid;


    @JsonProperty("Cd_serious_fraud_case") // Yes/No?
    public String cd_serious_fraud_case;
    @JsonProperty("Originator_type") // in crm5 too
    public int originator_type;
    @JsonProperty("Cfc_total_phone_costs")
    public float cfc_total_phone_costs;
    @JsonProperty("Showrbbawarning")
    public boolean showrbbawarning;
    @JsonProperty("Waiting_rate_london")
    public float waiting_rate_london;
    @JsonProperty("Total_attendance_costs_counsel")
    public float total_attendance_costs_counsel;
    @JsonProperty("Cdsp_category_1_type")
    public String cdsp_category_1_type;
    @JsonProperty("Total_travel_time_display")
    public String total_travel_time_display;
    @JsonProperty("Cfc_disb_total_net_ou")
    public float cfc_disb_total_net_ou;
    @JsonProperty("Cfc_total_letter_cost")
    public float cfc_total_letter_cost;
    @JsonProperty("Cdsp_category_3_radio")
    public boolean cdsp_category_3_radio;
    @JsonProperty("Fi_dtreceived") // in crm5 too
    public LocalDateTime fi_dtreceived;
    @JsonProperty("Hide") // in crm5 too
    public String hide;
    @JsonProperty("Ct_profit_costs_vat_rate")
    public float ct_profit_costs_vat_rate;
    @JsonProperty("Fi_form_subtype") // in crm5 too
    public String fi_form_subtype;

    @JsonProperty("Attendance_rate_counsel_london")
    public float attendance_rate_counsel_london;
    @JsonProperty("Fc_current_user_type") // in crm5 too
    public String fc_current_user_type;
    @JsonProperty("Full_grant_notes") // in crm5 too
    public String full_grant_notes;
    @JsonProperty("Caseworker") // in crm5 too
    public String caseworker;

    @JsonProperty("Fc_currentstage") // in crm5 too
    public String fc_currentstage;
    @JsonProperty("Cw_total_preparation_costs")
    public float cw_total_preparation_costs;

    @JsonProperty("Originator_dtlastaccessed") // in crm5 too
    public LocalDateTime originator_dtlastaccessed;
    @JsonProperty("Urn")
    public String urn;
    @JsonProperty("Cfc_cw_calls_uplift")
    public float cfc_cw_calls_uplift;
    @JsonProperty("Total_attendance_time_no_counsel")
    public float total_attendance_time_no_counsel;
    @JsonProperty("TLActionsOnLine") // in crm5 too
    public String tLActionsOnLine;
    @JsonProperty("Originator_tl_qn_answer_hash") // in crm5 too
    public String originator_tl_qn_answer_hash;

    @JsonProperty("Originator_displayname") // in crm5 too
    public String originator_displayname;
    @JsonProperty("Decided_by_original") // in crm5 too
    public String decided_by_original;
    @JsonProperty("Cfc_cw_no_of_calls")
    public int cfc_cw_no_of_calls;
    @JsonProperty("Prog")
    public String prog;
    @JsonProperty("Sd_address_line_1") // in crm5 too
    public String sd_address_line_1;
    @JsonProperty("Cdsp_other_details")
    public String cdsp_other_details;
    @JsonProperty("Cp_rbba_category_before_qc")
    public String cp_rbba_category_before_qc;

    @JsonProperty("Emailto")
    public String emailto;
    @JsonProperty("Sd_address_line_3") // in crm5 too
    public String sd_address_line_3;
    @JsonProperty("Sd_address_line_2") // in crm5 too
    public String sd_address_line_2;
    @JsonProperty("Taskid")
    public long taskid;
    @JsonProperty("Form_version")
    public int form_version;
    @JsonProperty("Total_preparation_time")
    public String total_preparation_time;
    @JsonProperty("Cld_no_of_defence_witnesses")
    public int cld_no_of_defence_witnesses;
    @JsonProperty("Unique_reference_number") // in crm5 too
    public String unique_reference_number;
    @JsonProperty("Attendance_rate_counsel_non_london")
    public float attendance_rate_counsel_non_london;
    @JsonProperty("Fc_rfi_text") // in crm5 too
    public String fc_rfi_text;
    @JsonProperty("Advocacy_rate_london")
    public float advocacy_rate_london;
    @JsonProperty("Rbba_category") // in crm5 too
    public String rbba_category;
    @JsonProperty("Cdsp_category_3_type")
    public String Cdsp_category_3_type;
    @JsonProperty("Solicitor_sign_date") // in crm5 too
    public LocalDate solicitor_sign_date;
    @JsonProperty("Fc_info_returned") // in crm5 too
    public boolean fc_info_returned;
    @JsonProperty("Total_travel_time")
    public float total_travel_time;
    @JsonProperty("Originator_notificationmethod") // in crm5 too
    public int originator_notificationmethod;
    @JsonProperty("Total_attendance_time")
    public float total_attendance_time;
    @JsonProperty("Tltaskid") // in crm5 too
    public int tltaskid;

    @JsonProperty("Cld_supplemental_claim") // Yes/No?
    public String cld_supplemental_claim;
    @JsonProperty("Ctou_travel_costs_vat_rate")
    public float ctou_travel_costs_vat_rate;
    @JsonProperty("Cld_prev_claims_attach_method") // Post/DX?
    public String cld_prev_claims_attach_method;
    @JsonProperty("Attendance_rate_no_counsel_non_london")
    public float attendance_rate_no_counsel_non_london;
    @JsonProperty("Statutory_charge") // in crm5 too
    public String statutory_charge;
    @JsonProperty("Originator_jobtitle") // in crm5 too
    public String originator_jobtitle;

    @JsonProperty("Cdsp_category")
    public String cdsp_category;

    @JsonProperty("Cd_main_offence")
    public String cd_main_offence;

    @JsonProperty("Letter_rate_non_london")
    public float letter_rate_non_london;
    @JsonProperty("Tlreadonly") // in crm5 too
    public int tlreadonly;
    @JsonProperty("Cld_prosecution_pages")
    public int cld_prosecution_pages;
    @JsonProperty("Total_advocacy_costs")
    public float total_advocacy_costs;
    @JsonProperty("Cfc_calls_rate")
    public float cfc_calls_rate;
    @JsonProperty("Printed")
    public boolean printed;


    @JsonProperty("Cat1national")
    public float cat1national;
    @JsonProperty("Total_preparation_costs")
    public float total_preparation_costs;
    @JsonProperty("Fc_processingofficedx") // in crm5 too
    public String fc_processingofficedx;
    @JsonProperty("Fc_ok_to_autogrant") // in crm5 too
    public boolean fc_ok_to_autogrant;
    @JsonProperty("Prog_prov")
    public String prog_prov;
    @JsonProperty("Originator_uniquename") // in crm5 too
    public String originator_uniquename;
    @JsonProperty("Prom")
    public String prom;
    @JsonProperty("Originator_id") // in crm5 too
    public int originator_id;
    @JsonProperty("Originator_tl_neverpermanentlyremove") // in crm5 too
    public int originator_tl_neverpermanentlyremove;
    @JsonProperty("Preparation_rate_london")
    public float preparation_rate_london;
    @JsonProperty("Cd_offence_date")
    public LocalDate cd_offence_date;
    @JsonProperty("Referral_reason_sol") // in crm5 too
    public String referral_reason_sol;
    @JsonProperty("Nsfc_other")
    public boolean nsfc_other;
    @JsonProperty("Originator_firm") // in crm5 too
    public String originator_firm;

    @JsonProperty("Fc_rfi_options") // in crm5 too
    public String fc_rfi_options;
    @JsonProperty("Fc_reject_reasons_text") // in crm5 too
    public String fc_reject_reasons_text;
    @JsonProperty("Cw_total_waiting_costs")
    public float cw_total_waiting_costs;
    @JsonProperty("Ct_waiting_costs_total")
    public float ct_waiting_costs_total;
    @JsonProperty("Cdsp_category_2_type")
    public String cdsp_category_2_type;
    @JsonProperty("Fc_initial_processingoffice") // in crm5 too
    public String fc_initial_processingoffice;
    @JsonProperty("Fc_decision_explanation") // in crm5 too
    public String fc_decision_explanation;
    @JsonProperty("Fc_processingofficeaddress") // in crm5 too
    public String fc_processingofficeaddress;
    @JsonProperty("Date_received") // in crm5 too
    public LocalDate date_received;
    @JsonProperty("Voucherrequired_count")
    public int voucherrequired_count;
    @JsonProperty("Fc_cw_requesting") // in crm5 too
    public String fc_cw_requesting;
    @JsonProperty("Cfc_cw_total_costs")
    public float cfc_cw_total_costs;
    @JsonProperty("Total_attendance_time_counsel")
    public float total_attendance_time_counsel;
    @JsonProperty("Originator__dtadded") // in crm5 too
    public LocalDate originator__dtadded;
    @JsonProperty("Fc_return_info_to_cw") // in crm5 too
    public boolean fc_return_info_to_cw;
    @JsonProperty("Cfc_disb_total_vat")
    public float cfc_disb_total_vat;


    @JsonProperty("Match_carried_out") // in crm5 too
    public boolean match_carried_out;
    @JsonProperty("Cfc_disb_attach_method") // Post/DX?
    public String cfc_disb_attach_method;
    @JsonProperty("Fc_possible_reject_reasons") // in crm5 too
    public String fc_possible_reject_reasons;
    @JsonProperty("Letter_rate_london")
    public float letter_rate_london;

    @JsonProperty("Sd_address_county") // in crm5 too
    public String sd_address_county;

    @JsonProperty("Cw_total_attendance_costs_counsel")
    public float cw_total_attendance_costs_counsel;
    @JsonProperty("Submitter_user_id") // in crm5 too
    public String submitter_user_id;
}