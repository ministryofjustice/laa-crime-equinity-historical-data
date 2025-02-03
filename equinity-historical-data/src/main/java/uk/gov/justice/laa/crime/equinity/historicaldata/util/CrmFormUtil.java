package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;

import java.time.LocalDate;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.isDateWithin7yrs;

@Component
@RequiredArgsConstructor
public class CrmFormUtil {

    private final AppUtil appUtil;

    public <T extends CrmFormModelInterface> void checkSubmittedDate(T crmFormModel) {
        if (!appUtil.isArchiveEnvironment()) {
            LocalDate submittedDate = crmFormModel.getFormDetails().getTlTaskLastUpdated();
            if (!isDateWithin7yrs(submittedDate)) {
                throw new ResourceNotFoundException("USN " + crmFormModel.getFormDetails().getUsn() + " not found");
            }
        }
    }
}
