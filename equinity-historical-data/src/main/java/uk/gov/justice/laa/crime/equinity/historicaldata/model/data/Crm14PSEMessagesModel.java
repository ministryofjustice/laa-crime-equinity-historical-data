package uk.gov.justice.laa.crime.equinity.historicaldata.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Immutable @Entity
@Table(name = "CrmFormCRM14PSEDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm14PSEMessagesModel {
    @Id
    @Column(name="USN_PSE")
    private Long usn_pse;

    @Column(name="USN")
    private Long USN;

    @Column(name = "DTLastActedOn")
    private Date dtLastActedOn;


    @Column(name = "Tlmessage")
    private String tlmessage;

}