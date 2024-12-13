package uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.BaseCrm4ReportModel;

@Data
@SuperBuilder
@NoArgsConstructor
@Immutable
@EqualsAndHashCode(callSuper = true)
@Entity(name="Crm4ProviderReport")
public class Crm4ProviderReportModel extends BaseCrm4ReportModel {
    public static final String CSV_HEADER = "Client UFN,Usn,Provider Account,Firm Name,Client Name,Rep Order Number," +
            "Maat ID,Prison Law,Date Received,Decision Date,Decision,Expenditure Type,Expert Name,Quantity,Rate,Unit," +
            "Total Cost,Additional Expenditure,Total Authority,Total Granted\n";

    public String exportToCSV() {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                getClientUfn(), getUsn(), getProviderAccount(), getFirmName(), getClientName(),
                getRepOrderNumber(), getMaatId(), getPrisonLaw(), getReceivedDate(), getDecisionDate(),
                getDecisionResult(), getExpenditureType(), getExpertName(), getQuantity(), getRate(), getUnit(),
                getTotalCost(), getAdditionalExpenditure(), getTotalAuthority(), getTotalGranted()
        );
    }
}
