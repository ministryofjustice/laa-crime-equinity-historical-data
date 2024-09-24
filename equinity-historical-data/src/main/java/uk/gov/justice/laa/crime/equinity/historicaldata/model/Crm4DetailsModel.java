package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Slf4j
public class Crm4DetailsModel implements CrmFormDetailsModelInterface {
    @JsonProperty("Usn")
    public Long usn;
    @JsonProperty("Date_received")
    public Date date_received;
    @JsonProperty("Time_received")
    public String time_received;
    @JsonProperty("Lsc_region")
    public String lsc_region;
    @JsonProperty("Lsc_accountoffice")
    public String lsc_accountoffice;

    @JsonProperty("Applyingge100")
    public boolean applyingge100;
    @JsonProperty("Post_mortem_app")
    public String post_mortem_app;
    @JsonProperty("Urn")
    public String urn;

    @JsonProperty("Firm_name")
    public String firm_name;
    @JsonProperty("Firm_address")
    public String firm_address;
    @JsonProperty("Firm_dx")
    public String firm_dx;
    @JsonProperty("Firm_supplier_no")
    public String firm_supplier_no;
    @JsonProperty("Provider_account")
    public String provider_account;
    @JsonProperty("Firm_phone")
    public String firm_phone;
    @JsonProperty("Contact_name")
    public String contact_name;
    @JsonProperty("Solicitorname")
    public String solicitorname;
    @JsonProperty("Solicitorid")
    public String solicitorid;
    @JsonProperty("Prison_law")
    public String prison_law;
    @JsonProperty("Rep_order_number")
    public String rep_order_number;
    @JsonProperty("Client_dateoforder")
    public Date client_dateoforder;
    @JsonProperty("Client_ufn")
    public String client_ufn;
    @JsonProperty("Maat_number")
    public int maat_number;
    @JsonProperty("Client_surname")
    public String client_surname;
    @JsonProperty("Client_forename")
    public String client_forename;
    @JsonProperty("Client_dob")
    public Date client_dob;


    @JsonProperty("Client_detained")
    public String client_detained;
    @JsonProperty("Client_locationdetained")
    public String client_locationdetained;
    @JsonProperty("Court_type")
    public String court_type;
    @JsonProperty("Psychiatricliaisonservice1")
    public String psychiatricliaisonservice1;
    @JsonProperty("Psychiatricliaisonservice2")
    public String psychiatricliaisonservice2;
    @JsonProperty("Mainoffence")
    public String mainoffence;
    @JsonProperty("Plea")
    public String plea;
    @JsonProperty("Nexthearingdate")
    public Date nexthearingdate;
    @JsonProperty("Nexthearingpurpose")
    public String Nexthearingpurpose;

    @JsonProperty("Is_case_subject_to_poca")
    public String is_case_subject_to_poca;
    @JsonProperty("Expenditure_type")
    public String expenditure_type;
    @JsonProperty("Prior_authority_granted")
    public String prior_authority_granted;
    @JsonProperty("Expert_name")
    public String expert_name;
    @JsonProperty("Company_name")
    public String company_name;
    @JsonProperty("Expert_type")
    public String expert_type;
    @JsonProperty("Expert_postcode")
    public String expert_postcode;

    @JsonProperty("Preparation_duration")
    public String preparation_duration;
    @JsonProperty("Preparation_rate")
    public int preparation_rate;
    @JsonProperty("Preparation_total")
    public int preparation_total;
    @JsonProperty("Travel_duration")
    public String travel_duration;
    @JsonProperty("Travel_per_hr")
    public int travel_per_hr;
    @JsonProperty("Travel_no_of_hrs")
    public String travel_no_of_hrs;

