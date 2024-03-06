package uk.gov.justice.laa.crime.equinity.historicaldata.model;

public class LinkedAttachment {
  private String fileName;
  private String savetype;
  private String name;


 // Getter Methods 

  public String getFileName() {
    return fileName;
  }

  public String getSavetype() {
    return savetype;
  }

  public String getName() {
    return name;
  }

 // Setter Methods 

  public void setFileName( String fileName ) {
    this.fileName = fileName;
  }

  public void setSavetype( String savetype ) {
    this.savetype = savetype;
  }

  public void setName( String name ) {
    this.name = name;
  }
}
