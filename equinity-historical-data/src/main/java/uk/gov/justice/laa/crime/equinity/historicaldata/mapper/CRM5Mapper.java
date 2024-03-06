package uk.gov.justice.laa.crime.equinity.historicaldata.mapper;

//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.CRM5Details;
import uk.gov.justice.laa.crime.equinity.historicaldata.generated.dto.Firm;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.CrmFormDetails;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Fielddata;

//@Mapper
public interface CRM5Mapper {


    CRM5Details fromJsonToDto(CrmFormDetails formDetails);

  //  @Mapping(target="firmName", source="fielddata.firm_phone")
    Firm firmJsonToDto(Fielddata fielddata);
}
