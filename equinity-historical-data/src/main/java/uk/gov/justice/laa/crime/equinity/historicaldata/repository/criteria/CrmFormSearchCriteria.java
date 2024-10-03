package uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDataModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormSearchCriteriaDTO;

import java.util.Arrays;
import java.util.List;

@Configuration
@NoArgsConstructor
public class CrmFormSearchCriteria {
    @Value("${server.api.pageSize:20}")
    private Integer DEFAULT_PAGE_SIZE;


    public PageRequest getNextPageRequest(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        int DEFAULT_PAGE = 0;
        int page = (crmFormSearchCriteriaDTO.page() == null) ? DEFAULT_PAGE : crmFormSearchCriteriaDTO.page();
        int pageSize = (crmFormSearchCriteriaDTO.pageSize() == null) ? DEFAULT_PAGE_SIZE :  crmFormSearchCriteriaDTO.pageSize();
        return PageRequest.of(page, pageSize,
                Sort.by("submittedDate").descending()
        );
    }

    public Specification<CrmFormDataModelInterface> getSpecification(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        return Specification
            .where(byUsn(crmFormSearchCriteriaDTO.usn())
                .and(byType(crmFormSearchCriteriaDTO.type()))
                .and(byClientName(crmFormSearchCriteriaDTO.client()))
                .and(byClientDoB(crmFormSearchCriteriaDTO.clientDoB()))
                .and(byDateSubmittedFrom(crmFormSearchCriteriaDTO.submittedFrom()))
                .and(byDateSubmittedTo(crmFormSearchCriteriaDTO.submittedTo()))
                .and(byProviderAccount(crmFormSearchCriteriaDTO.providerAccount()))
                .and(byProfileAcceptedTypes(crmFormSearchCriteriaDTO.profileAcceptedTypes()))
            );
    }

    private Specification<CrmFormDataModelInterface> byUsn(@Nullable String usn) {
        return (root, query, criteriaBuilder)
                -> usn == null ? null : criteriaBuilder.like(root.get("USN"), String.format("%%%s%%", usn));
    }

    private Specification<CrmFormDataModelInterface> byType(@Nullable Integer type) {
        return (root, query, criteriaBuilder)
                -> type == null ? null : criteriaBuilder.equal(root.get("typeId"), type);
    }

    private Specification<CrmFormDataModelInterface> byClientName(@Nullable String clientName) {
        return (root, query, criteriaBuilder)
                -> clientName == null ? null : criteriaBuilder.like(root.get("clientName"), String.format("%%%s%%", clientName));
    }

    private Specification<CrmFormDataModelInterface> byClientDoB(@Nullable String clientDOB) {
        return null;
    }

    private Specification<CrmFormDataModelInterface> byDateSubmittedFrom(@Nullable String dateSubmittedFrom) {
        return (root, query, criteriaBuilder)
                -> dateSubmittedFrom == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("submittedDate"), dateSubmittedFrom);
    }

    private Specification<CrmFormDataModelInterface> byDateSubmittedTo(@Nullable String dateSubmittedTo) {
        return (root, query, criteriaBuilder)
                -> dateSubmittedTo == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("submittedDate"), dateSubmittedTo);
    }

    private Specification<CrmFormDataModelInterface> byProviderAccount(@Nullable String providerAccount) {
        return (root, query, criteriaBuilder)
            -> providerAccount == null ? null : criteriaBuilder.like(root.get("providerAccount"), String.format("%%%s%%", providerAccount));
    }

    private Specification<CrmFormDataModelInterface> byProfileAcceptedTypes(@Nullable String types) {
        if (types == null) return null;

        List<String> convertedTypes = Arrays.asList(types.replace(" ", "").split(",", -1));
        return (root, query, criteriaBuilder)
                -> root.get("typeId").in(convertedTypes);
    }
}
