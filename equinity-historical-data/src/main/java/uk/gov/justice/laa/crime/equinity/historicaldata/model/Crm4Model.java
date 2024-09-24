package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crm4Model implements CrmFormModelInterface {
    @JsonProperty("fielddata")
    Crm4DetailsModel formDetails;
    @JsonProperty("read_only")
    private boolean read_only;
    @JsonProperty("targetpath")
    private String targetpath;

    @JsonProperty("linkedAttachments")
    private CrmEvidenceFilesModel evidenceFiles;

    public void addFileKeyToFurtherInfo(List<CrmFurtherInfoAttachmentsModel> furtherInfoAttachments) {
        for(CrmFurtherInfoAttachmentsModel furtherInfoAttachment: furtherInfoAttachments){
            if(StringUtils.isNotEmpty(furtherInfoAttachment.getRetrieve()) && StringUtils.isNotEmpty(furtherInfoAttachment.getName())){
                furtherInfoAttachment.setFileKey(retrieveFileKey(furtherInfoAttachment.getName()));
            }
        }
    }
    public String retrieveFileKey(String name) {
        String fileKey= null;
        for(CrmEvidenceFileModel evidenceFiles: getEvidenceFiles().getFiles()) {
        if (evidenceFiles.getName().equals(name)){
            fileKey = evidenceFiles.getKey();
            break;
        }
        }
      return fileKey;
    }

}