package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.crm15;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm15CarRegDetailsModel {
    @JsonProperty("Car_reg_numbers")
    public String car_reg_numbers;
}