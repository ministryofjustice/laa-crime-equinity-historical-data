package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7.Crm7Model;

import java.time.LocalDate;
import java.util.Date;

@UtilityClass
public class CrmFormUtil {

    public static void checkCrmFormDateReceived(Crm4Model crm4FormData) {
        checkDateReceived(crm4FormData.getFormDetails().getDate_received(), crm4FormData.getFormDetails().getUsn());
    }

    public static void checkCrmFormDateReceived(Crm5Model crm5FormData) {
        checkDateReceived(crm5FormData.getFormDetails().getDate_received(), (long) crm5FormData.getFormDetails().getUsn());
    }

    public static void checkCrmFormDateReceived(Crm7Model crm7FormData) {
        checkDateReceived(crm7FormData.getFormDetails().getDate_received(), (long) crm7FormData.getFormDetails().getUsn());
    }

    private static void checkDateReceived(Date dateReceived, Long usn) {
        checkDateReceived(DateUtil.convertDateToLocalDate(dateReceived), usn);
    }

    private static void checkDateReceived(LocalDate dateReceived, Long usn) {
        if (dateReceived != null && dateReceived.isBefore(DateUtil.minStartDate())) {
            throw new ResourceNotFoundException("USN " + usn + " is unavailable");
        }
    }
}
