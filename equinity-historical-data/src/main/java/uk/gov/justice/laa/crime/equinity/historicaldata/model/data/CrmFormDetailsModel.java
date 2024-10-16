package uk.gov.justice.laa.crime.equinity.historicaldata.model.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Immutable @Entity
@Table(name = "CrmFormDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmFormDetailsModel {

    @Id
    @Column(name="USN")
    private Long USN;

    @Column(name="TypeId")
    private Integer typeId;

    @Column(name = "OFDImage")
    @Lob
    private byte[] crmFile;
}
