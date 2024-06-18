package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmEvidenceFileDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmEvidenceFilesDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFileModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFilesModel;

@Mapper(componentModel = "spring")
public interface CrmEvidenceFilesMapper extends CrmMapper {
    @Mapping(target="files", source="files")
    CrmEvidenceFilesDTO getDTOsFromModel(CrmEvidenceFilesModel model);

    @Mapping(target="key", source="key")
    @Mapping(target="type", source="type")
    @Mapping(target="name", source="name")
    CrmEvidenceFileDTO getDTOFromModel(CrmEvidenceFileModel crmEvidenceFileModelModel);
}
