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
    public final Logger logger= LoggerFactory.getLogger(AddressController.class);
    private final AddRessSerivceImpl addRessSerivceImpl;

    public AddressController(AddRessSerivceImpl addRessSerivceImpl) {
        this.addRessSerivceImpl = addRessSerivceImpl;
    }
    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody AddresRegisterTypeEntity addresRegisterTypeEntity)
    {
        AddresRegisterTypeEntity data=addRessSerivceImpl.addresRegisterTypeEntity(addresRegisterTypeEntity);
        logger.info("addres Data {} ",data);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{aId}")
    public ResponseEntity<?> findById(@PathVariable Long aId)
    {
        return new ResponseEntity<>(addRessSerivceImpl.findById(aId),HttpStatus.OK);
    }
    @GetMapping(value = "/{comapnayPlcId}")
    public ResponseEntity<?> findByCompanyPlcId(@PathVariable Long comapnayPlcId)
    {
        List<?> data=addRessSerivceImpl.findByCompanyPlcDataPlcCompanyId(comapnayPlcId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data),HttpStatus.OK);
    }
}
