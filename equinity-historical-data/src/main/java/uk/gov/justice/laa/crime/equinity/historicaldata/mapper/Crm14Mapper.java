package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.*;

@Mapper(componentModel = "spring")
public interface Crm14Mapper extends CrmMapper {

    @Mapping(target="formDetails", source="formDetails")
    @Mapping(target="evidenceFiles", source="evidenceFiles")
    Crm14FormDTO getDTOFromModel(Crm14Model model);
    @Mapping(target="privateCompany", source="private_company")
    @Mapping(target="partnerPrivateCompany", source="partner_private_company")
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
    @Mapping(target = "crm15Details", source="model")
    @Mapping(target = "legalRepresentationDetails", source = "model")
    @Mapping(target = "aboutInformation", source = "model")
    @Mapping(target = "declarations", source = "model")
    @Mapping(target = "privacyAgree", source = "privacy_agree")
    @Mapping(target = "submit", source = "last_action")
    Crm14DetailsDTO getDTODetailsFromModel(Crm14DetailsModel model);

    @Mapping(target="usn", source="usn")
    @Mapping(target="urn", source="urn")
    @Mapping(target="prevAppUsn", source="prev_app_usn")
    @Mapping(target="prevAppMaat", source="prev_app_maat")
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
    @Mapping(target="appealLodgedDate", source="appeal_lodged_date")
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
    @Mapping(target = "chargedWithAdult", source = "charged_with_adult")
    @Mapping(target = "havePartner", source = "have_partner")
    @Mapping(target = "maritalStatus", source="marital_status_2")
    @Mapping(target = "dateOfSeparation", source = "date_of_separation")
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
    @Mapping(target="witnessTrace", source = "witness_trace")
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

    @Mapping(target="chargesBrought", source="charges_brought.charges")
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

    @Mapping(target="offenceDateOn", source="offence_date_1")
    @Mapping(target="offenceDateBetweenStart", source="offence_date_2")
    @Mapping(target="offenceDateBetweenEnd", source="offence_date_3")
    @Mapping(target="charge", source="charge")
    @Mapping(target="whenOffence", source="offence_when")
    Crm14ChargesBroughtDTO getChargesBroughtDTOFromModel(Crm14ChargesModel chargesModel);



