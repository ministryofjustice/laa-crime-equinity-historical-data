package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.Crm14PSEMessageModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.Crm14PSEMessageRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class Crm14PSEMessagesService {
    private final Crm14PSEMessageRepository pseMessageRepository;
    public List<Crm14PSEMessageModel> getMessages(Long usn) throws JSONException {
        return pseMessageRepository.findAllByUSN(usn);
    }
}