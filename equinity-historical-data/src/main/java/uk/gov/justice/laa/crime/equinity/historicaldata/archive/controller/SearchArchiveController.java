package uk.gov.justice.laa.crime.equinity.historicaldata.archive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.mapper.CrmFormsArchiveMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.service.SearchArchiveService;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchArchiveApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchArchiveController implements SearchArchiveApi {
    private final CrmFormsArchiveMapper searchResultsMapper;
    private final SearchArchiveService archiveService;


    @Override
    public ResponseEntity<List<CrmFormsDTO>> doSearchArchiveBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo, String providerAccount) {
        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, client, clientDoB, submittedFrom, submittedTo, providerAccount
        );
        crmFormSearchCriteriaDTO.validate();

        return ResponseEntity.ok(
            searchResultsMapper.getDTOsFromModel(
                archiveService.searchAllByCriteria(crmFormSearchCriteriaDTO)
            )
        );
    }
}
