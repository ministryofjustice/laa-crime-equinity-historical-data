package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CsvWriterServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CsvWriterService service;

    @Autowired
    HttpServletResponse response;

    @Test
    void getResponseHeaderFilename() {
        String responseHeader = service.getResponseHeaderFilename("mockFile.csv");

        softly.assertThat(responseHeader).contains("attachment");
        softly.assertThat(responseHeader).contains("filename=\"mockFile.csv\"");
    }

    @Test
    void setupResponseHeaders() {
        service.setupResponseHeaders("mockFile.csv");

        softly.assertThat(response.getContentType()).isEqualTo("text/csv");
    }
}