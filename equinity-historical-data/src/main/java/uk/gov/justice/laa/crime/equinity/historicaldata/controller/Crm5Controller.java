package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm5InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm5Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm5Controller implements Crm5InterfaceApi {
    private final CrmFileService crmFileService;
    private final Crm5Mapper crm5Mapper;


    @Override
    public ResponseEntity<CRM5DetailsDTO> getApplication(Long usn, String profileAcceptedTypes) {
        log.info("eForm CRM5 details request received :: usn=[{}]", usn);
        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_5, profileAcceptedTypes
        );
        Crm5DetailsModel crm5FileDetails = (Crm5DetailsModel) crmFileService
                .getCrmFormData(crmFormDetailsCriteriaDTO)
                .getFormDetails();
        return ResponseEntity.ok(crm5Mapper.getEntityFromModel(crm5FileDetails));
    }
}
