package uk.gov.justice.laa.crime.equinity.historicaldata.model.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Immutable @Entity
@Table(name = "CrmFormsView")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmFormViewModel implements CrmFormDataModelInterface {

    @Id
    @Column(name="USN")
    private String USN;

    @Column(name="ClientSurname")
    private String clientName;
    @Column(name="TypeId")
    private Integer typeId;
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
    @Column(name="LaaCaseRef")
    private String laaCaseRef;
}
