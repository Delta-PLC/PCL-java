package com.plc.plc.registerTypePlc.Controller;

import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import com.plc.plc.registerTypePlc.Dto.RegisterTypePlcSaveDto;
import com.plc.plc.registerTypePlc.Entity.RegisterTypePlc;
import com.plc.plc.registerTypePlc.Service.ServiceImpl.RegisterTypePlcServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/registerPlc/api")
public class RegisterTypePlcController {
    private final RegisterTypePlcServiceImpl registerPlcServiceImpl;

    public RegisterTypePlcController(RegisterTypePlcServiceImpl registerPlcServiceImpl) {
        this.registerPlcServiceImpl = registerPlcServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody RegisterTypePlcSaveDto registerTypePlcSaveDto) {
        RegisterTypePlc registerTypePlc = registerPlcServiceImpl.saveData(registerTypePlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(registerTypePlc), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{registerPlcId}")
    public ResponseEntity<?> updateRegisterPlcData(@PathVariable Long registerPlcId, @RequestBody RegisterTypePlcSaveDto registerTypePlcSaveDto) {
        RegisterTypePlc registerTypePlc = registerPlcServiceImpl.updateRegisterPlcData(registerPlcId, registerTypePlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(registerTypePlc), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getById/{registerPlcId}")
    public ResponseEntity<?> getById(@PathVariable Long registerPlcId) {
        RegisterTypePlc registerTypePlc = registerPlcServiceImpl.getById(registerPlcId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(registerTypePlc), HttpStatus.OK);
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

}