    @Mapping(target="income1.employersCrm15", source="employers_crm15")
    @Mapping(target="income1.allEmployers.you", source="employment_details.employers")
    @Mapping(target="income1.allEmployers.partner", source="partner_employment_details.employers")
    @Mapping(target="income1.selfEmployedNoOfBusinesses", source="self_employed_no_of_businesses")
    @Mapping(target="income1.businessPartnerships", source="business_partnerships_no_of")
    @Mapping(target="income1.privateCompanies", source="private_companies_no_of")
    @Mapping(target="income1.selfAssessmentTaxReceived", source="self_assessment_tax_received")
    @Mapping(target="income1.partnerSelfAssessmentTaxReceived", source="partner_self_assessment_tax_received")
    @Mapping(target="income1.childBenefit", source="child_benefit_crm15")
    @Mapping(target="income1.partnerChildBenefit", source="partner_child_benefit_crm15")
    @Mapping(target="income1.allBusinesses.you", source="business_details.businesses")
    @Mapping(target="income1.allBusinesses.partner", source="partner_business_details.businesses")
    @Mapping(target="income1.partnerSelfEmployedNoOfBusinesses", source="partner_self_employed_no_of_businesses")
    @Mapping(target="income1.partnerBusinessPartnershipsNoOf", source="partner_business_partnerships_no_of")
    @Mapping(target="income1.partnerPrivateCompaniesNoOf", source="partner_private_companies_no_of")
    @Mapping(target="income1.taxLiability", source="tax_liability")
    @Mapping(target="income1.taxLiabilityPaidEvery", source="tax_liability_paid_every")
    @Mapping(target="income1.partnerTaxLiability", source="partner_tax_liability")
    @Mapping(target="income1.partnerTaxLiabilityPaidEvery", source="partner_tax_liability_paid_every")
    @Mapping(target="income2.receivingBenefits2", source="receiving_benefits_2")
    @Mapping(target="income2.partnerReceivingBenefits2", source="partner_receiving_benefits_2")
    @Mapping(target="income2.statePensionCrm15", source="state_pension_crm15")
    @Mapping(target="income2.statePensionCrm15PaidEvery", source="state_pension_crm15_paid_every")
    @Mapping(target="income2.partnerStatePensionCrm15", source="partner_state_pension_crm15")
    @Mapping(target="income2.partnerStatePensionCrm15PaidEvery", source="partner_state_pension_crm15_paid_every")
    @Mapping(target="income2.childBenefitCrm15", source="child_benefit_crm15")
    @Mapping(target="income2.childBenefitCrm15PaidEvery", source="child_benefit_crm15_paid_every")
    @Mapping(target="income2.partnerChildBenefitCrm15", source="partner_child_benefit_crm15")
    @Mapping(target="income2.partnerChildBenefitCrm15PaidEvery", source="partner_child_benefit_crm15_paid_every")
    @Mapping(target="income2.taxCreditCrm15", source="tax_credit_crm15")
    @Mapping(target="income2.taxCreditCrm15PaidEvery", source="tax_credit_crm15_paid_every")
    @Mapping(target="income2.partnerTaxCreditCrm15", source="partner_tax_credit_crm15")
    @Mapping(target="income2.partnerTaxCreditCrm15PaidEvery", source="partner_tax_credit_crm15_paid_every")
    @Mapping(target="income2.universalCreditCrm15", source="universal_credit_crm15")
    @Mapping(target="income2.universalCreditCrm15PaidEvery", source="universal_credit_crm15_paid_every")
    @Mapping(target="income2.partnerUniversalCreditCrm15", source="partner_universal_credit_crm15")
    @Mapping(target="income2.partnerUniversalCreditCrm15PaidEvery", source="partner_universal_credit_crm15_paid_every")
    @Mapping(target="income2.incapacityBenefitCrm15", source="incapacity_benefit_crm15")
    @Mapping(target="income2.incapacityBenefitCrm15PaidEvery", source="incapacity_benefit_crm15_paid_every")
    @Mapping(target="income2.partnerIncapacityBenefitCrm15", source="partner_incapacity_benefit_crm15")
    @Mapping(target="income2.partnerIncapacityBenefitCrm15PaidEvery", source="partner_incapacity_benefit_crm15_paid_every")
    @Mapping(target="income2.disablementBenefitCrm15", source="disablement_benefit_crm15")
    @Mapping(target="income2.disablementBenefitCrm15PaidEvery", source="disablement_benefit_crm15_paid_every")
    @Mapping(target="income2.partnerDisablementBenefitCrm15", source="partner_disablement_benefit_crm15")
    @Mapping(target="income2.partnerDisablementBenefitCrm15PaidEvery", source="partner_disablement_benefit_crm15_paid_every")
    @Mapping(target="income2.jsaCrm15", source="jsa_crm15")
    @Mapping(target="income2.jsaCrm15PaidEvery", source="jsa_crm15_paid_every")
    @Mapping(target="income2.partnerJsaCrm15", source="partner_jsa_crm15")
    @Mapping(target="income2.partnerJsaCrm15PaidEvery", source="partner_jsa_crm15_paid_every")
    @Mapping(target="income2.otherBenefitsCrm15", source="other_benefits_crm15")
    @Mapping(target="income2.otherBenefitsCrm15PaidEvery", source="other_benefits_crm15_paid_every")
    @Mapping(target="income2.otherBenefitsCrm15Details", source="other_benefits_crm15_details")
    @Mapping(target="income2.partnerOtherBenefitsCrm15", source="partner_other_benefits_crm15")
    @Mapping(target="income2.partnerOtherBenefitsCrm15PaidEvery", source="partner_other_benefits_crm15_paid_every")
    @Mapping(target="income2.partnerOtherBenefitsCrm15Details", source="partner_other_benefits_crm15_details")
    @Mapping(target="income2.receivePension", source="receive_pension")
    @Mapping(target="income2.partnerReceivePension", source="partner_receive_pension")
    @Mapping(target="income2.totalPension", source="total_pension")
    @Mapping(target="income2.partnerTotalPension", source="partner_total_pension")
    @Mapping(target="income2.totalPensionPaidEvery", source="total_pension_paid_every")
    @Mapping(target="income2.partnerTotalPensionPaidEvery", source="partner_total_pension_paid_every")
    @Mapping(target="income2.year1", source="year_1")
    @Mapping(target="income2.year2to4", source="year_2_to_4")
    @Mapping(target="income2.year5to7", source="year_5_to_7")
    @Mapping(target="income2.year8to10", source="year_8_to_10")
    @Mapping(target="income2.year11to12", source="year_11_to_12")
    @Mapping(target="income2.year13to15", source="year_13_to_15")
    @Mapping(target="income2.year16to18", source="year_16_to_18")
    @Mapping(target="income2.receiveMaintenancePayments", source="receive_maintenance_payments")
    @Mapping(target="income2.maintenancePayment", source="maintenance_payment")
    @Mapping(target="income2.maintenancePaymentPaidEvery", source="maintenance_payment_paid_every")
    @Mapping(target="income2.partnerReceiveMaintenancePayments", source="partner_receive_maintenance_payments")
    @Mapping(target="income2.partnerMaintenance", source="partner_maintenance")
    @Mapping(target="income2.partnerMaintenancePaymentPaidEvery", source="partner_maintenance_payment_paid_every")
    @Mapping(target="income2.receiveInterest", source="receive_interest")
    @Mapping(target="income2.interest", source="interest")
    @Mapping(target="income2.interestPaidEvery", source="interest_paid_every")
    @Mapping(target="income2.partnerReceiveInterest", source="partner_receive_interest")
    @Mapping(target="income2.partnerInterest", source="partner_interest")
    @Mapping(target="income2.partnerInterestPaidEvery", source="partner_interest_paid_every")
    @Mapping(target="income2.incomeFromOtherSources", source="income_from_other_sources")
    @Mapping(target="income2.partnerIncomeFromOtherSources", source="partner_income_from_other_sources")
    @Mapping(target="income2.studentLoan", source="student_loan")
    @Mapping(target="income2.familyRent", source="family_rent")
    @Mapping(target="income2.otherRent", source="other_rent")
    @Mapping(target="income2.otherFinancialSupport", source="other_financial_support")
    @Mapping(target="income2.otherIncomeSourceFreetext", source="other_income_source_freetext")
    @Mapping(target="income2.totalAmountReceived", source="total_amount_received")
    @Mapping(target="income2.totalAmountReceivedEvery", source="total_amount_received_every")
    @Mapping(target="income2.partnerStudentLoan", source="partner_student_loan")
    @Mapping(target="income2.partnerFamilyRent", source="partner_family_rent")
    @Mapping(target="income2.partnerOtherRent", source="partner_other_rent")
    @Mapping(target="income2.partnerOtherFinancialSupport", source="partner_other_financial_support")
    @Mapping(target="income2.partnerOtherIncomeSourceFreetext", source="partner_other_income_source_freetext")
    @Mapping(target="income2.partnerTotalAmountReceived", source="partner_total_amount_received")
    @Mapping(target="income2.partnerTotalAmountReceivedEvery", source="partner_total_amount_received_every")
    @Mapping(target="income2.prevAnswersNoOtherIncome", source="prev_answers_no_other_income")
    @Mapping(target="income2.howPayBillsCrm15", source="how_pay_bills_crm15")
    @Mapping(target="landProperty.ownProperty", source="own_property")
    @Mapping(target="landProperty.partnerOwnProperty", source="partner_own_property")
    @Mapping(target="landProperty.residentialProperties", source="properties_residential")
    @Mapping(target="landProperty.commercialProperties", source="properties_commercial")
    @Mapping(target="landProperty.piecesOfLand", source="pieces_of_land")
    @Mapping(target="landProperty.partnerResidentialProperties", source="partner_properties_residential")
    @Mapping(target="landProperty.partnerCommercialProperties", source="partner_properties_commercial")
    @Mapping(target="landProperty.partnerPiecesOfLand", source="partner_pieces_of_land")
    @Mapping(target="landProperty.jointResidentialProperties", source="joint_properties_residential")
    @Mapping(target="landProperty.jointCommercialProperties", source="joint_properties_commercial")
    @Mapping(target="landProperty.jointPiecesOfLand", source="joint_pieces_of_land")
    @Mapping(target="landProperty.propertyList", source="land_and_property_table.properties")
    @Mapping(target="outgoings.usuallyLive", source="home_or_rent_payment")
    @Mapping(target="outgoings.mortgageRentAmount", source="mortgage_rent")
    @Mapping(target="outgoings.mortgageRentEvery", source="mortgage_rent_paid_every")
    @Mapping(target="outgoings.annualCouncilTax", source="council_tax")
    @Mapping(target="outgoings.councilTaxEvery", source="council_tax_paid_every")
    @Mapping(target="outgoings.boardAndLodgings", source="board_and_lodgings")
    @Mapping(target="outgoings.boardAndLodgingsEvery", source="board_and_lodgings_paid_every")
    @Mapping(target="outgoings.foodBill", source="food_bill")
    @Mapping(target="outgoings.foodBillEvery", source="food_bill_every")
    @Mapping(target="outgoings.boardAndLodgingsLandlord", source="board_and_lodgings_landlord")
    @Mapping(target="outgoings.boardAndLodgingsLandlordRelationship", source="board_lodgings_landlord_relationship")
    @Mapping(target="outgoings.childCareCosts", source="childcare_costs")
    @Mapping(target="outgoings.childCareCostsAmount", source="childcare_costs_amount")
    @Mapping(target="outgoings.childCareCostsEvery", source="childcare_costs_paid_every")
    @Mapping(target="outgoings.payMaintenance", source="pay_maintenance")
    @Mapping(target="outgoings.maintenanceAmount", source="maintenance_amount")
    @Mapping(target="outgoings.maintenanceAmountEvery", source="maintenance_amount_paid_every")
    @Mapping(target="outgoings.contributeLegalAid", source="contribute_legal_aid")
    @Mapping(target="outgoings.legalAidContribution", source="legal_aid_contribution")
    @Mapping(target="outgoings.legalAidContributionPaidEvery", source="legal_aid_contribution_paid_every")
    @Mapping(target="outgoings.legalAidContributionRef", source="legal_aid_contribution_ref")
    @Mapping(target="outgoings.paid40PercentTax", source="paid_40_percent_tax")
    @Mapping(target="outgoings.partnerPaid40PercentTax", source="partner_paid_40_percent_tax")
    @Mapping(target="outgoings.indictable", source="indictable")
    @Mapping(target="outgoings.householdOutgoingsExceedIncome", source="household_outgoings_exceed_income")
    @Mapping(target="outgoings.householdOutgoingsExceedIncomeHow", source="household_outgoings_exceed_income_how")
    @Mapping(target="savings.numBankAccounts", source="bank_accounts_crm15")
    @Mapping(target="savings.numPartnerBankAccounts", source="partner_bank_accounts_crm15")
    @Mapping(target="savings.numJointBankAccounts", source="joint_bank_accounts_crm15")
    @Mapping(target="savings.numBuildingSocietyAccount", source="building_society_account_crm15")
    @Mapping(target="savings.numPartnerBuildingSocietyAccount", source="partner_building_society_account_crm15")
    @Mapping(target="savings.numJointBuildingSocietyAccount", source="joint_building_society_account_crm15")
    @Mapping(target="savings.numCashIsa", source="cash_isa_crm15")
    @Mapping(target="savings.numPartnerCashIsa", source="partner_cash_isa_crm15")
    @Mapping(target="savings.numJointCashIsa", source="joint_cash_isa_crm15")
    @Mapping(target="savings.numNationalSavings", source="national_savings_crm15")
    @Mapping(target="savings.numPartnerNationalSavings", source="partner_national_savings_crm15")
    @Mapping(target="savings.numJointNationalSavings", source="joint_national_savings_crm15")
    @Mapping(target="savings.numOtherCashInvestments", source="other_cash_investments_crm15")
    @Mapping(target="savings.numPartnerOtherCashInvestments", source="partner_other_cash_investments_crm15")
    @Mapping(target="savings.numJointOtherCashInvestments", source="joint_other_cash_investments_crm15")
    @Mapping(target="savings.bankAccounts", source="bank_accounts.bankDetails")
    @Mapping(target="savings.salaryPaidIntoAccount", source="salary_paid_into_account")
    @Mapping(target="savings.partnerSalaryPaidIntoAccount", source="partner_salary_paid_into_account")
    @Mapping(target="savings.salaryAccount", source="salary_account")
    @Mapping(target="savings.partnerSalaryAccount", source="partner_salary_account")
    @Mapping(target="savings.premiumBonds", source="premium_bonds")
    @Mapping(target="savings.partnerPremiumBonds", source="partner_premium_bonds")
    @Mapping(target="savings.premiumBondsHolderNo", source="premium_bonds_holder_no")
    @Mapping(target="savings.partnerPremiumBondsHolderNo", source="partner_premium_bonds_holder_no")
    @Mapping(target="savings.premiumBondsTotal", source="premium_bonds_total_value")
    @Mapping(target="savings.nationalSavingsCert", source="national_savings_certificates")
    @Mapping(target="savings.partnerNationalSavingsCert", source="partner_national_savings_certificates")
    @Mapping(target="savings.savingCertificates", source="savings_certificates.certificates")
    @Mapping(target="savings.nationalSavingsCertTotal", source="national_savings_certificates_total_value")
    @Mapping(target="savings.partnerTrustFund", source="partner_trust_fund")
    @Mapping(target="savings.trustFund", source="trust_fund")
    @Mapping(target="savings.trustFundAmount", source="trust_fund_amount")
    @Mapping(target="savings.trustFundDividend", source="trust_fund_dividend")
    @Mapping(target="savings.partnerTrustFundAmount", source="partner_Trust_fund_amount")
    @Mapping(target="savings.partnerTrustFundDividend", source="partner_Trust_fund_dividend")
    @Mapping(target="savings.partnerFreezingOrderCrm15", source="partner_freezing_order_crm15")
    @Mapping(target="savings.freezingOrderCrm15", source="freezing_order_crm15")
    @Mapping(target="savings.stocks", source="stocks")
    @Mapping(target="savings.partnerStocks", source="partner_stocks")
    @Mapping(target="savings.jointStocks", source="joint_stocks")
    @Mapping(target="savings.shares", source="shares")
    @Mapping(target="savings.partnerShares", source="partner_shares")
    @Mapping(target="savings.jointShares", source="joint_shares")
    @Mapping(target="savings.peps", source="peps")
    @Mapping(target="savings.partnerPeps", source="partner_peps")
    @Mapping(target="savings.jointPeps", source="joint_peps")
    @Mapping(target="savings.shareIsa", source="share_isa")
    @Mapping(target="savings.partnerShareIsa", source="partner_share_isa")
    @Mapping(target="savings.jointShareIsa", source="joint_share_isa")
    @Mapping(target="savings.unitTrust", source="unit_trust")
    @Mapping(target="savings.partnerUnitTrust", source="partner_unit_trust")
    @Mapping(target="savings.jointUnitTrust", source="joint_unit_trust")
    @Mapping(target="savings.investmentBonds", source="investment_bonds")
    @Mapping(target="savings.partnerInvestmentBonds", source="partner_investment_bonds")
    @Mapping(target="savings.jointInvestmentBonds", source="joint_investment_bonds")
    @Mapping(target="savings.otherLumpSum", source="other_lump_sum")
    @Mapping(target="savings.partnerOtherLumpSum", source="partner_other_lump_sum")
    @Mapping(target="savings.jointOtherLumpSum", source="joint_other_lump_sum")
    @Mapping(target="savings.investments", source="investments_table.investments")
    @Mapping(target="savings.ownCar", source="own_car")
    @Mapping(target="savings.carRegs", source="car_reg_table.cars")
    Crm15DetailsDTO getCrm15IncomeDetailsDTOFromModel(Crm14DetailsModel chargesModel);

