package uk.gov.justice.laa.crime.equinity.historicaldata.archive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.mapper.CrmFormsArchiveMapper;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.repository.CrmFormsArchiveRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchDTO;


@Service
@RequiredArgsConstructor
public class SearchArchiveService {
    private final CrmFormsArchiveRepository searchRepository;
    private final CrmFormSearchCriteria crmFormSearchCriteria;
    private final CrmFormsArchiveMapper searchResultsMapper;

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
                        .filter(CrmFormArchiveModel.class::isInstance)
                        .map(CrmFormArchiveModel.class::cast)
                        .toList(),
                pagedResults
        );
    }
}
