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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14PSEMessageModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.Crm14PSEMessageRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm14PSEMessagesServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;
    @Autowired
    Crm14PSEMessageRepository pseMessageRepository;
    @Autowired
    Crm14PSEMessagesService crm14PSEMessagesService;

    @BeforeAll
    void preTest() throws IOException {
        Crm14PSEMessageModel crm14PSEMessageModel = new Crm14PSEMessageModel(4938478L,5001817L,new Date(2024,8, 19, 20, 15),"Thank you for providing this, we require a profit / loss sheet / full financial accounts in which we can see the list of business expenses being subtracted from the turnover to arrive at the NET profit. ");
        pseMessageRepository.save(crm14PSEMessageModel);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void getCrm14PSEMessages_ShouldReturnMessages() {
        Long usnToTest = 5001817L;
        List<Crm14PSEMessageModel> pseMessages= crm14PSEMessagesService.getMessages(usnToTest);
        softly.assertThat(pseMessages).isNotEmpty();
    }

    @Test
    void getCrm14PSEMessages_ShouldReturnEmpty() {
        long usnToTest = 54L;
        List<Crm14PSEMessageModel> pseMessages= crm14PSEMessagesService.getMessages(usnToTest);
        softly.assertThat(pseMessages).isEmpty();
    }
}