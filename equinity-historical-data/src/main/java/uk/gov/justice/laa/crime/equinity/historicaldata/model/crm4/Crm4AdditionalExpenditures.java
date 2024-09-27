package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4;

import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormDetailsModelInterface;

import java.util.ArrayList;
@Data
@NoArgsConstructor

public class Crm4AdditionalExpenditures implements CrmFormDetailsModelInterface {

     public ArrayList<Crm4ExpenditureModel> row;
}