package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm14InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm14Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14AttachmentModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.Crm14Model;
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
        log.info("eForm CRM14 details request received :: usn=[{}]", usn);

        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_14, profileAcceptedTypes
        );
        Crm14Model crm14FormData = crmFileService.getCrmFormData(crmFormDetailsCriteriaDTO);
        List<Crm14AttachmentModel> attachments = crm14AttachmentService.getCrm14Attachments(usn);
        crm14FormData.getFormDetails().setProcessedAttachments(attachments);
        crm14FormData.addProcessedAttachmentsToEvidence(attachments);
        return ResponseEntity.ok(mapper.getDTOFromModel(crm14FormData));
    }

}
