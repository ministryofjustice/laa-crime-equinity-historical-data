package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormSummaryRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArchiveCrmFormServiceTest {

    @Mock
    private CrmFormSummaryRepository mockRepository;

    @Test
    void shouldArchiveCrmFormData() {
        ArchiveCrmFormService archiveCrmFormService = new ArchiveCrmFormService(mockRepository);
        archiveCrmFormService.archiveCrmFormData();

        verify(mockRepository).archiveCrmFormData();
    }
}