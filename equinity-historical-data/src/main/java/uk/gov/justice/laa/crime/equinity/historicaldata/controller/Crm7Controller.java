package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm7InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm7Controller implements Crm7InterfaceApi {
    private final CrmFileService crmFileService;

    @Override
    public ResponseEntity<Crm7DetailsDTO> getApplicationCrm7(Long usn) {
        log.info("eForm CRM7 details request received :: usn=[{}]", usn);
        Crm7DetailsModel crm7FileDetails = crmFileService.getCrmFileContent(usn, Crm7Model.class).getFormDetails();
        return ResponseEntity.ok(null);
    }
}
