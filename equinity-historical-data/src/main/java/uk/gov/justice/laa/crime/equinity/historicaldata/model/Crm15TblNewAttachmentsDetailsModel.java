package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15TblNewAttachmentsDetailsModel {

    @JsonProperty("File_size_bytes")
    public double file_size_bytes;
    @JsonProperty("Status")
    public String status;
    @JsonProperty("Dtsubmitted")
    public String dtsubmitted;
    @JsonProperty("Evidence_type")
    public String evidence_type;
    @JsonProperty("Filename")
    public String filename;
    @JsonProperty("File_size_mb")
    public double file_size_mb;
    @JsonProperty("Caseworker_notes")
    public String caseworker_notes;
    @JsonProperty("Provider_notes")
    public String provider_notes;
    @JsonProperty("Attachment_store_id")
    public String attachment_store_id;
    @JsonProperty("Provider_firm_id")
    public int provider_firm_id;
}