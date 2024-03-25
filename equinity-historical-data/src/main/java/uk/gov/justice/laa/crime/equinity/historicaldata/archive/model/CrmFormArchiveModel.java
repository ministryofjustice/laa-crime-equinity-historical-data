package uk.gov.justice.laa.crime.equinity.historicaldata.archive.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;

@Immutable
@Entity
@Table(name = "CrmFormsView", catalog = "eq_archive", schema = "dbo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmFormArchiveModel implements CrmFormModelInterface {

    @Id
    @Column(name="USN")
    private String USN;

    @Column(name="ClientSurname")
    private String clientName;
    @Column(name="TypeName")
    private String type;
    @Column(name="StatusName")
    private String status;
    @Column(name="OriginatedDate")
    private String originatedDate;
    @Column(name="SubmittedDate")
    private String submittedDate;
    @Column(name="ProviderAccount")
    private String providerAccount;
    @Column(name="ProviderName")
    private String providerName;
}
