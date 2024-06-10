package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm14Mapper extends CrmMapper {

    @Mapping(target="legalRepresentativeUse.dateStamp.usn", source="datestamp_usn")
    @Mapping(target="legalRepresentativeUse.dateStamp.date", source="datestamp_date")
    @Mapping(target="legalRepresentativeUse.dateStamp.date", source="datestamp_time")
    @Mapping(target="legalRepresentativeUse.dateStamp.clientName", source="surname")
    @Mapping(target="legalRepresentativeUse.dateStamp.clientDateOfBirth", source="date_of_birth")
    @Mapping(target="legalRepresentativeUse.legalRepUse.usn", source="usn")
    @Mapping(target="legalRepresentativeUse.legalRepUse.urn", source="urn")
    // TODO - check if this field can be Enum
    @Mapping(target="legalRepresentativeUse.legalRepUse.applicationType", source="application_type")
    @Mapping(target="legalRepresentativeUse.legalRepUse.meansTested", source="means_tested")

    @Mapping(target="legalRepresentativeUse.legalRepUse.caseType", source="means_tested")

    Crm14DetailsDTO getDTOFromModel(Crm14DetailsModel model);
}
