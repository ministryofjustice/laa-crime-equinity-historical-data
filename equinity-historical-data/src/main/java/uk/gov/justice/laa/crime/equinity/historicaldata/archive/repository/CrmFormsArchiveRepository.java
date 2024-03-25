package uk.gov.justice.laa.crime.equinity.historicaldata.archive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;


public interface CrmFormsArchiveRepository extends JpaRepository<CrmFormArchiveModel, Integer>, JpaSpecificationExecutor<CrmFormModelInterface> {
}
