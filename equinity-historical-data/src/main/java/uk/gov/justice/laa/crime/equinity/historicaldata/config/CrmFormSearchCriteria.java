package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@Configuration
@NoArgsConstructor
public class CrmFormSearchCriteria {
    public Specification<CrmFormsModelInterface> getSpecification(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        return Specification
            .where(byUsn(crmFormSearchCriteriaDTO.usn())
            .and(byClientName(crmFormSearchCriteriaDTO.client()))
            .and(byClientDoB(crmFormSearchCriteriaDTO.clientDoB()))
            .and(byDateSubmittedFrom(crmFormSearchCriteriaDTO.submittedFrom()))
            .and(byDateSubmittedTo(crmFormSearchCriteriaDTO.submittedTo()))
            .and(byProviderAccount(crmFormSearchCriteriaDTO.providerAccount())));
    }

    private Specification<CrmFormsModelInterface> byUsn(@Nullable String usn){
        return (root, query, criteriaBuilder)
                -> usn == null ? null : criteriaBuilder.like(root.get("USN"), String.format("%%%s%%", usn));
    }

    private Specification<CrmFormsModelInterface> byClientName(@Nullable String clientName){
        return (root, query, criteriaBuilder)
                -> clientName == null ? null : criteriaBuilder.like(root.get("clientName"), String.format("%%%s%%", clientName));
    }

    private Specification<CrmFormsModelInterface> byClientDoB(@Nullable String clientDOB){
        return null;
    }

    private Specification<CrmFormsModelInterface> byDateSubmittedFrom(@Nullable LocalDate dateSubmittedFrom){
        return (root, query, criteriaBuilder)
                -> dateSubmittedFrom == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("submittedDate"), dateSubmittedFrom.toString());
    }

    private Specification<CrmFormsModelInterface> byDateSubmittedTo(@Nullable LocalDate dateSubmittedTo){
        return (root, query, criteriaBuilder)
                -> dateSubmittedTo == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("submittedDate"), dateSubmittedTo.toString());
    }

    private Specification<CrmFormsModelInterface> byProviderAccount(@Nullable String providerAccount){
        return (root, query, criteriaBuilder)
            -> providerAccount == null ? null : criteriaBuilder.like(root.get("providerAccount"), String.format("%%%s%%", providerAccount));
    }
}
