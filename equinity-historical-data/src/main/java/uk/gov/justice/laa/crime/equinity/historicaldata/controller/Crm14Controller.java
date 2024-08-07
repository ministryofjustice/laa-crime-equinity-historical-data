package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.annotation.Timed;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm14InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm14Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14AttachmentModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.Crm14AttachmentService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import java.util.List;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm14Controller implements Crm14InterfaceApi{
    private final CrmFileService crmFileService;
    private final Crm14AttachmentService crm14AttachmentService;
    private final Crm14Mapper mapper;

    @Override
    @Timed("laa_crime_equiniti_historic_data_view_crm14_details")
    public ResponseEntity<Crm14FormDTO> getApplicationCrm14(Long usn, String profileAcceptedTypes) {
        String logMessage = String.format("eForm CRM14 details request received :: usn=[%s]", usn);
        log.info(logMessage);
        // TODO (EMP-182): This is only to count how many requests are received. Review to replace once other metric systems are introduced
        Sentry.captureMessage(logMessage, SentryLevel.INFO);

        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_14, profileAcceptedTypes
        );
        Crm14Model crm14FormData = crmFileService.getCrmFormData(crmFormDetailsCriteriaDTO);
        List<Crm14AttachmentModel> attachments = crm14AttachmentService.getCrm14Attachments(usn);
        crm14FormData.getFormDetails().setProcessedAttachments(attachments);
        return ResponseEntity.ok(mapper.getDTOFromModel(crm14FormData));
    }
}
