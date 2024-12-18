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
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7OfficialUseDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7SummaryOfClaimDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormDetailsRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_7;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm7ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_7);
    private static final String DENIED_PROFILE_TYPES = "12,7,9";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFormDetailsRepository crmFormDetailsRepository;

    @Autowired
    Crm7Controller controller;

    Map<Long, String> validUsnTests;

    @BeforeAll
    void preTest() {
        validUsnTests = new HashMap<>();
        validUsnTests.put(5001662L, "src/test/resources/Crm7MockFile_5001662.txt");
        validUsnTests.put(5001597L, "src/test/resources/Crm7MockFile_5001597.txt");
        validUsnTests.put(4808706L,  "src/test/resources/Crm7MockFile_4808706.txt");
        validUsnTests.put(4808532L,  "src/test/resources/Crm7MockFile_4808532.txt");
        validUsnTests.put(4808666L, "src/test/resources/Crm7MockFile_4808666.txt");

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

    /**
     * USN input checks
     */
    @Test
    void getApplicationCrm7Test_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        String expectedMessage = "not be null";

        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(null, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(InvalidDataAccessApiUsageException.class)
            .hasMessageContaining(expectedMessage);
    }

    @Test
    void getApplicationCrm7Test_WhenGivenNonExistingUsnThenReturnTaskNotFoundException() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(usnTest, ACCEPTED_PROFILE_TYPES))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Task with USN").hasMessageContaining("not found");
    }

    @Test
    void getApplicationCrm7Test_WhenGivenExistingUsnThenReturnValidResponse() {
        String expectedClientFirstName = "James";
        String expectedClientSurname = "Bond";

        // Test with accepted types
        validUsnTests.keySet().forEach((usnToTest) -> {
            ResponseEntity<Crm7FormDTO> result = controller.getApplicationCrm7(usnToTest, ACCEPTED_PROFILE_TYPES);

            softly.assertThat(result.getBody()).isNotNull();
            softly.assertThat(result.getBody()).isInstanceOf(Crm7FormDTO.class);

            Crm7DetailsDTO crmFormDetails = Objects.requireNonNull(result.getBody()).getFormDetails();
            softly.assertThat(crmFormDetails).isInstanceOf(Crm7DetailsDTO.class);
            softly.assertThat(crmFormDetails.getUsn()).isEqualTo(usnToTest.intValue());
            softly.assertThat(crmFormDetails.getSummary()).isInstanceOf(Crm7SummaryOfClaimDTO.class);
            softly.assertThat(crmFormDetails.getSummary().getClientFirstName()).isEqualTo(expectedClientFirstName);
            softly.assertThat(crmFormDetails.getSummary().getClientSurname()).isEqualTo(expectedClientSurname);
            softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
    }

    @Test
    void getApplicationCrm7Test_WhenGivenExistingUsnWithNoProfileAcceptedTypesThenReturnValidResponse() {
        Long usnTest = 5001662L;
        String expectedClientFirstName = "James";
        String expectedClientSurname = "Bond";

        ResponseEntity<Crm7FormDTO> result = controller.getApplicationCrm7(usnTest, null);

        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm7FormDTO.class);

        Crm7DetailsDTO crmFormDetails = Objects.requireNonNull(result.getBody()).getFormDetails();
        softly.assertThat(crmFormDetails).isInstanceOf(Crm7DetailsDTO.class);
        softly.assertThat(crmFormDetails.getUsn()).isEqualTo(usnTest.intValue());
        softly.assertThat(crmFormDetails.getSummary()).isInstanceOf(Crm7SummaryOfClaimDTO.class);
        softly.assertThat(crmFormDetails.getSummary().getClientFirstName()).isEqualTo(expectedClientFirstName);
        softly.assertThat(crmFormDetails.getSummary().getClientSurname()).isEqualTo(expectedClientSurname);
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationCrm7Test_WhenGivenExistingUsnButNotAcceptedTypesThenReturnTaskNotFoundException() {
        Long usnTest = 5001662L;
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessageContaining("Unauthorized").hasMessageContaining("not have privileges");
    }
    @Test
    void getApplicationCrm7Test_OfficialUseResponse() {
        Long usnTest = 5001662L;
        ResponseEntity<Crm7FormDTO> result = controller.getApplicationCrm7(usnTest, null);

        Crm7DetailsDTO crmFormDetails = Objects.requireNonNull(result.getBody()).getFormDetails();
        softly.assertThat(crmFormDetails).isInstanceOf(Crm7DetailsDTO.class);
        softly.assertThat(crmFormDetails.getUsn()).isEqualTo(usnTest.intValue());
        softly.assertThat(crmFormDetails.getOfficeUseOnly()).isInstanceOf(Crm7OfficialUseDTO.class);
        softly.assertThat(crmFormDetails.getOfficeUseOnly().getQualityControl().getDecision()).isEqualTo("PG");
        softly.assertThat(crmFormDetails.getOfficeUseOnly().getAuthority().getSignedAuth()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
