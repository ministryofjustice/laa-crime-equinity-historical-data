package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.*;

import java.util.List;


@Service
@Slf4j
public class CrmFurtherInformationService {
    public void addFileKeyToFurtherInfo(CrmFormModelInterface crmModel) {
        List<CrmFurtherInfoAttachmentsModel> furtherInfoAttachments = ((CrmFurtherInformationModelInterface) crmModel.getFormDetails()).getFurtherInformationModel().getAttachments();

        if (furtherInfoAttachments != null) {
            for(CrmFurtherInfoAttachmentsModel furtherInfoAttachment: furtherInfoAttachments){
                if(StringUtils.isNotEmpty(furtherInfoAttachment.getRetrieve()) && StringUtils.isNotEmpty(furtherInfoAttachment.getName())){
                    furtherInfoAttachment.setFileKey(retrieveFileKey(furtherInfoAttachment.getName(),crmModel.getEvidenceFiles()));
                }
            }
        }
    }

    public String retrieveFileKey(String filename, CrmEvidenceFilesModel evidenceFilesModel) {
        String fileKey= null;
        for(CrmEvidenceFileModel evidenceFiles: evidenceFilesModel.getFiles()) {
            if (evidenceFiles.getName().equals(filename)){
                fileKey = evidenceFiles.getKey();
                break;
            }
        }
        return fileKey;
    }
}