    @JsonProperty("Total_authority")
    public int total_authority;
    @JsonProperty("Cw_travel_no_of_hrs")
    public String cw_travel_no_of_hrs;
    @JsonProperty("Cw_travel_per_hr")
    public int cw_travel_per_hr;
    @JsonProperty("Cw_travel_cost_total")
    public int cw_travel_cost_total;
    @JsonProperty("Ae")
    public Crm4AdditionalExpenditure additionalExpenditure;
    @JsonProperty("Attachments")
    public CrmFurtherInformationModel furtherInformationModel;
    @JsonProperty("Ae_cw")
    public Crm4AuthorisedAdditionalExpenditure authorisedAdditionalExpenditure;
    @JsonProperty("Relatedsubmissions")
    public Crm4RelatedSubmissions relatedSubmissions;
    @JsonProperty("Quotes_number")
    public int quotes_number;
    @JsonProperty("Obtained_alt_quotes")
    public String obtained_alt_quotes;
    @JsonProperty("No_alt_quote_reasons")
    public String no_alt_quote_reasons;
    @JsonProperty("Q1_company")
    public String q1_company;
    @JsonProperty("Q1_expertname")
    public String q1_expertname;
    @JsonProperty("Q1_phone")
    public String q1_phone;
    @JsonProperty("Q1_costbasis")
    public String q1_costbasis;
    @JsonProperty("Q1_hours")
    public float q1_hours;
    @JsonProperty("Q1_duration")
    public String q1_duration;
    @JsonProperty("Q1_hourlyrate")
    public float q1_hourlyrate;
    @JsonProperty("Travel_cost_total")
    public float travel_cost_total;
    @JsonProperty("Q1_ae_description")
    public String q1_ae_description;
    @JsonProperty("Q1_ae_amount")
    public float q1_ae_amount;
    @JsonProperty("Q1_travelduration")
    public String q1_travelduration;
    @JsonProperty("Q1_travelrate")
    public float q1_travelrate;
    @JsonProperty("Q1_total")
    public float q1_total;
    @JsonProperty("Q2_company")
    public String q2_company;
    @JsonProperty("Q2_expertname")
    public String q2_expertname;
    @JsonProperty("Q2_phone")
    public String q2_phone;
    @JsonProperty("Q2_costbasis")
    public String q2_costbasis;
    @JsonProperty("Q2_hours")
    public float q2_hours;
    @JsonProperty("Q2_duration")
    public String q2_duration;
    @JsonProperty("Q2_hourlyrate")
    public float q2_hourlyrate;

