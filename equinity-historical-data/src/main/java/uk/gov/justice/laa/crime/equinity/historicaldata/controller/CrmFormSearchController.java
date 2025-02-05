package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.CrmFormSearchService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.AppUtil;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CrmFormSearchController implements SearchApi {
    private final CrmFormSearchService searchService;
    private final AppUtil appUtil;

    @Override
    public ResponseEntity<SearchResultDTO> doSearchBy(
            String profileAcceptedTypes, String usn, Integer type, String client,
            String clientDoB, String submittedFrom, String submittedTo,
            String providerAccount, Integer page, Integer pageSize,
            String sort, String order) {

        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, type, client, clientDoB, submittedFrom, submittedTo, providerAccount, page, pageSize,
                profileAcceptedTypes, sort, order, appUtil.applySevenYearsLimit()
        );

        log.info("eForm search request received :: {} ", crmFormSearchCriteriaDTO);

        return ResponseEntity.ok(
                searchService.searchAllByCriteria(crmFormSearchCriteriaDTO)
        );
    }
}
