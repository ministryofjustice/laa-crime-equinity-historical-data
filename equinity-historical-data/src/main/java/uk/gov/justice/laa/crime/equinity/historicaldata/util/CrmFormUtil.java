package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.experimental.UtilityClass;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;

import java.time.LocalDate;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.getMinimumDate;

@UtilityClass
public class CrmFormUtil {

    public static <T extends CrmFormModelInterface> void checkSubmittedDate(T crmFormModel) {
        LocalDate submittedDate = crmFormModel.getFormDetails().getTlTaskLastUpdated();
        if (submittedDate != null && submittedDate.isBefore(getMinimumDate())) {
            throw new ResourceNotFoundException("USN " + crmFormModel.getFormDetails().getUsn() + " not found");
        }
    }
}
