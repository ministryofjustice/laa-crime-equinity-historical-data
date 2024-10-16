package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormCRM14AttachmentStoreModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormCRM14AttachmentStoreRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class Crm14AttachmentService {
    private final CrmFormCRM14AttachmentStoreRepository crmFormCRM14AttachmentStoreRepository;
    public List<CrmFormCRM14AttachmentStoreModel> getAttachments(Long usn) throws JSONException {
        return crmFormCRM14AttachmentStoreRepository.findByUSN(usn);
    }
}