package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormsViewModel;


public interface CrmFormsViewRepository extends JpaRepository<CrmFormsViewModel, Integer>, JpaSpecificationExecutor<CrmFormsViewModel> {
}

