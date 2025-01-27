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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormDetailsRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm4ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_4);
    private static final String DENIED_PROFILE_TYPES = "7,9,19";
    private static final Long OLD_FORM_USN = 5001613L;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFormDetailsRepository crmFormDetailsRepository;

    @Autowired
    Crm4Controller controller;

    @BeforeAll
    void preTest() {
        // Mocking good XML
        Map<Long, String> validUsnTests = Map.of(
                5001912L, "src/test/resources/Crm4MockFile_5001912.txt",
                4795804L, "src/test/resources/Crm4MockFile_4795804.txt",
                5001613L, "src/test/resources/Crm4MockFile_5001613.txt");

        validUsnTests.forEach((testUsn, testFile) -> {
            // Mocking good XML
            try {
                FileInputStream fis = new FileInputStream(testFile);
                JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
                byte[] fileDataByte = XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
                CrmFormDetailsModel crmFormDetail = new CrmFormDetailsModel();
                crmFormDetail.setUSN(testUsn);
                crmFormDetail.setTypeId(CRM_TYPE_4);
                crmFormDetail.setCrmFile(fileDataByte);
                crmFormDetailsRepository.save(crmFormDetail);
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
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(null, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .hasMessage("Expected USN not be null");
    }

    @Test
    void getApplicationCrm4Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(usnTest, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage("Task with USN 10 not found");
    }

    @Test
    void getApplicationCrm4Test_WhenGivenOldFormUsnThenReturnTaskNotFoundException() {
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(OLD_FORM_USN, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 5001613 is unavailable");
    }

    @NullSource  // test when profileTypes = null
    @ValueSource(strings = {"1"})
    @ParameterizedTest
    void getApplicationCrm4Test_WhenGivenExistingUsnThenReturnValidResponse(String profileAcceptedTypes) {
        Long usnTest = 5001912L;
        ResponseEntity<Crm4FormDTO> result = controller.getApplicationCrm4(usnTest, profileAcceptedTypes);

        Crm4FormDTO crm4FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm4FormDTO).isNotNull();
        softly.assertThat(crm4FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm4FormDTO.getFormDetails().getStandardProperties().getUsn()).isEqualTo(5001912);
        softly.assertThat(crm4FormDTO.getFormDetails().getCaseDetails().getFirm().getUrn()).isEqualTo("GH65789");
        softly.assertThat(crm4FormDTO.getFormDetails().getExpenditureDetails().getDetails()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm4Test_WhenFurtherAttachments_With_FileAttachment_Contains_FileKeyForDownload() {
        Long usnTest = 4795804L;
        ResponseEntity<Crm4FormDTO> result = controller.getApplicationCrm4(usnTest, null);

        Crm4FormDTO crm4FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm4FormDTO).isNotNull();
        softly.assertThat(crm4FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm4FormDTO.getFormDetails().getFurtherInformation().size()).isEqualTo(3);
        softly.assertThat(crm4FormDTO.getFormDetails().getFurtherInformation().get(0).getKey()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm4Test_WhenFurtherAttachments_With_MessageAttachment_Contains_EmptyFileKey() {
        Long usnTest = 4795804L;
        ResponseEntity<Crm4FormDTO> result = controller.getApplicationCrm4(usnTest, null);

        Crm4FormDTO crm4FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm4FormDTO).isNotNull();
        softly.assertThat(crm4FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm4FormDTO.getFormDetails().getFurtherInformation().size()).isEqualTo(3);
        softly.assertThat(crm4FormDTO.getFormDetails().getFurtherInformation().get(2).getKey()).isNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void getApplicationCrm4Test_WhenGivenExistingUsnButNotAcceptedTypeThenReturnTaskNotFoundException() {
        Long usnTest = 5001912L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm4(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [1]");
    }
}
