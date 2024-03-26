package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.CrmFormsViewMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.SearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController implements SearchApi {
    private final SearchService searchService;
    private final CrmFormsViewMapper searchResultsMapper;

    @Override
    public ResponseEntity<List<CrmFormsDTO>> doSearchBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo, String providerAccount) {
        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
          usn, client, clientDoB, submittedFrom, submittedTo, providerAccount
        );
        crmFormSearchCriteriaDTO.validate();

        return ResponseEntity.ok(
            searchResultsMapper.getDTOsFromModel(
                searchService.searchAllByCriteria(crmFormSearchCriteriaDTO)
            )
        );
    }
}
