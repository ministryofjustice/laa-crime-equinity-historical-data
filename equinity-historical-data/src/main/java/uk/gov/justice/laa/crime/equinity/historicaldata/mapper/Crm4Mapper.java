package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.*;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4AuthorisedExpenditureModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4DetailsModel;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.crm4.Crm4SubmissionModel;

@Mapper(componentModel = "spring")
public interface Crm4Mapper extends CrmEvidenceFilesMapper {

    @Mapping(target="formDetails", source="formDetails")
    @Mapping(target="evidenceFiles", source="evidenceFiles")
    Crm4FormDTO getDTOFromModel(Crm4Model model);

    @Mapping(target="greaterValue", source="applyingge100")
    @Mapping(target="postMortemExamination", source="post_mortem_app")
    @Mapping(target="caseDetails.isPOCA", source="is_case_subject_to_poca")
    @Mapping(target="caseDetails.firm.urn", source="urn")
    @Mapping(target="caseDetails.firm.firmName", source="firm_name")
    @Mapping(target="caseDetails.firm.firmAddress", source="firm_address")
    @Mapping(target="caseDetails.firm.firmDx", source="firm_dx")
    @Mapping(target="caseDetails.firm.firmPhone", source="firm_phone")
    @Mapping(target="caseDetails.firm.firmSupplierName", source="provider_account")
    @Mapping(target="caseDetails.firm.firmSupplierNo", source="firm_supplier_no")
    @Mapping(target="caseDetails.firm.firmContactName", source="contact_name")
    @Mapping(target="caseDetails.firm.firmSolicitorName", source="solicitorname")

    @Mapping(target="caseDetails.solicitorDetails.solicitorName", source="solicitorname")
    @Mapping(target="caseDetails.solicitorDetails.solicitorReference", source="solicitorid")

    @Mapping(target="caseDetails.clientDetails.repOrderNumber", source="rep_order_number")
    @Mapping(target="caseDetails.clientDetails.repOrderDate", source="client_dateoforder")
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
    @Mapping(target="caseDetails.proceedingDetails.purposeOfHearing", source = "nexthearingpurpose")
    @Mapping(target="additionalInfo", source="ai_text")
    @Mapping(target="expenditureDetails.details.expenditureType", source = "expenditure_type")
    @Mapping(target="expenditureDetails.details.courtOrderedReport", source = "expenditure_orderedbycourt")
    @Mapping(target="expenditureDetails.details.priorAuthority", source = "prior_authority_granted")
    @Mapping(target="expenditureDetails.details.expertName", source = "expert_name")
    @Mapping(target="expenditureDetails.details.companyName", source = "company_name")
    @Mapping(target="expenditureDetails.details.statusExpert", source = "expert_type")
    @Mapping(target="expenditureDetails.details.postCodeExpert", source = "expert_postcode")

    @Mapping(target="expenditureDetails.preparation", source = "crm4DetailsModel")

    @Mapping(target="expenditureDetails.dnaReport.dnaCost", source = "dna_cost")
    @Mapping(target="expenditureDetails.accommodation.costBasis", source = "accomm_basis")
    @Mapping(target="expenditureDetails.transcription.numMin", source = "transcription_no_of_mins")
    @Mapping(target="expenditureDetails.transcription.costPerMin", source = "transcription_cost_per_min")
    @Mapping(target="expenditureDetails.transcription.total", source = "transcription_total")
    @Mapping(target="expenditureDetails.photocopying.numPages", source = "photocopy_no_of_pages")
    @Mapping(target="expenditureDetails.photocopying.costPerPage", source = "photocopy_cost_per_page")
    @Mapping(target="expenditureDetails.photocopying.total", source = "photocopy_total")
    @Mapping(target="expenditureDetails.translator.numWords", source = "translator_no_of_words")
    @Mapping(target="expenditureDetails.translator.costPerWord", source = "translator_per_thou_words")
    @Mapping(target="expenditureDetails.translator.total", source = "translator_total_cost")
    @Mapping(target="expenditureDetails.other.quantity", source = "other_quantity")
    @Mapping(target="expenditureDetails.other.rate", source = "other_rate")
    @Mapping(target="expenditureDetails.other.total", source = "other_total")
    @Mapping(target="expenditureDetails.additionalExpenditure", source="additionalExpenditure.row")

