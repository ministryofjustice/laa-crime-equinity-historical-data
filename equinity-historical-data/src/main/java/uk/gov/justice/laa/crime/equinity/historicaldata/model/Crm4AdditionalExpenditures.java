package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor

public class Crm4AdditionalExpenditures implements CrmFileDetailsModelInterface {    

     public ArrayList<Crm4ExpenditureModel> row;
}