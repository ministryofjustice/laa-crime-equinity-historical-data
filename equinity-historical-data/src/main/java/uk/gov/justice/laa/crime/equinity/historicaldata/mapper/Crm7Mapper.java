package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm7DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm7Mapper extends CrmMapper {
    @Mapping(target="usn", source="usn")
    @Mapping(target="summary", source="model")
    @Mapping(target="solicitorDetails", source="model")
    @Mapping(target="caseDetails", source="model")
    @Mapping(target="nonStandardFeeClaim", source="model")
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
}

