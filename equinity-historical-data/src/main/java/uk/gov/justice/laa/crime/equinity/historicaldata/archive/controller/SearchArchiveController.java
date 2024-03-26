package uk.gov.justice.laa.crime.equinity.historicaldata.archive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.service.SearchArchiveService;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchArchiveApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchDTO;

@RestController
@RequiredArgsConstructor
public class SearchArchiveController implements SearchArchiveApi {
    private final SearchArchiveService searchService;

    @Override
    public ResponseEntity<SearchDTO> doSearchArchiveBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo, String providerAccount, Integer page, Integer perPage) {
        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, client, clientDoB, submittedFrom, submittedTo, providerAccount, null, null
        );

        return ResponseEntity.ok(
                searchService.searchAllByCriteria(crmFormSearchCriteriaDTO)
        );
    }
}
