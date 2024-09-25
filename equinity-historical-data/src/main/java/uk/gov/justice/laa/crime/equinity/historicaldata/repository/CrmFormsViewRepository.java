package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDataModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormViewModel;


public interface CrmFormsViewRepository extends JpaRepository<CrmFormViewModel, Integer>, JpaSpecificationExecutor<CrmFormDataModelInterface> {
}
