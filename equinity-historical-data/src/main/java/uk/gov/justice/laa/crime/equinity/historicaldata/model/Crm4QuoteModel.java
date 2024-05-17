package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import lombok.Data;

/**
 * Crm4QuoteDTO
 */
@Data
public class Crm4QuoteModel {
  private String companyName;
  private String expertName;
  private String contactPhone;
  private String costBasis;
  private Float preparationHours;
  private Float hourlyRate;
  private String addtionalItemDesc;
  private Float addtionalItemAmount;
  private String travelHours;
  private Float travelHourlyRate;
  private Float quoteTotal;
  private String qcDetails;
}

