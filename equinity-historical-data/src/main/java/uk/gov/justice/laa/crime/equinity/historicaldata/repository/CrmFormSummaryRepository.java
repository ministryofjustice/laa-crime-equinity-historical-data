package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDataModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormSummaryModel;


public interface CrmFormSummaryRepository extends JpaRepository<CrmFormSummaryModel, Integer>, JpaSpecificationExecutor<CrmFormDataModelInterface> {
    @Procedure("ArchiveCrmFormData")
    void archiveCrmFormData ();
}
