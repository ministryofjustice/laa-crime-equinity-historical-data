package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    public LocalDateTime getDTOriginated() {
        return DTOriginated;
    }

    @Setter
    @Column(name = "DTOriginated")
    private LocalDateTime DTOriginated;



    public byte[] getOFDImage() {
        return OFDImage;
    }

    public void setOFDImage(byte[] OFDImage) {
        System.out.println("inside setOFDImage::");
        this.OFDImage = OFDImage;
    }

    @Column(name = "OFDImage")
    private byte[] OFDImage;

    public long getID() {
        return ID;
    }

}
