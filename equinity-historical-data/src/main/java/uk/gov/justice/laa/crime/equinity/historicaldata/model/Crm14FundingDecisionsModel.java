package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm14FundingDecisionsModel {
    public List<Crm14FundDecisionModel> decisions;
}