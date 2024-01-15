package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "Tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "UF2Text")
    private String UF2Text;

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

    public String getUF2Text() {
        return UF2Text;
    }

    public void setUF2Text(String UF2Text) {
        this.UF2Text = UF2Text;
    }


    public Tasks() {

    }

    public Tasks(String UF2Text) {
        this.UF2Text = UF2Text;
    }


    @Override
    public String toString() {
        return "Tutorial [id=" + ID + ", UF2Text=" +UF2Text + "]";
    }
}