    @Mapping(target="salaryPaidEvery", source="salary_paid_every")
    @Mapping(target="salary", source="salary")
    @Mapping(target="salaryTax", source="salary_tax")
    @Mapping(target="employersPostcode", source="employers_postcode")
    @Mapping(target="otherDeductionPaidEvery", source="other_deduction_paid_every")
    @Mapping(target="nameAndJobTitleDisplay", source="name_and_job_title_display")
    @Mapping(target="otherDeduction", source="deductions_display")
    @Mapping(target="salaryDisplay", source="salary_display")
    @Mapping(target="employersAddressLine2", source="employers_address_2")
    @Mapping(target="employersAddressLine3", source="employers_address_3")
    @Mapping(target="incomeTax", source="income_tax")
    @Mapping(target="nationalInsurance", source="national_insurance")
    @Mapping(target="jobTitle", source="job_title")
    @Mapping(target="incomeTaxPaidEvery", source="income_tax_paid_every")
    @Mapping(target="employersName", source="employers_name")
    @Mapping(target="employersAddressLine1", source="employers_address_1")
    @Mapping(target="nationalInsurancePaidEvery", source="national_insurance_paid_every")
    @Mapping(target="otherDeductionDetails", source="other_deduction_details")
    @Mapping(target="addressDisplay", source="address_display")
    Crm15EmployerDTO getCrm15EmployerDTOFromModel(Crm15EmployerDetailsModel employersModel);

