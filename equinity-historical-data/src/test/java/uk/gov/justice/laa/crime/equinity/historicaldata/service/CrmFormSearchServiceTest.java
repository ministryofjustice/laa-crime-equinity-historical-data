package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchCrmFormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.SearchResultDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.data.CrmFormSummaryModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.CrmFormSummaryRepository;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.criteria.input.CrmFormSearchCriteriaDTO;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmFormSearchServiceTest {
    private static final Integer CRM4_TYPE_ID = 1;
    private static final String CRM4_TYPE_NAME = "CRM4";
    private static final Integer CRM5_TYPE_ID = 4;
    private static final String CRM5_TYPE_NAME = "CRM5";
    private static final Integer CRM7_TYPE_ID = 5;
    private static final String ACCEPTED_TYPES_DEFAULT = "1,4,5";

    @InjectSoftAssertions
    private SoftAssertions softly;

    @Autowired
    CrmFormSummaryRepository searchRepository;

    @Autowired
    CrmFormSearchService searchService;

    @BeforeAll
    void preTest() {
        CrmFormSummaryModel searchModel = new CrmFormSummaryModel();
        searchModel.setUSN("1826829");
        searchModel.setClientName("Mock Client");
        searchModel.setClientDoB("1977-08-01");
        searchModel.setTypeId(CRM4_TYPE_ID);
        searchModel.setType(CRM4_TYPE_NAME);
        searchModel.setOriginatedDate("2024-02-12 16:47:12.29");
        searchModel.setSubmittedDate("2024-02-15 16:47:12.29");
        searchRepository.save(searchModel);

        searchModel.setUSN("1826830");
        searchModel.setClientName("Mock Client Name");
        searchModel.setClientDoB("1977-01-08");
        searchModel.setTypeId(CRM4_TYPE_ID);
        searchModel.setType(CRM4_TYPE_NAME);
        searchModel.setOriginatedDate("2024-02-11 16:47:12.29");
        searchModel.setSubmittedDate("2024-02-14 16:46:12.29");
        searchRepository.save(searchModel);

        searchModel.setUSN("1826831");
        searchModel.setClientName("Mocked Client Name");
        searchModel.setClientDoB("1977-01-08");
        searchModel.setTypeId(CRM5_TYPE_ID);
        searchModel.setType(CRM5_TYPE_NAME);
        searchModel.setOriginatedDate("2024-02-10 16:47:12.29");
        searchModel.setSubmittedDate("2024-02-14 16:45:12.29");
        searchRepository.save(searchModel);
    }

    @AfterAll
    void postTest() {
        softly.assertAll();
    }

    /**
     * Search by USN
     */
    @Test
    void searchAllByCriteriaTest_GivenExistingFullUsnShouldReturnSingleForm() {
        String usn = "1826829";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(usn, null, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(1);
        softly.assertThat(results.getResults().get(0)).isInstanceOf(SearchCrmFormDTO.class);
        softly.assertThat(results.getResults().get(0).getUsn()).isEqualTo(usn);
    }

    @Test
    void searchAllByCriteriaTest_GivenExistingPartialUsnShouldReturnMultipleForms() {
        String usn = "18268";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(usn, null, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isGreaterThan(1);

        softly.assertThat(results.getResults().get(0)).isInstanceOf(SearchCrmFormDTO.class);
        softly.assertThat(results.getResults().get(0).getUsn()).isEqualTo("1826829");
        softly.assertThat(results.getResults().get(1).getUsn()).isEqualTo("1826830");
    }

    @Test
    void searchAllByCriteriaTest_GivenNonExistingUsnShouldReturnResourceNotFoundException() { // capability
        String usn = "1826832";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(usn, null, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No Tasks were found");
    }

    /**
     * Search by Type
     */
    @Test
    void searchAllByCriteriaTest_WhenTypeIsGivenExistingInMultipleThenShouldReturnMultipleForms() {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, CRM4_TYPE_ID, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isGreaterThan(1);
        results.getResults().forEach(result -> softly.assertThat(result.getType()).isEqualTo(CRM4_TYPE_NAME));
    }


    @Test
    void searchAllByCriteriaTest_WhenTypeIsGivenExistsUniqueMultipleThenShouldReturnSingleForm() {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, CRM5_TYPE_ID, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(1);
        results.getResults().forEach(result -> softly.assertThat(result.getType()).isEqualTo(CRM5_TYPE_NAME));
    }

    @Test
    void searchAllByCriteriaTest_WhenTypeIsGivenNonExistingValueThenShouldReturnResourceNotFoundException() {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, CRM7_TYPE_ID, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No Tasks were found");
    }

    /**
     * Search by Client Name
     */
    @Test
    void searchAllByCriteriaTest_WhenClientNameIsGivenExistingInMultipleThenShouldReturnMultipleForms() {
        String client = "Mock Client";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, client, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isGreaterThan(1);
        results.getResults().forEach(result -> softly.assertThat(result.getClientName()).contains("Mock Client"));
    }

    @Test
    void searchAllByCriteriaTest_WhenClientNameIsGivenExistsUniqueMultipleThenShouldReturnSingleForm() {
        String client = "Mock Client Name";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, client, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(1);
        results.getResults().forEach(result -> {
                    softly.assertThat(result.getClientName()).isEqualTo("Mock Client Name");
                }
        );
    }

    @Test
    void searchAllByCriteriaTest_WhenClientNameIsGivenNonExistingValueThenShouldReturnResourceNotFoundException() {
        String client = "Fake Client";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, client, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No Tasks were found");
    }

    /**
     * Search by Client Date Of Birth
     */
    @Test
    void searchAllByCriteriaTest_WhenClientDateOfBirthNameIsGivenNonExistingValueThenShouldReturnResourceNotFoundException() {
        String dateToTest = "1978-03-04";
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, null, dateToTest,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        // execute
        softly.assertThatThrownBy(() -> searchService.searchAllByCriteria(searchCriteria))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No Tasks were found");
    }

    @ParameterizedTest
    @MethodSource("inputForClientDateOfBirthTest")
    void searchAllByCriteriaTest_WhenClientDateOfBirthNameIsGivenThenShouldReturnResultsWithMatchingDate(String clientDoB, int expectedResults) {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, null, clientDoB,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(expectedResults);
    }

    private static Stream<Arguments> inputForClientDateOfBirthTest() {
        return Stream.of(
                Arguments.of("1977-01-08", 2),
                Arguments.of("1977-08-01", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("inputForSubmittedDateRangeTest")
    public void searchAllByCriteriaTest_WhenSubmittedDateIsGivenThenShouldReturnResultsWithinThatRange(String submittedFrom, String submittedTo, int expectedResult) {
        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(null, null, null, null,
                submittedFrom, submittedTo, null, null, null, ACCEPTED_TYPES_DEFAULT, null, null);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results).isInstanceOf(SearchResultDTO.class);
        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> inputForSubmittedDateRangeTest() {
        return Stream.of(
                Arguments.of("2024-02-15", "2024-02-15", 1),
                Arguments.of("2024-02-14", "2024-02-14", 2),
                Arguments.of("2024-02-14", "2024-02-15", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("inputForSortAndOrderTest")
    void searchAllByCriteriaTest_WhenSortAndOrderIsGivenThenShouldReturnSortedResults(String sort, String order, List<String> expectedUsnOrder) {
        String partialUsn = "18268";

        CrmFormSearchCriteriaDTO searchCriteria = new CrmFormSearchCriteriaDTO(partialUsn, null, null, null,
                null, null, null, null, null, ACCEPTED_TYPES_DEFAULT, sort, order);

        SearchResultDTO results = searchService.searchAllByCriteria(searchCriteria);

        softly.assertThat(results.getResults()).isNotEmpty();
        softly.assertThat(results.getResults().size()).isEqualTo(3);

        softly.assertThat(results.getResults().get(0).getUsn()).isEqualTo(expectedUsnOrder.get(0));
        softly.assertThat(results.getResults().get(1).getUsn()).isEqualTo(expectedUsnOrder.get(1));
        softly.assertThat(results.getResults().get(2).getUsn()).isEqualTo(expectedUsnOrder.get(2));
    }

    private static Stream<Arguments> inputForSortAndOrderTest() {
        return Stream.of(
                Arguments.of(null, null, List.of("1826829", "1826830",  "1826831")),
                Arguments.of("submittedDate", null, List.of("1826829", "1826830",  "1826831")),
                Arguments.of("submittedDate", "desc", List.of("1826829", "1826830",  "1826831")),
                Arguments.of("submittedDate", "asc", List.of("1826831", "1826830",  "1826829")),
                Arguments.of("originatedDate", null, List.of("1826829", "1826830",  "1826831")),
                Arguments.of("originatedDate", "desc", List.of("1826829", "1826830",  "1826831")),
                Arguments.of("originatedDate", "asc", List.of("1826831", "1826830",  "1826829"))
        );
    }

}