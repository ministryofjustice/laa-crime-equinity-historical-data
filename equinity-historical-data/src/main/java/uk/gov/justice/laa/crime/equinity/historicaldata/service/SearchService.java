package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.SearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.CrmFormsViewMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormsViewModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.SearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormsViewRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final CrmFormsViewRepository searchRepository;
    private final SearchCriteria searchCriteria;
    private final CrmFormsViewMapper searchMapper;

    public List<CrmFormsViewModel> searchAllByCriteria(SearchCriteriaDTO searchCriteriaDTO) {


        List<CrmFormsViewModel> searchResults =  searchRepository.findAll(
                searchCriteria.getSpecification(searchCriteriaDTO)
            )
                .stream()
                .toList();

        if (searchResults.isEmpty()) {
            throw new ResourceNotFoundException("No Tasks were found");
        }

        return searchResults;
    }
}