    @Mapping(target="drawingsEvery", source="drawings_every")
    @Mapping(target="previousFinancialsDisplay", source="previous_financials_display")
    @Mapping(target="directorShareIncomeDisplay", source="directors_share_income_display")
    @Mapping(target="tradingAddressLine3", source="trading_address_3")
    @Mapping(target="tradingAddressLine2", source="trading_address_2")
    @Mapping(target="tradingAddressLine1", source="trading_address_1")
    @Mapping(target="profitEvery", source="profit_every")
    @Mapping(target="businessWithOther", source="in_business_with_other")
    @Mapping(target="businessWithName", source="in_business_with_name")
    @Mapping(target="businessType", source="business_type")
    @Mapping(target="businessOtherEmployees", source="business_other_employees")
    @Mapping(target="turnover", source="turnover")
    @Mapping(target="profit", source="profit")
    @Mapping(target="businessNature", source="business_nature")
    @Mapping(target="businessDetailsDisplay", source="business_details_display")
    @Mapping(target="tradingAddressPostcode", source="trading_address_postcode")
    @Mapping(target="tradingName", source="trading_name")
    @Mapping(target="turnoverEvery", source="turnover_every")
    @Mapping(target="shareSales", source="share_sales")
    @Mapping(target="remuneration", source="remuneration")
    @Mapping(target="tradingDetailsDisplay", source="trading_details_display")
    @Mapping(target="percentProfits", source="percent_of_profits")
    @Mapping(target="drawings", source="drawings")
    @Mapping(target="businessEmployeesNo", source="business_no_of_employees")
    @Mapping(target="businessTradingDate", source="business_trading_date")
    Crm15BusinessesDTO getCrm15BusinessesDTOFromModel(Crm15BusinessDetailsModel businessesModel);


