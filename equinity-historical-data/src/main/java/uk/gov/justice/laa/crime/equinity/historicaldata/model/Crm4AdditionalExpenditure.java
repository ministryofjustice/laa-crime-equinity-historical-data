package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class Crm4AdditionalExpenditure  {
    public List<Crm4ExpenditureModel> row;
}