package uk.gov.justice.laa.crime.equinity.historicaldata.model.report;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;

@Data
@SuperBuilder
@NoArgsConstructor
@Immutable
@EqualsAndHashCode(callSuper = true)
@Entity(name="Crm4PeriodicalReport")
public class Crm4PeriodicalReportModel extends AbstractCrm4ReportModel {

    @Column(name="Granting Caseworker")
    private String grantingCaseworker;

    public String exportToCSV() {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                getClientUfn(), getUsn(), getPrisonLaw(), getFirmName(), getClientName(),
                getRepOrderNumber(), getMaatId(), getPrisonLaw(), getReceivedDate(), getDecisionDate(),
                getDecisionResult(), getExpenditureType(), getExpertName(), getQuantity(), getRate(), getUnit(),
                getTotalCost(), getAdditionalExpenditure(), getTotalAuthority(), getTotalGranted(), grantingCaseworker
        );
    }

    public static String exportHeaderToCSV() {
        return "Client UFN,Usn,Provider Account,Firm Name,Client Name,Rep Order Number,Maat ID,Prison Law,Date Received,Decision Date,Decision,Expenditure Type,Expert Name,Quantity,Rate,Unit,Total Cost,Additional Expenditure,Total Authority,Total Granted,Granting Caseworker\n";
    }
}
