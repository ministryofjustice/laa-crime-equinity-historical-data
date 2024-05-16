package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Crm4DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm4DetailsModel;

@Mapper(componentModel = "spring")
public interface Crm4Mapper {

    @Mapping(target="greaterValue", source="applyingge100")
    @Mapping(target="postMortemExamination", source="post_mortem_app")
    @Mapping(target="caseDetails.isPOCA", source="is_case_subject_to_poca")
    @Mapping(target="caseDetails.firm.urn", source="urn")
    @Mapping(target="caseDetails.firm.firmName", source="firm_name")
    @Mapping(target="caseDetails.firm.firmAddress", source="firm_address")
    @Mapping(target="caseDetails.firm.firmPhone", source="firm_phone")
    @Mapping(target="caseDetails.firm.firmSupplierName", source="provider_account")
    @Mapping(target="caseDetails.firm.firmSupplierNo", source="firm_supplier_no")
    @Mapping(target="caseDetails.firm.firmContactName", source="contact_name")
    @Mapping(target="caseDetails.firm.firmSolicitorName", source="solicitorname")

    @Mapping(target="caseDetails.solicitorDetails.solicitorName", source="solicitorname")
    @Mapping(target="caseDetails.solicitorDetails.solicitorReference", source="solicitorid")

    @Mapping(target="caseDetails.clientDetails.ufn", source="client_ufn")
    @Mapping(target="caseDetails.clientDetails.maatNumber", source="maat_number")
    @Mapping(target="caseDetails.clientDetails.firstName", source="client_forename")
    @Mapping(target="caseDetails.clientDetails.surname", source="client_surname")
    @Mapping(target="caseDetails.clientDetails.dateOfBirth", source="client_dob")
    @Mapping(target="caseDetails.clientDetails.prisonLaw", source="prison_law")

    @Mapping(target="caseDetails.proceedingDetails.isClientDetained", source="client_detained")
    @Mapping(target="caseDetails.proceedingDetails.detainedDetails", source="client_locationdetained")
    @Mapping(target="caseDetails.proceedingDetails.courtType", source="court_type")
    @Mapping(target="caseDetails.proceedingDetails.psychiatricLiaison", source="psychiatricliaisonservice1")
    @Mapping(target="caseDetails.proceedingDetails.psychiatricDetails", source="psychiatricliaisonservice2")
    @Mapping(target="caseDetails.proceedingDetails.mainOffence", source="mainoffence")
    @Mapping(target="caseDetails.proceedingDetails.actualPlea", source="plea")
    @Mapping(target="caseDetails.proceedingDetails.dateOfHearing", source = "nexthearingdate")
    @Mapping(target="additionalInfo", source="ai_text")
    @Mapping(target="expenditureDetails.details.expenditureType", source = "expenditure_type")
    @Mapping(target="expenditureDetails.details.priorAuthority", source = "prior_authority_granted")
    @Mapping(target="expenditureDetails.details.expertName", source = "expert_name")
    @Mapping(target="expenditureDetails.details.companyName", source = "company_name")
    @Mapping(target="expenditureDetails.details.statusExpert", source = "expert_type")
    @Mapping(target="expenditureDetails.details.postCodeExpert", source = "expert_postcode")
    @Mapping(target="expenditureDetails.preparation.hours", source = "preparation_hrs")
    @Mapping(target="expenditureDetails.preparation.hourlyRate", source = "preparation_rate")
    @Mapping(target="expenditureDetails.preparation.total", source = "preparation_total")
    @Mapping(target="expenditureDetails.additionalExpenditure", source="ae.row")
    @Mapping(target="expenditureDetails.travel.hours", source = "travel_no_of_hrs")
    @Mapping(target="expenditureDetails.travel.rate", source = "travel_per_hr")
    @Mapping(target="expenditureDetails.travel.total", source = "travel_cost_total")
    @Mapping(target="expenditureDetails.authority", source = "total_authority")

    @Mapping(target="alternativeQuotes.alternativeQuote", source = "obtained_alt_quotes")
    @Mapping(target="alternativeQuotes.reason", source = "no_alt_quote_reasons")
    @Mapping(target="alternativeQuotes.numberOfQuotes", source = "quotes_number")


    @Mapping(target="priorAuthorityDetails.authorityDetails", source = "authority_sought")
    @Mapping(target="priorAuthorityDetails.prosecutionSummary", source = "prosecution_case_summary")
    @Mapping(target="priorAuthorityDetails.defenceMitigation", source = "defence_summary")
    @Mapping(target="priorAuthorityDetails.qcDetails", source = "qc_without_junior_details")

    @Mapping(target="solicitor.declaration", source="solicitor_declaration")
    @Mapping(target="solicitor.certification.date", source="solicitor_sign_date")
    @Mapping(target="solicitor.certification.name", source="solicitorname")
    @Mapping(target="officeUseOnly.decision", source = "tlstatename")

    Crm4DetailsDTO getEntityFromModel(Crm4DetailsModel crm4DetailsModel);
}