package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm5InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm5Controller implements Crm5InterfaceApi {
    private final CrmFileService crmFileService;

    @Override
    public ResponseEntity<CRM5DetailsDTO> getApplication(Long usn) {
        log.info("eForm CRM5 details request received :: usn=[{}]", usn);
        CRM5DetailsDTO crm5DetailsDTO = crmFileService.getCrmFileData(usn);
        return ResponseEntity.ok(crm5DetailsDTO);
    }

}
