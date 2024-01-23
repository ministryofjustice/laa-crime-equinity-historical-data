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
@Table(name="TaskTypes")
@AllArgsConstructor
@NoArgsConstructor
public class TaskType {

    @Id
    @Column(name = "Type")
    private Integer typeId;

    @Column(name = "Description")
    private String name;

    @Column(name = "UF1Description")
    private String UF1Description;

    @Column(name = "UF2Description")
    private String UF2Description;

    @Column(name = "UF4Description")
    private String UF4Description;

    @Column(name = "UF5Description")
    private String UF5Description;
}