    @Mapping(target="bedroomsResidentialProperty", source="residential_property_bedrooms")
    @Mapping(target="propertyNotHomeAddress3", source="property_not_home_address_3")
    @Mapping(target="propertyNotHomeAddress2", source="property_not_home_address_2")
    @Mapping(target="propertyNotHomeAddress1", source="property_not_home_address_1")
    @Mapping(target="percentOwned", source="percent_owned")
    @Mapping(target="partnerPercentOwned", source="partner_percent_owned")
    @Mapping(target="commercialPropertyUsedFor", source="commercial_property_used_for")
    @Mapping(target="estimatedMarketValue", source="estimated_market_value")
    @Mapping(target="usualHomeAddress", source="usual_home_address")
    @Mapping(target="partnerUsualHomeAddress", source="partner_usual_home_address")
    @Mapping(target="addressListedBelow", source="address_listed_below")
    @Mapping(target="mortgageDisplay", source="mortgage_display")
    @Mapping(target="otherOwnersName2", source="other_owners_name_2")
    @Mapping(target="otherOwnersName1", source="other_owners_name_1")
    @Mapping(target="otherOwnersRelationship1", source="other_owners_relationship_other_1")
    @Mapping(target="otherOwnersRelationship2", source="other_owners_relationship_other_2")
    @Mapping(target="residentialCommercialLandDetailsDisplay", source="residential_commercial_land_details_display")
    @Mapping(target="mortgageToPay", source="mortgage_to_pay")
    @Mapping(target="residentialPropertyType", source="residential_property_type")
    @Mapping(target="landpAddressIndicatorDisplay", source="l_and_p_address_indicator_display")
    @Mapping(target="ownershipDetailsDisplay", source="ownership_details_display")
    @Mapping(target="otherOwnersRelationshipOther2", source="other_owners_relationship_other_2")
    @Mapping(target="otherOwnersRelationshipOther1", source="other_owners_relationship_other_1")
    @Mapping(target="landUse", source="land_use")
    @Mapping(target="otherPropertyType", source="other_property_type")
    @Mapping(target="type", source="type")
    @Mapping(target="lAndpAddressDisplay", source="l_and_p_address_display")
    @Mapping(target="propertyNotHomePostcode", source="property_not_home_postcode")
    Crm15PropertyDTO getCrm15BusinessesDTOFromModel(Crm15PropertyDetailsModel propertyModel);
    @Mapping(target="carRegNumber", source="car_reg_numbers")
    Crm15CarRegistrationDTO getCrm15CarRegDTOFromModel(Crm15CarRegDetailsModel carRegModel);

    @Mapping(target="investmentType", source="investment")
    @Mapping(target="investmentInfo", source="the_investment")
    @Mapping(target="investmentValue", source="value")
    @Mapping(target="investmentHolder", source="investment_holder")
    Crm15InvestmentDTO getInvestmentDTOFromModel(Crm15InvestmentDetailsModel invModel);

    @Mapping(target="customerNumber", source="customer_number")
    @Mapping(target="certNumber", source="cert_number")
    @Mapping(target="youOrPartner", source="you_or_partner")
    Crm15SavingCertificateDTO getInvestmentDTOFromModel(Crm15SavingsCertDetailsModel savingsCertModel);

    @Mapping(target="accountHolder", source="account_holder")
    @Mapping(target="bank", source="bank")
    @Mapping(target="sortCode", source="sort_code_brancg")
    @Mapping(target="accountType", source="account_type")
    @Mapping(target="accountNumber", source="account_number")
    @Mapping(target="overdrawn", source="overdrawn")
    @Mapping(target="balance", source="balance")
    Crm15BankAccountDTO getInvestmentDTOFromModel(Crm15BankAccountDetailsModel bankAccModel);
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
