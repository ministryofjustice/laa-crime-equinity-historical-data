package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Crm5ControllerTest {

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    Crm5Controller controller;

    @Test
    void getApplication_TaskNotFound() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplication(usnTest))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Task with USN").hasMessageContaining("not found");

    }

    @Test
    void getApplication_Valid() {
        Long usnTest = 5001604L;
        ResponseEntity<CRM5DetailsDTO> result = controller.getApplication(usnTest);
        softly.assertThat(result.getBody()).isInstanceOf(CRM5DetailsDTO.class);
        softly.assertThat(result.getBody().getUsn()).isEqualTo(5001604);
        softly.assertThat(result.getBody().getFirm().getFirmName()).isEqualTo("MOCK_FIRM_001");
    }

    @BeforeAll
    void preTest() throws IOException {
        // Mocking good XML
        FileInputStream fis = new FileInputStream("src/test/resources/Crm5MockFile_5001604.txt");
        JSONObject mockedCrm5Json = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
        byte[] fileDataByte = XML.toString(mockedCrm5Json).getBytes(StandardCharsets.UTF_8);
        TaskImageFilesModel taskModel = new TaskImageFilesModel();
        taskModel.setID(5001604L);
        taskModel.setCrmFile(fileDataByte);
        taskImageFilesRepository.save(taskModel);
    }
}