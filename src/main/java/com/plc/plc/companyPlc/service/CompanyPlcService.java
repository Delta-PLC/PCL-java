package com.plc.plc.companyPlc.service;

import com.plc.company.dto.CompanySaveDto;
import com.plc.plc.companyPlc.Dto.CompanyPlcSaveDto;
import com.plc.plc.companyPlc.Entity.CompanyPlc;

import java.util.List;

public interface CompanyPlcService {
    CompanyPlc saveData(CompanyPlcSaveDto companyPlcSaveDto);
    CompanyPlc getById(Long companyPlcId);
    List<CompanyPlc> findAll();
    void deleteById(Long companyPlcId);
    CompanyPlc updateCompanyPlc(Long companyPlcId,CompanyPlcSaveDto companyPlcSaveDto);
    List<String> findDistinctByPlcName();
    List<CompanyPlc> findByPlcName(String name);

}
