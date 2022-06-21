package com.plc.plc.MethodPlc.Controller;

import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import com.plc.plc.MethodPlc.Dto.MethodSaveDto;
import com.plc.plc.MethodPlc.Entity.Method;
import com.plc.plc.MethodPlc.Service.ServiceImpl.MethodServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/method/api")
public class MethodController {
    private  final MethodServiceImpl methodServiceImpl;

    public MethodController(MethodServiceImpl methodServiceImpl) {
        this.methodServiceImpl = methodServiceImpl;
    }


    @PostMapping
    public ResponseEntity<?> saveMethodData(@RequestBody MethodSaveDto methodSaveDto) {
        Method method = methodServiceImpl.saveData(methodSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(method), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{methodId}")
    public ResponseEntity<?> getById(@PathVariable Long methodId) {
        Method methodByid = methodServiceImpl.findById(methodId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(methodByid), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllMethod() {
        List<?> AllMethod = methodServiceImpl.getAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(AllMethod), HttpStatus.OK);
    }

    @PutMapping(value = "/{methodId}")
    public ResponseEntity<?> updateMethodData(@PathVariable Long methodId,@RequestBody MethodSaveDto methodSaveDto) {
        Method method = methodServiceImpl.updateData(methodId, methodSaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(method), HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/{methodId}")
    public ResponseEntity<?> deleteMethodDataById(@PathVariable Long methodId)
    {
        methodServiceImpl.deleteMethodById(methodId);
        return new ResponseEntity<>(new MessageResponse("Delete SuccessFully "+methodId),HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{methodId}/update/{customerPlcId}")
    public ResponseEntity<?> updateCustomerPlc(@PathVariable Long methodId,@PathVariable Long customerPlcId)
    {
        methodServiceImpl.UpdateMethodIdInCustomerPlc(methodId, customerPlcId);
        return new ResponseEntity<>(new MessageResponse("update SuccessFully "+customerPlcId),HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{methodId}/update/{customerPlcId}")
    public ResponseEntity<?> deleteCustomerPlc(@PathVariable Long methodId,@PathVariable Long customerPlcId)
    {
        methodServiceImpl.RemoveMethodIdInCustomerPlc(methodId, customerPlcId);
        return new ResponseEntity<>(new MessageResponse("delete SuccessFully "+customerPlcId),HttpStatus.NO_CONTENT);
    }
}
