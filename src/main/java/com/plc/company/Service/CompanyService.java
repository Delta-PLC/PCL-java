package com.plc.company.Service;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.dto.CompanySaveDto;


public interface CompanyService {
    CompanyEntity save(CompanySaveDto companySaveDto);}
