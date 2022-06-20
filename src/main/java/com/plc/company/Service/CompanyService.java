package com.plc.company.Service;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.dto.CompanySaveDto;

import java.util.List;


public interface CompanyService {
    CompanyEntity save(CompanySaveDto companySaveDto);
    CompanyEntity findById(Long CompanyId);
    List<?> findAll();
}
