package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4;

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
  private String preparationHours;
  private Float hourlyRate;
  private String additionalItemDesc;
  private Float additionalItemAmount;
  private String travelHours;
  private Float travelHourlyRate;
  private Float quoteTotal;
  private String qcDetails;
}

