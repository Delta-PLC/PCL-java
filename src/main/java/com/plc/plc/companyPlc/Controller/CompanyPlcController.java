package com.plc.plc.companyPlc.Controller;

import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import com.plc.plc.companyPlc.Dto.CompanyPlcSaveDto;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.companyPlc.service.ServiceImpl.CompanyPlcServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/companyPlc/api")
public class CompanyPlcController {
    private final CompanyPlcServiceImpl companyPlcServiceImpl;

    public CompanyPlcController(CompanyPlcServiceImpl companyPlcServiceImpl) {
        this.companyPlcServiceImpl = companyPlcServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody CompanyPlcSaveDto companyPlcSaveDto) {
        Object saveData = companyPlcServiceImpl.saveData(companyPlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(saveData), HttpStatus.CREATED);
    }

    @GetMapping(value = "getById/{plcId}")
    public ResponseEntity<?> getById(@PathVariable Long plcId) {
        CompanyPlc companyPlc = companyPlcServiceImpl.getById(plcId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(companyPlc),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CompanyPlc> AllData = companyPlcServiceImpl.findAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(AllData), HttpStatus.OK);
    }
    @GetMapping(value = "/distinct")
    public ResponseEntity<?> getAllDistinctByPlcName() {
        List<String > AllData = companyPlcServiceImpl.findDistinctByPlcName();
        return new ResponseEntity<>(PageResponse.SuccessResponse(AllData), HttpStatus.OK);
    }
    @GetMapping(value = "/findByPlcName")
    public ResponseEntity<?> getAllPlcName() {
        List<CompanyPlc> AllData = companyPlcServiceImpl.findByPlcName();
        return new ResponseEntity<>(PageResponse.SuccessResponse(AllData), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{plcId}")
    public ResponseEntity<?> dataDeleteById(@PathVariable Long plcId)
    {
        companyPlcServiceImpl.deleteById(plcId);
        return new ResponseEntity<>(new MessageResponse("Delete SuccessFully "+plcId),HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/update/{plcId}")
    public ResponseEntity<?> updateData(@PathVariable Long plcId, @RequestBody CompanyPlcSaveDto companyPlcSaveDto) {
        CompanyPlc companyPlc = companyPlcServiceImpl.updateCompanyPlc(plcId, companyPlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(companyPlc), HttpStatus.OK);
    }
}
