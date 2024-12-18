package uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider;

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
@Entity(name="Crm14ProviderReport")
public class Crm14ProviderReportModel {

    public static final String CSV_HEADER = "Usn,DTCreated,MAAT,DTFirstSubmitted,DefendantName,FirmNo," +
            "Originator,Court,CaseType,IOJDecision,DTDecision,ProviderName,Charges";

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
    private String firmNo;
    @Column(name="Originator")
    private String originatorName;
    @Column(name="Court")
    private String court;
    @Column(name="CaseType")
    private String caseType;
    @Column(name="IOJDecision")
    private String ioJDecision;
    @Column(name="DTDecision")
    private LocalDate decisionDate;
    @Column(name="ProviderName")
    private String providerName;
    @Column(name="Charges")
    private String charges;

    public String[] exportToCSVArray() {
        return String.format(
            "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
            usn, createdDate, MAAT, firstSubmittedDate, defendantName, firmNo,
            originatorName, court, caseType, ioJDecision, decisionDate, providerName, charges
        ).split(",");
    }

    public static String[] exportHeaderToCSVArray() {
        return CSV_HEADER.split(",");
    }
}
