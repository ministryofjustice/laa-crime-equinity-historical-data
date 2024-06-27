package uk.gov.justice.laa.crime.equinity.historicaldata.repository.report;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm4PeriodicalReportModel;

import java.util.List;


public interface Crm4PeriodicalReportRepository extends JpaRepository<Crm4PeriodicalReportModel, Integer> {

    @Procedure("Reporting_CDS4PeriodicalReport")
    @Transactional
    List<Crm4PeriodicalReportModel> getReport(@Param("decisionRangeStart") String decisionRangeStart, @Param("decisionRangeEnd") String decisionRangeEnd);
}