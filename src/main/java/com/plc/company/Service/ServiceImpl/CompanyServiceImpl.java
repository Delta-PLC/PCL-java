package com.plc.company.Service.ServiceImpl;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Repository.CompanyRepository;
import com.plc.company.Service.CompanyService;
import com.plc.company.dto.CompanySaveDto;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyEntity save(CompanySaveDto companySaveDto) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName(companySaveDto.getCompanyName());
        companyEntity.setCompanyAdd(companySaveDto.getCompanyAdd());
        companyEntity.setCompanyEmail(companySaveDto.getCompanyEmail());
        companyEntity.setCompanyNumber(companySaveDto.getCompanyNumber());
        companyEntity.setCompanyGstNumber(companySaveDto.getCompanyGstNumber());
        companyEntity.setCompanyActive(companySaveDto.isCompanyActive());
        return companyRepository.save(companyEntity);
    }
}
