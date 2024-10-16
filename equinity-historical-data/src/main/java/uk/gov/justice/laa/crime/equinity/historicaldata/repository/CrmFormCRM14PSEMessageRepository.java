package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormCRM14PSEMessageModel;

import java.util.List;

@Repository
public interface CrmFormCRM14PSEMessageRepository extends JpaRepository<CrmFormCRM14PSEMessageModel, Long>{
    List<CrmFormCRM14PSEMessageModel> findAllByUSN(long usn);

}
