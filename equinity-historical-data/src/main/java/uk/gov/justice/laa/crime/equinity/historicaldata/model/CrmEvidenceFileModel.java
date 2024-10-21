package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmEvidenceFileModel {
    @JsonProperty("fileName")
    private String key;
    @JsonProperty("savetype")
    private String type;
    @JsonProperty("name")
    private String name;

    private Boolean isProcessed;
}