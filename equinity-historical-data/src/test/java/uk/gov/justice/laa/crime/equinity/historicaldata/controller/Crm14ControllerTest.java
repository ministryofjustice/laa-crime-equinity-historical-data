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
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);
    private static final String DENIED_PROFILE_TYPES = "9,19";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    Crm14Controller controller;

    Map<Long, String> validUsnTests;

    @BeforeAll
    void preTest() {
        validUsnTests = new HashMap<>();
        validUsnTests.put(5001817L, "src/test/resources/Crm14MockFile_5001817.txt");

        validUsnTests.forEach((testUsn, testFile) -> {
            // Mocking good XML
            try {
                FileInputStream fis = new FileInputStream(testFile);
                JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
                byte[] fileDataByte = XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
                TaskImageFilesModel taskModel = new TaskImageFilesModel();
                taskModel.setUSN(testUsn);
                taskModel.setTypeId(CRM_TYPE_14);
                taskModel.setCrmFile(fileDataByte);
                taskImageFilesRepository.save(taskModel);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * USN input checks
     */
    @Test
    void getApplication_Crm14Test_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        String expectedMessage = "not be null";

        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm14(null, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(InvalidDataAccessApiUsageException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void getApplication_Crm14Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm14(usnTest, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Task with USN").hasMessageContaining("not found");
    }

    @Test
    void getApplication_Crm14Test_WhenGivenExistingUsnThenReturnValidResponse() {

        // Test with accepted types
        validUsnTests.keySet().forEach((usnToTest) -> {
            ResponseEntity<Crm14DetailsDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);

            softly.assertThat(result.getBody()).isNotNull();
            softly.assertThat(result.getBody()).isInstanceOf(Crm14DetailsDTO.class);
            softly.assertThat(Objects.requireNonNull(result.getBody()).getLegalRepresentativeUse().getDateStamp().getUsn()).isEqualTo(usnToTest.intValue());
            softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
    }
}
