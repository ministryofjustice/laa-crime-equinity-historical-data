package uk.gov.justice.laa.crime.equinity.historicaldata.archive.service;

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
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.model.CrmFormArchiveModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.archive.repository.CrmFormsArchiveRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.config.CrmFormSearchCriteriaDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchCrmFormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchArchiveServiceTest {
    private static final Integer CRM4_TYPE_ID = 1;
    private static final String CRM4_TYPE_NAME = "CRM4";
    private static final Integer CRM5_TYPE_ID = 4;
    private static final String CRM5_TYPE_NAME = "CRM5";
    private static final Integer CRM7_TYPE_ID = 5;
    private static final String ACCEPTED_TYPES_DEFAULT = "1,4,5";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFormsArchiveRepository searchRepository;

    @Autowired
    SearchArchiveService searchService;

    @BeforeAll
    void preTest() {
        CrmFormArchiveModel searchModel = new CrmFormArchiveModel();
        searchModel.setUSN("1826829");
        searchModel.setClientName("Mock Client");
        searchModel.setTypeId(CRM4_TYPE_ID);
        searchModel.setType(CRM4_TYPE_NAME);
        searchRepository.save(searchModel);

        searchModel.setUSN("1826830");
        searchModel.setClientName("Mock Client Name");
        searchModel.setTypeId(CRM4_TYPE_ID);
        searchModel.setType(CRM4_TYPE_NAME);
        searchRepository.save(searchModel);

        searchModel.setUSN("1826831");
        searchModel.setClientName("Mocked Client Name");
        searchModel.setTypeId(CRM5_TYPE_ID);
        searchModel.setType(CRM5_TYPE_NAME);
        searchRepository.save(searchModel);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    @Test
    void searchAllByCriteriaTest_GivenExistingFullUsnShouldReturnSingleForm() {
        String usn = "1826829";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(usn, null,null, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(1);
        softly.assertThat(results.getResults().get(0)).isInstanceOf(SearchCrmFormDTO.class);
        softly.assertThat(results.getResults().get(0).getUsn()).isEqualTo(usn);
    }

    @Test
    void searchAllByCriteriaTest_GivenExistingPartialUsnShouldReturnMultipleForms() {
        String usn = "18268";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(usn, null,null, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isGreaterThan(1);


        softly.assertThat(results.getResults().get(0)).isInstanceOf(SearchCrmFormDTO.class);
        softly.assertThat(results.getResults().get(0).getUsn()).isEqualTo("1826829");
        softly.assertThat(results.getResults().get(1).getUsn()).isEqualTo("1826830");
    }

    @Test
    void searchAllByCriteriaTest_GivenNonExistingUsnShouldReturnResourceNotFoundException() {
        String usn = "1826832";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(usn, null,null, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        String expectedMessage = "No Tasks were found";

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }

    /**
     * Search by Type
     */
    @Test
    void searchAllByCriteriaTest_WhenTypeIsGivenExistingInMultipleThenShouldReturnMultipleForms() {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, CRM4_TYPE_ID,null, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isGreaterThan(1);
        results.getResults().forEach(result -> {
                    softly.assertThat(result).isInstanceOf(SearchCrmFormDTO.class);
                    softly.assertThat(result.getType()).isEqualTo(CRM4_TYPE_NAME);
                }
        );
    }

    @Test
    void searchAllByCriteriaTest_WhenTypeIsGivenExistsUniqueMultipleThenShouldReturnSingleForm() {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, CRM5_TYPE_ID,null, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(1);
        results.getResults().forEach(result -> {
                softly.assertThat(result).isInstanceOf(SearchCrmFormDTO.class);
                softly.assertThat(result.getType()).isEqualTo(CRM5_TYPE_NAME);
            }
        );
    }

    @Test
    void searchAllByCriteriaTest_WhenTypeIsGivenNonExistingValueThenShouldReturnResourceNotFoundException() {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, CRM7_TYPE_ID,null, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        String expectedMessage = "No Tasks were found";

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }

    /**
     * Search by Client Name
     */
    @Test
    void searchAllByCriteriaTest_WhenClientNameIsGivenExistingInMultipleThenShouldReturnMultipleForms() {
        String client = "Mock Client";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, client, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isGreaterThan(1);
        results.getResults().forEach(result -> {
                    softly.assertThat(result).isInstanceOf(SearchCrmFormDTO.class);
                    softly.assertThat(result.getClientName()).contains("Mock Client");
                }
        );
    }

    @Test
    void searchAllByCriteriaTest_WhenClientNameIsGivenExistsUniqueMultipleThenShouldReturnSingleForm() {
        String client = "Mock Client Name";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null,client, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(1);
        results.getResults().forEach(result -> {
                    softly.assertThat(result).isInstanceOf(SearchCrmFormDTO.class);
                    softly.assertThat(result.getClientName()).isEqualTo("Mock Client Name");
                }
        );
    }

    @Test
    void searchAllByCriteriaTest_WhenClientNameIsGivenNonExistingValueThenShouldReturnResourceNotFoundException() {
        String client = "Fake Client";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null,client, null, null, null, null, null, null, ACCEPTED_TYPES_DEFAULT);
        String expectedMessage = "No Tasks were found";

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }
}