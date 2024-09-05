package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Named;

public interface CrmMapper {
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

    default <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String s) {
        return (s == null || s.isEmpty()) ? null
                : Enum.valueOf(enumClass, s.toUpperCase());
    }
    @Named("convertToTimeSpentString")
    default String convertToTimeSpentString(String t) {
        return (t == null || t.isEmpty()) ? null
                : (t.length() > 8 ? t.substring(t.length() - 8) : t);
    }
}

