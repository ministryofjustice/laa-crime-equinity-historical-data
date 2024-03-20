package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormsViewModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.SearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormsViewRepository;

import java.util.List;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchServiceTest {
    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFormsViewRepository searchRepository;

    @Autowired
    SearchService searchService;

    @BeforeAll
    void preTest() {
        CrmFormsViewModel searchModel1 = new CrmFormsViewModel();
        searchModel1.setUSN("1826829");
        searchModel1.setClientName("Mock Client");
        searchRepository.save(searchModel1);

        CrmFormsViewModel searchModel2 = new CrmFormsViewModel();
        searchModel2.setUSN("1826830");
        searchModel2.setClientName("Mock Client Name");
        searchRepository.save(searchModel2);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void searchAllByCriteriaTest_GivenExistingFullUsnShouldReturnSingleForm() {
        String usn = "1826829";
        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO(usn, null, null, null, null, null);
        List<CrmFormsViewModel> results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results).isInstanceOf(List.class);
        softly.assertThat(results.size()).isEqualTo(1);
        softly.assertThat(results.get(0)).isInstanceOf(CrmFormsViewModel.class);
        softly.assertThat(results.get(0).getUSN()).isEqualTo(usn);
    }

    @Test
    void searchAllByCriteriaTest_GivenExistingPartialUsnShouldReturnMultipleForms() {
        String usn = "18268";
        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO(usn, null, null, null, null, null);
        List<CrmFormsViewModel> results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isNotEmpty();
        softly.assertThat(results).isInstanceOf(List.class);
        softly.assertThat(results.size()).isGreaterThan(1);
        softly.assertThat(results.get(0)).isInstanceOf(CrmFormsViewModel.class);
        softly.assertThat(results.get(0).getUSN()).isEqualTo("1826829");
        softly.assertThat(results.get(1).getUSN()).isEqualTo("1826830");
    }

    @Test
    void searchAllByCriteriaTest_GivenNonExistingUsnShouldReturnResourceNotFoundException() {
        String usn = "1826831";
        SearchCriteriaDTO searchCriteria = new SearchCriteriaDTO(usn, null, null, null, null, null);
        String expectedMessage = "No Tasks were found";

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }
}