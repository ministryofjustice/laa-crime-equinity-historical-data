package uk.gov.justice.laa.crime.equinity.historicaldata.model.report;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Entity(name="Crm14CaseSummaryReport")
public class Crm14CaseSummaryReportModel {

    public static final String CSV_HEADER = "Usn,DTCreated,MAAT,DTFirstSubmitted,DefendantName,Provider Account," +
            "Originator,QueueType,QueueName,QueueSortOrder,Court,Caseworker,Stage,DTFirstSentToNct,CaseType," +
            "DTLastReturnedToProvider,BenefitCheckResult,IOJDecision,MeansTested,MeansDecision,OverallDecision," +
            "DTDecision,ProviderName,ApplicationType,IsNewApplication,IsCIFC,FundingDecisionUpdateCount,IsPriorityCase," +
            "SubmissionCount,LatestSubmissionDate,ReturnDate1,ResubmissionDate1,ReturnDate2,ResubmissionDate2," +
            "ReturnDate3,ResubmissionDate3,ReturnDate4,ResubmissionDate4,ReturnDate5,ResubmissionDate5";

    public static final String[] CSV_HEADER_ARRAY = CSV_HEADER.split(",");

    @Id
    @Column(name="USN")
    private Long usn;
    @Column(name="DTCreated")
    private LocalDate createdDate;
    @Column(name="MAAT")
    private String MAAT;
    @Column(name="DTFirstSubmitted")
    private LocalDate firstSubmittedDate;
    @Column(name="DefendantName")
    private String defendantName;
    @Column(name="FirmNo")
    private String providerAccount;
    @Column(name="Originator")
    private String originatorName;
    @Column(name="QueueType")
    private String queueType;
    @Column(name="QueueName")
    private String queueName;
    @Column(name="QueueSortOrder")
    private String queueSortOrder;
    @Column(name="Court")
    private String court;
    @Column(name="Caseworker")
    private String caseworker;
    @Column(name="Stage")
    private String state;
    @Column(name="DTFirstSentToNct")
    private LocalDate firstSentToNctDate;
    @Column(name="CaseType")
    private String caseType;
    @Column(name="DTLastReturnedToProvider")
    private LocalDate lastReturnedToProviderDate;
    @Column(name="BenefitCheckResult")
    private String benefitCheckResult;
    @Column(name="IOJDecision")
    private String ioJDecision;
    @Column(name="MeansTested")
    private String meansTested;
    @Column(name="MeansDecision")
    private String meansDecision;
    @Column(name="OverallDecision")
    private String overallDecision;
    @Column(name="DTDecision")
    private LocalDate decisionDate;
    @Column(name="ProviderName")
    private String providerName;
    @Column(name="ApplicationType")
    private String applicationType;
    @Column(name="IsNewApplication")
    private String isNewApplication;
    @Column(name="IsCIFC")
    private String isCIFC;
    @Column(name="FundingDecisionUpdateCount")
    private Integer fundingDecisionUpdateCount;
    @Column(name="IsPriorityCase")
    private String isPriorityCase;
    @Column(name="SubmissionCount")
    private Integer submissionCount;
    @Column(name="LatestSubmissionDate")
    private LocalDate latestSubmissionDate;
    @Column(name="ReturnDate1")
    private LocalDate returnDate1;
    @Column(name="ResubmissionDate1")
    private LocalDate resubmissionDate1;
    @Column(name="ReturnDate2")
    private LocalDate returnDate2;
    @Column(name="ResubmissionDate2")
    private LocalDate resubmissionDate2;
    @Column(name="ReturnDate3")
    private LocalDate returnDate3;
    @Column(name="ResubmissionDate3")
    private LocalDate resubmissionDate3;
    @Column(name="ReturnDate4")
    private LocalDate returnDate4;
    @Column(name="ResubmissionDate4")
    private LocalDate resubmissionDate4;
    @Column(name="ReturnDate5")
    private LocalDate returnDate5;
    @Column(name="ResubmissionDate5")
    private LocalDate resubmissionDate5;

    public String[] exportToCSVArray() {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                usn, createdDate, MAAT, firstSubmittedDate, defendantName, providerAccount,
                originatorName, queueType, queueName, queueSortOrder, court,
                sanitiseField(caseworker), state,
                firstSentToNctDate, caseType, lastReturnedToProviderDate, benefitCheckResult,
                ioJDecision, meansTested, meansDecision, overallDecision, decisionDate, providerName,
                applicationType, isNewApplication, isCIFC, fundingDecisionUpdateCount, isPriorityCase,
                submissionCount, latestSubmissionDate, returnDate1, resubmissionDate1, returnDate2,
                resubmissionDate2, returnDate3, resubmissionDate3, returnDate4, resubmissionDate4,
                returnDate5, resubmissionDate5
        ).split(",");
    }

    private String sanitiseField(String text) {
        return (text == null) ? "" : text.replace(",", ";");
    }
}
