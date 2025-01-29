package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
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
import java.time.ZoneId;
import java.util.Date;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.convertDateToLocalDate;

@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFormUtilTest {

    private static final Date DATE_RECEIVED_OVER_7_YRS_AGO = Date.from(DateUtil.getMinStartDate()
            .minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

    @InjectSoftAssertions
    private SoftAssertions softly;

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void checkCrmFormDateReceived_AcceptsCrm4FormDateReceived() {
        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setUsn(100L);
        crm4DetailsModel.setDate_received(new Date());
        Crm4Model crm4FormData = new Crm4Model(crm4DetailsModel, false, null, null);

        CrmFormUtil.checkCrmFormDateReceived(crm4FormData);
    }

    @Test
    void checkCrmFormDateReceived_WhenCrm4FormDateReceivedIsOver7YrsAgoThenThrowException() {
        Crm4DetailsModel crm4DetailsModel = new Crm4DetailsModel();
        crm4DetailsModel.setUsn(101L);
        crm4DetailsModel.setDate_received(DATE_RECEIVED_OVER_7_YRS_AGO);
        Crm4Model crm4FormData = new Crm4Model(crm4DetailsModel, false, null, null);

        softly.assertThatThrownBy(() -> CrmFormUtil.checkCrmFormDateReceived(crm4FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 101 not found");
    }

    @Test
    void checkCrmFormDateReceived_AcceptsCrm5FormDateReceived() {
        Crm5DetailsModel crm5DetailsModel = new Crm5DetailsModel();
        crm5DetailsModel.setUsn(200);
        crm5DetailsModel.setDate_received(new Date());
        Crm5Model crm5FormData = new Crm5Model();
        crm5FormData.setFormDetails(crm5DetailsModel);

        CrmFormUtil.checkCrmFormDateReceived(crm5FormData);
    }

    @Test
    void checkCrmFormDateReceived_WhenCrm5FormDateReceivedIsOver7YrsAgoThenThrowException() throws Exception {
        Crm5DetailsModel crm5DetailsModel = new Crm5DetailsModel();
        crm5DetailsModel.setUsn(201);
        crm5DetailsModel.setDate_received(DATE_RECEIVED_OVER_7_YRS_AGO);
        Crm5Model crm5FormData = new Crm5Model();
        crm5FormData.setFormDetails(crm5DetailsModel);

        softly.assertThatThrownBy(() -> CrmFormUtil.checkCrmFormDateReceived(crm5FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 201 not found");
    }

    @Test
    void checkCrmFormDateReceived_AcceptsCrm7FormDateReceived() {
        Crm7DetailsModel crm7DetailsModel = new Crm7DetailsModel();
        crm7DetailsModel.setUsn(300L);
        crm7DetailsModel.setDate_received(LocalDate.now());
        Crm7Model crm7FormData = new Crm7Model();
        crm7FormData.setFormDetails(crm7DetailsModel);

        CrmFormUtil.checkCrmFormDateReceived(crm7FormData);
    }

    @Test
    void checkCrmFormDateReceived_WhenCrm7FormDateReceivedIsOver7YrsAgoThenThrowException() {
        Crm7DetailsModel crm7DetailsModel = new Crm7DetailsModel();
        crm7DetailsModel.setUsn(301L);
        crm7DetailsModel.setDate_received(convertDateToLocalDate(DATE_RECEIVED_OVER_7_YRS_AGO));
        Crm7Model crm7FormData = new Crm7Model();
        crm7FormData.setFormDetails(crm7DetailsModel);

        softly.assertThatThrownBy(() -> CrmFormUtil.checkCrmFormDateReceived(crm7FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 301 not found");
    }

    @Test
    void checkCrmFormDateReceived_AcceptsCrm14FormDateReceived() {
        Crm14DetailsModel crm14DetailsModel = new Crm14DetailsModel();
        crm14DetailsModel.setUsn(400);
        crm14DetailsModel.setDate_received(new Date());
        Crm14Model crm14FormData = new Crm14Model();
        crm14FormData.setFormDetails(crm14DetailsModel);

        CrmFormUtil.checkCrmFormDateReceived(crm14FormData);
    }

    @Test
    void checkCrmFormDateReceived_WhenCrm14FormDateReceivedIsOver7YrsAgoThenThrowException() {
        Crm14DetailsModel crm14DetailsModel = new Crm14DetailsModel();
        crm14DetailsModel.setUsn(401);
        crm14DetailsModel.setDate_received(DATE_RECEIVED_OVER_7_YRS_AGO);
        Crm14Model crm14FormData = new Crm14Model();
        crm14FormData.setFormDetails(crm14DetailsModel);

        softly.assertThatThrownBy(() -> CrmFormUtil.checkCrmFormDateReceived(crm14FormData))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("USN 401 not found");
    }
}
