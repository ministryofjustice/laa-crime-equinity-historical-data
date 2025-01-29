package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14.Crm14Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7.Crm7Model;

import java.time.LocalDate;
import java.util.Date;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.getMinStartDate;

@UtilityClass
public class CrmFormUtil {

    public static <T extends CrmFormModelInterface> void checkCrmFormDateReceived(T crmFormModel) {
        if (crmFormModel instanceof Crm4Model crm4Model) {
            checkDateReceived(crm4Model.getFormDetails().getDate_received(), crm4Model.getFormDetails().getUsn());
        } else if (crmFormModel instanceof Crm5Model crm5Model) {
            checkDateReceived(crm5Model.getFormDetails().getDate_received(), (long) crm5Model.getFormDetails().getUsn());
        } else if (crmFormModel instanceof Crm7Model crm7Model)  {
            checkDateReceived(crm7Model.getFormDetails().getDate_received(), crm7Model.getFormDetails().getUsn());
        } else if (crmFormModel instanceof Crm14Model crm14Model) {
            checkDateReceived(crm14Model.getFormDetails().getDate_received(), (long) crm14Model.getFormDetails().getUsn());
        }
    }

    private static void checkDateReceived(Date dateReceived, Long usn) {
        checkDateReceived(DateUtil.convertDateToLocalDate(dateReceived), usn);
    }

    private static void checkDateReceived(LocalDate dateReceived, Long usn) {
        if (dateReceived != null && dateReceived.isBefore(getMinStartDate())) {
            throw new ResourceNotFoundException("USN " + usn + " not found");
        }
    }
}
