package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm7InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm7Controller implements Crm7InterfaceApi {
    private final CrmFileService crmFileService;

    @Override
    public ResponseEntity<Crm7DetailsDTO> getApplicationCrm7(Long usn) {
        log.info("eForm CRM7 details request received :: usn=[{}]", usn);
        JSONObject crm7File = crmFileService.getCrmFormJson(usn);
//        Crm7DetailsDTO crm7DetailsDTO = crmFileService.getCrmFileData(usn);
        Crm7DetailsDTO crm7DetailsDTO = null;
        return ResponseEntity.ok(crm7DetailsDTO);
    }
}
