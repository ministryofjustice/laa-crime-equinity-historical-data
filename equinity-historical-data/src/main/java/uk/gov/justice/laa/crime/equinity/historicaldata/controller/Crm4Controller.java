package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm4InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm4Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;


@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4Controller implements Crm4InterfaceApi {
    private final CrmFileService crmFileService;
    private final Crm4Mapper crm4Mapper;

    @Override
    public ResponseEntity<Crm4DetailsDTO> getApplicationCrm4(Long usn, String profileAcceptedTypes) {
        log.info("eForm CRM4 details request received :: usn=[{}]", usn);
        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_4, profileAcceptedTypes
        );
        Crm4DetailsModel crm4FileDetails = (Crm4DetailsModel) crmFileService
                .getCrmImageFile(crmFormDetailsCriteriaDTO)
                .getFormDetails();
        crm4FileDetails.setAllQuotes();
        return ResponseEntity.ok(crm4Mapper.getEntityFromModel(crm4FileDetails));
    }
}
