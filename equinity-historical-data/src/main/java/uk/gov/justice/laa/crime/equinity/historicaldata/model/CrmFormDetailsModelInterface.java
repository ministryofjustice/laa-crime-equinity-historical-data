package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import java.time.LocalDate;

public interface CrmFormDetailsModelInterface {
    Long getUsn();
    LocalDate getTlTaskLastUpdated();
}