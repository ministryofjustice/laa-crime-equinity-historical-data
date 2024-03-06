package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.api.Crm5InterfaceApi;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5DetailsDTO;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Crm5Model;
import uk.gov.justice.laa.crime.equinity.historicaldata.service.TaskSearchService;

@RestController
@RequiredArgsConstructor
public class Crm5Controller implements Crm5InterfaceApi {
    private final TaskSearchService taskService;
//    private final CRM5Mapper crm5Mapper;

    @Override
    public ResponseEntity<CRM5DetailsDTO> getApplication(Long usn) {
        CRM5DetailsDTO crm5DetailsDTO = new CRM5DetailsDTO();
        Crm5Model crmFormDetailsModel = getCrmFile(usn);
        // Mapping
        crm5DetailsDTO.setUsn(Integer.parseInt(Long.toString(usn)));
        crm5DetailsDTO.setDetailsOfApplication(crmFormDetailsModel.getTargetpath());
        // Return
        return ResponseEntity.ok(crm5DetailsDTO);
    }
    public Crm5Model getCrmFile(long taskId) {
        Crm5Model crmFormDetails = null;
        JSONObject crmFileJsonObject = taskService.getCrmFormJson(taskId);
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            crmFormDetails = om.readValue(crmFileJsonObject.toString(), Crm5Model.class);
            System.out.println("getTargetPath::"+crmFormDetails.getTargetpath());
//            System.out.println("getSolicitorid::"+crmFormDetails.getFielddataObject().getSolicitorid());
//            System.out.println("Fc_reject_reasons_text::"+crmFormDetails.getFielddataObject().getFc_reject_reasons_text());
//            System.out.println("getDate_received::"+crmFormDetails.getFielddataObject().getClient_dob());
//            System.out.println("getLinkedAttachment::"+crmFormDetails.getLinkedAttachmentsObject().getLinkedAttachment().getName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        Crm5FormDetailsModel crm5Details = crm5Mapper.fromJsonToDto(crmFormDetails);
//        System.out.println("USN**::"+crm5Details.getUsn());

        return crmFormDetails;
    }
}
