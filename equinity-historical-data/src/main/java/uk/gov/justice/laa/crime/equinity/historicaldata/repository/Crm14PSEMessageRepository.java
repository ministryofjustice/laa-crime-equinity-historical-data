package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14PSEMessageModel;

import java.util.List;

@Repository
public interface Crm14PSEMessageRepository extends JpaRepository<Crm14PSEMessageModel, Long>{
    List<Crm14PSEMessageModel> findAllByUSN(long usn);

}
