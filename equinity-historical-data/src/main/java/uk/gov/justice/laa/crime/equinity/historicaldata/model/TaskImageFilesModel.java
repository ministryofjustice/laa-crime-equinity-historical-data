package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Immutable @Entity
@Table(name = "TaskImageFiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskImageFilesModel {

    @Id
    private Long ID;

    @Column(name = "OFDImage")
    @Lob
    private byte[] crmFile;
}
