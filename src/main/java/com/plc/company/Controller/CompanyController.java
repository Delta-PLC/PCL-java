package com.plc.company.Controller;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Service.ServiceImpl.CompanyServiceImpl;
import com.plc.company.dto.CompanySaveDto;
import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/company/api")
public class CompanyController {
    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody CompanySaveDto companySaveDto) {
        Object data = companyServiceImpl.save(companySaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(data), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<?> deleteDataById(@PathVariable Long companyId) {
        companyServiceImpl.DeleteCompanyData(companyId);
        return new ResponseEntity<>(new MessageResponse("delete Successfully =>" + companyId), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{companyId}")
    public ResponseEntity<?> getByIdCompanyData(@PathVariable Long companyId) {
        CompanyEntity company = companyServiceImpl.findByCompanyId(companyId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(company), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllData() {
        List<?> getAllCompanyData = companyServiceImpl.findCompanyDataAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(getAllCompanyData), HttpStatus.OK);
    }


    @PutMapping(value = "/{companyId}")
    public ResponseEntity<?> updateCompanyData(@PathVariable Long companyId, @RequestBody CompanySaveDto companySaveDto) {
        CompanyEntity companyEntity = companyServiceImpl.updateCompanyData(companyId, companySaveDto);
        return new ResponseEntity<>(PageResponse.SuccessResponse(companyEntity), HttpStatus.OK);
    }

}

