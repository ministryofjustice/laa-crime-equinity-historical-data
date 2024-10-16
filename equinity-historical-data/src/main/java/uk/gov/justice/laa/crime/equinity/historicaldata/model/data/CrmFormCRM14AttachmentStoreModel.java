package uk.gov.justice.laa.crime.equinity.historicaldata.model.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Immutable @Entity
@Table(name = "CrmFormCRM14AttachmentStore")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmFormCRM14AttachmentStoreModel {


    @Column(name="USN")
    private Long USN;

    @Id
    @Column(name = "AttachmentId" , updatable = false, nullable = false, unique=true)
    private String attachmentId;
    public String getAttachmentId() {
        return attachmentId.toLowerCase();
    }

    @Column(name="Filename")
    private String fileName;

    @Column(name = "FileSize")
    private Integer fileSize;

    @Column(name = "Submitted")
    private Date submitted;

    @Column(name = "Processed")
    private Date processed;

    @Column(name = "Status")
    private String status;

    @Column(name = "EvidenceType")
    private String evidenceType;

    @Column(name = "ProviderNotes")
    private String providerNotes;

    @Column(name = "CaseworkerNotes")
    private String caseworkerNotes;

    @Column(name = "ProviderFirmId")
    private Integer providerFirmId;
}