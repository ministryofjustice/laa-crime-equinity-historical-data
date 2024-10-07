package uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormDetailsCriteriaDTO;

@Configuration
@NoArgsConstructor
public class CrmFormDetailsCriteria {
    public Specification<CrmFormModelInterface> getSpecification(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) {
        return Specification
            .where(byUsn(crmFormDetailsCriteriaDTO.usn())
                .and(byType(crmFormDetailsCriteriaDTO.type()))
            );
    }

    private Specification<CrmFormModelInterface> byUsn(@Nullable Long usn) {
        return (root, query, criteriaBuilder)
                -> usn == null ? null : criteriaBuilder.equal(root.get("USN"), usn);
    }

    private Specification<CrmFormModelInterface> byType(@Nullable Integer type) {
        return (root, query, criteriaBuilder)
                -> type == null ? null : criteriaBuilder.equal(root.get("typeId"), type);
    }
}
