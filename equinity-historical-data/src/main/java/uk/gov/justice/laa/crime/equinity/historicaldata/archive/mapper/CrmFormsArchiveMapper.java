package uk.gov.justice.laa.crime.equinity.historicaldata.archive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchCrmFormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchPagingDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CrmFormsArchiveMapper {

    @Mapping(target="results", source="crmFormsArchiveModels")
    @Mapping(target="paging", source="page")
    SearchResultDTO getDTOsFromModel(List<CrmFormArchiveModel> crmFormsArchiveModels, Page<CrmFormModelInterface> page);

    @Mapping(target="usn", source="USN")
    @Mapping(target="type", source="type")
    @Mapping(target="clientName", source="clientName")
    @Mapping(target="originatedDate", source="originatedDate")
    @Mapping(target="submittedDate", source="submittedDate")
    @Mapping(target="providerAccount", source="providerAccount")
    @Mapping(target="providerName", source="providerName")
    SearchCrmFormDTO getDTOFromModel(CrmFormArchiveModel crmFormArchiveModel);

    @Mapping(target="size", source="page.size")
    @Mapping(target="number", source="page.number")
    @Mapping(target="total", source="page.totalPages")
    @Mapping(target="itemsPage", source="page.numberOfElements")
    @Mapping(target="itemsTotal", source="page.totalElements")
    SearchPagingDTO getPagingDTOFromPage(Page<CrmFormModelInterface> page);
}
