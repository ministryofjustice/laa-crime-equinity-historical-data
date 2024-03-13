package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm5Mapper {
    @Mapping(target="detailsOfWorkCompleted", source="fd_details_of_work")
    @Mapping(target="detailsOfApplication", source="fd_additional_work")
    @Mapping(target="statementOfCase", source="statement_of_case")
    @Mapping(target="hasPreviousApplication", source="prev_app")
    @Mapping(target="previousApplicationRef", source="prev_app_ref")
    @Mapping(target="appealedPrevDecision", source="appealed_prev_decision")
    @Mapping(target="appealedPrevDecisionDetails", source="appealed_prev_decision_details")
    @Mapping(target="urgencyReason", source="urgency_reason")
    @Mapping(target="firm.firmPhone", source="firm_phone")
    @Mapping(target="firm.firmAddress", source="firm_office")
    @Mapping(target="firm.firmName", source="firm_name")
    @Mapping(target="firm.firmSupplierNo", source="firm_supplier_no")
    @Mapping(target="firm.firmContactName", source="contact_name")
    @Mapping(target="firm.firmSolicitorName", source="solicitorname")
    @Mapping(target="proceedings.typeOfProceedings.preCharge", source="proceedings_precharge")
    @Mapping(target="proceedings.typeOfProceedings.appealProceedings", source="proceedings_crowncourtappeal")
    @Mapping(target="proceedings.typeOfProceedings.prisonLaw", source="proceedings_prisonlaw")
    @Mapping(target="proceedings.detailsOfProceedings.dateOfNextHearing", source="proceedings_nexthearingdate")
    @Mapping(target="proceedings.detailsOfProceedings.isCounselInstructed", source="proceedings_counsel_instructed")
    @Mapping(target="caseDetails.levelOfWork", source="level_of_work")
    @Mapping(target="caseDetails.cwCriminalInvestigation", source="criminal_investigation")
    @Mapping(target="caseDetails.cwCcrc", source="ccrc")
    @Mapping(target="caseDetails.cwAppealsReview", source="appeals_and_review")
    @Mapping(target="caseDetails.cwPrisonLaw", source="prison_law")
    @Mapping(target="clientDetails.UFN", source="client_ufn")
    @Mapping(target="clientDetails.firstName", source="client_forename")
    @Mapping(target = "clientDetails.middleName", expression = "java(null)")
    @Mapping(target="clientDetails.surname", source="client_surname")
    @Mapping(target="clientDetails.maritalStatus", source="client_marital_status")
    @Mapping(target="clientDetails.dateOfBirth", source="client_dob")
    @Mapping(target="clientDetails.address.postcode", source="client_address_postcode")
    @Mapping(target="clientDetails.address.addressLine1", source="client_address_line1")
    @Mapping(target="clientDetails.address.addressLine2", source="client_address_line2")
    @Mapping(target="clientDetails.address.addressLine3", expression = "java(null)")
    @Mapping(target="clientDetails.address.city", source="client_address_town")
    @Mapping(target="clientDetails.address.county", source="client_address_county")
    @Mapping(target="clientDetails.address.country", expression = "java(null)")

    CRM5DetailsDTO getEntityFromModel(Crm5DetailsModel crm5DetailsModel);


}

