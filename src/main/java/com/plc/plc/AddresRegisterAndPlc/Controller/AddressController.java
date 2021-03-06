package com.plc.plc.AddresRegisterAndPlc.Controller;

import com.plc.payload.Response.PageResponse;
import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import com.plc.plc.AddresRegisterAndPlc.Service.ServiceImpl.AddRessSerivceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "address/api")
@CrossOrigin
public class AddressController {
    public final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddRessSerivceImpl addRessSerivceImpl;

    public AddressController(AddRessSerivceImpl addRessSerivceImpl) {
        this.addRessSerivceImpl = addRessSerivceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody AddresRegisterTypeEntity addresRegisterTypeEntity) {
        AddresRegisterTypeEntity data = addRessSerivceImpl.addresRegisterTypeEntity(addresRegisterTypeEntity);
        logger.info("addres Data {} ", data);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{aId}")
    public ResponseEntity<?> findById(@PathVariable Long aId) {
        return new ResponseEntity<>(addRessSerivceImpl.findById(aId), HttpStatus.OK);
    }

    @GetMapping(value = "/com/{comapnayPlcId}")
    public ResponseEntity<?> findByCompanyPlcId(@PathVariable Long comapnayPlcId) {
        List<?> data = addRessSerivceImpl.findByCompanyPlcDataPlcCompanyId(comapnayPlcId);
        logger.info("company plc id By Data {}", data);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.OK);
    }

    @GetMapping(value = "/address")
    public ResponseEntity<?> findByAddress(@RequestParam Integer address) {
        List<AddresRegisterTypeEntity> data=addRessSerivceImpl.findByAddress(address);
        logger.info("find By Address in AddressAndRegisterType {}",data);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.OK);
    }

    @GetMapping(value = "/company/{companyId}/register/{registerId}")
    public ResponseEntity<?> findByCompanyIdAndRegisterId(@PathVariable Long companyId, @PathVariable Long registerId) {
        List<AddresRegisterTypeEntity> addressRegisterTypeEntity = this.addRessSerivceImpl.findByCompanyPlcIdAndRegisterPlcId(companyId, registerId);
        logger.info("company and register in AddressAndRegisterType {}",addressRegisterTypeEntity);
        return new ResponseEntity<>(PageResponse.SuccessResponse(addressRegisterTypeEntity), HttpStatus.OK);
    }
    @GetMapping(value = "/company/{companyId}/register/{registerPlcId}/address/{add_id}")
    public ResponseEntity<?> findByAddressWithRegistrePlcandAddId(

                                                    @PathVariable Long companyId
                                                    ,@PathVariable Long registerPlcId,
                                                    @PathVariable Long add_id)
    {
        AddresRegisterTypeEntity data=this.addRessSerivceImpl.findByAddIdAndCompanyIdAndRegisterId(companyId,registerPlcId,add_id);
        logger.info("plc Name With Adsress {}",data);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data),HttpStatus.OK);
    }
    @GetMapping(value = "/addresswithPlcname/{registerPlcId}")
    public ResponseEntity<?> findByAddressWithRegistrePlc(@RequestParam Integer address
            ,@PathVariable Long registerPlcId)
    {
       List <AddresRegisterTypeEntity> data=this.addRessSerivceImpl.findByAddressAndRegisterPlc(address,registerPlcId);
        logger.info("plc Name With Adsress {}",data);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data),HttpStatus.OK);
    }
}
