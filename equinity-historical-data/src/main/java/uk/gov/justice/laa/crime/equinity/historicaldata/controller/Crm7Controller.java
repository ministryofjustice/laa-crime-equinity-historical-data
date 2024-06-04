package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm7InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm7Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_7;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm7Controller implements Crm7InterfaceApi {
    private final CrmFileService crmFileService;
    private final Crm7Mapper mapper;

    @Override
    public ResponseEntity<Crm7DetailsDTO> getApplicationCrm7(Long usn) {
        log.info("eForm CRM7 details request received :: usn=[{}]", usn);
        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
            usn, CRM_TYPE_7, null
        );
        Crm7DetailsModel crm7FileDetails = (Crm7DetailsModel) crmFileService.getCrmImageFile(crmFormDetailsCriteriaDTO).getFormDetails();

        return ResponseEntity.ok(mapper.getDTOFromModel(crm7FileDetails));
    }
}