    @Mapping(target="expenditureDetails.travel", source = "crm4DetailsModel")

    @Mapping(target="expenditureDetails.authority.total", source = "total_authority")
    @Mapping(target="expenditureDetails.authority.vatDeclaration", source = "inc_vat_declaration")
    @Mapping(target="expenditureDetails.authority.travelDeclaration", source = "declaration_no_travel")

    @Mapping(target="alternativeQuotes.alternativeQuote", source = "obtained_alt_quotes")
    @Mapping(target="alternativeQuotes.reason", source = "no_alt_quote_reasons")
    @Mapping(target="alternativeQuotes.numberOfQuotes", source = "quotes_number")
    @Mapping(target="alternativeQuotes.quotes", source = "allQuotes")
    @Mapping(target="priorAuthorityDetails.authorityDetails", source = "authority_sought")
    @Mapping(target="priorAuthorityDetails.prosecutionSummary", source = "prosecution_case_summary")
    @Mapping(target="priorAuthorityDetails.defenceMitigation", source = "defence_summary")
    @Mapping(target="priorAuthorityDetails.qcDetails", source = "qc_without_junior_details")
    @Mapping(target="solicitor.declaration", source="solicitor_declaration")
    @Mapping(target="solicitor.certification.date", source="solicitor_sign_date")
    @Mapping(target="solicitor.certification.name", source="solicitorname")

    @Mapping(target="officeUseOnly.qualityControl", source="crm4DetailsModel")
    @Mapping(target="officeUseOnly.preparation.requested.hours", source="preparation_hrs", qualifiedByName="convertToTimeSpentString")
    @Mapping(target="officeUseOnly.preparation.requested.hourlyRate", source="preparation_rate")
    @Mapping(target="officeUseOnly.preparation.requested.total", source="preparation_total")
    @Mapping(target="officeUseOnly.preparation.authorised.hours", source="cw_preparation_hrs", qualifiedByName="convertToTimeSpentString")
    @Mapping(target="officeUseOnly.preparation.authorised.hourlyRate", source="cw_preparation_rate")
    @Mapping(target="officeUseOnly.preparation.authorised.total", source="cw_preparation_total")
    @Mapping(target="officeUseOnly.dnaReport.requested.dnaCost", source = "dna_cost")
    @Mapping(target="officeUseOnly.dnaReport.authorised.dnaCost", source = "cw_dna_cost")
    @Mapping(target="officeUseOnly.accommodation.costBasis", source = "accomm_basis")
    @Mapping(target="officeUseOnly.transcription.requested.numMin", source="transcription_no_of_mins")
    @Mapping(target="officeUseOnly.transcription.requested.costPerMin", source="transcription_cost_per_min")
    @Mapping(target="officeUseOnly.transcription.requested.total", source="transcription_total")
    @Mapping(target="officeUseOnly.transcription.authorised.numMin", source="cw_transcription_no_of_mins")
    @Mapping(target="officeUseOnly.transcription.authorised.costPerMin", source="cw_transcription_cost_per_min")
    @Mapping(target="officeUseOnly.transcription.authorised.total", source="cw_transcription_total")
    @Mapping(target="officeUseOnly.translator.requested.numWords", source="translator_no_of_words")
    @Mapping(target="officeUseOnly.translator.requested.costPerWord", source="translator_per_thou_words")
    @Mapping(target="officeUseOnly.translator.requested.total", source="translator_total_cost")
    @Mapping(target="officeUseOnly.translator.authorised.numWords", source="cw_translator_no_of_words")
    @Mapping(target="officeUseOnly.translator.authorised.costPerWord", source="cw_translator_per_thou_words")
    @Mapping(target="officeUseOnly.translator.authorised.total", source="cw_translator_total_cost")
    @Mapping(target="officeUseOnly.photocopying.requested.numPages", source="photocopy_no_of_pages")
    @Mapping(target="officeUseOnly.photocopying.requested.costPerPage", source="photocopy_cost_per_page")
    @Mapping(target="officeUseOnly.photocopying.requested.total", source="photocopy_total")
    @Mapping(target="officeUseOnly.photocopying.authorised.numPages", source="cw_photocopy_no_of_pages")
    @Mapping(target="officeUseOnly.photocopying.authorised.costPerPage", source="cw_photocopy_cost_per_page")
    @Mapping(target="officeUseOnly.photocopying.authorised.total", source="cw_photocopy_total")
    @Mapping(target="officeUseOnly.additionalExpenditure", source="authorisedAdditionalExpenditure.expenditure")
    @Mapping(target="officeUseOnly.travel.requested.hours", source="travel_no_of_hrs", qualifiedByName="convertToTimeSpentString")
    @Mapping(target="officeUseOnly.travel.requested.rate", source="travel_per_hr")
    @Mapping(target="officeUseOnly.travel.requested.total", source="travel_cost_total")
    @Mapping(target="officeUseOnly.travel.authorised.hours", source="cw_travel_no_of_hrs", qualifiedByName="convertToTimeSpentString")
    @Mapping(target="officeUseOnly.travel.authorised.rate", source="cw_travel_per_hr")
    @Mapping(target="officeUseOnly.travel.authorised.total", source="cw_travel_cost_total")
    @Mapping(target="officeUseOnly.authority.allowedAmount", source="amount_allowed")
    @Mapping(target="officeUseOnly.authority.destructionDate", source="destruction_date")
    @Mapping(target="officeUseOnly.authority.signedAuth", source="signed_authority")
    @Mapping(target="officeUseOnly.relatedSubmissions", source="relatedSubmissions.submissions")
    @Mapping(target="furtherInformation", source="furtherInformationModel.attachments")
    @Mapping(target="standardProperties", source="crm4DetailsModel")
    Crm4DetailsDTO getEntityFromModel(Crm4DetailsModel crm4DetailsModel);


