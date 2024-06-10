package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm14Mapper extends CrmMapper {

    @Mapping(target="legalRepresentativeUse.dateStamp.usn", source="usn")
    @Mapping(target="legalRepresentativeUse.dateStamp.usn", source="usn")

    Crm14DetailsDTO getDTOFromModel(Crm14DetailsModel model);
}
