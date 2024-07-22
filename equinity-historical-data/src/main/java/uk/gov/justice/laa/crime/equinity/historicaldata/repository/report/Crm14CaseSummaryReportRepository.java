package uk.gov.justice.laa.crime.equinity.historicaldata.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm14CaseSummaryReportModel;

import java.util.List;


public interface Crm14CaseSummaryReportRepository extends JpaRepository<Crm14CaseSummaryReportModel, Integer> {

    @Procedure("Reporting_CRM14CaseSummaryReport")
    List<Crm14CaseSummaryReportModel> getReport(
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