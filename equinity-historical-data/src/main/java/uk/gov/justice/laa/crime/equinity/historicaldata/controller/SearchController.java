package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.SearchApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.model.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.TaskSearchService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class SearchController implements SearchApi {
    private final TaskSearchService taskService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            .withLocale(Locale.UK);

    @Override
    public ResponseEntity<CrmFormsDTO> doSearchBy(String usn, String client, String clientDoB, String submittedFrom, String submittedTo) throws ConstraintViolationException {
        LocalDate dateSubmittedFrom = (!Objects.isNull(submittedFrom)) ? LocalDate.parse(submittedFrom, formatter) : null;
        LocalDate dateSubmittedTo = (!Objects.isNull(submittedTo)) ? LocalDate.parse(submittedTo, formatter) : null;

        if (dateSubmittedFrom != null && dateSubmittedTo != null) {
            if (dateSubmittedFrom.isAfter(dateSubmittedTo))
                throw new RuntimeException("Submitted From date must be before that Submitted To date");
        }

//        List<Task> tasks = taskService.searchAllByCriteria(null, dateSubmittedFrom, dateSubmittedTo);
        return new ResponseEntity<>(new CrmFormsDTO(), HttpStatus.OK);
    }
}
