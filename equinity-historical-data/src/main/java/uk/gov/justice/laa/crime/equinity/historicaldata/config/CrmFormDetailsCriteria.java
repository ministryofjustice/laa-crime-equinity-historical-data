package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFileModelInterface;

import java.util.Arrays;
import java.util.List;

@Configuration
@NoArgsConstructor
public class CrmFormDetailsCriteria {
    public Specification<CrmFileModelInterface> getSpecification(CrmFormDetailsCriteriaDTO crmFormDetailsCriteriaDTO) {
        return Specification
            .where(byUsn(crmFormDetailsCriteriaDTO.usn())
                .and(byType(crmFormDetailsCriteriaDTO.type()))
                .and(byProfileAcceptedTypes(crmFormDetailsCriteriaDTO.profileAcceptedTypes()))
            );
    }

    private Specification<CrmFileModelInterface> byUsn(@Nullable Long usn) {
        return (root, query, criteriaBuilder)
                -> usn == null ? null : criteriaBuilder.equal(root.get("USN"), usn);
    }

    private Specification<CrmFileModelInterface> byType(@Nullable Integer type) {
        return (root, query, criteriaBuilder)
                -> type == null ? null : criteriaBuilder.equal(root.get("typeId"), type);
    }

    private Specification<CrmFileModelInterface> byProfileAcceptedTypes(@Nullable String types) {
        if (types == null) return null;

        List<String> convertedTypes = Arrays.asList(types.replace(" ", "").split(",", -1));
        return (root, query, criteriaBuilder)
                -> root.get("typeId").in(convertedTypes);
    }
}
