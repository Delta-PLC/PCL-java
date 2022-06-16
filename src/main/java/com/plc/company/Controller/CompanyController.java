package com.plc.company.Controller;

import com.plc.company.Service.ServiceImpl.CompanyServiceImpl;
import com.plc.company.dto.CompanySaveDto;
import com.plc.payload.Response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
