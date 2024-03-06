package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;


@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFileServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    TaskRepository taskRepository;

    @Autowired
    CrmFileService crmFileService;


    void setupMockitoForTest() {
//        given(taskRepository.findById(any())).willReturn(TASK);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrmFileDataTest() {
        // TODO (EMP-151): Add a mock to fake the return of the data. Also, can be used with a specific profile and use database connection
        long ustToTest = 10L;
        String expectedPath = "toBeFilled";
        String expectedFcCurrentUser = "toBeExpected";

        Crm5Model result = crmFileService.getCrmFileData(ustToTest);

        softly.assertThat(result).isInstanceOf(Crm5Model.class);
        softly.assertThat(result.getTargetpath()).isEqualTo(expectedPath);
        softly.assertThat(result.getFormDetails()).isInstanceOf(Crm5DetailsModel.class);
        softly.assertThat(result.getFormDetails().getFc_current_user()).isEqualTo(expectedFcCurrentUser);
    }
}