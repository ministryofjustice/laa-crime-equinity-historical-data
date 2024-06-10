package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm14LegalRepUseDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm14DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm14Mapper extends CrmMapper {

    // TODO - check if this field can be Enum
    @Mapping(target="legalRepresentativeUse.dateStamp.usn", source="datestamp_usn")
    @Mapping(target="legalRepresentativeUse.dateStamp.date", source="datestamp_date")
    @Mapping(target="legalRepresentativeUse.dateStamp.time", source="datestamp_time")
    @Mapping(target="legalRepresentativeUse.dateStamp.clientName", source="surname")
    @Mapping(target="legalRepresentativeUse.dateStamp.clientDateOfBirth", source="date_of_birth")
    @Mapping(target="legalRepresentativeUse.legalRepUse", source="model")

    @Mapping(target = "aboutYouPart1", expression = "java(null)")
    @Mapping(target = "aboutYouPart2", expression = "java(null)")
    @Mapping(target = "aboutYouPartner", expression = "java(null)")
    @Mapping(target = "interestOfJusticePart1", expression = "java(null)")
    @Mapping(target = "interestOfJusticePart2", expression = "java(null)")
    @Mapping(target = "evidencePart1", expression = "java(null)")
    @Mapping(target = "evidencePart2", expression = "java(null)")
    @Mapping(target = "income", expression = "java(null)")
    @Mapping(target = "legalRepresentationDetails", expression = "java(null)")
    @Mapping(target = "aboutInformation", expression = "java(null)")
    @Mapping(target = "declarations", expression = "java(null)")
    @Mapping(target = "privacyAgree", expression = "java(null)")
    @Mapping(target = "submit", expression = "java(null)")
    Crm14DetailsDTO getDTOFromModel(Crm14DetailsModel model);

    @Mapping(target="usn", source="usn")
    @Mapping(target="urn", source="urn")
    @Mapping(target="applicationType", source="application_type")
    @Mapping(target="meansTested", source="means_tested")
    @Mapping(target="caseType", expression="java(convertCaseType(model))")
    @Mapping(target="originatingCourt", source="court_originating_display")
    @Mapping(target="courtName", source="court_name_display")
    @Mapping(target="isPriorityCase", source="priority")
    @Mapping(target="priorityCaseType.custody", source="custody")
    @Mapping(target="priorityCaseType.vulnerable", source="vulnerable")
    @Mapping(target="priorityCaseType.youth", source="youth")
    @Mapping(target="priorityCaseType.lateApplication", source="late_application_cc")
    @Mapping(target="priorityCaseType.imminentHearing", source="hearing_date_imminent")
    @Mapping(target="dateOfTrial", source="date_of_trial")
    Crm14LegalRepUseDTO getLegalUseRepDTOFromModel(Crm14DetailsModel model);

    default String convertCaseType(Crm14DetailsModel model) {
        if (model.summary) {
            return "Summary-Only";
        } else if (model.indictable) {
            return "Indictable";
        } else if (model.either_way) {
            return "Either-Way";
        } else if (model.trial_in_crown_court) {
            return "Trial already in Crown Court";
        } else if (model.committal) {
            return "Committal for Sentence";
        } else if (model.appeal_to_crown_court) {
            return "Appeal to Crown Court";
        } else if (model.appeal_no_changes) {
            return "Appeal to Crown Court and no changes";
        }

        return "Unknown";
    }
}
