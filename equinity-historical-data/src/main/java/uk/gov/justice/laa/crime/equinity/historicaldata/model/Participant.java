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
@Table(name="Participants")
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DisplayName")
    private String name;
}
