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
@Entity(name="Crm4PeriodicalReport")
public class Crm4PeriodicalReportModel {
    @Column(name="Client UFN")
    private String clientUsn;
    @Id
    @Column(name="Usn")
    private Long usn;
    @Column(name="Provider Account")
    private String providerAccount;
    @Column(name="Firm Name")
    private String firmName;
    @Column(name="Client Name")
    private String clientName;
    @Column(name="Rep Order Number")
    private String repOrderNumber;
    @Column(name="Maat ID")
    private String maatId;
    @Column(name="Prison Law")
    private String prisonLaw;
    @Column(name="Date Received")
    private LocalDate receivedDate;
    @Column(name="Decision Date")
    private LocalDate decisionDate;
    @Column(name="Decision")
    private String decisionResult;
    @Column(name="Expenditure Type")
    private String expenditureType;
    @Column(name="Expert Name")
    private String expertName;
    @Column(name="Quantity")
    private Float quantity;
    @Column(name="Rate")
    private Float rate;
    @Column(name="Unit")
    private String unit;
    @Column(name="Total Cost")
    private Float totalCost;
    @Column(name="Additional Expenditure")
    private Float additionalExpenditure;
    @Column(name="Total Authority")
    private Float totalAuthority;
    @Column(name="Total Granted")
    private Float totalGranted;
    @Column(name="Granting Caseworker")
    private String grantingCaseworker;

    public String exportToCSV() {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                clientUsn, usn, providerAccount, firmName, clientName,
                repOrderNumber, maatId, prisonLaw, receivedDate, decisionDate,
                decisionResult, expenditureType, expertName, quantity, rate, unit,
                totalCost, additionalExpenditure, totalAuthority, totalGranted, grantingCaseworker
        );
    }

    public static String exportHeaderToCSV() {
        return "Client UFN,Usn,Provider Account,Firm Name,Client Name,Rep Order Number,Maat ID,Prison Law,Date Received,Decision Date,Decision,Expenditure Type,Expert Name,Quantity,Rate,Unit,Total Cost,Additional Expenditure,Total Authority,Total Granted,Granting Caseworker\n";
    }
}
