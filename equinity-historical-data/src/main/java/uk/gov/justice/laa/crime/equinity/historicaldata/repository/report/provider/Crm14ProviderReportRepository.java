package uk.gov.justice.laa.crime.equinity.historicaldata.repository.report.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.provider.Crm14ProviderReportModel;

import java.util.List;


public interface Crm14ProviderReportRepository extends JpaRepository<Crm14ProviderReportModel, Integer> {

    @Procedure("Reporting_CRM14ProviderReport")
    List<Crm14ProviderReportModel> getReport(
            @Param("providerAccount") String providerAccount,
            @Param("filterByDecisionDate") Integer filterByDecision,
            @Param("decisionStart") String decisionRangeStart, @Param("decisionEnd") String decisionRangeEnd,
            @Param("filterBySubmittedDate") Integer filterBySubmitDate,
            @Param("submittedStart") String submitRangeStart, @Param("submittedEnd") String submitRangeEnd,
            @Param("filterByCreatedDate") Integer filterByCreateDate,
            @Param("createdStart") String createRangeStart, @Param("createdEnd") String createRangeEnd,
            @Param("stageFilter") String state,
            @Param("filterByLatestSubmittedDate") Integer filterByLastSubmitDate,
            @Param("latestSubmittedDateStart") String lastSubmitRangeStart, @Param("latestSubmittedDateEnd") String lastSubmitRangeEnd
    );
}
