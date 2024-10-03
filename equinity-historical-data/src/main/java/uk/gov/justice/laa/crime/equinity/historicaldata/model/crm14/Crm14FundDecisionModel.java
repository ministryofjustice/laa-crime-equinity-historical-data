package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class Crm14FundDecisionModel {
    public boolean subFormVisible;
    public String maatNumber;
    public String caseNumber;
    public String justiceTest;
    public String meansTestResultType;
    public String officialSignFullName;
    public String appropriateOfficerName;
    public String justiceTestReasons;
    public String overallResultMagsorcfs;
    public Date appropriateOfficerSignDate;
    public String meansTestResultAppealToCc;
    public String overallResultNonMeans;
    public String meansTestResultMagsorcfs;
    public String meansTestResultCc;
    public String overallResultAppealToCc;
    public String overallResultType;
    public String overallResultCc;
    public Date officialSignDate;
}