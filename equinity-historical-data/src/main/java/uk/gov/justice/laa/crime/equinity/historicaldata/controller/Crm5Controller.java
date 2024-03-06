package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm5InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.TaskSearchService;

@RestController
@RequiredArgsConstructor
public class Crm5Controller implements Crm5InterfaceApi {
    private final TaskSearchService taskService;
//    private final CRM5Mapper crm5Mapper;

    @Override
    public ResponseEntity<CRM5DetailsDTO> getApplication(Long usn) {
        CRM5DetailsDTO crm5Details = new CRM5DetailsDTO();
//        CrmFormDetails CrmFormDetails = getCrmFile(usn);
//        crm5Details.setUsn(CrmFormDetails.getFielddataObject().getUsn());
        return ResponseEntity.ok(crm5Details);
    }
//    public CrmFormDetails getCrmFile(long taskId) {
//        CrmFormDetails crmFormDetails = null;
//        JSONObject crmFileJsonObject = taskService.getXMLJsonObject(taskId);
//        ObjectMapper om = new ObjectMapper();
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        try {
//            crmFormDetails = om.readValue(crmFileJsonObject.toString(), CrmFormDetails.class);
//            System.out.println("getSubmitter_user_id::"+crmFormDetails.getFielddataObject().getSubmitter_user_id());
//            System.out.println("getSolicitorid::"+crmFormDetails.getFielddataObject().getSolicitorid());
//            System.out.println("Fc_reject_reasons_text::"+crmFormDetails.getFielddataObject().getFc_reject_reasons_text());
//            System.out.println("getDate_received::"+crmFormDetails.getFielddataObject().getClient_dob());
//            System.out.println("getLinkedAttachment::"+crmFormDetails.getLinkedAttachmentsObject().getLinkedAttachment().getName());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        CRM5Details crm5Details = crm5Mapper.fromJsonToDto(crmFormDetails);
//        System.out.println("USN**::"+crm5Details.getUsn());
//
//        return crmFormDetails;
//    }
}
