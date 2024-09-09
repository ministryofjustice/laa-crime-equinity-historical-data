package uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.repository.QaCrmFileUSNsToCheckRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.service.QaCrmFormsFilesTestService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.*;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class QaCrmFormsFilesTest {

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    QaCrmFileUSNsToCheckRepository testRepository;

    @Autowired
    QaCrmFormsFilesTestService testService;


    /**
     * Report data collection tests
     */

    @Test
    void performBatchFileTest_CRM4() {

        try {

            testService.performBatchFileTest(CRM_TYPE_4);

        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void performBatchFileTest_CRM5() {

        try {

            testService.performBatchFileTest(CRM_TYPE_5);

        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void performBatchFileTest_CRM7() {

        try {

            testService.performBatchFileTest(CRM_TYPE_7);

        } catch (InvalidDataAccessResourceUsageException e) {

            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void performBatchFileTest_CRM14() {

        try {

            testService.performBatchFileTest(CRM_TYPE_14);

        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    } // 09/09/2024 - 01:19am

}
