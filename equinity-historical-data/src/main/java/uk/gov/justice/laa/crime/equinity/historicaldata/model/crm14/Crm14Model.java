package uk.gov.justice.laa.crime.equinity.historicaldata.model.crm14;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFileModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormCRM14AttachmentStoreModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm14Model implements CrmFormModelInterface {
    @JsonProperty("fielddata")
    Crm14DetailsModel formDetails;
    @JsonProperty("read_only")
    private boolean read_only;
    @JsonProperty("attachfields")
    private String attachfields;
    @JsonProperty("targetpath")
    private String targetpath;
    @JsonProperty("linkedAttachments")
    private CrmEvidenceFilesModel evidenceFiles;

    // TODO (EMP-557): Should it be considered moving this function to class CrmEvidenceFilesModel ?
    public void addProcessedAttachmentsToEvidence(List<CrmFormCRM14AttachmentStoreModel> processedAttachments) {
        for(CrmFormCRM14AttachmentStoreModel processedAttachment: processedAttachments){
            CrmEvidenceFileModel evidenceFileModel = new CrmEvidenceFileModel();
            evidenceFileModel.setKey("att_"+processedAttachment.getAttachmentId()+".att");
            evidenceFileModel.setName(processedAttachment.getFileName());
            evidenceFileModel.setType(processedAttachment.getEvidenceType());
            getEvidenceFiles().getFiles().add(evidenceFileModel);
        }
    }
}