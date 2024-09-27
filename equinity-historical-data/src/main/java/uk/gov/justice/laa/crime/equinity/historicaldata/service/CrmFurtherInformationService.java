package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFileModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmEvidenceFilesModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormModelInterface;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFurtherInfoAttachmentsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm5.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm7.Crm7Model;

import java.util.List;


@Service
@Slf4j
public class CrmFurtherInformationService {

    public static final int CRM_TYPE_4 = 1;
    public static final int CRM_TYPE_5 = 4;
    public static final int CRM_TYPE_7 = 5;

    public void addFileKeyToFurtherInfo(int crmType, CrmFormModelInterface crmModel) {
        List<CrmFurtherInfoAttachmentsModel> furtherInfoAttachments = switch (crmType) {
            case CRM_TYPE_4 -> ((Crm4Model) crmModel).getFormDetails().getFurtherInformationModel().getAttachments();
            case CRM_TYPE_5 -> ((Crm5Model) crmModel).getFormDetails().getFurtherInformationModel().getAttachments();
            case CRM_TYPE_7 -> ((Crm7Model) crmModel).getFormDetails().getFurtherInformationModel().getAttachments();
            default -> null;
        };

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