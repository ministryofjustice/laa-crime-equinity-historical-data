package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

public interface CrmMapper {
    // Generic converters
    default Integer emptyIntToNull(String s) {
        return (s == null || s.isEmpty()) ? null : Integer.parseInt(s);
    }

    default <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String s) {
        return (s == null || s.isEmpty()) ? null
                : Enum.valueOf(enumClass, s.toUpperCase());
    }
}

