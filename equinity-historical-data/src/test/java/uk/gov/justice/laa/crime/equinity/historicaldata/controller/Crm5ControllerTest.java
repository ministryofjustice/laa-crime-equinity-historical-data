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
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm5FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormDetailsRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;


@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Crm5ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_5);
    private static final String DENIED_PROFILE_TYPES = "9,19";

    @Autowired
    CrmFormDetailsRepository detailsRepository;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    Crm5Controller controller;

    Map<Long, String> validUsnTests;

    @Test
    void getApplication_TaskNotFound() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplication(usnTest, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Task with USN").hasMessageContaining("not found");

    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnThenReturnValidResponse() {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, ACCEPTED_PROFILE_TYPES);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm5FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(result.getBody().getFormDetails().getFirm().getFirmName()).isEqualTo("MOCK_FIRM_001");
    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnWithNoProfileAcceptedTypesThenReturnValidResponse() {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm5FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(result.getBody().getFormDetails().getFirm().getFirmName()).isEqualTo("MOCK_FIRM_001");
    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnButNotAcceptedTypeThenReturnTaskNotFoundException() {
        Long usnTest = 5001604L;

        softly.assertThatThrownBy(() -> controller.getApplication(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessageContaining("Unauthorized").hasMessageContaining("not have privileges");
    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnWithAcPreparationTimeFormatThenReturnValidResponse() {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm5FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(result.getBody().getFormDetails().getAllCosts().getAnticipatedCosts().getPreparation().getTime()).isEqualTo("00:15:00");    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnWithAcPreparationDateTimeFormatThenReturnValidResponse() {
        Long usnTest = 5001716L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm5FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getUsn()).isEqualTo(5001716L);
        softly.assertThat(result.getBody().getFormDetails().getAllCosts().getAnticipatedCosts().getPreparation().getTime()).isEqualTo("193:48:00");    }
    @Test
    void getApplicationTest_OfficialUseResponse() {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm5FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(result.getBody().getFormDetails().getOfficeUseOnly().getQualityControl().getDecision()).isEqualTo("G");
        softly.assertThat(result.getBody().getFormDetails().getOfficeUseOnly().getAuthority().getSignedAuthDate()).isNotNull();
    }


    @BeforeAll
    void preTest() throws IOException {
        // Mocking good XML
        validUsnTests = new HashMap<>();
        validUsnTests.put(5001604L, "src/test/resources/Crm5MockFile_5001604.txt");
        validUsnTests.put(5001716L, "src/test/resources/Crm5MockFile_5001716.txt");

        validUsnTests.forEach((testUsn, testFile) -> {
            // Mocking good XML
            try {
                FileInputStream fis = new FileInputStream(testFile);
                JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
                byte[] fileDataByte = XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
                CrmFormDetailsModel taskModel = new CrmFormDetailsModel();
                taskModel.setUSN(testUsn);
                taskModel.setTypeId(CRM_TYPE_5);
                taskModel.setCrmFile(fileDataByte);
                detailsRepository.save(taskModel);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}