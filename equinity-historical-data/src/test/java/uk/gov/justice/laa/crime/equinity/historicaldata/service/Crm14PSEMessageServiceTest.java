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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormCRM14PSEMessageModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormCRM14PSEMessageRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14PSEMessageServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;
    @Autowired
    CrmFormCRM14PSEMessageRepository crm14PSEMessageRepository;
    @Autowired
    Crm14PSEMessageService crm14PSEMessageService;

    @BeforeAll
    void preTest() {
        CrmFormCRM14PSEMessageModel crm14PSEMessage = new CrmFormCRM14PSEMessageModel(4938478L,5001817L, Date.from(Instant.parse("2024-08-19T20:15:00.00Z")),"Thank you for providing this, we require a profit / loss sheet / full financial accounts in which we can see the list of business expenses being subtracted from the turnover to arrive at the NET profit. ");
        crm14PSEMessageRepository.save(crm14PSEMessage);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrm14PSEMessages_ShouldReturnMessages() {
        Long usnToTest = 5001817L;
        List<CrmFormCRM14PSEMessageModel> pseMessages= crm14PSEMessageService.getMessages(usnToTest);
        softly.assertThat(pseMessages).isNotEmpty();
    }

    @Test
    void getCrm14PSEMessages_ShouldReturnEmpty() {
        long usnToTest = 54L;
        List<CrmFormCRM14PSEMessageModel> pseMessages= crm14PSEMessageService.getMessages(usnToTest);
        softly.assertThat(pseMessages).isEmpty();
    }
}