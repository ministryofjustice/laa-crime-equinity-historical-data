package uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.controller.Crm14Controller;
import uk.gov.justice.laa.crime.equinity.historicaldata.controller.Crm4Controller;
import uk.gov.justice.laa.crime.equinity.historicaldata.controller.Crm5Controller;
import uk.gov.justice.laa.crime.equinity.historicaldata.controller.Crm7Controller;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.model.QaCrmFileTestErrorsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.model.QaCrmFileTestSuccessModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.model.QaCrmFileUSNsToCheckModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.repository.QaCrmFileTestErrorsRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.repository.QaCrmFileTestSuccessRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.repository.QaCrmFileUSNsToCheckRepository;

import java.util.Arrays;
import java.util.List;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class QaCrmFormsFilesTestService {
    private final QaCrmFileUSNsToCheckRepository testRepository;
    private final QaCrmFileTestSuccessRepository successRepository;
    private final QaCrmFileTestErrorsRepository errorsRepository;
    private final Crm4Controller crm4Controller;
    private final Crm5Controller crm5Controller;
    private final Crm7Controller crm7Controller;
    private final Crm14Controller crm14Controller;


    public void performBatchFileTest(Integer typeId) {
        List<QaCrmFileUSNsToCheckModel> USNsToTest = getUSNsToTest(typeId);

        log.info("Found {} records type=[{}]", USNsToTest.size(), typeId);
        USNsToTest.forEach(this::testOpeningCrmFileDetails);
    }

    private List<QaCrmFileUSNsToCheckModel> getUSNsToTest(Integer typeId) {
        log.info("Searching USNs to perform CrmFile test by type=[{}]", typeId);
        return testRepository.findUSNsToTestByType(typeId);
    }

    private void testOpeningCrmFileDetails(QaCrmFileUSNsToCheckModel usnToTest) {
        try {
            switch (usnToTest.getTypeId()) {
                case CRM_TYPE_4:
                    crm4Controller.getApplicationCrm4(usnToTest.getUSN(), null);
                    break;
                case CRM_TYPE_5:
                    crm5Controller.getApplication(usnToTest.getUSN(), null);
                    break;
                case CRM_TYPE_7:
                    crm7Controller.getApplicationCrm7(usnToTest.getUSN(), null);
                    break;
                case CRM_TYPE_14:
                    crm14Controller.getApplicationCrm14(usnToTest.getUSN(), null);
                    break;
                default:
                    log.warn("Unsupported CRM file usn=[{}] type=[{}]", usnToTest.getUSN(), usnToTest.getTypeId());
            }

            log.info("CrmForm file test successfully completed usn=[{}] type=[{}]", usnToTest.getUSN(), usnToTest.getTypeId());
            successRepository.save(new QaCrmFileTestSuccessModel(usnToTest.getUSN(), usnToTest.getTypeId()));
        } catch (Exception e) {
            log.info("CrmForm file test failed usn=[{}] type=[{}] with exception [{}] :: [{}] ", usnToTest.getUSN(), usnToTest.getTypeId(), e.getClass(), e.getMessage());
            errorsRepository.save(new QaCrmFileTestErrorsModel(usnToTest.getUSN(), usnToTest.getTypeId(), String.format("%s :: %s (StackTrace: %s...)", e.getClass(), e.getMessage(), Arrays.toString(e.getStackTrace()).substring(0, 2500))));
        }
    }

}
