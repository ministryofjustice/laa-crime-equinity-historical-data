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
@Entity(name="Crm5UpperLimitReport")
public class Crm5UpperLimitReportModel {

    public static final String CSV_HEADER = "Usn,Provider Account,Firm Name,Client Name,LAA Case Ref,New Limit Request," +
            "Upper Limit Extended,Date Received,Decision Date,User,Review,Level Of Work\n";

    @Id
    @Column(name="USN")
    private Long usn;
    @Column(name="Provider_Account")
    private String providerAccount;
    @Column(name="Firm_Name")
    private String firmName;
    @Column(name="Client_Name")
    private String clientName;
    @Column(name="LAA_Case_Ref")
    private String laaCaseRef;
    @Column(name="Ou_New_limit_Request")
    private double newLimitRequest;
    @Column(name="Ou_Upper_Limit_Extended")
    private double upperLimitExtended;
    @Column(name="Date_Received")
    private LocalDate receivedDate;
    @Column(name="FC_Decision_Date")
    private LocalDate decisionDate;
    @Column(name="FC_Current_User")
    private String currentUser;
    @Column(name="Review_Process")
    private String review;
    @Column(name="Level_Of_Work")
    private String levelOfWork;

    public String exportToCSV() {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                usn, providerAccount, firmName, clientName, laaCaseRef,
                newLimitRequest, upperLimitExtended, receivedDate, decisionDate,
                currentUser, review, levelOfWork
        );
    }
}
