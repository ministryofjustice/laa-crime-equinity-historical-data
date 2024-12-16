package uk.gov.justice.laa.crime.equinity.historicaldata.model.report;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractCrm4ReportModel {
    @Column(name="Client UFN")
    private String clientUfn;
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
    private Double quantity;
    @Column(name="Rate")
    private Double rate;
    @Column(name="Unit")
    private String unit;
    @Column(name="Total Cost")
    private Double totalCost;
    @Column(name="Additional Expenditure")
    private Double additionalExpenditure;
    @Column(name="Total Authority")
    private Double totalAuthority;
    @Column(name="Total Granted")
    private Double totalGranted;
}