    @Mapping(target="hours", source = "preparation_duration", qualifiedByName="convertToTimeSpentString")
    @Mapping(target="hourlyRate", source = "preparation_rate")
    @Mapping(target="total", source = "preparation_total")
    Crm4PreparationDetailsDTO getPreparationDetailsFromModel(Crm4DetailsModel crm4DetailsModel);

    @Mapping(target="hours", source = "travel_duration", qualifiedByName="convertToTimeSpentString")
    @Mapping(target="rate", source = "travel_per_hr")
    @Mapping(target="total", source = "travel_cost_total")
    Crm4TravelDTO getTravelDetailsFromModel(Crm4DetailsModel crm4DetailsModel);

    @Mapping(target="decision", source="decision_original")
    @Mapping(target="decisionReason", expression="java(convertDecisionReason(crm4DetailsModel))")
    Crm4DecisionDTO getDecisionFromModel(Crm4DetailsModel crm4DetailsModel);

    @Mapping(target="usn", source="usn")
    @Mapping(target="clientName", source="clientname")
    @Mapping(target="state", source="state")
    @Mapping(target="formType", source="formtype")
    @Mapping(target="dtSubmitted", source="datesubmitted")
    @Mapping(target="decision", source="decision")
    Crm4RelatedSubmissionDTO getCrm4RelatedSubmissionsFromModel(Crm4SubmissionModel cr4RelatedSubmissionModel);

    @Mapping(target="description", source="description")
    @Mapping(target="justification", source="justification")
    @Mapping(target="rate", source="rate")
    @Mapping(target="quantity", source="quantity")
    @Mapping(target="total", source="total")
    @Mapping(target="authorisedTotal", source="total_cw")
    @Mapping(target="authorisedQuantity", source="quantity_cw")
    @Mapping(target="authorisedRate", source="rate_cw")
    Crm4AuthorisedExpenditureDTO getCrm4RelatedSubmissionsFromModel(Crm4AuthorisedExpenditureModel authorisedExpenditureModel);

    @Mapping(target = "usn", source = "usn")
    @Mapping(target="dateReceived", source = "date_received")
    @Mapping(target="timeReceived", source = "time_received")
    @Mapping(target="submitterUserId",expression = "java(null)")
    @Mapping(target="language", expression = "java(null)")
    @Mapping(target="region", source = "lsc_region")
    @Mapping(target="office", source = "lsc_accountoffice")
    CrmStandardPropertiesDTO getStandardPropertiesFromModel(Crm4DetailsModel model);
}