    @JsonProperty("Q2_ae_description")
    public String q2_ae_description;
    @JsonProperty("Q2_ae_amount")
    public float q2_ae_amount;
    @JsonProperty("Q2_travelduration")
    public String q2_travelduration;
    @JsonProperty("Q2_travelrate")
    public float q2_travelrate;
    @JsonProperty("Q2_total")
    public float q2_total;
    @JsonProperty("Q3_company")
    public String Q3_company;
    @JsonProperty("Q3_expertname")
    public String Q3_expertname;
    @JsonProperty("Q3_phone")
    public String Q3_phone;
    @JsonProperty("Q3_costbasis")
    public String q3_costbasis;
    @JsonProperty("Q3_hours")
    public float q3_hours;
    @JsonProperty("Q3_duration")
    public String q3_duration;
    @JsonProperty("Q3_hourlyrate")
    public float q3_hourlyrate;
    @JsonProperty("Q3_ae_description")
    public String Q3_ae_description;
    @JsonProperty("Q3_ae_amount")
    public float Q3_ae_amount;
    @JsonProperty("Q3_travelduration")
    public String Q3_travelduration;
    @JsonProperty("Q3_travelrate")
    public float Q3_travelrate;
    @JsonProperty("Q3_total")
    public float Q3_total;
    @JsonProperty("Authority_sought")
    public String authority_sought;
    @JsonProperty("Prosecution_case_summary")
    public String prosecution_case_summary;
    @JsonProperty("Defence_summary")
    public String defence_summary;
    @JsonProperty("Qc_without_junior_details")
    public String qc_without_junior_details;
    @JsonProperty("Ai_text")
    public String ai_text;
    @JsonProperty("Expenditure_orderedbycourt")
    public String expenditure_orderedbycourt;
    @JsonProperty("Dna_cost")
    public float dna_cost;
    @JsonProperty("Cw_dna_cost")
    public float cw_dna_cost;
    @JsonProperty("Accomm_basis")
    public String accomm_basis;
    @JsonProperty("Transcription_no_of_mins")
    public float transcription_no_of_mins;
    @JsonProperty("Transcription_cost_per_min")
    public float transcription_cost_per_min;
    @JsonProperty("Transcription_total")
    public float transcription_total;
    @JsonProperty("Cw_transcription_no_of_mins")
    public float cw_transcription_no_of_mins;
    @JsonProperty("Cw_transcription_cost_per_min")
    public float cw_transcription_cost_per_min;
    @JsonProperty("Cw_transcription_total")
    public float cw_transcription_total;
    @JsonProperty("Cw_photocopy_no_of_pages")
    public float cw_photocopy_no_of_pages;
    @JsonProperty("Cw_photocopy_cost_per_page")
    public float cw_photocopy_cost_per_page;
    @JsonProperty("Cw_photocopy_total")
    public float cw_photocopy_total;
    @JsonProperty("Photocopy_no_of_pages")
    public float photocopy_no_of_pages;
    @JsonProperty("Photocopy_cost_per_page")
    public float photocopy_cost_per_page;
    @JsonProperty("Photocopy_total")
    public float photocopy_total;
    @JsonProperty("Translator_no_of_words")
    public float translator_no_of_words;
    @JsonProperty("Translator_per_thou_words")
    public float translator_per_thou_words;
    @JsonProperty("Translator_total_cost")
    public float translator_total_cost;
    @JsonProperty("Cw_translator_no_of_words")
    public float cw_translator_no_of_words;
    @JsonProperty("cw_translator_per_thou_words")
    public float cw_translator_per_thou_words;
    @JsonProperty("Cw_translator_total_cost")
    public float cw_translator_total_cost;
    @JsonProperty("Other_quantity")
    public float other_quantity;
    @JsonProperty("Other_rate")
    public float other_rate;
    @JsonProperty("Other_total")
    public float other_total;
    @JsonProperty("Solicitor_declaration")
    public boolean solicitor_declaration;
    @JsonProperty("Solicitor_sign_date")
    public Date solicitor_sign_date;
    @JsonProperty("TlStateName")
    public String tlstatename;
    @JsonProperty("Decision_original")
    public String decision_original;
    @JsonProperty("Full_grant_notes")
    public String full_grant_notes;
    @JsonProperty("Preparation_hrs")
    public String preparation_hrs;
    @JsonProperty("Cw_preparation_hrs")
    public int cw_preparation_hrs;
    @JsonProperty("Cw_preparation_rate")
    public int cw_preparation_rate;
    @JsonProperty("Cw_preparation_total")
    public int cw_preparation_total;
    @JsonProperty("Amount_allowed")
    public float amount_allowed;
    @JsonProperty("Destruction_date")
    public Date destruction_date;
    @JsonProperty("Signed_authority")
    public String signed_authority;
    public List<Crm4QuoteModel> allQuotes;
    public void setAllQuotes()  {
        if (this.quotes_number > 0) {
            this.allQuotes = new ArrayList<>();
            for (int i = 1; i <= quotes_number; i++) {
                Method instanceMethod;
                Crm4QuoteModel  quote= new Crm4QuoteModel();
                try {
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_company");
                    String  companyName = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_expertname");
                    String  expertName = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_phone");
                    String  contactPhone = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_costbasis");
                    String  costBasis = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_duration");
                    String prepHrs = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_hourlyrate");
                    Float prepHrlyRate = (Float) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_ae_description");
                    String addItmDesc = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_ae_amount");
                    Float addItmAmt = (Float) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_travelduration");
                    String trvlHrs = (String) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_travelrate");
                    Float trvlHrsRate = (Float) instanceMethod.invoke(this);
                    instanceMethod = this.getClass().getMethod("getQ"+i+"_total");
                    Float qtTotal = (Float) instanceMethod.invoke(this);
                    quote.setCompanyName(companyName);
                    quote.setExpertName(expertName);
                    quote.setContactPhone(contactPhone);
                    quote.setCostBasis(costBasis);
                    quote.setPreparationHours(prepHrs);
                    quote.setHourlyRate(prepHrlyRate);
                    quote.setAdditionalItemDesc(addItmDesc);
                    quote.setAdditionalItemAmount(addItmAmt);
                    quote.setTravelHours(trvlHrs);
                    quote.setTravelHourlyRate(trvlHrsRate);
                    quote.setQuoteTotal(qtTotal);
                } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
                    log.error("eForm CRM4 unable to call quote Method:: urn={} :: {}", urn, e.getMessage());
                }
                allQuotes.add(quote);
            }
        }
    }

}
