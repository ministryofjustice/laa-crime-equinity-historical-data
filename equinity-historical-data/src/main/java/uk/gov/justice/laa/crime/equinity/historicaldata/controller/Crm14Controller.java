package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.annotation.Timed;
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
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFileModel;
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
        addProcessedAttachmentsToEvidence(crm14FormData,attachments);
        return ResponseEntity.ok(mapper.getDTOFromModel(crm14FormData));
    }

    public void addProcessedAttachmentsToEvidence(Crm14Model crm14FormData, List<Crm14AttachmentModel> processedAttachments) {
        /*if (CollectionUtils.isEmpty(processedAttachments)){
            return;
        }
        /*
        List<CrmEvidenceFileModel> files = crm14FormData.getEvidenceFiles().getFiles();
        if (CollectionUtils.isEmpty(files)){
            files = new ArrayList<>();
        }
        */
        for(Crm14AttachmentModel processedAttachment: processedAttachments){
            CrmEvidenceFileModel evidenceFileModel = new CrmEvidenceFileModel();
            evidenceFileModel.setKey("att_"+processedAttachment.getAttachmentId()+".att");
            evidenceFileModel.setName(processedAttachment.getFileName());
            evidenceFileModel.setType(processedAttachment.getEvidenceType());
            crm14FormData.getEvidenceFiles().getFiles().add(evidenceFileModel);
        }
    }
}
