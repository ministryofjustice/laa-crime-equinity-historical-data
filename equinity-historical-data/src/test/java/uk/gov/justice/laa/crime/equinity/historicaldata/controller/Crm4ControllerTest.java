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
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_4);
    private static final String DENIED_PROFILE_TYPES = "7,9,19";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    Crm4Controller controller;

    Map<Long, String> validUsnTests;

    @BeforeAll
    void preTest() throws IOException {
        // Mocking good XML
        validUsnTests = new HashMap<>();
        validUsnTests.put(5001912L, "src/test/resources/Crm4MockFile_5001912.txt");
        validUsnTests.put(4795804L, "src/test/resources/Crm4MockFile_4795804.txt");

        validUsnTests.forEach((testUsn, testFile) -> {
            // Mocking good XML
            try {
                FileInputStream fis = new FileInputStream(testFile);
                JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
                byte[] fileDataByte = XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
                TaskImageFilesModel taskModel = new TaskImageFilesModel();
                taskModel.setUSN(testUsn);
                taskModel.setTypeId(CRM_TYPE_4);
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
    void getApplicationCrm4Test_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        String expectedMessage = "not be null";

        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(null, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(InvalidDataAccessApiUsageException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void getApplicationCrm4Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(usnTest, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Task with USN").hasMessageContaining("not found");
    }

    @Test
    void getApplicationCrm4Test_WhenGivenExistingUsnThenReturnValidResponse() {
        Long usnTest = 5001912L;
        String urn = "GH65789";
        ResponseEntity<Crm4FormDTO> result;

        // Test with Accepted profile
        result = controller.getApplicationCrm4(usnTest, ACCEPTED_PROFILE_TYPES);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm4FormDTO.class);
        softly.assertThat(result.getBody().getFormDetails()).isNotNull();
        softly.assertThat(result.getBody().getFormDetails()).isInstanceOf(Crm4DetailsDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getCaseDetails().getFirm().getUrn()).isEqualTo(urn);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getExpenditureDetails().getDetails()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm4Test_WhenGivenExistingUsnWithNoProfileAcceptedTypesThenReturnValidResponse() {
        Long usnTest = 5001912L;
        String urn = "GH65789";
        ResponseEntity<Crm4FormDTO> result = controller.getApplicationCrm4(usnTest, null);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm4FormDTO.class);
        softly.assertThat(result.getBody().getFormDetails()).isNotNull();
        softly.assertThat(result.getBody().getFormDetails()).isInstanceOf(Crm4DetailsDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getCaseDetails().getFirm().getUrn()).isEqualTo(urn);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getExpenditureDetails().getDetails()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void getApplicationCrm4Test_WhenFurtherAttachments_With_FileAttachment_Contains_FileKeyForDownload() {
        Long usnTest = 4795804L;
        ResponseEntity<Crm4FormDTO> result = controller.getApplicationCrm4(usnTest, null);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm4FormDTO.class);
        softly.assertThat(result.getBody().getFormDetails()).isNotNull();
        softly.assertThat(result.getBody().getFormDetails()).isInstanceOf(Crm4DetailsDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getFurtherInformation()).isNotEmpty();
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getFurtherInformation().size()).isEqualTo(3);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getFurtherInformation().get(0).getKey()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void getApplicationCrm4Test_WhenFurtherAttachments_With_MessageAttachment_Contains_EmptyFileKey() {
        Long usnTest = 4795804L;
        ResponseEntity<Crm4FormDTO> result = controller.getApplicationCrm4(usnTest, null);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm4FormDTO.class);
        softly.assertThat(result.getBody().getFormDetails()).isNotNull();
        softly.assertThat(result.getBody().getFormDetails()).isInstanceOf(Crm4DetailsDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getFurtherInformation()).isNotEmpty();
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getFurtherInformation().get(2).getKey()).isNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm4Test_WhenGivenExistingUsnButNotAcceptedTypeThenReturnTaskNotFoundException() {
        Long usnTest = 5001912L;

        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessageContaining("Unauthorized").hasMessageContaining("User profile does not have privileges");
    }
}