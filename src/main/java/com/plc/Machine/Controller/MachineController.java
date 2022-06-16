package com.plc.Machine.Controller;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Service.ServiceImpl.MachineServiceImpl;
import com.plc.payload.Response.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/machine/api")
public class MachineController {
    private static final Logger log= LoggerFactory.getLogger(MachineController.class);
    private final MachineServiceImpl machineServiceImpl;

    public MachineController(MachineServiceImpl machineServiceImpl) {
        this.machineServiceImpl = machineServiceImpl;
    }
    @PostMapping
    public ResponseEntity<?> saveMachine(@RequestBody MachineSaveDto machineSaveDto)
    {
        Object machineData=machineServiceImpl.save(machineSaveDto);
        log.info("machine save Data =>{} ",machineData);
        return new ResponseEntity<>(PageResponse.SuccessResponse(machineData), HttpStatus.CREATED);
    }
}
