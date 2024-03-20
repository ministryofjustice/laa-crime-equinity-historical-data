package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Immutable @Entity
@Table(name = "CrmFormsView")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmFormsViewModel {

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
    private LocalDate originatedDate;
    @Column(name="SubmittedDate")
    private LocalDate submittedDate;
    @Column(name="ProviderAccount")
    private String providerAccount;
    @Column(name="ProviderName")
    private String providerName;
}
