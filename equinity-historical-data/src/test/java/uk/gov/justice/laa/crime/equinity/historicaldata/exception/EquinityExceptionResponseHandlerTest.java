package uk.gov.justice.laa.crime.equinity.historicaldata.exception;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.justice.laa.crime.equinity.historicaldata.controller.Crm5Controller;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquinityExceptionResponseHandlerTest {
    private static final String HEADER_EQ_API_CLIENT_ID = "EQ-API-CLIENT-ID";
    private static final String HEADER_EQ_API_SECRET = "EQ-API-SECRET";

    private static final String CLIENT_ID = "local-client-x";
    private static final String SECRET = "local-secret-x";

    @MockBean
    Crm5Controller crm5Controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldHandleConstraintViolationExceptionAsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/?type=1&submittedFrom=2024-12-32")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("doSearchBy.submittedFrom: must match \"^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$\""));
    }

    @Test
    void shouldHandleDateRangeConstraintViolationExceptionAsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/?type=1&submittedFrom=2024-01-31&submittedTo=2024-01-01")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Date Range Constraint Violation Exception :: submitted start date [2024-01-31] must not be after end date [2024-01-01]"));
    }

    @Test
    void shouldHandleMethodArgumentTypeMismatchExceptionAsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/crm4/www")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Method parameter 'usn': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"www\""));
    }

    @Test
    void shouldHandleMissingServletRequestParameterExceptionAsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/report/crm14/?decisionFrom=2022-08-20&decisionTo=2022-08-30")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Required request parameter 'filterByDecision' for method parameter type Integer is not present"));
    }

    @Test
    void shouldHandleStartDateConstraintViolationExceptionAsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/?type=1&submittedFrom=2001-01-01")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Start Date Constraint Violation Exception :: submitted start date [2001-01-01] cannot be earlier than 7 years ago"));
    }

    @Test
    void shouldHandleUnauthorizedUserProfileExceptionAsUnauthorizedStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/crm4/1234567")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(""));
    }

    @Test
    void shouldHandleNoResourceFoundExceptionAsNotFoundStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/crm4")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No static resource api/internal/v1/equinity/crm4."));
    }

    @Test
    void shouldHandleResourceNotFoundExceptionAsNotFoundStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/crm4/1234567")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task with USN 1234567 not found"));
    }

    @Test
    void shouldHandleNotEnoughSearchParametersExceptionAsRequestedRangeNotSatisfiableStatus() throws Exception {
        mockMvc.perform(get("/api/internal/v1/equinity/search/")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isRequestedRangeNotSatisfiable())
                .andExpect(content().string("Not enough search parameters. Search criteria needs at least 1 input"));
    }

    @Test
    void shouldHandleCrmImageFilmException() throws Exception {
        when(crm5Controller.getApplication(2345678L, null)).thenThrow(new JSONException("Some error"));

        mockMvc.perform(get("/api/internal/v1/equinity/crm5/2345678")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("There was a problem reading CRM image file. class org.json.JSONException :: Some error "));
    }

    @Test
    void shouldHandleGenericException() throws Exception {
        when(crm5Controller.getApplication(3456789L, null)).thenThrow(new RuntimeException("Some error"));

        mockMvc.perform(get("/api/internal/v1/equinity/crm5/3456789")
                        .header(HEADER_EQ_API_CLIENT_ID, CLIENT_ID)
                        .header(HEADER_EQ_API_SECRET, SECRET))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("There was an unexpected problem with the application. class java.lang.RuntimeException :: Some error "));
    }
}
