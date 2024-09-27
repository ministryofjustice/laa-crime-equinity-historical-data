package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm4InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.Crm4Mapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFurtherInformationService;

import static uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFileService.CRM_TYPE_4;


@RestController
@RequiredArgsConstructor
@Slf4j
public class Crm4Controller implements Crm4InterfaceApi {
    private final CrmFileService crmFileService;
    private final CrmFurtherInformationService crmFurtherInfoService;
    private final Crm4Mapper crm4Mapper;

    @Override
    @Timed("laa_crime_equiniti_historic_data_view_crm4_details")
    public ResponseEntity<Crm4FormDTO> getApplicationCrm4(Long usn, String profileAcceptedTypes) {
        log.info("eForm CRM4 details request received :: usn=[{}]", usn);

        CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO = new CrmFormDetailsCriteriaDTO(
                usn, CRM_TYPE_4, profileAcceptedTypes
        );
        Crm4Model crmFormData = crmFileService.getCrmFormData(crmFormDetailsCriteriaDTO);
        crmFormData.getFormDetails().setAllQuotes();
        crmFurtherInfoService.addFileKeyToFurtherInfo(CRM_TYPE_4,crmFormData);
        return ResponseEntity.ok(crm4Mapper.getDTOFromModel(crmFormData));
    }
}
