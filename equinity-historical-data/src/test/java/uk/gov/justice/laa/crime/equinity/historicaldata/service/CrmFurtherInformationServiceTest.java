package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFileModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFurtherInfoAttachmentsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFurtherInformationModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFurtherInformationServiceTest {

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFurtherInformationService crmFurtherInformationService;

    @Test
    void shouldAddFileKeyToFurtherInfoAndUpdateEvidenceFileName() {

        Crm4Model crm4Model = new Crm4Model();
        List<CrmFurtherInfoAttachmentsModel> crmFurtherInfoAttachments = buildCrmFurtherInfoAttachments("Attachment1", "xxx");
        Crm4DetailsModel crm4DetailsModel = buildCrm4Details(crmFurtherInfoAttachments);
        crm4Model.setFormDetails(crm4DetailsModel);
        crm4Model.setEvidenceFiles(buildCrmEvidenceFiles());

        crmFurtherInformationService.addFileKeyToFurtherInfo(crm4Model);

        List<CrmEvidenceFileModel> evidenceFiles = crm4Model.getEvidenceFiles().getFiles();
        softly.assertThat(evidenceFiles.get(0).getName()).isEqualTo("document.pdf");
        softly.assertThat(evidenceFiles.get(1).getName()).isEqualTo("letter.docx");
    }

    @Test
    void shouldNotAddFileKeyToFurtherInfo_whenFurtherInfoAttachmentsIsNull() {

        Crm4Model crm4Model = new Crm4Model();
        crm4Model.setFormDetails(buildCrm4Details(null));
        crm4Model.setEvidenceFiles(buildCrmEvidenceFiles());

        crmFurtherInformationService.addFileKeyToFurtherInfo(crm4Model);

        List<CrmEvidenceFileModel> evidenceFiles = crm4Model.getEvidenceFiles().getFiles();
        softly.assertThat(evidenceFiles.get(0).getName()).isEqualTo("Attachment1");
        softly.assertThat(evidenceFiles.get(1).getName()).isEqualTo("letter.docx");
    }

    @Test
    void shouldNotAddFileKeyToFurtherInfo_whenFurtherInfoAttachmentsNameAndRetrieveIsEmpty() {

        Crm4Model crm4Model = new Crm4Model();
        List<CrmFurtherInfoAttachmentsModel> crmFurtherInfoAttachments = buildCrmFurtherInfoAttachments("", "");
        crm4Model.setFormDetails(buildCrm4Details(crmFurtherInfoAttachments));
        crm4Model.setEvidenceFiles(buildCrmEvidenceFiles());

        crmFurtherInformationService.addFileKeyToFurtherInfo(crm4Model);

        List<CrmEvidenceFileModel> evidenceFiles = crm4Model.getEvidenceFiles().getFiles();
        softly.assertThat(evidenceFiles.get(0).getName()).isEqualTo("Attachment1");
        softly.assertThat(evidenceFiles.get(1).getName()).isEqualTo("letter.docx");
    }

    private static Crm4DetailsModel buildCrm4Details(List<CrmFurtherInfoAttachmentsModel> furtherInfoAttachmentsModels) {
        CrmFurtherInformationModel crmFurtherInformationModel = new CrmFurtherInformationModel();
        crmFurtherInformationModel.setAttachments(furtherInfoAttachmentsModels);

        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setFurtherInformationModel(crmFurtherInformationModel);
        return crm4DetailsModel;
    }

    private static List<CrmFurtherInfoAttachmentsModel> buildCrmFurtherInfoAttachments(String name, String retrieve) {
        List<CrmFurtherInfoAttachmentsModel> furtherInfoAttachmentsModels = new ArrayList<>();
        CrmFurtherInfoAttachmentsModel crmFurtherInfoAttachmentsModel = new CrmFurtherInfoAttachmentsModel();
        crmFurtherInfoAttachmentsModel.setRetrieve(retrieve);
        crmFurtherInfoAttachmentsModel.setName(name);
        crmFurtherInfoAttachmentsModel.setOriginalfilename("document.pdf");
        furtherInfoAttachmentsModels.add(crmFurtherInfoAttachmentsModel);
        return furtherInfoAttachmentsModels;
    }

    private static CrmEvidenceFilesModel buildCrmEvidenceFiles() {
        CrmEvidenceFilesModel crmEvidenceFilesModel = new CrmEvidenceFilesModel();
        List<CrmEvidenceFileModel> files = new ArrayList<>();
        files.add(buildCrmEvidenceFile("1234.att", "Attachment1"));
        files.add(buildCrmEvidenceFile("2345.att", "letter.docx"));
        crmEvidenceFilesModel.setFiles(files);
        return crmEvidenceFilesModel;
    }

    private static CrmEvidenceFileModel buildCrmEvidenceFile(String key, String name) {
        CrmEvidenceFileModel crmEvidenceFileModel = new CrmEvidenceFileModel();
        crmEvidenceFileModel.setKey(key);
        crmEvidenceFileModel.setType("OFDAttachRef");
        crmEvidenceFileModel.setName(name);
        return crmEvidenceFileModel;
    }


}