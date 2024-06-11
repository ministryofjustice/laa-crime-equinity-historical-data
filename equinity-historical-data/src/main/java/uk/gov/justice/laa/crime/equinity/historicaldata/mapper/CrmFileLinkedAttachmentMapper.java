package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CrmEvidenceFileDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFileModel;

@Mapper(componentModel = "spring")
public interface CrmFileLinkedAttachmentMapper extends CrmMapper {
//    @Mapping(target="files", source="linkedAttachmentsModel")
//    CrmEvidenceFilesDTO getDTOsFromModel(List<CrmFileLinkedAttachmentModel> linkedAttachmentsModel);

    @Mapping(target="key", source="key")
    @Mapping(target="type", source="type")
    @Mapping(target="name", source="name")
    CrmEvidenceFileDTO getDTOFromModel(CrmEvidenceFileModel crmEvidenceFileModelModel);
}
