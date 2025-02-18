package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormSummaryRepository;

@Service
@Profile("archive")
@RequiredArgsConstructor
public class ArchiveCrmFormService {

    private final CrmFormSummaryRepository repository;

    @Scheduled(cron = "${scheduled.archiveCrmFormData.cron}")
    @Transactional
    public void archiveCrmFormData() {
         repository.archiveCrmFormData();
    }
}
