package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;


public interface CrmFormDetailsRepository extends JpaRepository<CrmFormDetailsModel, Long>, JpaSpecificationExecutor<CrmFormModelInterface> {}

