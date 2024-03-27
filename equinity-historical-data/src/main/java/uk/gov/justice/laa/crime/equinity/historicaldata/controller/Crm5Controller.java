package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm5InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;

@RestController
@RequiredArgsConstructor
public class Crm5Controller implements Crm5InterfaceApi {
    private final CrmFileService crmFileService;

    @Override
    public ResponseEntity<CRM5DetailsDTO> getApplication(Long usn) {
        CRM5DetailsDTO crm5DetailsDTO = crmFileService.getCrmFileData(usn);
        return ResponseEntity.ok(crm5DetailsDTO);
    }

}
