package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormCRM14AttachmentStoreModel;

import java.util.List;

@Repository
public interface CrmFormCRM14AttachmentStoreRepository extends JpaRepository<CrmFormCRM14AttachmentStoreModel, Long>{
    List<CrmFormCRM14AttachmentStoreModel> findByUSN(long usn);

}