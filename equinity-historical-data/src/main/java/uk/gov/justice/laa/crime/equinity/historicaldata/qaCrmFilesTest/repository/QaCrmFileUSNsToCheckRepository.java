package uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.model.QaCrmFileUSNsToCheckModel;

import java.util.List;


public interface QaCrmFileUSNsToCheckRepository extends JpaRepository<QaCrmFileUSNsToCheckModel, Long> {
    @Query(value = "SELECT TOP(300000) USN, TypeId FROM CrmFormsView WHERE TypeId = ?1 AND NOT EXISTS (SELECT NULL FROM QA_CRMFileTestSuccess q WHERE q.usn = CrmFormsView.usn)", nativeQuery = true)
    List<QaCrmFileUSNsToCheckModel> findUSNsToTestByType(Integer typeId);
}
