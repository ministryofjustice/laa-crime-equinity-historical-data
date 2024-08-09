package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.mapper.CrmFormsViewMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormViewModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormsViewRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final CrmFormsViewRepository searchRepository;
    private final CrmFormSearchCriteria crmFormSearchCriteria;
    private final CrmFormsViewMapper searchResultsMapper;

    @Timed("laa_crime_equiniti_historic_data_search")
    public SearchResultDTO searchAllByCriteria(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        log.info("Sending paged request. Search criteria :: {}", crmFormSearchCriteriaDTO);
        return convertResults(searchPageByCriteria(crmFormSearchCriteriaDTO));
    }

    private Page<CrmFormModelInterface> searchPageByCriteria(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {

        Page<CrmFormModelInterface> pagedResults = searchRepository.findAll(
                crmFormSearchCriteria.getSpecification(crmFormSearchCriteriaDTO),
                crmFormSearchCriteria.getNextPageRequest(crmFormSearchCriteriaDTO)
        );

        if (pagedResults.getTotalElements() == 0) {
            log.info("No results found for the given search criteria :: {}", crmFormSearchCriteriaDTO);
            throw new ResourceNotFoundException("No Tasks were found");
        }

        return pagedResults;
    }

    private SearchResultDTO convertResults(Page<CrmFormModelInterface> pagedResults) {
        log.info("Converting search results. Search criteria :: totalElements=[{}] pages=[{}]", pagedResults.getTotalElements(), pagedResults.getTotalPages());
        return searchResultsMapper.getDTOsFromModel(
                pagedResults.stream()
                        .filter(CrmFormViewModel.class::isInstance)
                        .map(CrmFormViewModel.class::cast)
                        .toList(),
                pagedResults
        );
    }
}
