package uk.gov.justice.laa.crime.equinity.historicaldata.config;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;

import java.time.LocalDateTime;

@Configuration
@NoArgsConstructor
public class TaskSearchCriteria {
    public Specification<Task> getSpecification(@Nullable Integer taskTypeId, @Nullable LocalDateTime dateFrom, @Nullable LocalDateTime dateTo) {
        return Specification
            .where(byTaskTypeId(taskTypeId))
            .and(byOriginatorDateFrom(dateFrom))
            .and(byOriginatorDateTo(dateTo));
    }

    private Specification<Task> byTaskTypeId(@Nullable Integer taskTypeId){
        return (root, query, criteriaBuilder)
            -> taskTypeId == null ? null : criteriaBuilder.equal(root.get("typeId"), taskTypeId);
    }

    private Specification<Task> byOriginatorDateFrom(@Nullable LocalDateTime dateFrom){
        return (root, query, criteriaBuilder)
            -> dateFrom == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("DTOriginated"), dateFrom);
    }

    private Specification<Task> byOriginatorDateTo(@Nullable LocalDateTime dateTo){
        return (root, query, criteriaBuilder)
            -> dateTo == null ? null : criteriaBuilder.lessThan(root.get("DTOriginated"), dateTo);
    }
}
