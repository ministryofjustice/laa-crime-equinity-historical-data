package uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm4ProviderReportModel;

import java.util.List;

public interface Crm4ProviderReportRepository extends JpaRepository<Crm4ProviderReportModel, Integer> {

    @Procedure("Reporting_CRM4PeriodicalReport_ProviderVersion")
    List<Crm4ProviderReportModel> getReport(@Param("decisionRangeStart") String decisionRangeStart,
                                            @Param("decisionRangeEnd") String decisionRangeEnd,
                                            @Param("providerAccount") String providerAccount);
}
