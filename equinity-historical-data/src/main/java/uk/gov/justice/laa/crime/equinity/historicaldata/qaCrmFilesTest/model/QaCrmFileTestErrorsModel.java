package uk.gov.justice.laa.crime.equinity.historicaldata.qaCrmFilesTest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;

@Immutable @Entity
@Table(name = "QA_CRMFileTestErrors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaCrmFileTestErrorsModel implements CrmFormModelInterface {

    @Id
    @Column(name="USN")
    private Long USN;

    @Column(name="TypeId")
    private Integer typeId;

    @Column(name="ErrorMsg")
    private String errorMsg;
}
