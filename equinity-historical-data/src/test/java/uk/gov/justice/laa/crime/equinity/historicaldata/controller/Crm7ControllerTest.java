package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7OfficialUseDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7SummaryOfClaimDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormDetailsRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.CrmFormUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static org.mockito.Mockito.mockStatic;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_7;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm7ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_7);
    private static final String DENIED_PROFILE_TYPES = "12,7,9";
    private static final Long OLD_FORM_USN = 5001664L;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFormDetailsRepository crmFormDetailsRepository;

    @Autowired
    Crm7Controller controller;

    private MockedStatic<CrmFormUtil> mockStatic;

    @BeforeAll
    void preTest() {
        Map<Long, String> validUsnTests = Map.of(
                5001662L, "src/test/resources/Crm7MockFile_5001662.txt",
                5001597L, "src/test/resources/Crm7MockFile_5001597.txt",
                4808706L,  "src/test/resources/Crm7MockFile_4808706.txt",
                4808532L,  "src/test/resources/Crm7MockFile_4808532.txt",
                4808666L, "src/test/resources/Crm7MockFile_4808666.txt"
        );

        validUsnTests.forEach((testUsn, testFile) -> {
            // Mocking good XML
            try {
                FileInputStream fis = new FileInputStream(testFile);
                JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
                byte[] fileDataByte = XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
                CrmFormDetailsModel crmFormDetail = new CrmFormDetailsModel();
                crmFormDetail.setUSN(testUsn);
                crmFormDetail.setTypeId(CRM_TYPE_7);
                crmFormDetail.setCrmFile(fileDataByte);
                crmFormDetailsRepository.save(crmFormDetail);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @BeforeEach
    public void setUp() {
        mockStatic = mockStatic(CrmFormUtil.class);
    }

    @AfterEach
    public void tearDown() {
        mockStatic.close();
    }

    /**
     * USN input checks
     */
    @Test
    void getApplicationCrm7Test_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(null, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(InvalidDataAccessApiUsageException.class)
            .hasMessage("Expected USN not be null");
    }

    @Test
    void getApplicationCrm7Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(usnTest, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage("Task with USN 10 not found");
    }

    @Test
    void getApplicationCrm7Test_WhenGivenOldFormUsnThenReturnTaskNotFoundException() {
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(OLD_FORM_USN, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 5001664 is unavailable");
    }

    @ParameterizedTest
    @ValueSource(longs = {5001662L,5001597L, 4808706L, 4808532L, 4808666L})
    void getApplicationCrm7Test_WhenGivenExistingUsnThenReturnValidResponse(Long usn) {
        String expectedClientFirstName = "James";
        String expectedClientSurname = "Bond";

        // Test with accepted types
            ResponseEntity<Crm7FormDTO> result = controller.getApplicationCrm7(usn, ACCEPTED_PROFILE_TYPES);

            Crm7FormDTO crm7FormDTO = Objects.requireNonNull(result.getBody());
            softly.assertThat(crm7FormDTO).isNotNull();
            softly.assertThat(crm7FormDTO.getFormDetails()).isNotNull();
            softly.assertThat(crm7FormDTO.getFormDetails().getUsn()).isEqualTo(usn.intValue());
            softly.assertThat(crm7FormDTO.getFormDetails().getSummary()).isInstanceOf(Crm7SummaryOfClaimDTO.class);
            softly.assertThat(crm7FormDTO.getFormDetails().getSummary().getClientFirstName()).isEqualTo(expectedClientFirstName);
            softly.assertThat(crm7FormDTO.getFormDetails().getSummary().getClientSurname()).isEqualTo(expectedClientSurname);
            softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm7Test_WhenGivenExistingUsnWithNoProfileAcceptedTypesThenReturnValidResponse() {
        Long usnTest = 5001662L;

        ResponseEntity<Crm7FormDTO> result = controller.getApplicationCrm7(usnTest, null);

        Crm7FormDTO crm7FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm7FormDTO).isNotNull();
        softly.assertThat(crm7FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm7FormDTO.getFormDetails().getUsn()).isEqualTo(usnTest.intValue());
        softly.assertThat(crm7FormDTO.getFormDetails().getSummary()).isInstanceOf(Crm7SummaryOfClaimDTO.class);
        softly.assertThat(crm7FormDTO.getFormDetails().getSummary().getClientFirstName()).isEqualTo("James");
        softly.assertThat(crm7FormDTO.getFormDetails().getSummary().getClientSurname()).isEqualTo("Bond");
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm7Test_WhenGivenExistingUsnButNotAcceptedTypesThenReturnTaskNotFoundException() {
        Long usnTest = 5001662L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [5]");
    }

    @Test
    void getApplicationCrm7Test_OfficialUseResponse() {
        Long usnTest = 5001662L;
        ResponseEntity<Crm7FormDTO> result = controller.getApplicationCrm7(usnTest, null);

        Crm7FormDTO crm7FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm7FormDTO).isNotNull();
        softly.assertThat(crm7FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm7FormDTO.getFormDetails().getUsn()).isEqualTo(usnTest.intValue());
        softly.assertThat(crm7FormDTO.getFormDetails().getOfficeUseOnly()).isInstanceOf(Crm7OfficialUseDTO.class);
        softly.assertThat(crm7FormDTO.getFormDetails().getOfficeUseOnly().getQualityControl().getDecision()).isEqualTo("PG");
        softly.assertThat(crm7FormDTO.getFormDetails().getOfficeUseOnly().getAuthority().getSignedAuth()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
