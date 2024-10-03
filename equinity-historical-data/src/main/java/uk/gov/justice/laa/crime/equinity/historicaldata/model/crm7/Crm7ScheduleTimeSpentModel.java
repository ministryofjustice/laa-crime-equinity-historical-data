package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Crm7ScheduleTimeSpentModel {
    // Schedule
    @JsonProperty("row")
    List<Crm7TimeSpentModel> timeSpent;
}