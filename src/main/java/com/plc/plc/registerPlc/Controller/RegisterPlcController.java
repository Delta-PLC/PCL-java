package com.plc.plc.registerPlc.Controller;

import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import com.plc.plc.registerPlc.Dto.RegisterPlcSaveDto;
import com.plc.plc.registerPlc.Entity.RegisterPlc;
import com.plc.plc.registerPlc.Service.ServiceImpl.RegisterPlcServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/registerPlc/api")
public class RegisterPlcController {
    private final RegisterPlcServiceImpl registerPlcServiceImpl;

    public RegisterPlcController(RegisterPlcServiceImpl registerPlcServiceImpl) {
        this.registerPlcServiceImpl = registerPlcServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody RegisterPlcSaveDto registerPlcSaveDto) {
        RegisterPlc registerPlc = registerPlcServiceImpl.saveData(registerPlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(registerPlc), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{registerPlcId}")
    public ResponseEntity<?> updateRegisterPlcData(@PathVariable Long registerPlcId, @RequestBody RegisterPlcSaveDto registerPlcSaveDto) {
        RegisterPlc registerPlc = registerPlcServiceImpl.updateRegisterPlcData(registerPlcId, registerPlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(registerPlc), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getById/{registerPlcId}")
    public ResponseEntity<?> getById(@PathVariable Long registerPlcId) {
        RegisterPlc registerPlc = registerPlcServiceImpl.getById(registerPlcId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(registerPlc), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<?> AllRegisterData = registerPlcServiceImpl.findAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(AllRegisterData), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{registerPlcId}")
    public ResponseEntity<?> deleteById(@PathVariable Long registerPlcId) {
        registerPlcServiceImpl.deleteById(registerPlcId);
        return new ResponseEntity<>(new MessageResponse("Delete SuccessFully " + registerPlcId), HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "{companyPlcId}/updateInRegister/{registerPlcId}")
    public ResponseEntity<?> updateCompanyPlcInRegister(@PathVariable Long companyPlcId,@PathVariable Long registerPlcId)
    {
        registerPlcServiceImpl.updateCompanyPlcIdInRegisterPlc(companyPlcId,registerPlcId);
        return new ResponseEntity<>(new MessageResponse("Company Plc "+ companyPlcId +"update in Register "+registerPlcId),HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "{registerPlcId}/updateInRegister/{companyPlcId}")
    public ResponseEntity<?> RemoveCompanyPlcInRegister(@PathVariable Long registerPlcId,@PathVariable Long companyPlcId)
    {
        registerPlcServiceImpl.updateCompanyPlcIdInRegisterPlc(registerPlcId,registerPlcId);
        return new ResponseEntity<>(new MessageResponse("Company Plc "+ companyPlcId +"remove in Register "+registerPlcId),HttpStatus.NO_CONTENT);
    }
}
