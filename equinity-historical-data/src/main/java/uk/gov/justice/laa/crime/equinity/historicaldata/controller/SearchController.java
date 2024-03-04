package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.TaskSearchService;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class SearchController implements SearchApi {
    private final TaskSearchService taskService;

    @Override
    public ResponseEntity<CrmFormsDTO> doSearchBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo,String providerAccount) {
        LocalDate dateSubmittedFrom = DateUtil.convertStringToLocalDate(submittedFrom);
        LocalDate dateSubmittedTo = DateUtil.convertStringToLocalDate(submittedTo);

        DateUtil.checkDateRangeIsValid(dateSubmittedFrom, dateSubmittedTo);

//        List<Task> tasks = taskService.searchAllByCriteria(null, dateSubmittedFrom, dateSubmittedTo);
        return new ResponseEntity<>(new CrmFormsDTO(), HttpStatus.OK);
    }
}
