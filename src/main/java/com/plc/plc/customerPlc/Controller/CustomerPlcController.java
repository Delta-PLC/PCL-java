package com.plc.plc.customerPlc.Controller;

import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import com.plc.plc.customerPlc.Dto.CustomerPlcSaveDto;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import com.plc.plc.customerPlc.Service.ServiceImpl.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/customerPlc/api")
public class CustomerPlcController {
    private final CustomerServiceImpl customerServiceImpl;

    public CustomerPlcController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveCustomerPlcData(@RequestBody CustomerPlcSaveDto customerPlcSaveDto) {
        CustomerPlc customerPlc = customerServiceImpl.saveData(customerPlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(customerPlc), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customerPlcId}")
    public ResponseEntity<?> getById(@PathVariable Long customerPlcId) {
        CustomerPlc customerPlc = customerServiceImpl.getById(customerPlcId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(customerPlc), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCustomerPlc() {
        List<?> customerPlc = customerServiceImpl.findAllCustomerPlcData();
        return new ResponseEntity<>(PageResponse.SuccessResponse(customerPlc), HttpStatus.OK);
    }

    @PutMapping(value = "/{customerPlcId}")
    public ResponseEntity<?> updateCustomerPlcData(@PathVariable Long customerPlcId, @RequestBody CustomerPlcSaveDto customerPlcSaveDto) {
        CustomerPlc customerPlc = customerServiceImpl.updateCustomerPlcData(customerPlcId, customerPlcSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(customerPlc), HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/{customerPlcId}")
    public ResponseEntity<?> deleteCustomerPlcDataById(@PathVariable Long customerPlcId)
    {
        customerServiceImpl.DeleteById(customerPlcId);
        return new ResponseEntity<>(new MessageResponse("Delete SuccessFully "+customerPlcId),HttpStatus.NO_CONTENT);
    }
}
