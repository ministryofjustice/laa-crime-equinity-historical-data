package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Crm7DisbursementModel {
    // Schedule
    @JsonProperty("Disbursement")
    public String disbursement;
    @JsonProperty("Details")
    public String details;
    @JsonProperty("Net")
    public Float net;
    @JsonProperty("Vat_rate")
    public Float vat_rate;
    @JsonProperty("Vat")
    public Float vat;
    @JsonProperty("Total")
    public Float total;
    @JsonProperty("Voucherrequired")
    public Integer voucherRequired;
    @JsonProperty("Net_cw")
    public Float net_cw;
    @JsonProperty("Vat_rate_cw")
    public Float vat_rate_cw;
    @JsonProperty("Vat_cw")
    public Float vat_cw;
    @JsonProperty("Total_cw")
    public Float total_cw;
    @JsonProperty("Reason_cw")
    public String reason_cw;
    @JsonProperty("Summary_cw")
    public String summary_cw;
}