package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.TaskImageFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskImageFilesRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.*;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFileServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskImageFilesRepository taskImageFilesRepository;

    @Autowired
    CrmFileService crmFileService;

    Map<Long, String> crmFileTests;
    Map<Long, Integer> crmFileTestTypes;
    Map<Long, Integer> linkedAttachmentTests;


    @BeforeAll
    void preTest() throws IOException {
        crmFileTestTypes = new HashMap<>();
        crmFileTestTypes.put(5001912L, CRM_TYPE_4);
        crmFileTestTypes.put(5001604L, CRM_TYPE_5);
        crmFileTestTypes.put(11L, CRM_TYPE_5);
        crmFileTestTypes.put(5001662L, CRM_TYPE_7);
        crmFileTestTypes.put(4808706L, CRM_TYPE_7);
        crmFileTestTypes.put(5001597L, CRM_TYPE_7);


        crmFileTests =  new HashMap<>();
        crmFileTests.put(5001604L, "src/test/resources/Crm5MockFile_5001604.txt"); // Mocking good XML
        crmFileTests.put(11L, "src/test/resources/Crm5MockOFDFile_WrongFormat.txt"); // Invalid XML file
        crmFileTests.put(5001912L, "src/test/resources/Crm4MockFile_5001912.txt");
        crmFileTests.put(4808706L, "src/test/resources/Crm7MockFile_4808706.txt");
        crmFileTests.put(5001662L, "src/test/resources/Crm7MockFile_5001662.txt");
        crmFileTests.put(5001597L, "src/test/resources/Crm7MockFile_5001597.txt");

        crmFileTests.forEach((testUsn, testFile) -> {
            try {
                createMock(testUsn, crmFileTestTypes.get(testUsn), loadMockFile(testFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        linkedAttachmentTests = new HashMap<>();
        linkedAttachmentTests.put(5001604L, 0);
        linkedAttachmentTests.put(5001662L, 0);
        linkedAttachmentTests.put(5001912L, 3);
        linkedAttachmentTests.put(4808706L, 2);
        linkedAttachmentTests.put(5001597L, 1);
    }

    private byte[] loadMockFile(String fileName) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            JSONObject mockedCrmFileJson = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
            return XML.toString(mockedCrmFileJson).getBytes(StandardCharsets.UTF_8);
        } catch (JSONException e) {
            return loadMockInvalidFile(fileName);
        }
    }

    private byte[] loadMockInvalidFile(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            return fis.readAllBytes();
        }
    }

    private void createMock(Long mockID, Integer typeId, byte[] mockFile) {
        TaskImageFilesModel taskModel = new TaskImageFilesModel();
        taskModel.setUSN(mockID);
        taskModel.setTypeId(typeId);
        taskModel.setCrmFile(mockFile);
        taskImageFilesRepository.save(taskModel);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrmFileDataTest_ShouldReturnValidOFDFile() {
        long usnToTest = 5001604L;
        int typeId = crmFileTestTypes.get(usnToTest);
        String expectedUrgent ="Yes";
        String expectedFirmName ="MOCK_FIRM_001";

        // execute
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usnToTest, typeId, Integer.toString(typeId)
        );
        Crm5DetailsModel result = (Crm5DetailsModel) crmFileService
                .getCrmFormData(detailsCriteriaDTO)
                .getFormDetails();

        // checks
        softly.assertThat(result).isInstanceOf(Crm5DetailsModel.class);
        softly.assertThat(result.getFirm_name()).isEqualTo(expectedFirmName);
        softly.assertThat(result.getUrgent()).isEqualTo(expectedUrgent);
    }

    @Test
    void getCrmFileDataTest_ShouldReturnException() {
        long usnToTest = 11L;
        int typeId = crmFileTestTypes.get(usnToTest);
        String expectedMessage = "JSONObject";

        // execute
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usnToTest, typeId, Integer.toString(typeId)
        );

        softly.assertThatThrownBy(() -> crmFileService.getCrmFormData(detailsCriteriaDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void getConvertCrmFormLinkedAttachmentsTest_ShouldJSONArrayObject() {

        // Test with accepted types
        linkedAttachmentTests.forEach((usnToTest, expectedAttachments) -> {
            int typeId = crmFileTestTypes.get(usnToTest);

            // Execute
            JSONObject result = crmFileService.convertCrmFormLinkedAttachments(
                crmFileService.getCrmFileJson(
                    new CrmFormDetailsCriteriaDTO(usnToTest, typeId, Integer.toString(typeId))
                )
            );

            // Check
            softly.assertThat(result).isNotNull();
            softly.assertThat(result.has("linkedAttachment")).isTrue();
            softly.assertThat(result.get("linkedAttachment")).isInstanceOf(JSONArray.class);
            softly.assertThat(result.getJSONArray("linkedAttachment").length()).isEqualTo(expectedAttachments);
        });
    }
}