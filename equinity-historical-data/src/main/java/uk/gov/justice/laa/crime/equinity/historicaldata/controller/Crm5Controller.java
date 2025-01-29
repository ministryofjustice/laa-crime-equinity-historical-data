package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm5InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm5FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm5Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFurtherInformationService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_5;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm5Controller implements Crm5InterfaceApi {
    private final CrmFileService crmFileService;
    private final CrmFurtherInformationService crmFurtherInfoService;
    private final Crm5Mapper crm5Mapper;


    @Override
    @Timed("laa_crime_equiniti_historic_data_view_crm5_details")
    public ResponseEntity<Crm5FormDTO> getApplication(Long usn, String profileAcceptedTypes) {
        log.info("eForm CRM5 details request received :: usn=[{}]", usn);

        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_5, profileAcceptedTypes
        );
        Crm5Model crmFormData = crmFileService.getCrmFormData(crmFormDetailsCriteriaDTO);
        crmFurtherInfoService.addFileKeyToFurtherInfo(crmFormData);
        return ResponseEntity.ok(crm5Mapper.getDTOFromModel(crmFormData));
    }
}
