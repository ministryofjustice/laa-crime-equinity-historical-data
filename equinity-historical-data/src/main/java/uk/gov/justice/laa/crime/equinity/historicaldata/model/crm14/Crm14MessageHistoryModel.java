package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm14MessageHistoryModel {
    @JsonProperty("row")
    public List<Crm14MessageModel> messages;
}