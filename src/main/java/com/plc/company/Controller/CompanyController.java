package com.plc.company.Controller;

import com.plc.company.Service.ServiceImpl.CompanyServiceImpl;
import com.plc.company.dto.CompanySaveDto;
import com.plc.payload.Response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "company/api")
public class CompanyController {
    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }
    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody CompanySaveDto companySaveDto)
    {
        Object data=companyServiceImpl.save(companySaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getAllCompanyData()
    {
        Object data=companyServiceImpl.findAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.OK);
    }
    @GetMapping(value = "/{companyId}")
    public ResponseEntity<?> getByIdCompanyData(@PathVariable Long companyId)
    {
        Object data=companyServiceImpl.findById(companyId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.OK);
    }

}
