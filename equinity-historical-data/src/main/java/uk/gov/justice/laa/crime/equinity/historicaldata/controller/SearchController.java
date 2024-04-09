package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.SearchService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchController implements SearchApi {
    private final SearchService searchService;

    @Override
    public ResponseEntity<SearchResultDTO> doSearchBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo, String providerAccount, Integer page, Integer pageSize) {
        log.info("eForm search request received :: usn=[{}] client=[{}] submittedFrom=[{}] submittedTo=[{}] provider=[{}] page=[{}] pageSize=[{}] ",
                usn, client, submittedFrom, submittedTo, providerAccount, page, pageSize);
        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, client, clientDoB, submittedFrom, submittedTo, providerAccount, page, pageSize
        );

        return ResponseEntity.ok(
                searchService.searchAllByCriteria(crmFormSearchCriteriaDTO)
        );
    }
}
