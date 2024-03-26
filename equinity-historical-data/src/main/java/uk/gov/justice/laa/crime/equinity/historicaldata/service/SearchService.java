package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.CrmFormsViewMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormViewModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormsViewRepository;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final CrmFormsViewRepository searchRepository;
    private final CrmFormSearchCriteria crmFormSearchCriteria;
    private final CrmFormsViewMapper searchResultsMapper;

    public SearchDTO searchAllByCriteria(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        return convertResults(searchPageByCriteria(crmFormSearchCriteriaDTO));
    }

    private Page<CrmFormModelInterface> searchPageByCriteria(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        Page<CrmFormModelInterface> pagedResults = searchRepository.findAll(
                crmFormSearchCriteria.getSpecification(crmFormSearchCriteriaDTO),
                crmFormSearchCriteria.getNextPageRequest(crmFormSearchCriteriaDTO)
        );

        if (pagedResults.getTotalElements() == 0) {
            throw new ResourceNotFoundException("No Tasks were found");
        }

        return pagedResults;
    }

    private SearchDTO convertResults(Page<CrmFormModelInterface> pagedResults) {
        return searchResultsMapper.getDTOsFromModel(
                pagedResults.stream()
                        .filter(CrmFormViewModel.class::isInstance)
                        .map(CrmFormViewModel.class::cast)
                        .toList(),
                pagedResults
        );
    }
}
