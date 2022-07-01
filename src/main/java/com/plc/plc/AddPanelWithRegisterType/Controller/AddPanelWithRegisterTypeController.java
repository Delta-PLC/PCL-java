package com.plc.plc.AddPanelWithRegisterType.Controller;

import com.plc.payload.Response.PageResponse;
import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;
import com.plc.plc.AddPanelWithRegisterType.Service.serviceImpl.AddPanelWithRegisterTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/panelWithRegister/api")
public class AddPanelWithRegisterTypeController {
    private final AddPanelWithRegisterTypeServiceImpl addPanelWithRegisterTypeServiceimpl;

    public AddPanelWithRegisterTypeController(AddPanelWithRegisterTypeServiceImpl addPanelWithRegisterTypeServiceimpl) {
        this.addPanelWithRegisterTypeServiceimpl = addPanelWithRegisterTypeServiceimpl;
    }

    @PostMapping
    public ResponseEntity<?> savedata(@RequestBody AddPanelWithRegisterType addPanelWithRegisterType) {
        AddPanelWithRegisterType data = this.addPanelWithRegisterTypeServiceimpl.saveData(addPanelWithRegisterType);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findByid(@PathVariable Long id) {
        AddPanelWithRegisterType data = this.addPanelWithRegisterTypeServiceimpl.findById(id);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.OK);
    }

    @GetMapping(value = "/{panelId}/registerType/{registerId}")
    public ResponseEntity<?> findByPanelIdAndRegisterId(@PathVariable Long panelId, @PathVariable Long registerId) {
        List<?> data = this.addPanelWithRegisterTypeServiceimpl.findByPanelDataAndRegisterType(panelId, registerId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.OK);
    }
}
