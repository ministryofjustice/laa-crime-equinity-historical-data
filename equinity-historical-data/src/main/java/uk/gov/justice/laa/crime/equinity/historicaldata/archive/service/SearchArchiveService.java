package uk.gov.justice.laa.crime.equinity.historicaldata.archive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.repository.CrmFormsArchiveRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteria;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchArchiveService {
    private final CrmFormsArchiveRepository archiveRepository;
    private final CrmFormSearchCriteria crmFormSearchCriteria;

    public List<CrmFormArchiveModel> searchAllByCriteria(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        List<CrmFormArchiveModel> searchResults = archiveRepository.findAll(
                crmFormSearchCriteria.getSpecification(crmFormSearchCriteriaDTO)
            )
            .stream()
            .filter(CrmFormArchiveModel.class::isInstance)
            .map(CrmFormArchiveModel.class::cast)
            .toList();

        if (searchResults.isEmpty()) {
            throw new ResourceNotFoundException("No Tasks were found");
        }

        return searchResults;
    }
}
