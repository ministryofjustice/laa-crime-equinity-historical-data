package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.annotation.Timed;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm4InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm4Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;


@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4Controller implements Crm4InterfaceApi {
    private final CrmFileService crmFileService;
    private final Crm4Mapper crm4Mapper;

    @Override
    @Timed("laa_crime_equiniti_historic_data_view_crm4_details")
    public ResponseEntity<Crm4FormDTO> getApplicationCrm4(Long usn, String profileAcceptedTypes) {
        String logMessage = String.format("eForm CRM4 details request received :: usn=[%s]", usn);
        log.info(logMessage);
        // TODO (EMP-182): This is only to count how many requests are received. Review to replace once other metric systems are introduced
        Sentry.captureMessage(logMessage, SentryLevel.INFO);

        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_4, profileAcceptedTypes
        );
        Crm4Model crmFormData = crmFileService.getCrmFormData(crmFormDetailsCriteriaDTO);
        crmFormData.getFormDetails().setAllQuotes();
        return ResponseEntity.ok(crm4Mapper.getDTOFromModel(crmFormData));
    }
}
