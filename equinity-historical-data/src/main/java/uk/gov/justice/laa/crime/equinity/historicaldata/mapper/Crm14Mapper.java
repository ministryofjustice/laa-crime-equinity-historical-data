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
    @Mapping(target = "aboutYouPartner", source = "model")
    @Mapping(target = "interestOfJusticePart1",  source = "model")
    @Mapping(target = "interestOfJusticePart2",  source = "model")
    @Mapping(target = "evidencePart1", source = "model")
    @Mapping(target = "evidencePart2", expression = "java(null)")
    @Mapping(target = "income", source = "model")
    @Mapping(target = "legalRepresentationDetails", source = "model")
    @Mapping(target = "aboutInformation", source = "model")
    @Mapping(target = "declarations", source = "model")
    @Mapping(target = "privacyAgree", source = "privacy_agree")
    @Mapping(target = "submit", source = "last_action")

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
    @Mapping(target = "applicantFullName", source="user_signed_name")
    @Mapping(target = "applicantSignedDate", source="user_signed_date")
    @Mapping(target = "partnerFullName", source="partner_sign_fullname")
    @Mapping(target = "partnerSignedDate", source="partner_sign_date")
    @Mapping(target = "legalRepFullName", source="legal_rep_fullname")
    @Mapping(target = "legalRepSignedDate", source="legal_rep_sign_date")
    @Mapping(target = "legalRepAccountNum", source="legal_rep_laa_account")
    Crm14DeclarationsDTO getDeclarationsDTOFromModel(Crm14DetailsModel model);

    @Mapping(target = "ethnicity.white.british", source="british")
    @Mapping(target = "ethnicity.white.irish", source="british")
    @Mapping(target = "ethnicity.white.whiteOther", source="white_other")
    @Mapping(target = "ethnicity.mixed.whiteCaribbean", source="white_and_black_caribbean")
    @Mapping(target = "ethnicity.mixed.whiteAsian", source="white_and_asian")
    @Mapping(target = "ethnicity.mixed.whiteAfrican", source="white_and_black_african")
    @Mapping(target = "ethnicity.mixed.mixedOther", source="mixed_other")
    @Mapping(target = "ethnicity.asian.indian", source="indian")
    @Mapping(target = "ethnicity.asian.pakistani", source="pakistani")
    @Mapping(target = "ethnicity.asian.bangladeshi", source="bangladeshi")
    @Mapping(target = "ethnicity.asian.asianOther", source="asian_other")
    @Mapping(target = "ethnicity.black.blackCaribbean", source="black_caribbean")
    @Mapping(target = "ethnicity.black.blackAfrican", source="black_african")
    @Mapping(target = "ethnicity.black.blackOther", source="black_other")
    @Mapping(target = "ethnicity.other.chinese", source="chinese")
    @Mapping(target = "ethnicity.other.gypsy", source="gypsy_or_traveller")
    @Mapping(target = "ethnicity.other.other", source="other_ethnicity")
    @Mapping(target = "ethnicity.other.dontSay", source="prefer_not_to_say_ethnicity")
    @Mapping(target = "disabledDefinition", source="disabled_definition")
    @Mapping(target = "gender", source="gender")
    @Mapping(target = "disabled", source="disabled")
    Crm14AboutInfoDTO getAboutInfoDTOFromModel(Crm14DetailsModel model);

    @Mapping(target = "solicitorApplyOffice", source="firm_office")
    @Mapping(target = "solicitorAccountNum", source="legal_rep_laa_account")
    @Mapping(target = "title", source="solicitor_title")
    @Mapping(target = "solicitorFullName", source="legal_rep_fullname")
    @Mapping(target = "firmName", source="solicitor_firm")
    @Mapping(target="homeAddress.addressLine1", source="solicitor_address_1")
    @Mapping(target="homeAddress.addressLine2", source="solicitor_address_2")
    @Mapping(target="homeAddress.addressLine3", source="solicitor_address_3")
    @Mapping(target="homeAddress.postCode", source="solicitor_postcode")
    @Mapping(target = "landLineNumber", source="solicitor_phone_landline")
    @Mapping(target = "mobileNumber", source="solicitor_phone_mobile")
    @Mapping(target = "documentExchange", source="solicitor_firm_dx")
    @Mapping(target = "fax", source="solicitor_fax")
    @Mapping(target = "emailId", source="solicitor_email")
    @Mapping(target = "adminEmailId", source="firm_administrator_email")
    @Mapping(target = "declarationStatement", source="legal_rep_declaration")
    @Mapping(target = "declarationConfirm", source="legal_rep_declaration_confirm")
    @Mapping(target = "signDate", source="legal_rep_sign_date")
    Crm14LegalRepresentationDTO getLegalRepresentationDTOFromModel(Crm14DetailsModel model);

    @Mapping(target="partnerDetails.title", source="partner_title")
    @Mapping(target="partnerDetails.otherTitle", source = "partner_title_other")
    @Mapping(target="partnerDetails.clientForeName", source="partner_forenames")
    @Mapping(target="partnerDetails.clientOtherNames", source="partner_other_names")
    @Mapping(target="partnerDetails.clientSurname", source="partner_surname")
    @Mapping(target="partnerDetails.clientDateOfBirth", source="partner_date_of_birth")
    @Mapping(target="partnerDetails.nationalInsurance", source="partner_ni")
    @Mapping(target="partnerDetails.applicationRegistrationCard", source="partner_arc")
    @Mapping(target="partnerDetails.welshCorrespondence", expression = "java(null)")
    @Mapping(target="homeAddress.addressLine1", source = "partner_usual_address_1")
    @Mapping(target="homeAddress.addressLine2", source = "partner_usual_address_2")
    @Mapping(target="homeAddress.addressLine3", source = "partner_usual_address_3")
    @Mapping(target="homeAddress.postCode", source = "partner_usual_postcode")
    @Mapping(target="coDefendant", source = "any_codefendants")
    @Mapping(target="partnerDifferentHome", source = "partner_different_home")
    @Mapping(target="conflictOfInterest", source = "partner_conflict_of_interest")
    Crm14APartnerDetailsDTO getPartnerDetailsDTOFromModel(Crm14DetailsModel model);

    @Mapping(target="loseLibertyDetails", source = "lose_liberty_details")
    @Mapping(target="suspendedSentenceDetails", source = "suspended_sentence_details")
    @Mapping(target="loseLivelihoodDetails", source = "lose_livelihood_details")
    @Mapping(target="damageReputationDetails", source = "damage_reputation_details")
    @Mapping(target="questionLawDetails", source = "question_of_law_details")
    @Mapping(target="ownCaseDetails", source = "cant_present_own_case_details")
    @Mapping(target="witnessTraceDetails", source = "witness_trace_details")
    @Mapping(target="expertExamDetails", source = "expert_cross_exam_details")
    @Mapping(target="interestsAnotherDetails", source = "interests_of_another_details")
    @Mapping(target="otherReasonRepresentedDetails", source = "other_reason_to_be_represented_details")
    Crm14InterestOfJusticePart2DTO getInterestOfJusticePart2DTOFromModel(Crm14DetailsModel model);

    @Mapping(target="receiveBenefits", source = "receive_benefits")
    @Mapping(target="proofBenefits", source = "do_you_have_proof")
    @Mapping(target="benefits.you.incomeSupport", source = "income_support")
    @Mapping(target="benefits.you.esa", source = "esa")
    @Mapping(target="benefits.you.statePension", source = "state_pension")
    @Mapping(target="benefits.you.jsa", source = "jsa")
    @Mapping(target="benefits.partner.incomeSupport", source = "income_support_partner")
    @Mapping(target="benefits.partner.esa", source = "esa_partner")
    @Mapping(target="benefits.partner.statePension", source = "state_pension_partner")
    @Mapping(target="benefits.partner.jsa", source = "jsa_partner")
    Crm14IncomeDTO getIncomeDTOFromModel(Crm14DetailsModel model);


    @Mapping(target="chargesBrought", expression = "java(null)")
    @Mapping(target="offenceType", source = "offence_type")
    @Mapping(target="anyDefendants", source = "any_codefendants")
    @Mapping(target="defendantDetails", source = "codefendants_details")
    @Mapping(target="notSameSolicitor", source = "why_not_rep_same_solicitor")
    @Mapping(target="notSameSolicitorReason", source = "why_not_rep_same_solicitor_reasons")
    @Mapping(target="otherCases", source = "other_cases")
    @Mapping(target="otherCaseCharges.you.charges", source = "other_case_charges")
    @Mapping(target="otherCaseCharges.you.court", source = "other_case_court")
    @Mapping(target="otherCaseCharges.you.nextHearing", source = "other_case_next_hearing")
    @Mapping(target="otherCaseCharges.partner.charges", source = "partner_other_case_charges")
    @Mapping(target="otherCaseCharges.partner.court", source = "partner_other_case_court")
    @Mapping(target="otherCaseCharges.partner.nextHearing", source = "partner_other_case_next_hearing")
    @Mapping(target="laCourt", source = "court_name_display")
    @Mapping(target="laCourtNextHearing", source = "hearing_date")
    @Mapping(target="proceedingsConcluded", source = "proceedings_already_concluded")
    @Mapping(target="proceedingsConcludedNotes", source = "proceedings_already_concluded_notes")
    Crm14InterestOfJusticePart1DTO getInterestOfJusticePart1DTOFromModel(Crm14DetailsModel model);
    @Mapping(target="remandedInCustody", source = "remanded_in_custody")
    @Mapping(target="remandedDate", source = "remanded_in_custody_date")
    @Mapping(target="heardInMagistrateCourt", source = "heard_in_magistrates_court")
    @Mapping(target="employed", source = "employed")
    @Mapping(target="employmentCeased", source = "employment_ceased_3_months")
    @Mapping(target="lostJobDuetoCustody", source = "lost_job_due_to_custody")
    @Mapping(target="lostJobDuetoCustodyDate", source = "lost_job_due_to_custody_date")
    @Mapping(target="partnerEmployed", source = "partner_employed")
    Crm14EvidencePart1DTO getEvidencePart1DTOFromModel(Crm14DetailsModel model);



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