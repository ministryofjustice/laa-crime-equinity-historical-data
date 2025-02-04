package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormDetailsRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.AppUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Map.entry;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM14_CHARGES_BROUGHT;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_FORM_FIELD_DATA;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_LINKED_EVIDENCE;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_LINKED_EVIDENCE_FILES;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_ROW;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_7;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFileServiceTest {
    private static final String TEST_FILES_PATH = "src/test/resources/%s";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @MockBean
    AppUtil appUtil;

    @Autowired
    CrmFormDetailsRepository crmFormDetailsRepository;

    @Autowired
    CrmFileService crmFileService;

    Map<Long, Map.Entry<Integer, String>> crmFileTests;

    @BeforeAll
    void preTest() {
        crmFileTests = Map.of(
                5001912L, entry(CRM_TYPE_4, format(TEST_FILES_PATH, "Crm4MockFile_5001912.txt")),
                492914L, entry(CRM_TYPE_4, format(TEST_FILES_PATH, "Crm4MockFile_492914.txt")),
                5001613L, entry(CRM_TYPE_4, format(TEST_FILES_PATH, "Crm4MockFile_5001613.txt")),
                5001604L, entry(CRM_TYPE_5, format(TEST_FILES_PATH, "Crm5MockFile_5001604.txt")),
                11L, entry(CRM_TYPE_5, format(TEST_FILES_PATH, "Crm5MockOFDFile_WrongFormat.txt")),
                4808706L, entry(CRM_TYPE_7, format(TEST_FILES_PATH, "Crm7MockFile_4808706.txt")),
                5001662L, entry(CRM_TYPE_7, format(TEST_FILES_PATH, "Crm7MockFile_5001662.txt")),
                5001597L, entry(CRM_TYPE_7, format(TEST_FILES_PATH, "Crm7MockFile_5001597.txt")),
                5001669L, entry(CRM_TYPE_14, format(TEST_FILES_PATH, "Crm14MockFile_5001669.txt"))
        );

        crmFileTests.forEach((testUsn, entry) -> {
            try {
                createMock(testUsn, entry.getKey(), loadMockFile(entry.getValue()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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
        CrmFormDetailsModel crmFormDetail = new CrmFormDetailsModel();
        crmFormDetail.setUSN(mockID);
        crmFormDetail.setTypeId(typeId);
        crmFormDetail.setCrmFile(mockFile);
        crmFormDetailsRepository.save(crmFormDetail);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @BeforeEach
    void setUp() {
        when(appUtil.applySevenYearsLimit()).thenReturn(false);
    }

    @Test
    void getCrmFileDataTest_ShouldReturnValidOFDFile() {
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = buildDetailsCriteriaDTO(5001604L);

        // execute
        CrmFormModelInterface result = crmFileService.getCrmFormData(detailsCriteriaDTO);

        // checks
        softly.assertThat(result.getFormDetails()).isInstanceOf(Crm5DetailsModel.class);
        Crm5DetailsModel formDetails = (Crm5DetailsModel) result.getFormDetails();
        softly.assertThat(formDetails.getFirm_name()).isEqualTo("MOCK_FIRM_001");
        softly.assertThat(formDetails.getUrgent()).isEqualTo("Yes");
    }

    @Test
    void getCrmFileDataTest_ShouldReturnException() {
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = buildDetailsCriteriaDTO(11L);

        // execute
        softly.assertThatThrownBy(() -> crmFileService.getCrmFormData(detailsCriteriaDTO))
                .isInstanceOf(JSONException.class)
                .hasMessage("JSONObject[\"fd:formdata\"] not found.");
    }

    @ParameterizedTest
    @MethodSource("inputForLinkedAttachmentsTest")
    void getConvertCrmFormLinkedAttachmentsTest_ShouldJSONArrayObject(Long usn, int expectedAttachments) {
        // Test with accepted types
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = buildDetailsCriteriaDTO(usn);
        JSONObject result = crmFileService.getCrmFileJson(detailsCriteriaDTO);

        // Execute
        crmFileService.convertCrmFormObjectToArray(result, CRM_LINKED_EVIDENCE, CRM_LINKED_EVIDENCE_FILES);

        // Check
        softly.assertThat(result).isNotNull();
        softly.assertThat(result.has(CRM_LINKED_EVIDENCE)).isTrue();
        softly.assertThat(result.get(CRM_LINKED_EVIDENCE)).isInstanceOf(JSONObject.class);
        softly.assertThat(result.getJSONObject(CRM_LINKED_EVIDENCE).has(CRM_LINKED_EVIDENCE_FILES)).isTrue();
        softly.assertThat(result.getJSONObject(CRM_LINKED_EVIDENCE).getJSONArray(CRM_LINKED_EVIDENCE_FILES).length()).isEqualTo(expectedAttachments);

    }

    private static Stream<Arguments> inputForLinkedAttachmentsTest() {
        return Stream.of(
                Arguments.of(5001604L, 0),
                Arguments.of(5001662L, 0),
                Arguments.of(5001912L, 3),
                Arguments.of(4808706L, 2),
                Arguments.of(5001597L, 1)
        );
    }

    @Test
    void getConvertCrmFormChargesBroughtTest_ShouldJSONArrayObject() {
        // Test with accepted types
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = buildDetailsCriteriaDTO(5001669L);
        JSONObject crmToTest = crmFileService.getCrmFileJson(detailsCriteriaDTO);
        JSONObject result = crmToTest.getJSONObject(CRM_FORM_FIELD_DATA);

        // Execute
        crmFileService.convertCrmFormObjectToArray(result, CRM14_CHARGES_BROUGHT, CRM_ROW);

        // Check
        softly.assertThat(result).isNotNull();
        softly.assertThat(result.has(CRM14_CHARGES_BROUGHT)).isTrue();
        softly.assertThat(result.get(CRM14_CHARGES_BROUGHT)).isInstanceOf(JSONObject.class);
        softly.assertThat(result.getJSONObject(CRM14_CHARGES_BROUGHT).has(CRM_ROW)).isTrue();
        softly.assertThat(result.getJSONObject(CRM14_CHARGES_BROUGHT).getJSONArray(CRM_ROW).length()).isEqualTo(3);

    }

    @Test
    void getCrmFileDataTest_ShouldThrowResourceNotFoundException() {
        when(appUtil.applySevenYearsLimit()).thenReturn(true); // switch on 7rs limit

        // criteria for CRM form with date submitted over 7 ys ago
        CrmFormDetailsCriteriaDTO detailsCriteriaDTO = buildDetailsCriteriaDTO(5001613L);

        softly.assertThatThrownBy(() -> crmFileService.getCrmFormData(detailsCriteriaDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 5001613 not found");
    }

    private CrmFormDetailsCriteriaDTO buildDetailsCriteriaDTO(Long usn) {
        return new CrmFormDetailsCriteriaDTO(
                usn, crmFileTests.get(usn).getKey(), Integer.toString(crmFileTests.get(usn).getKey())
        );
    }
}
