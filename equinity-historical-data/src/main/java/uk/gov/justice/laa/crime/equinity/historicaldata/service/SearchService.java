package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormViewModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormsViewRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final CrmFormsViewRepository searchRepository;
    private final CrmFormSearchCriteria crmFormSearchCriteria;

    public List<CrmFormViewModel> searchAllByCriteria(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {

        List<CrmFormViewModel> searchResults =  searchRepository.findAll(
                crmFormSearchCriteria.getSpecification(crmFormSearchCriteriaDTO)
            )
                .stream()
                .filter(CrmFormViewModel.class::isInstance)
                .map(CrmFormViewModel.class::cast)
                .toList();

        if (searchResults.isEmpty()) {
            throw new ResourceNotFoundException("No Tasks were found");
        }

        return searchResults;
    }
}
