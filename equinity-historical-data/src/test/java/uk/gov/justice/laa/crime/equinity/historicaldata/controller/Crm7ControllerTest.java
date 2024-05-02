package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Crm7ControllerTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    Crm7Controller controller;

    /**
     * USN input checks
     */
    @Test
    void doSearchByTest_WhenUsnInputIsGivenNullThenReturnInvalidDataAccessApiUsageException() {
        String expectedMessage = "not be null";

        // execute
        softly.assertThatThrownBy(() -> controller.getApplicationCrm7(null))
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .hasMessageContaining(expectedMessage);
    }
}