package uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.model.QaCrmFileTestErrorsModel;


public interface QaCrmFileTestErrorsRepository extends JpaRepository<QaCrmFileTestErrorsModel, Long> {
}
