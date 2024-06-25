package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm14ChargesModel {
    @JsonProperty("Offence_date_1")
    public Date offence_date_1;
    @JsonProperty("Offence_date_2")
    public Date offence_date_2;
    @JsonProperty("Offence_date_3")
    public Date offence_date_3;
    @JsonProperty("Offence_when")
    public String offence_when;
    @JsonProperty("Charge")
    public String charge;

}