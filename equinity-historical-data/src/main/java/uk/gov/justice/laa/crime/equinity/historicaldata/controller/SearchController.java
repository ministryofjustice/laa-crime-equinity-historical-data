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
    public ResponseEntity<SearchResultDTO> doSearchBy(
            String profileAcceptedTypes, String usn, Integer type, String client,
            String clientDoB, String submittedFrom, String submittedTo,
            String providerAccount, Integer page, Integer pageSize) {
        log.info("eForm search request received :: usn=[{}] type=[{}] client=[{}] submittedFrom=[{}] submittedTo=[{}] provider=[{}] page=[{}] pageSize=[{}] ",
                usn, type, client, submittedFrom, submittedTo, providerAccount, page, pageSize);
        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, type, client, clientDoB, submittedFrom, submittedTo, providerAccount, page, pageSize,
                profileAcceptedTypes
        );

        return ResponseEntity.ok(
                searchService.searchAllByCriteria(crmFormSearchCriteriaDTO)
        );
    }
}
