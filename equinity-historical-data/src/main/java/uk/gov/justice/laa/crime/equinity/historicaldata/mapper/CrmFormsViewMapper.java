package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormsViewModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CrmFormsViewMapper {
    @Mapping(target="usn", source="USN")
    @Mapping(target="type", source="type")
    @Mapping(target="clientName", source="clientName")
    @Mapping(target="originatedDate", source="originatedDate")
    @Mapping(target="submittedDate", source="submittedDate")
    @Mapping(target="providerAccount", source="providerAccount")
    @Mapping(target="providerName", source="providerName")
    CrmFormsDTO getDTOFromModel(CrmFormsViewModel crmFormsViewModel);

    List<CrmFormsDTO> getDTOsFromModel(List<CrmFormsViewModel> crmFormsViewModels);
}

