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
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
@NoArgsConstructor
public class CrmFormSearchCriteria {
    @Value("${server.api.pageSize:20}")
    private Integer DEFAULT_PAGE_SIZE;

    private static final String USN_COL = "USN";
    private static final String SUBMITTED_DATE_COL = "submittedDate";
    private static final String TYPE_ID_COL = "typeId";
    private static final String PROVIDER_ACCOUNT_COL = "providerAccount";
    private static final String CLIENT_NAME_COL = "clientName";
    private static final String SEARCH_BY_CONTAINS_TEMPLATE = "%%%s%%";


    public PageRequest getNextPageRequest(CrmFormSearchCriteriaDTO crmFormSearchCriteriaDTO) {
        int DEFAULT_PAGE = 0;
        int page = (crmFormSearchCriteriaDTO.page() == null) ? DEFAULT_PAGE : crmFormSearchCriteriaDTO.page();
        int pageSize = (crmFormSearchCriteriaDTO.pageSize() == null) ? DEFAULT_PAGE_SIZE :  crmFormSearchCriteriaDTO.pageSize();
        return PageRequest.of(page, pageSize,
                Sort.by(SUBMITTED_DATE_COL).descending()
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
                -> usn == null ? null : criteriaBuilder.like(root.get(USN_COL), String.format(SEARCH_BY_CONTAINS_TEMPLATE, usn));
    }

    private Specification<CrmFormDataModelInterface> byType(@Nullable Integer type) {
        return (root, query, criteriaBuilder)
                -> type == null ? null : criteriaBuilder.equal(root.get(TYPE_ID_COL), type);
    }

    private Specification<CrmFormDataModelInterface> byClientName(@Nullable String clientName) {
        return (root, query, criteriaBuilder)
                -> clientName == null ? null : criteriaBuilder.like(root.get(CLIENT_NAME_COL), String.format(SEARCH_BY_CONTAINS_TEMPLATE, clientName));
    }

    private Specification<CrmFormDataModelInterface> byClientDoB(@Nullable String clientDOB) {
        return null;
    }

    private Specification<CrmFormDataModelInterface> byDateSubmittedFrom(@Nullable String dateSubmittedFrom) {
        return (root, query, criteriaBuilder)
                -> dateSubmittedFrom == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get(SUBMITTED_DATE_COL), dateSubmittedFrom);
    }

    private Specification<CrmFormDataModelInterface> byDateSubmittedTo(@Nullable String dateSubmittedTo) {
        LocalDate localDateSubmittedTo = DateUtil.convertStringToLocalDate(dateSubmittedTo);
        if (localDateSubmittedTo == null) {
            return null;
        }

        return (root, query, criteriaBuilder)
                -> criteriaBuilder.lessThan(root.get(SUBMITTED_DATE_COL), localDateSubmittedTo.plusDays(1).toString());
    }

    private Specification<CrmFormDataModelInterface> byProviderAccount(@Nullable String providerAccount) {
        return (root, query, criteriaBuilder)
            -> providerAccount == null ? null : criteriaBuilder.like(root.get(PROVIDER_ACCOUNT_COL), String.format(SEARCH_BY_CONTAINS_TEMPLATE, providerAccount));
    }

    private Specification<CrmFormDataModelInterface> byProfileAcceptedTypes(@Nullable String types) {
        if (types == null) return null;

        List<String> convertedTypes = Arrays.asList(types.replace(" ", "").split(",", -1));
        return (root, query, criteriaBuilder)
                -> root.get(TYPE_ID_COL).in(convertedTypes);
    }
}
