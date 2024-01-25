package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;


@Getter
@Entity
@Table(name = "Tasks")
@AllArgsConstructor
@NoArgsConstructor
public class ParentTask {

    @Id
    private long ID;

    @Getter
    private String UF2Text;
}
