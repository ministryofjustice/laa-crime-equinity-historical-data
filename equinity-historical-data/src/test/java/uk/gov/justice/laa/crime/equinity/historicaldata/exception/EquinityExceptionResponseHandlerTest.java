package uk.gov.justice.laa.crime.equinity.historicaldata.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquinityExceptionResponseHandlerTest {
    private static final String HEADER_EQ_API_CLIENT_ID = "EQ-API-CLIENT-ID";
    private static final String HEADER_EQ_API_SECRET = "EQ-API-SECRET";

    private static final String CLIENT_ID = "local-client-x";
    private static final String SECRET = "local-secret-x";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldHandleResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/crm4/1111")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task with USN 1111 not found"));
    }

    @Test
    void shouldHandleUnauthorizedUserProfileException() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/crm4/1111")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(""));
    }

    @Test
    void shouldHandleConstraintViolationException() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/?type=1&submittedFrom=2024-12-32")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Start Date Constraint Violation Exception :: submitted start date [2001-01-01] cannot be earlier than 7 years ago"));
    }

    @Test
    void shouldHandleDateRangeConstraintViolationException() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/?type=1&submittedFrom=2024-01-31&submittedTo=2024-01-01")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Date Range Constraint Violation Exception :: submitted start date [2024-01-31] must not be after end date [2024-01-01]"));
    }

    @Test
    void shouldNotEnoughSearchParametersException() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isRequestedRangeNotSatisfiable())
                .andExpect(content().string("Not enough search parameters. Search criteria needs at least 1 input"));
    }

    @Test
    void shouldHandleStartDateConstraintViolationException() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/?type=1&submittedFrom=2001-01-01")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Start Date Constraint Violation Exception :: submitted start date [2001-01-01] cannot be earlier than 7 years ago"));
    }


}