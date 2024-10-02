package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFurtherInformationDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFurtherInfoAttachmentsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

import static uk.gov.justice.laa.crime.equinity.historicaldata.util.DateUtil.timeDifference;


public interface CrmMapper {
    static final String DEFAULT_START_DATETIME="1899-12-30T00:00:00";
    static final String ISO_DATE_PATTERN="yyyy-MM-dd'T'HH:mm:ss";
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


    @Named("convertToTimeSpentString")
    default String convertToTimeSpentString(String t){
        if (StringUtils.isEmpty(t)){
            return null;
        }
        if (t.length() > 8){
            return timeDifference(DEFAULT_START_DATETIME,t,ISO_DATE_PATTERN);
        } else {
            return t;
        }
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

