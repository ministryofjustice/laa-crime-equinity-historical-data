package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm7SummaryOfClaimDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm7Mapper {
    @Mapping(target="usn", source="usn")
    @Mapping(target="summary", source="model")
    Crm7DetailsDTO getDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="clientSurname", source="client_surname")
    @Mapping(target="clientFirstName", source="client_forename")
    @Mapping(target="clientDateOfBirth", source="client_dob")
    @Mapping(target="ufn", source="client_ufn")
    @Mapping(target="maatNumber", expression="java(emptyIntToNull(model.getMaat_number()))")
    @Mapping(target="representationOrderNumber", source="rep_order_no")
    @Mapping(target="representationOrderDate", source="rep_order_date")
    @Mapping(target="representationOrderSubmitMode", expression="java(convertToEnum(Crm7SummaryOfClaimDTO.RepresentationOrderSubmitModeEnum.class, model.getCp_rep_order_attach_method()))")
    @Mapping(target="isLocatedInDesignatedArea", expression="java(convertToEnum(Crm7SummaryOfClaimDTO.IsLocatedInDesignatedAreaEnum.class, model.getSc_case_in_designated_area()))")
    @Mapping(target="stateReached", source="tlStateName")
    @Mapping(target="outcomeCode", source="sc_outcome_code")
    @Mapping(target="matterType", source="sc_matter_type")
    Crm7SummaryOfClaimDTO getSummaryDTOFromModel(Crm7DetailsModel model);

    default Integer emptyIntToNull(String s) {
        return (s == null || s.isEmpty()) ? null : Integer.parseInt(s);
    }

    default <T extends Enum<T>> T convertToEnum(Class<T> enumClass, String s) {
        return (s == null || s.isEmpty()) ? null
                : Enum.valueOf(enumClass, s.toUpperCase());
    }
}

