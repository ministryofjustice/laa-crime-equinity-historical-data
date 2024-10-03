package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class CrmFurtherInformationModel {
    @JsonProperty("row")
    public List<CrmFurtherInfoAttachmentsModel> attachments;
}