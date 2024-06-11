package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.*;
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
    @Mapping(target = "aboutYouPart1.hasHomeAddress", source = "have_home_address")
    @Mapping(target = "aboutYouPart1.aboutYou", source = "model")
    @Mapping(target = "aboutYouPart1.contactDetails", source = "model")
    @Mapping(target = "aboutYouPart2", source = "model")
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

    @Mapping(target="title", source="title")
    @Mapping(target="otherTitle", expression = "java(null)")
    @Mapping(target="clientForeName", source="forenames")
    @Mapping(target="clientOtherNames", source="other_names")
    @Mapping(target="clientSurname", source="surname")
    @Mapping(target="clientDateOfBirth", source="date_of_birth")
    @Mapping(target="nationalInsurance", source="ni_number")
    @Mapping(target="applicationRegistrationCard", source="arc_number")
    @Mapping(target="welshCorrespondence", source="welsh_corr_flag")
    Crm14AboutYouDTO getAboutYouDTOFromModel(Crm14DetailsModel model);


    @Mapping(target="homeAddress.addressLine1", source="home_address_1")
    @Mapping(target="homeAddress.addressLine2", source="home_address_2")
    @Mapping(target="homeAddress.addressLine3", source="home_address_3")
    @Mapping(target="homeAddress.postCode", source="home_postcode")
    @Mapping(target="correspondenceType", source="what_contact_address")
    @Mapping(target="correspondenceAddress.addressLine1", source="contact_address_1")
    @Mapping(target="correspondenceAddress.addressLine2", source="contact_address_2")
    @Mapping(target="correspondenceAddress.addressLine3", source="contact_address_3")
    @Mapping(target="correspondenceAddress.postCode", source="contact_postcode")
    @Mapping(target="emailId", source="email")
    @Mapping(target="phoneNumber", source="phone_landline")
    @Mapping(target="mobileNumber", source="phone_mobile")
    @Mapping(target="workPhoneNumber", source="phone_work")
    Crm14ContactDetailsDTO getContactDetailsDTOFromModel(Crm14DetailsModel model);

    @Mapping(target = "homeAddressType", source = "home_address_type")
    @Mapping(target = "relationshipToHomeOwner", source = "relationship_to_home_owner")
    @Mapping(target = "under18", source = "under_18")
    @Mapping(target = "havePartner", source = "have_partner")
    @Mapping(target = "maritalStatus", source="marital_status_2")
    Crm14AboutYouPart2DTO getAboutYouPart2DTOFromModel(Crm14DetailsModel model);


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
