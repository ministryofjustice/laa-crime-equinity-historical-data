package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormsModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormViewModel;


public interface CrmFormsViewRepository extends JpaRepository<CrmFormViewModel, Integer>, JpaSpecificationExecutor<CrmFormsModelInterface> {
}
