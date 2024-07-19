package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14AttachmentModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.AttachmentStoreRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class Crm14AttachmentService {
    private final AttachmentStoreRepository attachmentStoreRepository;
    public List<Crm14AttachmentModel> getCrm14Attachments(Long usn) throws JSONException {
        return attachmentStoreRepository.findByUSN(usn);
    }
}