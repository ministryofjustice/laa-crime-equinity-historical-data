package uk.gov.justice.laa.crime.equinity.historicaldata.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;

import java.util.List;


public interface Crm4PeriodicalReportRepository extends JpaRepository<Crm4PeriodicalReportModel, Integer> {

    @Procedure("Reporting_CRM4PeriodicalReport")
    List<Crm4PeriodicalReportModel> getReport(@Param("decisionRangeStart") String decisionRangeStart, @Param("decisionRangeEnd") String decisionRangeEnd);
}