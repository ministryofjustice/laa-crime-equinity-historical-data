package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormCRM14AttachmentStoreModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormCRM14AttachmentStoreRepository;

import java.util.List;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14AttachmentServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;
    @Autowired
    CrmFormCRM14AttachmentStoreRepository crmFormCRM14AttachmentStoreRepository;
    @Autowired
    Crm14AttachmentService crm14AttachmentService;

    @BeforeAll
    void preTest() {
        CrmFormCRM14AttachmentStoreModel attachment = new CrmFormCRM14AttachmentStoreModel(5001817L,"61c6df22-c18c-4182-9560-897b0e18dfcd","Screenshot 2022-05-23 at 13.26.59.png",3,null,null,"Accepted","BANK_STATEMENTS",
                "3x monthly statements","",341);
        crmFormCRM14AttachmentStoreRepository.save(attachment);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrm14PSE_ShouldReturnValidAttachments() {
        long usnToTest = 5001817L;
        List<CrmFormCRM14AttachmentStoreModel> attachments = crm14AttachmentService.getAttachments(usnToTest);
        softly.assertThat(attachments).isNotEmpty();
    }

    @Test
    void getCrm14PSE_ShouldReturnEmpty() {
        long usnToTest = 54L;
        List<CrmFormCRM14AttachmentStoreModel> attachments= crm14AttachmentService.getAttachments(usnToTest);
        softly.assertThat(attachments).isEmpty();
    }
}