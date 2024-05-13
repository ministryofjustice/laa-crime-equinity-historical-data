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
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7SummaryOfClaimDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm7ControllerTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    Crm7Controller controller;

    @BeforeAll
    void preTest() throws IOException {
        // Mocking good XML
        FileInputStream fis = new FileInputStream("src/test/resources/Crm7MockFile_5001662.txt");
        JSONObject mockedCrm5Json = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
        byte[] fileDataByte = XML.toString(mockedCrm5Json).getBytes(StandardCharsets.UTF_8);
        TaskImageFilesModel taskModel = new TaskImageFilesModel();
        taskModel.setID(5001662L);
        taskModel.setCrmFile(fileDataByte);
        taskImageFilesRepository.save(taskModel);
    }

    /**
     * USN input checks
     */
    @Test
    void getApplicationCrm7Test_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        String expectedMessage = "not be null";

        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(null))
            .isInstanceOf(InvalidDataAccessApiUsageException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void getApplicationCrm7Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(usnTest))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Task with USN").hasMessageContaining("not found");
    }

    @Test
    void getApplicationCrm7Test_WhenGivenExistingUsnThenReturnValidResponse() {
        Long usnTest = 5001662L;
        ResponseEntity<Crm7DetailsDTO> result = controller.getApplicationCrm7(usnTest);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm7DetailsDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getUsn()).isEqualTo(usnTest.intValue());
        softly.assertThat(Objects.requireNonNull(result.getBody()).getSummary()).isInstanceOf(Crm7SummaryOfClaimDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getSummary().getClientFirstName()).isEqualTo("James");
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}