package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFurtherInformationDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmDecisionReasonDetailsModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFurtherInfoAttachmentsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

public interface CrmMapper {
    String DECISION_GRANTED = "G";

    // Generic converters
    default Integer emptyIntToNull(String s) {
        return (s == null || s.isEmpty()) ? null : Integer.parseInt(s);
    }

    default Long emptyLongToNull(String s) {
        return (s == null || s.isEmpty()) ? null : Long.parseLong(s);
    }

    default Float emptyFloatToNull(String s) {
        return (s == null || s.isEmpty()) ? null : Float.parseFloat(s);
    }

    default Date emptyDateToNull(String s) throws ParseException {
        return (s == null || s.isEmpty()) ? null : DateUtil.convertStringToSimpleDate(s);
    }

    default <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String s) {
        return (s == null || s.isEmpty()) ? null
            : Enum.valueOf(enumClass, s.toUpperCase().replace(" ", "_"));
    }

    default String convertDecisionReason(CrmDecisionReasonDetailsModelInterface model) {
        return (model.getDecision_original().equals(DECISION_GRANTED)) ?
                model.getFull_grant_notes() : model.getReason_details();
    }

    @Mapping(target = "name", source = "name")
    @Mapping(target="originalFileName", source = "originalfilename")
    @Mapping(target="attachedPersonId", source = "personid")
    @Mapping(target="attachedPerson", source = "personname")
    @Mapping(target="description",source = "description")
    @Mapping(target="dateReceived", source = "dtreceived")
    @Mapping(target="downloadFile", source = "retrieve")
    @Mapping(target="key", source = "fileKey")
    CrmFurtherInformationDTO getFurtherInformationFromModel(CrmFurtherInfoAttachmentsModel model);
}

