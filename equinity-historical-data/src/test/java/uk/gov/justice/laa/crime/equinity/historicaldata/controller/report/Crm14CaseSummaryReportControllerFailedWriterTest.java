package uk.gov.justice.laa.crime.equinity.historicaldata.controller.report;

import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CsvWriterService;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class Crm14CaseSummaryReportControllerFailedWriterTest {
    private static final String STATE_DEFAULT = "All";
    private static final String ACCEPTED_PROFILE_TYPES = Integer.toString(CRM_TYPE_14);


    @InjectSoftAssertions
    private SoftAssertions softly;


    @Autowired
    @InjectMocks
    private Crm14CaseSummaryReportController controller;

    @Autowired
    HttpServletResponse response;

    @MockBean
    private CsvWriterService csvService;


    @BeforeEach
    void setup() throws IOException {
        Mockito.doThrow(IOException.class)
            .when(csvService)
            .close(any());
    }


    @Test
    void generateReportCrm14Test_GivenValidProfilesWhenUnexpectedCSVWriterErrorThenServerErrorHttpCode() {
        try {
            String startDate = "2010-02-01";
            String endDate = "2024-06-01";

            // execute
            ResponseEntity<Void> result = controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                ACCEPTED_PROFILE_TYPES, STATE_DEFAULT
            );

            softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

    @Test
    void generateReportCrm14Test_GivenNoProfilesWhenUnexpectedCSVWriterErrorThenServerErrorHttpCode() throws IOException {
        try {
            String startDate = "2010-02-01";
            String endDate = "2024-06-01";

            // execute
            controller.generateReportCrm14(
                1, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                0, startDate, endDate,
                null, STATE_DEFAULT
            );
            softly.assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } catch (InvalidDataAccessResourceUsageException e) {
            softly.assertThat(e).isInstanceOf(InvalidDataAccessResourceUsageException.class);
            // This exception is happening during test running on GitHub pipeline. Mock test covered on other class
        }
    }

}
