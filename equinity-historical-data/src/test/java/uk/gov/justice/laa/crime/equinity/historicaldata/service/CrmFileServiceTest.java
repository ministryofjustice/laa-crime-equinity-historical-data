package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TaskRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFileServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CrmFileService crmFileService;


    @BeforeAll
    void preTest() throws FileNotFoundException {
        Task taskModel = new Task();
        taskModel.setID(10L);
        FileInputStream fis = new FileInputStream("src/test/resources/Crm5MockOFDFile.txt");
        JSONObject mockedCrm5Json = new JSONObject(IOUtils.toString(fis, StandardCharsets.UTF_8));
        byte[] fileDataByte = XML.toString(mockedCrm5Json).getBytes(StandardCharsets.UTF_8);

        taskModel.setCrmFile(fileDataByte);
        taskRepository.save(taskModel);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrmFileDataTest() {
        long ustToTest = 10L;
        String expectedPath = "\\\\laa-uat\\OFServerForms\\CDS5_1.ofmx";
        String expectedFcCurrentUser = "MOCK_USER_001";
        String expectedUrgent ="Yes";
        String expectedFirmName ="MOCK_FIRM_001";

        CRM5DetailsDTO   result = crmFileService.getCrmFileData(ustToTest);
        softly.assertThat(result).isInstanceOf(CRM5DetailsDTO.class);
        softly.assertThat(result.getFirm().getFirmName()).isEqualTo(expectedFirmName);
        softly.assertThat(result.getUrgent()).isEqualTo(expectedUrgent);
    }
}