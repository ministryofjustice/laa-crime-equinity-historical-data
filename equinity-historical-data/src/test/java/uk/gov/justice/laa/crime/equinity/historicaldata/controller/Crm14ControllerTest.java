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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.UnauthorizedUserProfileException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14AttachmentModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14PSEMessageModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.AttachmentStoreRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.Crm14AttachmentService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.Crm14PSEMessagesService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14ControllerTest {
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);
    private static final String DENIED_PROFILE_TYPES = Integer.toString(CRM_TYPE_5);

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    AttachmentStoreRepository attachmentStoreRepository;

    @Autowired
    Crm14AttachmentService crm14AttachmentService;

    @MockBean
    Crm14PSEMessagesService crm14PSEMessagesService;

    @Autowired
    Crm14Controller controller;

    Map<Long, String> validUsnTests;

    private List<Crm14PSEMessageModel> pseTLmessages;

    @BeforeAll
    void preTest() {
        validUsnTests = new HashMap<>();
        validUsnTests.put(5001817L, "src/test/resources/Crm14MockFile_5001817.txt");
        validUsnTests.put(5001669L, "src/test/resources/Crm14MockFile_5001669.txt");
        validUsnTests.put(1826833L, "src/test/resources/Crm14MockFile_1826833.txt");

        Crm14AttachmentModel attachModel = new Crm14AttachmentModel(5001817L,"61c6df22-c18c-4182-9560-897b0e18dfcd","Screenshot 2022-05-23 at 13.26.59.png",3,null,null,"Accepted","BANK_STATEMENTS",
                "3x monthly statements","",341);
        attachmentStoreRepository.save(attachModel);

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

        pseTLmessages = new ArrayList<>();
        Crm14PSEMessageModel crm14PSEMessageModel = getCrm14PSEMessagesModel();
        pseTLmessages.add(crm14PSEMessageModel);
    }
    private static Crm14PSEMessageModel getCrm14PSEMessagesModel() {
        Crm14PSEMessageModel crm14PSEMessageModel = new Crm14PSEMessageModel();
        crm14PSEMessageModel.setUSN(5001817L);
        crm14PSEMessageModel.setUsn_pse(4938478L);
        crm14PSEMessageModel.setDateLastUpdate( new Date(2024,8, 19, 20, 15));
        crm14PSEMessageModel.setMessage("Thank you for providing this, we require a profit / loss sheet / full financial accounts in which we can see the list of business expenses being subtracted from the turnover to arrive at the NET profit. ");
        return crm14PSEMessageModel;
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
            ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);

            softly.assertThat(result.getBody()).isNotNull();
            softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
            softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getLegalRepresentativeUse().getDateStamp().getUsn()).isEqualTo(usnToTest.intValue());
            softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
    }

    @Test
    void getApplication_Crm14Test_NewAttachments() {
        Long usnToTest = 5001669L;
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getEvidencePart2().getNewAttachments()).isNotEmpty();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void getApplication_Crm14Test_HowBills() {
        Long usnToTest = 5001669L;
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getIncome().getNoMoneySleepingAtFriend()).isEqualTo(true);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getIncome().getHomeless()).isEqualTo(true);
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void getApplication_Crm14Test_ProcessedAttachments() {
        Long usnToTest = 5001817L;
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getEvidencePart2().getProcessedAttachments()).isNotEmpty();
        softly.assertThat(Objects.requireNonNull(result.getBody()).getEvidenceFiles().getFiles()).hasSize(2);
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void getApplication_Crm14PSE_TLMessage() {
        Long usnToTest = 5001669L;
        when(crm14PSEMessagesService.getMessages(5001669L)).thenReturn(pseTLmessages);
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getEvidencePart2().getPseMessages()).isNotEmpty();
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getEvidencePart2().getPseMessages().get(0).getPseUsn()).isEqualTo(4938478L);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getEvidencePart2().getPseMessages().get(0).getMessage()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void getApplication_Crm14Test_AddProcessedAttachmentsToEvidenceFiles() {
        Long usnToTest = 5001817L;
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getEvidencePart2().getProcessedAttachments()).hasSize(1);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getEvidenceFiles().getFiles()).hasSize(2);
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    void getApplication_Crm14Test_FundingDecisions() {
        Long usnToTest = 1826833L;
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getOfficeUseOnly().getFundingDecisions().get(0).getCaseNumber()).isEqualTo("061601049057");
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    void getApplication_Crm14Test_MessageHistory() {
        Long usnToTest = 1826833L;
        // Test with accepted types
        ResponseEntity<Crm14FormDTO> result = controller.getApplicationCrm14(usnToTest, ACCEPTED_PROFILE_TYPES);
        softly.assertThat(result.getBody()).isNotNull();
        softly.assertThat(result.getBody()).isInstanceOf(Crm14FormDTO.class);
        softly.assertThat(Objects.requireNonNull(result.getBody()).getFormDetails().getOfficeUseOnly().getMessageHistory().get(0).getSenderUniqueName()).isNotNull();
        softly.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    void getApplication_Crm14Test_WhenGivenExistingUsnWrongProfileType() {
        Long usnTest = 5001817L;
        // Test with accepted types
        softly.assertThatThrownBy(() -> controller.getApplicationCrm14(usnTest, DENIED_PROFILE_TYPES))
                .isInstanceOf(UnauthorizedUserProfileException.class)
                .hasMessageContaining("Unauthorized").hasMessageContaining("not have privileges");
    }
}