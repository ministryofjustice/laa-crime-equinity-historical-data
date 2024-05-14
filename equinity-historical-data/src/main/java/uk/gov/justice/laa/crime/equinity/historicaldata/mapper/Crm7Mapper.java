package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7TimeSpentModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Crm7Mapper extends CrmMapper {
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
    @Mapping(target="disbursement", expression="java(null)")
    @Mapping(target="claimTotals", expression="java(null)")
    @Mapping(target="coversheet", expression="java(null)")
    @Mapping(target="caseInformation", expression="java(null)")
    @Mapping(target="officeUseOnly", expression="java(null)")
    Crm7DetailsDTO getDTOFromModel(Crm7DetailsModel model);

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
    @Mapping(target="courtIdentifier", source="sc_court_identifier")
    @Mapping(target="isYouthCourt", expression="java(convertToEnum(Crm7EqualOpportunitiesDTO.IsYouthCourtEnum.class, model.getSc_youth_court()))")
    Crm7EqualOpportunitiesDTO getSummaryEqualOpportunitiesDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="isLocatedInDesignatedArea", source="sc_prov_case_in_designated_area")
    @Mapping(target="stateReached", source="tltaskstate")
    @Mapping(target="equalOpportunitiesCode", source="sc_eo_code")
    @Mapping(target="profitCost", source="ctou_profit_costs_total")
    @Mapping(target="disbursements", source ="cfc_disb_grand_total_ou")
    @Mapping(target="travelCost", source="ctou_travel_costs_total")
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
    @Mapping(target="wastedCosts", source="cd_wasted_costs_order_details")
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
    @Mapping(target="tapedEvidenceTime", expression="java(null)")
    @Mapping(target="isRemittedBackToMagistrates", expression="java(convertToEnum(Crm7ClaimDetailsDTO.IsRemittedBackToMagistratesEnum.class, model.getCld_remitted_to_mags()))")
    @Mapping(target="dateRemittedBackToMagistrates", expression="java(null)")
    @Mapping(target="crownCourtAttachments", expression="java(convertToEnum(Crm7ClaimDetailsDTO.CrownCourtAttachmentsEnum.class, model.getCld_prev_claims_attach_method()))")
    Crm7ClaimDetailsDTO getClaimDetailsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="isClaimBeforeGrantDate", expression="java(convertToEnum(Crm7PreOrderWorkDTO.IsClaimBeforeGrantDateEnum.class, model.getPow_claiming()))")
    @Mapping(target="dateSubmitted", expression="java(null)")
    @Mapping(target="firstCourtHearingDate", source="date_first_hearing")
    @Mapping(target="dateReceivedByCourt", source="date_received")
    Crm7PreOrderWorkDTO getPreOrderWorkDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="schedule", source="schedule")
    @Mapping(target="laaAdjustments", source="schedule")
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

    @Mapping(target="timeTotals", expression="java(getClaimOfCostsTimesDTOFromModel(model))")
    @Mapping(target="costTotals", expression="java(getClaimOfCostsCostsDTOFromModel(model))")
    @Mapping(target="totals", expression="java(getClaimOfCostTotalsDTOFromModel(model))")
    @Mapping(target="officeUse", expression="java(getClaimOfCostOfficeUseTotalsDTOFromModel(model))")
    @Mapping(target="lettersAndPhoneCalls", expression="java(null)")
    Crm7ClaimOfCostsDTO getClaimOfCostsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="travel", source="total_travel_time_display")
    @Mapping(target="waiting", source="total_waiting_time_display")
    @Mapping(target="attendance", source="total_attendance_time_display")
    @Mapping(target="preparation", source="total_preparation_time_display")
    @Mapping(target="advocacy", source="total_advocacy_time")
    Crm7ClaimOfCostsTableDTO getClaimOfCostsTimesDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="travel", source="total_travel_costs")
    @Mapping(target="waiting", source="total_waiting_costs")
    @Mapping(target="attendance", source="total_attendance_costs")
    @Mapping(target="preparation", source="total_preparation_costs")
    @Mapping(target="advocacy", source="total_advocacy_costs")
    Crm7ClaimOfCostsTableDTO getClaimOfCostsCostsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="basic", source="total_basic_claim")
    @Mapping(target="total", source="total_claim")
    Crm7ClaimOfCostsTotalsDTO getClaimOfCostTotalsDTOFromModel(Crm7DetailsModel model);

    @Mapping(target="basic", source="cw_total_basic_claim")
    @Mapping(target="total", source="cw_total_claim")
    Crm7ClaimOfCostsTotalsDTO getClaimOfCostOfficeUseTotalsDTOFromModel(Crm7DetailsModel model);
}

