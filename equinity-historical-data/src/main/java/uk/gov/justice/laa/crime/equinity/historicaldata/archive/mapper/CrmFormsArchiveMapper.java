package uk.gov.justice.laa.crime.equinity.historicaldata.archive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CrmFormsArchiveMapper {

    @Mapping(target="usn", source="USN")
    @Mapping(target="type", source="type")
    @Mapping(target="clientName", source="clientName")
    @Mapping(target="originatedDate", source="originatedDate")
    @Mapping(target="submittedDate", source="submittedDate")
    @Mapping(target="providerAccount", source="providerAccount")
    @Mapping(target="providerName", source="providerName")
    CrmFormsDTO getDTOFromModel(CrmFormArchiveModel crmFormsArchiveModel);

    List<CrmFormsDTO> getDTOsFromModel(List<CrmFormArchiveModel> crmFormsArchiveModels);
}

