package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm5FormDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5Model;

@Mapper(componentModel = "spring")
public interface Crm5Mapper extends CrmMapper{
    @Mapping(target="formDetails", source="formDetails")
    @Mapping(target="evidenceFiles", source="evidenceFiles")
    Crm5FormDTO getDTOFromModel(Crm5Model model);

    @Mapping(target="detailsOfWorkCompleted", source="fd_details_of_work")
    @Mapping(target="detailsOfApplication", source="fd_additional_work")
    @Mapping(target="statementOfCase", source="description_of_case")
    @Mapping(target="hasPreviousApplication", source="prev_app")
    @Mapping(target="previousApplicationRef", source="prev_app_ref")
    @Mapping(target="appealedPrevDecision", source="appealed_prev_decision")
    @Mapping(target="appealedPrevDecisionDetails", source="appealed_prev_decision_details")
    @Mapping(target="urgencyReason", source="urgency_reason")
    @Mapping(target="firm.firmPhone", source="firm_phone")
    @Mapping(target="firm.firmAddress", source="firm_address")
    @Mapping(target="firm.firmName", source="firm_name")
    @Mapping(target="firm.firmSupplierNo", source="firm_supplier_no")
    @Mapping(target="firm.firmContactName", source="contact_name")
    @Mapping(target="firm.firmSolicitorName", source="solicitorname")
    @Mapping(target="firm.firmSolicitorRef", source="solicitorid")
    @Mapping(target="proceedings.typeOfProceedings.preCharge", source="proceedings_precharge")
    @Mapping(target="proceedings.typeOfProceedings.appealProceedings", source="proceedings_crowncourtappeal")
    @Mapping(target="proceedings.typeOfProceedings.prisonLaw", source="proceedings_prisonlaw")
    @Mapping(target="proceedings.detailsOfProceedings.dateOfNextHearing", source="proceedings_nexthearingdate")
    @Mapping(target="proceedings.detailsOfProceedings.isCounselInstructed", source="proceedings_counsel_instructed")
    @Mapping(target="caseDetails.levelOfWork", source="level_of_work")
    @Mapping(target="caseDetails.cwCriminalProceeding", source="criminal_proceeding")
    @Mapping(target="caseDetails.cwCriminalInvestigation", source="criminal_investigation")
    @Mapping(target="caseDetails.cwCcrc", source="ccrc")
    @Mapping(target="caseDetails.cwAppealsReview", source="appeals_and_review")
    @Mapping(target="caseDetails.cwPrisonLaw", source="prison_law")
    @Mapping(target="clientDetails.UFN", source="client_ufn")
    @Mapping(target="clientDetails.firstName", source="client_forename")
    @Mapping(target="clientDetails.middleName", expression = "java(null)")
    @Mapping(target="clientDetails.surname", source="client_surname")
    @Mapping(target="clientDetails.maritalStatus", source="client_marital_status")
    @Mapping(target="clientDetails.clientNfa", source="client_nfa")
    @Mapping(target="clientDetails.dateOfBirth", source="client_dob")
    @Mapping(target="clientDetails.address.postcode", source="client_address_postcode")
    @Mapping(target="clientDetails.address.addressLine1", source="client_address_line1")
    @Mapping(target="clientDetails.address.addressLine2", source="client_address_line2")
    @Mapping(target="clientDetails.address.addressLine3", expression = "java(null)")
    @Mapping(target="clientDetails.address.city", source="client_address_town")
    @Mapping(target="clientDetails.address.county", source="client_address_county")
    @Mapping(target="clientDetails.address.country", expression = "java(null)")
    @Mapping(target="adviceAssistance.transferFromSolicitor",  source="aa_within_6_months")
    @Mapping(target="adviceAssistance.adviceCriteria",  source="aa_criteria")
    @Mapping(target="adviceAssistance.laaAdviceAssistance.providedAdvice",  source="aa_cds_provided_same_matter")
    @Mapping(target="adviceAssistance.laaAdviceAssistance.notes",  source="aa_cds_provided_same_matter_reason")
    @Mapping(target="capitalDetails.isUnder18", source="client_under_18")
    @Mapping(target="capitalDetails.hasIncomeSupport",  expression = "java(null)")
    @Mapping(target="capitalDetails.numOfDependants", source="client_dependants")
    @Mapping(target="capitalDetails.clientSavings", source="client_savings")
    @Mapping(target="capitalDetails.partnerSavings", source="client_partner_savings")
    @Mapping(target="capitalDetails.totalSavings", source="client_total_savings")
    @Mapping(target="incomeDetails.hasIncomeSupport", source="id_client_state_benefit")
    @Mapping(target="incomeDetails.weeklyClientIncome", source="id_client_weekly_income")
    @Mapping(target="incomeDetails.weeklyPartnerIncome", source="id_partner_weekly_income")
    @Mapping(target="incomeDetails.weeklyIncomeWithoutDeduction", source="client_total_savings")
    @Mapping(target="incomeDetails.incomeTaxDeductions", source="id_income_tax")
    @Mapping(target="incomeDetails.niDeductions", source="id_ni_contributions")
    @Mapping(target="incomeDetails.partnerDeductions", source="id_partner_deductions")
    @Mapping(target="incomeDetails.socialFundDeductions", source="id_attendance_allowance")
    @Mapping(target="incomeDetails.dependantChildrenUnder15", source="id_dependants_under_16")
    @Mapping(target="incomeDetails.deductionUnder15", source="id_dependants_under_16_cost")
    @Mapping(target="incomeDetails.dependantChildrenOver16", source="id_dependants_16_plus")
    @Mapping(target="incomeDetails.deductionOver16", source="id_dependants_16_plus_cost")
    @Mapping(target="incomeDetails.totalDeductions", source="id_total_deductions")
    @Mapping(target="incomeDetails.totalWeeklyIncome", source="id_total_weekly_income")
    @Mapping(target="courtAppealFunding.hasCourtAppeal", source="caf_approached_for_funding")
    @Mapping(target="courtAppealFunding.appealDetails", source="caf_court_funding_details")
    @Mapping(target="courtAppealFunding.benefitOfCourt", source="caf_client_has_cop_rep_order")
    @Mapping(target="courtAppealFunding.expertReport", source="caf_for_expert_report_only")
    @Mapping(target="allCosts.accruedCosts.attendance.time", source="ctd_attendance_time")
    @Mapping(target="allCosts.accruedCosts.attendance.cost", source="ctd_attendance_cost")
    @Mapping(target="allCosts.accruedCosts.preparation.time", source="ctd_preparation_time")
    @Mapping(target="allCosts.accruedCosts.preparation.cost", source="ctd_preparation_costs")
    @Mapping(target="allCosts.accruedCosts.advocacy.time", source="ctd_advocacy_time")
    @Mapping(target="allCosts.accruedCosts.advocacy.cost", source="ctd_advocacy_costs")
    @Mapping(target="allCosts.accruedCosts.travel.time", source="ctd_travel_time")
    @Mapping(target="allCosts.accruedCosts.travel.cost", source="ctd_travel_time_costs")
    @Mapping(target="allCosts.accruedCosts.waiting.time", source="ctd_waiting_time")
    @Mapping(target="allCosts.accruedCosts.waiting.cost", source="ctd_waiting_time_cost")
    @Mapping(target="allCosts.accruedCosts.letters.total", source="ctd_letters")
    @Mapping(target="allCosts.accruedCosts.letters.cost", source="ctd_letters_cost")
    @Mapping(target="allCosts.accruedCosts.telephoneCalls.total", source="ctd_phone_calls")
    @Mapping(target="allCosts.accruedCosts.telephoneCalls.cost", source="ctd_phone_calls_cost")
    @Mapping(target="allCosts.accruedCosts.mileage.total", source="ctd_mileage_miles")
    @Mapping(target="allCosts.accruedCosts.mileage.cost", source="ctd_mileage_cost")
    @Mapping(target="allCosts.accruedCosts.otherDisbursement.cost", source="ctd_other_cost")
    @Mapping(target="allCosts.accruedCosts.totalCost.cost", source="ctd_total_costs")
    @Mapping(target="allCosts.anticipatedCosts.attendance.time", source="ac_attendance_time")
    @Mapping(target="allCosts.anticipatedCosts.attendance.cost", source="ac_attendance_time_cost")
    @Mapping(target ="allCosts.anticipatedCosts.preparation.time", source = "ac_preparation_time", qualifiedByName = "convertToTimeSpentString")
    @Mapping(target="allCosts.anticipatedCosts.preparation.cost", source="ac_preparation_time_cost")
    @Mapping(target="allCosts.anticipatedCosts.advocacy.time", source="ac_advocacy_time")
    @Mapping(target="allCosts.anticipatedCosts.advocacy.cost", source="ac_advocacy_time_cost")
    @Mapping(target="allCosts.anticipatedCosts.travel.time", source="ac_travel_time")
    @Mapping(target="allCosts.anticipatedCosts.travel.cost", source="ac_travel_time_cost")
    @Mapping(target="allCosts.anticipatedCosts.waiting.time", source="ac_waiting_time")
    @Mapping(target="allCosts.anticipatedCosts.waiting.cost", source="ac_waiting_time_cost")
    @Mapping(target="allCosts.anticipatedCosts.letters.total", source="ac_letters")
    @Mapping(target="allCosts.anticipatedCosts.letters.cost", source="ac_letters_cost")
    @Mapping(target="allCosts.anticipatedCosts.telephoneCalls.total", source="ac_phone_calls")
    @Mapping(target="allCosts.anticipatedCosts.telephoneCalls.cost", source="ac_phone_calls_cost")
    @Mapping(target="allCosts.anticipatedCosts.mileage.total", source="ac_mileage")
    @Mapping(target="allCosts.anticipatedCosts.mileage.cost", source="ac_mileage_cost")
    @Mapping(target="allCosts.anticipatedCosts.otherDisbursement.cost", source="ac_other_cost")
    @Mapping(target="allCosts.anticipatedCosts.totalCost.cost", source="ac_total_costs")
    @Mapping(target="allCosts.newLimitRequest.cost", source="new_limit_request")
    @Mapping(target="caseHistory.summary", source="ch_case_history")
    @Mapping(target="caseHistory.additionalInfo", source="ch_additional_info")
    @Mapping(target="solicitor.declaration.date", source="solicitor_sign_date")
    @Mapping(target="solicitor.declaration.name", source="solicitor_sign_forename")
    @Mapping(target="solicitor.certification.date", source="certification_sol_date")
    @Mapping(target="solicitor.certification.name", source="certification_sol_name")
    @Mapping(target="officeUseOnly.qualityControl.decision", source="decision_original")
    @Mapping(target="officeUseOnly.qualityControl.decisionReason", source="full_grant_notes")
    @Mapping(target="officeUseOnly.authority.newLimitRequest", source="new_limit_request")
    @Mapping(target="officeUseOnly.authority.upperLimitExtended", source="ou_upper_limit_extended")
    @Mapping(target="officeUseOnly.authority.signedAuth", source="ou_signed_auth")
    @Mapping(target="officeUseOnly.authority.signedAuthDate", source="ou_signed_auth_date")
    CRM5DetailsDTO getDetailsDTOFromModel(Crm5DetailsModel crm5DetailsModel);
}