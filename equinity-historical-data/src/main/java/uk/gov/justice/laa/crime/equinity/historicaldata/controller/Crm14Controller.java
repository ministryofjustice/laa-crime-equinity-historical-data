package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm14InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm14Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_14;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm14Controller implements Crm14InterfaceApi{
    private final CrmFileService crmFileService;
    private final Crm14Mapper mapper;

    @Override
    public ResponseEntity<Crm14DetailsDTO> getApplicationCrm14(Long usn, String profileAcceptedTypes) {
        log.info("eForm CRM14 details request received :: usn=[{}]", usn);
        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_14, profileAcceptedTypes
        );
        Crm14DetailsModel crm14FileDetails = (Crm14DetailsModel) crmFileService.getCrmImageFile(crmFormDetailsCriteriaDTO).getFormDetails();

        return ResponseEntity.ok(mapper.getDTOFromModel(crm14FileDetails));
    }
}
