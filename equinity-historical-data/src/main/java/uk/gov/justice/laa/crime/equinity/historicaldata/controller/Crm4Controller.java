package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm4InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm4Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4Controller implements Crm4InterfaceApi {
    private final CrmFileService crmFileService;
    private final Crm4Mapper crm4Mapper;

    @Override
    public ResponseEntity<Crm4DetailsDTO> getApplicationCrm4(Long usn) {
        log.info("eForm CRM4 details request received :: usn=[{}]", usn);

        Crm4DetailsModel crm4FileDetails = crmFileService.getCrmFileContent(usn, Crm4Model.class).getFormDetails();
        Crm4DetailsDTO crm4DetailsDTO = crm4Mapper.getEntityFromModel(crm4FileDetails);
        return ResponseEntity.ok(crm4DetailsDTO);
    }
}