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
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm5FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormDetailsRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.CrmFormUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Crm5ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_5);
    private static final String DENIED_PROFILE_TYPES = "9,19";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    private CrmFormUtil crmFormUtil;

    @Autowired
    CrmFormDetailsRepository crmFormDetailsRepository;

    @Autowired
    Crm5Controller controller;


    @BeforeAll
    void preTest() {
        // Mocking good XML
        Map.of(
                5001604L, "src/test/resources/Crm5MockFile_5001604.txt",
                5001716L, "src/test/resources/Crm5MockFile_5001716.txt"
        ).forEach((testUsn, testFile) -> {
            // Mocking good XML
            try {
                CrmFormDetailsModel crmFormDetail = buildCrmFormDetailsModel(testUsn, testFile);
                crmFormDetailsRepository.save(crmFormDetail);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void getApplication_TaskNotFound() {
        Long usnTest = 10L;
        softly.assertThatThrownBy(() -> controller.getApplication(usnTest, ACCEPTED_PROFILE_TYPES))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Task with USN 10 not found");

    }

    @NullSource  // test when profileTypes = null
    @ValueSource(strings = {"4"})
    @ParameterizedTest
    void getApplicationTest_WhenGivenExistingUsnThenReturnValidResponse(String profileAcceptedTypes) {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, profileAcceptedTypes);

        Crm5FormDTO crm5FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm5FormDTO).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(crm5FormDTO.getFormDetails().getFirm().getFirmName()).isEqualTo("MOCK_FIRM_001");
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnButNotAcceptedTypeThenReturnTaskNotFoundException() {
        Long usnTest = 5001604L;
        softly.assertThatThrownBy(() -> controller.getApplication(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessage("Unauthorized. User profile does not have privileges to access requested report type [4]");
    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnWithAcPreparationTimeFormatThenReturnValidResponse() {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        Crm5FormDTO crm5FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm5FormDTO).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(crm5FormDTO.getFormDetails().getAllCosts().getAnticipatedCosts().getPreparation().getTime()).isEqualTo("00:15:00");
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationTest_WhenGivenExistingUsnWithAcPreparationDateTimeFormatThenReturnValidResponse() {
        Long usnTest = 5001716L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        Crm5FormDTO crm5FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm5FormDTO).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails().getUsn()).isEqualTo(5001716);
        softly.assertThat(crm5FormDTO.getFormDetails().getAllCosts().getAnticipatedCosts().getPreparation().getTime()).isEqualTo("193:48:00");
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getApplicationTest_OfficialUseResponse() {
        Long usnTest = 5001604L;
        ResponseEntity<Crm5FormDTO> result = controller.getApplication(usnTest, null);

        Crm5FormDTO crm5FormDTO = Objects.requireNonNull(result.getBody());
        softly.assertThat(crm5FormDTO).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails()).isNotNull();
        softly.assertThat(crm5FormDTO.getFormDetails().getUsn()).isEqualTo(5001604);
        softly.assertThat(crm5FormDTO.getFormDetails().getOfficeUseOnly().getQualityControl().getDecision()).isEqualTo("G");
        softly.assertThat(crm5FormDTO.getFormDetails().getOfficeUseOnly().getAuthority().getSignedAuthDate()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private static CrmFormDetailsModel buildCrmFormDetailsModel(Long testUsn, String testFile) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(testFile);
        JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
        byte[] fileDataByte = XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
        CrmFormDetailsModel crmFormDetail = new CrmFormDetailsModel();
        crmFormDetail.setUSN(testUsn);
        crmFormDetail.setTypeId(CRM_TYPE_5);
        crmFormDetail.setCrmFile(fileDataByte);
        return crmFormDetail;
    }
}
