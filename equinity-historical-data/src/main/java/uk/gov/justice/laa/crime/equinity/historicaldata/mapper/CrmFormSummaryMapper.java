package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormDataModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchCrmFormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchPagingDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormSummaryModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CrmFormSummaryMapper {
    @Mapping(target="results", source="crmFormsViewModels")
    @Mapping(target="paging", source="page")
    SearchResultDTO getDTOsFromModel(List<CrmFormSummaryModel> crmFormsViewModels, Page<CrmFormDataModelInterface> page);

    @Mapping(target="usn", source="USN")
    @Mapping(target="type", source="type")
    @Mapping(target="clientName", source="clientName")
    @Mapping(target="originatedDate", source="originatedDate")
    @Mapping(target="submittedDate", source="submittedDate")
    @Mapping(target="providerAccount", source="providerAccount")
    @Mapping(target="providerName", source="providerName")
    @Mapping(target="laaCaseRef", source="laaCaseRef")
    @Mapping(target="status", source="status")
    SearchCrmFormDTO getDTOFromModel(CrmFormSummaryModel crmFormsViewModel);

    @Mapping(target="size", source="page.size")
    @Mapping(target="number", source="page.number")
    @Mapping(target="total", source="page.totalPages")
    @Mapping(target="itemsPage", source="page.numberOfElements")
    @Mapping(target="itemsTotal", source="page.totalElements")
    @Mapping(target="sort", expression="java(page.getSort().toString())")
    SearchPagingDTO getPagingDTOFromPage(Page<CrmFormDataModelInterface> page);
}

