package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
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

        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, type, client, clientDoB, submittedFrom, submittedTo, providerAccount, page, pageSize,
                profileAcceptedTypes
        );

        String logMessage = String.format("eForm search request received :: %s ", crmFormSearchCriteriaDTO);
        log.info(logMessage);
        // TODO (EMP-182): This is only to count how many requests are received. Review to replace once other metric systems are introduced
        Sentry.captureMessage(logMessage, SentryLevel.INFO);

        return ResponseEntity.ok(
                searchService.searchAllByCriteria(crmFormSearchCriteriaDTO)
        );
    }
}
