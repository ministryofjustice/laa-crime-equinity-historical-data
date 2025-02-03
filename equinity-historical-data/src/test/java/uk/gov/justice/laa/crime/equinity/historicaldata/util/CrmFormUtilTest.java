package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.Crm14DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.Crm14Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7.Crm7DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7.Crm7Model;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.getDateSevenYearsAgo;

@ExtendWith({SoftAssertionsExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFormUtilTest {

    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final LocalDate SUBMITTED_DATE_OVER_7_YRS_AGO = getDateSevenYearsAgo().minusDays(1);

    @Mock
    private AppUtil mockAppUtil;

    private CrmFormUtil crmFormUtil;

    @InjectSoftAssertions
    private SoftAssertions softly;

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @BeforeEach
    void setUp() {
        crmFormUtil = new CrmFormUtil(mockAppUtil);
    }

    @Test
    void checkSubmittedDate_AcceptsNullTlTaskLastUpdated() {
        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setUsn(100L);
        Crm4Model crm4FormData = new Crm4Model(crm4DetailsModel, false, null, null);

        crmFormUtil.checkSubmittedDate(crm4FormData);
    }

    @Test
    void checkSubmittedDate_WhenArchiveEnvironmentAcceptsTaskLastUpdatedOver7YrsAgo() {
        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setUsn(100L);
        crm4DetailsModel.setTlTaskLastUpdated(SUBMITTED_DATE_OVER_7_YRS_AGO);

        Crm4Model crm4FormData = new Crm4Model(crm4DetailsModel, false, null, null);

        when(mockAppUtil.isArchiveEnvironment()).thenReturn(true);

        crmFormUtil.checkSubmittedDate(crm4FormData);

        verify(mockAppUtil).isArchiveEnvironment();
    }

    @Test
    void checkSubmittedDate_AcceptsCrm4FormTlTaskLastUpdated() {
        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setUsn(100L);
        crm4DetailsModel.setTlTaskLastUpdated(CURRENT_DATE);
        Crm4Model crm4FormData = new Crm4Model(crm4DetailsModel, false, null, null);

        crmFormUtil.checkSubmittedDate(crm4FormData);
    }

    @Test
    void checkSubmittedDate_WhenCrm4FormTlTaskLastUpdatedIsOver7YrsAgoThenThrowException() {
        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setUsn(101L);
        crm4DetailsModel.setTlTaskLastUpdated(SUBMITTED_DATE_OVER_7_YRS_AGO);
        Crm4Model crm4FormData = new Crm4Model(crm4DetailsModel, false, null, null);

        softly.assertThatThrownBy(() -> crmFormUtil.checkSubmittedDate(crm4FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 101 not found");
    }

    @Test
    void checkSubmittedDate_AcceptsCrm5FormTlTaskLastUpdated() {
        Crm5DetailsModel crm5DetailsModel = new Crm5DetailsModel();
        crm5DetailsModel.setUsn(200L);
        crm5DetailsModel.setTlTaskLastUpdated(CURRENT_DATE);
        Crm5Model crm5FormData = new Crm5Model();
        crm5FormData.setFormDetails(crm5DetailsModel);

        crmFormUtil.checkSubmittedDate(crm5FormData);
    }

    @Test
    void checkSubmittedDate_WhenCrm5FormTlTaskLastUpdatedIsOver7YrsAgoThenThrowException() throws Exception {
        Crm5DetailsModel crm5DetailsModel = new Crm5DetailsModel();
        crm5DetailsModel.setUsn(201L);
        crm5DetailsModel.setTlTaskLastUpdated(SUBMITTED_DATE_OVER_7_YRS_AGO);
        Crm5Model crm5FormData = new Crm5Model();
        crm5FormData.setFormDetails(crm5DetailsModel);

        softly.assertThatThrownBy(() -> crmFormUtil.checkSubmittedDate(crm5FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 201 not found");
    }

    @Test
    void checkSubmittedDate_AcceptsCrm7FormTlTaskLastUpdated() {
        Crm7DetailsModel crm7DetailsModel = new Crm7DetailsModel();
        crm7DetailsModel.setUsn(300L);
        crm7DetailsModel.setTlTaskLastUpdated(CURRENT_DATE);
        Crm7Model crm7FormData = new Crm7Model();
        crm7FormData.setFormDetails(crm7DetailsModel);

        crmFormUtil.checkSubmittedDate(crm7FormData);
    }

    @Test
    void checkSubmittedDate_WhenCrm7FormTlTaskLastUpdatedIsOver7YrsAgoThenThrowException() {
        Crm7DetailsModel crm7DetailsModel = new Crm7DetailsModel();
        crm7DetailsModel.setUsn(301L);
        crm7DetailsModel.setTlTaskLastUpdated(SUBMITTED_DATE_OVER_7_YRS_AGO);
        Crm7Model crm7FormData = new Crm7Model();
        crm7FormData.setFormDetails(crm7DetailsModel);

        softly.assertThatThrownBy(() -> crmFormUtil.checkSubmittedDate(crm7FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 301 not found");
    }

    @Test
    void checkSubmittedDate_AcceptsCrm14FormTlTaskLastUpdated() {
        Crm14DetailsModel crm14DetailsModel = new Crm14DetailsModel();
        crm14DetailsModel.setUsn(400L);
        crm14DetailsModel.setTlTaskLastUpdated(CURRENT_DATE);
        Crm14Model crm14FormData = new Crm14Model();
        crm14FormData.setFormDetails(crm14DetailsModel);

        crmFormUtil.checkSubmittedDate(crm14FormData);
    }

    @Test
    void checkSubmittedDate_WhenCrm14FormTlTaskLastUpdatedIsOver7YrsAgoThenThrowException() {
        Crm14DetailsModel crm14DetailsModel = new Crm14DetailsModel();
        crm14DetailsModel.setUsn(401L);
        crm14DetailsModel.setTlTaskLastUpdated(SUBMITTED_DATE_OVER_7_YRS_AGO);
        Crm14Model crm14FormData = new Crm14Model();
        crm14FormData.setFormDetails(crm14DetailsModel);

        softly.assertThatThrownBy(() -> crmFormUtil.checkSubmittedDate(crm14FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 401 not found");
    }
}
