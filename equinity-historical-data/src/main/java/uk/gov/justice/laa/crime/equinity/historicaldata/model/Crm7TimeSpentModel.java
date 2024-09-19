package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Crm7TimeSpentModel {
    // Schedule
    @JsonProperty("Line")
    public int line;
    @JsonProperty("Fe_initials")
    public String fe_initials;
    @JsonProperty("Date")
    public LocalDate date;
    @JsonProperty("Cost_type")
    public String cost_type;
    @JsonProperty("Time_total")
    public String time_total; // format 00:00:00
    @JsonProperty("Hearing_codes")
    public String hearing_codes;
    @JsonProperty("Person_codes")
    public String person_codes;
    @JsonProperty("Rate")
    public Float rate;
    @JsonProperty("Basic_claim")
    public Float basic_claim;
    @JsonProperty("Uplift")
    public Float uplift;
    @JsonProperty("Claim")
    public Float claim;

    @JsonProperty("Cost_type_full_list")
    public String cost_type_full_list;
    @JsonProperty("Time_calc")
    public float time_calc;
    @JsonProperty("Hearing_codes_options")
    public String hearing_codes_options;
    @JsonProperty("Time_display")
    public String time_display; // format 00:00:00
    @JsonProperty("Person_codes_options")
    public String person_codes_options;

    /** *** **/

    // LAA Adjustments
    @JsonProperty("Time_total_cw")
    public String time_total_cw; // format 00:00:00
    @JsonProperty("Rate_cw")
    public float rate_cw;
    @JsonProperty("Basic_cw")
    public float basic_cw;
    @JsonProperty("Uplift_cw")
    public float uplift_cw;
    @JsonProperty("Claim_cw")
    public float claim_cw;
    @JsonProperty("Assessment_cw")
    public String assessment_cw;
    /** *** **/

    @JsonProperty("Time_attendances_no_counsel")
    public String time_attendances_no_counsel; // format 00:00:00
    @JsonProperty("Time_attendances_counsel")
    public String time_attendances_counsel; // format 00:00:00
    @JsonProperty("Time_preparation_cw")
    public String time_preparation_cw; // format 00:00:00
    @JsonProperty("Time_attendances_cw")
    public String time_attendances_cw; // format 00:00:00
    @JsonProperty("Time_waiting_cw")
    public String time_waiting_cw; // format 00:00:00
    @JsonProperty("Time_travel_cw")
    public String time_travel_cw; // format 00:00:00
    @JsonProperty("Time_attendances_no_counsel_cw")
    public String time_attendances_no_counsel_cw; // format 00:00:00
    @JsonProperty("Time_attendances_counsel_cw")
    public String time_attendances_counsel_cw; // format 00:00:00
    @JsonProperty("Time_advocacy_cw")
    public String time_advocacy_cw; // format 00:00:00
}