package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="TaskTypeStates")
@AllArgsConstructor
@NoArgsConstructor
public class TaskTypeState {

    @Id
    @Column(name = "TaskTypeID")
    private Integer taskTypeId;

    @Id
    @Column(name = "StateID")
    private Integer stateId;

    @Column(name = "DisplayName")
    private String name;

    @Column(name = "Description")
    private String description;
}
