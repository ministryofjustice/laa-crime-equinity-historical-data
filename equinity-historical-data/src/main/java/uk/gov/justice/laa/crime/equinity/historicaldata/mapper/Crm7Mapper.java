package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7TimeSpentModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Crm7Mapper extends CrmMapper {

    @Mapping(target="formDetails", source="formDetails")
    @Mapping(target="evidenceFiles", source="evidenceFiles")
    Crm7FormDTO getDTOFromModel(Crm7Model model);

    @Mapping(target="usn", source="usn")
    @Mapping(target="summary", source="model")
    @Mapping(target="solicitorDetails", source="model")
    @Mapping(target="caseDetails", source="model")
    @Mapping(target="nonStandardFeeClaim", source="model")
    @Mapping(target="caseDisposal", source="model")
    @Mapping(target="claimDetails", source="model")
    @Mapping(target="preOrderWork", source="model")
    @Mapping(target="scheduleOfTimeSpent", source="model")
    @Mapping(target="claimOfCosts", source="model")
    @Mapping(target="disbursement", source="model")
    @Mapping(target="claimTotals", source="model")
    @Mapping(target="coversheet", source="coversheet_printed")
    @Mapping(target="caseInformation", source="model")
    @Mapping(target="officeUseOnly", source="model")
    Crm7DetailsDTO getDetailsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="clientSurname", source="client_surname")
    @Mapping(target="clientFirstName", source="client_forename")
    @Mapping(target="clientDateOfBirth", source="client_dob")
    @Mapping(target="ufn", source="client_ufn")
    @Mapping(target="maatNumber", expression="java(emptyIntToNull(model.getMaat_number()))")
    @Mapping(target="representationOrderNumber", source="rep_order_no")
    @Mapping(target="representationOrderDate", source="rep_order_date")
    @Mapping(target="representationOrderSubmitMode", expression="java(convertToEnum(Crm7SummaryOfClaimDTO.RepresentationOrderSubmitModeEnum.class, model.getCp_rep_order_attach_method()))")
    @Mapping(target="isLocatedInDesignatedArea", expression="java(convertToEnum(Crm7SummaryOfClaimDTO.IsLocatedInDesignatedAreaEnum.class, model.getSc_case_in_designated_area()))")
    @Mapping(target="stateReached", source="tlStateName")
    @Mapping(target="outcomeCode", source="sc_outcome_code")
    @Mapping(target="matterType", source="sc_matter_type")
    @Mapping(target="equalOpportunities", source="model")
    @Mapping(target="officeUse", source="model")
    Crm7SummaryOfClaimDTO getSummaryDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="monitoring1", source="sc_eo_1")
    @Mapping(target="monitoring2", source="sc_eo_2")
    @Mapping(target="monitoring3", source="sc_eo_3")
    @Mapping(target="dateClassOfWorkClosed", source="sc_date_class_of_work")
    @Mapping(target="numberDefendantsRepresented", source="sc_no_of_defendants")
    @Mapping(target="numberCourtAttendances", source="sc_no_of_court_attendances")
    @Mapping(target="courtIdentifier", source="sc_court_indentifier")
    @Mapping(target="isYouthCourt", expression="java(convertToEnum(Crm7EqualOpportunitiesDTO.IsYouthCourtEnum.class, model.getSc_youth_court()))")
    Crm7EqualOpportunitiesDTO getSummaryEqualOpportunitiesDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="isLocatedInDesignatedArea", source="sc_prov_case_in_designated_area")
    @Mapping(target="stateReached", source="tltaskstate")
    @Mapping(target="equalOpportunitiesCode", source="sc_eo_code")
    @Mapping(target="profitCost", source="ctou_profit_costs_total")
    @Mapping(target="disbursements", source ="cfc_disb_grand_total_ou")
    @Mapping(target="travelCost", source="ctou_travel_costs_total")
    @Mapping(target="waitingCost", source="ctou_waiting_costs_total")
    Crm7SummaryOfficeUseDTO getSummaryOfficeUseDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="firmName", source="firm_name")
    @Mapping(target="address", source="firm_address")
    @Mapping(target="providerAccount", source="firm_supplier_no")
    @Mapping(target="telephone", source="firm_phone")
    @Mapping(target="contactName", source="contact_name")
    @Mapping(target="solicitorName", source="solicitorname")
    @Mapping(target="solicitorReference", source="solicitorid")
    Crm7SolicitorDetailsDTO getSolicitorDetailsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="urn", source="urn")
    @Mapping(target="mainOffence", source="cd_main_offence")
    @Mapping(target="dateOffenceCommited", source="cd_offence_date")
    @Mapping(target="isSeriousFraudCase", expression="java(convertToEnum(Crm7CaseDetailsDTO.IsSeriousFraudCaseEnum.class, model.getCd_serious_fraud_case()))")
    @Mapping(target="isIndictableOnlyOffenceCharge", expression="java(convertToEnum(Crm7CaseDetailsDTO.IsIndictableOnlyOffenceChargeEnum.class, model.getCd_indictable_only()))")
    @Mapping(target="dateChargeLaid", source="cd_offence_date")
    @Mapping(target="indictmentAttachment", expression="java(convertToEnum(Crm7CaseDetailsDTO.IndictmentAttachmentEnum.class, model.getCd_indictment_attach_method()))")
    @Mapping(target="isWastedCostsCase", expression="java(convertToEnum(Crm7CaseDetailsDTO.IsWastedCostsCaseEnum.class, model.getCd_wasted_cost_order()))")
    @Mapping(target="wastedCosts", source="cd_wasted_costs_order_net")
    @Mapping(target="orderDetails", source="cd_wasted_costs_order_details")
    Crm7CaseDetailsDTO getCaseDetailsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="reason.isCoreCostExceededLimit", source="nsfc_core_costs_exceeded")
    @Mapping(target="reason.isEnhancedRatesClaim", source="nsfc_enhanced_rates_claimed")
    @Mapping(target="reason.isCounselAssigned", source="nsfc_counsel_assigned")
    @Mapping(target="reason.isExtradition", source="nsfc_extradition")
    @Mapping(target="reason.isRepresentationOrderWithdrawn", source="nsfc_rep_order_withdrawn")
    @Mapping(target="reason.isOther", source="nsfc_other")
    @Mapping(target="dateWithdrawn", source="rep_order_date")
    @Mapping(target="additionalDetails", source="nsfc_order_withdrawn_details")
    Crm7NonStandardFeeClaimDTO getNonStandardFeeClaimDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="category", source="cdsp_category")
    @Mapping(target="option.type1", source="cdsp_category_1_type")
    @Mapping(target="option.type2", source="cdsp_category_2_type")
    @Mapping(target="option.type3", source="cdsp_category_3_type")
    @Mapping(target="selection.radio1", source="cdsp_category_1_radio")
    @Mapping(target="selection.radio2", source="cdsp_category_2_radio")
    @Mapping(target="selection.radio3", source="cdsp_category_3_radio")
    Crm7CaseDisposalDTO getCaseDisposalDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="wasCounselAssigned", expression="java(convertToEnum(Crm7ClaimDetailsDTO.WasCounselAssignedEnum.class, model.getCounsel_instructed_assigned()))")
    @Mapping(target="wasCounselUnassigned", expression="java(convertToEnum(Crm7ClaimDetailsDTO.WasCounselUnassignedEnum.class, model.getCounsel_instructed_unassigned()))")
    @Mapping(target="wasAgentInstructed", expression="java(convertToEnum(Crm7ClaimDetailsDTO.WasAgentInstructedEnum.class, model.getAgent_instructed()))")
    @Mapping(target="prosecutionEvidencePages", source="cld_prosecution_pages")
    @Mapping(target="defenceStatementPages", source="cld_defence_pages")
    @Mapping(target="defenceWitnesses", source="cld_no_of_defence_witnesses")
    @Mapping(target="isSupplementalClaim", expression="java(convertToEnum(Crm7ClaimDetailsDTO.IsSupplementalClaimEnum.class, model.getCld_supplemental_claim()))")
    @Mapping(target="supplementalClaimAttachments", expression="java(convertToEnum(Crm7ClaimDetailsDTO.SupplementalClaimAttachmentsEnum.class, model.getCld_attach_method()))")
    @Mapping(target="wasTimeSpentOnTapedEvidence", expression="java(convertToEnum(Crm7ClaimDetailsDTO.WasTimeSpentOnTapedEvidenceEnum.class, model.getCld_taped_evidence()))")
    @Mapping(target="tapedEvidenceTime", source="cld_tape_running_time")
    @Mapping(target="isRemittedBackToMagistrates", expression="java(convertToEnum(Crm7ClaimDetailsDTO.IsRemittedBackToMagistratesEnum.class, model.getCld_remitted_to_mags()))")
    @Mapping(target="dateRemittedBackToMagistrates", expression="java(null)")
    @Mapping(target="crownCourtAttachments", expression="java(convertToEnum(Crm7ClaimDetailsDTO.CrownCourtAttachmentsEnum.class, model.getCld_prev_claims_attach_method()))")
    Crm7ClaimDetailsDTO getClaimDetailsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="isClaimBeforeGrantDate", expression="java(convertToEnum(Crm7PreOrderWorkDTO.IsClaimBeforeGrantDateEnum.class, model.getPow_claiming()))")
    @Mapping(target="dateSubmitted", source="date_submitted")
    @Mapping(target="firstCourtHearingDate", source="date_first_hearing")
    @Mapping(target="dateReceivedByCourt", source="date_received")
    Crm7PreOrderWorkDTO getPreOrderWorkDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="schedule", source="schedule")
    @Mapping(target="laaAdjustments", source="schedule")
    @Mapping(target="timeTotals", expression="java(getScheduleTimesDTOFromModel(model))")
    @Mapping(target="costTotals", expression="java(getScheduleCostsDTOFromModel(model))")
    @Mapping(target="totals", expression="java(getScehduleTotalsDTOFromModel(model))")
    @Mapping(target="officeUse", expression="java(getScheduleOfficeUseTotalsDTOFromModel(model))")
    Crm7ScheduleOfTimeSpentDTO getScheduleOfTimeSpentDTOFromModel(Crm7DetailsModel model);

    List<Crm7ScheduleTimeTableDTO> getTimeSpentScheduleTableDTOFromModel(List<Crm7TimeSpentModel> schedule);

    @Mapping(target="line", source="line")
    @Mapping(target="feeEarnerInitials", source="fe_initials")
    @Mapping(target="date", source="date")
    @Mapping(target="costType", source="cost_type")
    @Mapping(target="time", source="time_total")
    @Mapping(target="hearingTypeCode", source="hearing_codes")
    @Mapping(target="personAttendedCode", source="person_codes")
    @Mapping(target="hourlyRate", source="rate")
    @Mapping(target="basicClaim", source="basic_claim")
    @Mapping(target="uplift", source="uplift")
    @Mapping(target="claim", source="claim")
    Crm7ScheduleTimeTableDTO getTimeSpentScheduleDTOFromModel(Crm7TimeSpentModel schedule);

    List<Crm7ScheduleAdjustmentsTableDTO> getTimeSpentAdjustmentsTableDTOFromModel(List<Crm7TimeSpentModel> schedule);

    @Mapping(target="line", source="line")
    @Mapping(target="time", source="time_total_cw")
    @Mapping(target="hourlyRate", source="rate_cw")
    @Mapping(target="basicClaim", source="basic_cw")
    @Mapping(target="uplift", source="uplift_cw")
    @Mapping(target="claim", source="claim_cw")
    @Mapping(target="comments", source="assessment_cw")
    Crm7ScheduleAdjustmentsTableDTO getTimeSpentAdjustmentsDTOFromModel(Crm7TimeSpentModel schedule);

    @Mapping(target="lettersAndPhoneCalls", source="model")
    Crm7ClaimOfCostsDTO getClaimOfCostsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="travel", source="total_travel_time_display")
    @Mapping(target="waiting", source="total_waiting_time_display")
    @Mapping(target="attendance", source="total_attendance_time_display")
    @Mapping(target="attendanceCounsel", source="total_attendance_time_counsel")
    @Mapping(target="attendanceNoCounsel", source="total_attendance_time_no_counsel")
    @Mapping(target="preparation", source="total_preparation_time_display")
    @Mapping(target="advocacy", source="total_advocacy_time")
    Crm7ScheduleOfTimeCostTableDTO getScheduleTimesDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="travel", source="total_travel_costs")
    @Mapping(target="waiting", source="total_waiting_costs")
    @Mapping(target="attendance", source="total_attendance_costs")
    @Mapping(target="attendanceCounsel", source="total_attendance_costs_counsel")
    @Mapping(target="attendanceNoCounsel", source="total_attendance_costs_no_counsel")
    @Mapping(target="preparation", source="total_preparation_costs")
    @Mapping(target="advocacy", source="total_advocacy_costs")
    Crm7ScheduleOfTimeCostTableDTO getScheduleCostsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="basic", source="total_basic_claim")
    @Mapping(target="total", source="total_claim")
    Crm7ScheduleOfTimeTotalsDTO getScehduleTotalsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="basic", source="cw_total_basic_claim")
    @Mapping(target="total", source="cw_total_claim")
    Crm7ScheduleOfTimeTotalsDTO getScheduleOfficeUseTotalsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="totals", expression="java(getClaimOfCostCommunicationsTotalsDTOFromModel(model))")
    @Mapping(target="officeOnly", expression="java(getClaimOfCostCommunicationsOfficeDTOFromModel(model))")
    @Mapping(target="assessmentReasons", source="assessment_reasons")
    Crm7CommunicationsDTO getClaimOfCostCommunicationsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="letters", expression="java(getClaimOfCostCommunicationsLettersDTOFromModel(model))")
    @Mapping(target="telephoneCalls", expression="java(getClaimOfCostCommunicationsCallsDTOFromModel(model))")
    @Mapping(target="total", source="cfc_total_costs")
    @Mapping(target="solicitorCost", source="cfc_sol_core_costs")
    Crm7CommunicationsCostsDTO getClaimOfCostCommunicationsTotalsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="number", source="cfc_no_of_letters")
    @Mapping(target="rate", source="cfc_letter_rate")
    @Mapping(target="uplift", source="cfc_letter_uplift")
    @Mapping(target="cost", source="cfc_total_letter_cost")
    Crm7CommunicationsCostsTableDTO getClaimOfCostCommunicationsLettersDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="number", source="cfc_no_of_calls")
    @Mapping(target="rate", source="cfc_calls_rate")
    @Mapping(target="uplift", source="cfc_cw_calls_uplift")
    @Mapping(target="cost", source="cfc_total_phone_costs")
    Crm7CommunicationsCostsTableDTO getClaimOfCostCommunicationsCallsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="letters", expression="java(getClaimOfCostOfficeLettersDTOFromModel(model))")
    @Mapping(target="telephoneCalls", expression="java(getClaimOfCostOfficeCallsDTOFromModel(model))")
    @Mapping(target="total", source="cfc_cw_total_costs")
    @Mapping(target="solicitorCost", source="cfc_cw_sol_core_costs")
    Crm7CommunicationsCostsDTO getClaimOfCostCommunicationsOfficeDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="number", source="cfc_cw_no_of_letters")
    @Mapping(target="rate", source="cfc_cw_letters_rate")
    @Mapping(target="uplift", source="cfc_cw_letters_uplift")
    @Mapping(target="cost", source="cfc_cw_letters_costs")
    Crm7CommunicationsCostsTableDTO getClaimOfCostOfficeLettersDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="number", source="cfc_cw_no_of_calls")
    @Mapping(target="rate", source="cfc_cw_calls_rate")
    @Mapping(target="uplift", source="cfc_calls_uplift")
    @Mapping(target="cost", source="cfc_cw_calls_costs")
    Crm7CommunicationsCostsTableDTO getClaimOfCostOfficeCallsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="disbursements", expression="java(null)")
    @Mapping(target="totals", expression="java(getDisbursementTotalsDTOFromModel(model))")
    @Mapping(target="invoiceAttachments", expression="java(convertToEnum(Crm7DisbursementDTO.InvoiceAttachmentsEnum.class, model.getCfc_disb_attach_method()))")
    @Mapping(target="officeUse", expression="java(getDisbursementOfficeDTOFromModel(model))")
    Crm7DisbursementDTO getDisbursementDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", source="cfc_disb_total_net")
    @Mapping(target="vat", source="cfc_disb_total_vat")
    @Mapping(target="total", source="cfc_disb_grand_total")
    Crm7ClaimTotalCostDetailsDTO getDisbursementTotalsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", source="cfc_disb_total_net_ou")
    @Mapping(target="vat", source="cfc_disb_total_vat_ou")
    @Mapping(target="total", source="cfc_disb_grand_total_ou")
    Crm7ClaimTotalCostDetailsDTO getDisbursementOfficeDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="deductions", source="cd_wasted_costs_order_net")
    @Mapping(target="total", expression="java(getClaimTotalsCostDTOFromModel(model))")
    @Mapping(target="officeUse", expression="java(getClaimTotalsOfficeDTOFromModel(model))")
    Crm7ClaimTotalsDTO getClaimTotalsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="profit", expression="java(getClaimTotalsCostProfitDTOFromModel(model))")
    @Mapping(target="disbursements", source="cfc_disb_grand_total")
    @Mapping(target="travel", expression="java(getClaimTotalsCostTravelDTOFromModel(model))")
    @Mapping(target="waiting", expression="java(getClaimTotalsCostWaitDTOFromModel(model))")
    Crm7ClaimTotalCostsDTO getClaimTotalsCostDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", source="cfc_sol_core_costs")
    @Mapping(target="vat", source="ct_profit_costs_vat_rate")
    @Mapping(target="total", source="ct_profit_costs_total")
    Crm7ClaimTotalCostDetailsDTO getClaimTotalsCostProfitDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", expression="java(null)")
    @Mapping(target="vat", source="ct_travel_costs_vat_rate")
    @Mapping(target="total", source="ct_travel_costs_total")
    Crm7ClaimTotalCostDetailsDTO getClaimTotalsCostTravelDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", expression="java(null)")
    @Mapping(target="vat", source="ct_waiting_costs_vat_rate")
    @Mapping(target="total", source="ct_waiting_costs_total")
    Crm7ClaimTotalCostDetailsDTO getClaimTotalsCostWaitDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="profit", expression="java(getClaimTotalsOfficeProfitDTOFromModel(model))")
    @Mapping(target="disbursements", source="cfc_disb_grand_total_ou")
    @Mapping(target="travel", expression="java(getClaimTotalsOfficeTravelDTOFromModel(model))")
    @Mapping(target="waiting", expression="java(getClaimTotalsOfficeWaitDTOFromModel(model))")
    Crm7ClaimTotalCostsDTO getClaimTotalsOfficeDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", source="cfc_cw_sol_core_costs")
    @Mapping(target="vat", source="ctou_profit_costs_vat_rate")
    @Mapping(target="total", source="ctou_profit_costs_total")
    Crm7ClaimTotalCostDetailsDTO getClaimTotalsOfficeProfitDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", expression="java(null)")
    @Mapping(target="vat", source="ctou_travel_costs_vat_rate")
    @Mapping(target="total", source="ctou_travel_costs_total")
    Crm7ClaimTotalCostDetailsDTO getClaimTotalsOfficeTravelDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="net", expression="java(null)")
    @Mapping(target="vat", source="ctou_waiting_costs_vat_rate")
    @Mapping(target="total", source="ctou_waiting_costs_total")
    Crm7ClaimTotalCostDetailsDTO getClaimTotalsOfficeWaitDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="relevantDetails", source="relevant_case_info_details")
    @Mapping(target="solicitorCertification", source="model")
    @Mapping(target="additionalInfo", source="additional_info")
    Crm7CaseInformationDTO getCaseInformationDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="name", source="solicitor_sign_name")
    @Mapping(target="date", source="solicitor_sign_date")
    Crm7CaseInformationSolicitorDTO getCaseInformationSolicitorDTOFromModel(Crm7DetailsModel model);
    @Mapping(target="qualityControl", source="model")
    @Mapping(target="authority", source="model")
    Crm7OfficialUseDTO getOfficialUseDTOFromModel(Crm7DetailsModel model);
    @Mapping(target="decision", source="decision_original")
    @Mapping(target="decisionReason", source="full_grant_notes")
    @Mapping(target="destructionDate", source="destruction_date")
    Crm7DecisionDTO getQualityControlDTOFromModel(Crm7DetailsModel model);
    @Mapping(target="signedAuth", source="ou_signed_auth")
    @Mapping(target="signedAuthDate", source="ou_signed_auth_date")
    Crm7AuthorityDTO getAuthorityDTOFromModel(Crm7DetailsModel model);
}
