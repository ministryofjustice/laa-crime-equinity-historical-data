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
@Table(name = "CrmFormCRM14PSEMessage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmFormCRM14PSEMessageModel {
    @Id
    @Column(name="USN_PSE")
    private Long usn_pse;

    @Column(name="USN")
    private Long USN;

    @Column(name = "DateLastUpdate")
    private Date dateLastUpdate;


    @Column(name = "message")
    private String message;

}