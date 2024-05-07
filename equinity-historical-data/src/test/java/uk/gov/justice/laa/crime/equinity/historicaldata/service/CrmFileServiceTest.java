package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFileServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    CrmFileService crmFileService;


    @BeforeAll
    void preTest() throws IOException {
        // Mocking good XML
        FileInputStream fis = new FileInputStream("src/test/resources/Crm5MockFile_5001604.txt");
        JSONObject mockedCrm5Json = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
        byte[] fileDataByte = XML.toString(mockedCrm5Json).getBytes(StandardCharsets.UTF_8);
        createMock(10L, fileDataByte);

        // Mocking XML with bad JSON format
        fis = new FileInputStream("src/test/resources/Crm5MockOFDFile_WrongFormat.txt");
        fileDataByte = fis.readAllBytes();
        createMock(11L, fileDataByte);
    }

    private void createMock(Long mockID, byte[] mockFile) throws IOException {
        TaskImageFilesModel taskModel = new TaskImageFilesModel();
        taskModel.setID(mockID);
        taskModel.setCrmFile(mockFile);
        taskImageFilesRepository.save(taskModel);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrmFileDataTest_ShouldReturnValidOFDFile() {
        long ustToTest = 10L;
        String expectedUrgent ="Yes";
        String expectedFirmName ="MOCK_FIRM_001";

        // execute
        Crm5DetailsModel result = crmFileService.getCrmFileContent(ustToTest, Crm5Model.class).getFormDetails();

        // checks
        softly.assertThat(result).isInstanceOf(Crm5DetailsModel.class);
        softly.assertThat(result.getFirm_name()).isEqualTo(expectedFirmName);
        softly.assertThat(result.getUrgent()).isEqualTo(expectedUrgent);
    }

    @Test
    void getCrmFileDataTest_ShouldReturnException() {
        long ustToTest = 11L;
        String expectedMessage = "JSONObject";

        // execute
        softly.assertThatThrownBy(() -> crmFileService.getCrmFileContent(ustToTest, Crm5Model.class))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(expectedMessage);
    }
}