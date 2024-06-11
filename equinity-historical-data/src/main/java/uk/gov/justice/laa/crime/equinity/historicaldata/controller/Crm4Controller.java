package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm4InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm4Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;


@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4Controller implements Crm4InterfaceApi {
    private final CrmFileService crmFileService;
    private final Crm4Mapper crm4Mapper;

    @Override
    public ResponseEntity<Crm4FormDTO> getApplicationCrm4(Long usn, String profileAcceptedTypes) {
        log.info("eForm CRM4 details request received :: usn=[{}]", usn);
        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_4, profileAcceptedTypes
        );
        Crm4Model crm4FormData = crmFileService.getCrmImageFile(crmFormDetailsCriteriaDTO);

        Crm4DetailsModel crm4FileDetails = crm4FormData.getFormDetails();
        crm4FileDetails.setAllQuotes();

        CrmEvidenceFilesModel evidenceFiles = crm4FormData.getEvidenceFiles();

        Crm4FormDTO crmFormDTO = new Crm4FormDTO();
        crmFormDTO.setFormDetails(crm4Mapper.getEntityFromModel(crm4FileDetails));
        crmFormDTO.setEvidenceFiles(null);
        return ResponseEntity.ok(crmFormDTO);
    }

}
