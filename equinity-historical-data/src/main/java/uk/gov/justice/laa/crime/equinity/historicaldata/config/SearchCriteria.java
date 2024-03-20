package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormsViewModel;

import java.time.LocalDate;

@Configuration
@NoArgsConstructor
public class SearchCriteria {
    public Specification<CrmFormsViewModel> getSpecification(SearchCriteriaDTO searchCriteriaDTO) {
        return Specification
            .where(byUsn(searchCriteriaDTO.usn())
            .and(byClientName(searchCriteriaDTO.client())))
            .and(byClientDoB(searchCriteriaDTO.clientDoB()))
            .and(byDateSubmittedFrom(searchCriteriaDTO.submittedFrom()))
            .and(byDateSubmittedTo(searchCriteriaDTO.submittedTo()))
            .and(byProviderAccount(searchCriteriaDTO.providerAccount()));
    }

    private Specification<CrmFormsViewModel> byUsn(@Nullable String usn){
        return (root, query, criteriaBuilder)
                -> usn == null ? null : criteriaBuilder.like(root.get("USN"), String.format("%%%s%%", usn));
    }

    private Specification<CrmFormsViewModel> byClientName(@Nullable String clientName){
        return (root, query, criteriaBuilder)
                -> clientName == null ? null : criteriaBuilder.like(root.get("clientName"), String.format("%%%s%%", clientName));
    }

    private Specification<CrmFormsViewModel> byClientDoB(@Nullable String clientDOB){
        return null;
    }

    private Specification<CrmFormsViewModel> byDateSubmittedFrom(@Nullable LocalDate dateSubmittedFrom){
        return (root, query, criteriaBuilder)
                -> dateSubmittedFrom == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("submittedDate"), dateSubmittedFrom.toString());
    }

    private Specification<CrmFormsViewModel> byDateSubmittedTo(@Nullable LocalDate dateSubmittedTo){
        return (root, query, criteriaBuilder)
                -> dateSubmittedTo == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("submittedDate"), dateSubmittedTo.toString());
    }

    private Specification<CrmFormsViewModel> byProviderAccount(@Nullable String providerAccount){
        return (root, query, criteriaBuilder)
            -> providerAccount == null ? null : criteriaBuilder.like(root.get("providerAccount"), String.format("%%%s%%", providerAccount));
    }
}
