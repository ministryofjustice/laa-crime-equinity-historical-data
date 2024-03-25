package uk.gov.justice.laa.crime.equinity.historicaldata.archive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.mapper.CrmFormsArchiveMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.service.SearchArchiveService;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchArchiveController {
    private final CrmFormsArchiveMapper searchResultsMapper;
    private final SearchArchiveService archiveService;


    @GetMapping(value= "/api/internal/v1/equinity/archive/search/")
    public ResponseEntity<List<CrmFormsDTO>> doSearchArchiveBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo, String providerAccount) {
        LocalDate dateSubmittedFrom = DateUtil.convertStringToLocalDate(submittedFrom);
        LocalDate dateSubmittedTo = DateUtil.convertStringToLocalDate(submittedTo);
        DateUtil.checkDateRangeIsValid(dateSubmittedFrom, dateSubmittedTo);

        CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO = new CrmFormSearchCriteriaDTO(
                usn, client, clientDoB, dateSubmittedFrom, dateSubmittedTo, providerAccount
        );

        return ResponseEntity.ok(
            searchResultsMapper.getDTOsFromModel(
                archiveService.searchAllByCriteria(crmFormSearchCriteriaDTO)
            )
        );
    }
}
