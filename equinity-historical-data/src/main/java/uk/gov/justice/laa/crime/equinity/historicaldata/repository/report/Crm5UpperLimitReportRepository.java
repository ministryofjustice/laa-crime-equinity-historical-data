package uk.gov.justice.laa.crime.equinity.historicaldata.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.report.Crm5UpperLimitReportModel;

import java.util.List;


public interface Crm5UpperLimitReportRepository extends JpaRepository<Crm5UpperLimitReportModel, Integer> {

    @Procedure("Reporting_CRM5UpperLimitReport")
    List<Crm5UpperLimitReportModel> getReport(@Param("decisionRangeStart") String decisionRangeStart, @Param("decisionRangeEnd") String decisionRangeEnd);
}