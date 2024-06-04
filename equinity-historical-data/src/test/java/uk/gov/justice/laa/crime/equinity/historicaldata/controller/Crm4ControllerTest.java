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
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4ControllerTest {
    private static final String ACCEPTED_TYPES_DEFAULT = null;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    Crm4Controller controller;

    @BeforeAll
    void preTest() throws IOException {
        // Mocking good XML
        FileInputStream fis = new FileInputStream("src/test/resources/Crm4MockFile_5001912.txt");
        JSONObject mockedCrm5Json = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
        byte[] fileDataByte = XML.toString(mockedCrm5Json).getBytes(StandardCharsets.UTF_8);
        TaskImageFilesModel taskModel = new TaskImageFilesModel();
        taskModel.setUSN(5001912L);
        taskModel.setTypeId(CRM_TYPE_4);
        taskModel.setCrmFile(fileDataByte);
        taskImageFilesRepository.save(taskModel);
    }

    /**
     * USN input checks
     */
    @Test
    void getApplicationCrm4Test_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        String expectedMessage = "not be null";

        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(null, ACCEPTED_TYPES_DEFAULT))
            .isInstanceOf(InvalidDataAccessApiUsageException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void getApplicationCrm4Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(usnTest, ACCEPTED_TYPES_DEFAULT))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Task with USN").hasMessageContaining("not found");
    }

    @Test
    void getApplicationCrm4Test_WhenGivenExistingUsnThenReturnValidResponse() {
        Long usnTest = 5001912L;
        String urn = "GH65789";
        ResponseEntity<Crm4DetailsDTO> result = controller.getApplicationCrm4(usnTest, ACCEPTED_TYPES_DEFAULT);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm4DetailsDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getCaseDetails().getFirm().getUrn()).isEqualTo(urn);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getExpenditureDetails().getDetails()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}