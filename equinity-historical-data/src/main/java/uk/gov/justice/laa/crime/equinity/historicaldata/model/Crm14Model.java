package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public void addProcessedAttachmentsToEvidence(List<Crm14AttachmentModel> processedAttachments) {
        for(Crm14AttachmentModel processedAttachment: processedAttachments){
            CrmEvidenceFileModel evidenceFileModel = new CrmEvidenceFileModel();
            evidenceFileModel.setKey("att_"+processedAttachment.getAttachmentId()+".att");
            evidenceFileModel.setName(processedAttachment.getFileName());
            evidenceFileModel.setType(processedAttachment.getEvidenceType());
            getEvidenceFiles().getFiles().add(evidenceFileModel);
        }
    }
}