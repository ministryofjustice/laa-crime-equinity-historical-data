package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14AttachmentModel;

import java.util.List;

@Repository
public interface AttachmentStoreRepository extends JpaRepository<Crm14AttachmentModel, Long>{
    List<Crm14AttachmentModel> findByUSN(long usn);

}