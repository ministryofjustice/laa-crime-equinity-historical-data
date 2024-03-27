package uk.gov.justice.laa.crime.equinity.historicaldata.archive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchCrmFormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchDTO;

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
    SearchCrmFormDTO getDTOFromModel(CrmFormArchiveModel crmFormArchiveModel);

    @Mapping(target="page", source="page.number")
    @Mapping(target="perPage", source="page.size")
    @Mapping(target="totalPages", source="page.totalPages")
    @Mapping(target="totalRecords", source="page.totalElements")
    @Mapping(target="searchResultsDTO", source="crmFormsArchiveModels")
    SearchDTO getDTOsFromModel(List<CrmFormArchiveModel> crmFormsArchiveModels, Page<CrmFormModelInterface> page);
}

