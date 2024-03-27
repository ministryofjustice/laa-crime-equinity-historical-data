package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmFormsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormViewModel;

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
    CrmFormsDTO getDTOFromModel(CrmFormViewModel crmFormsViewModel);

    @Mapping(target="page", source="page.number")
    @Mapping(target="perPage", source="page.size")
    @Mapping(target="totalPages", source="page.totalPages")
    @Mapping(target="totalRecords", source="page.totalElements")
    @Mapping(target="searchResultsDTO", source="crmFormsViewModels")
    SearchDTO getDTOsFromModel(List<CrmFormViewModel> crmFormsViewModels, Page<CrmFormModelInterface> page);
}

