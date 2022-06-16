package com.plc.company.Service.ServiceImpl;

import com.plc.company.Entity.Company;
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
    public Company save(CompanySaveDto companySaveDto) {
        Company company = new Company();
        company.setCompanyName(companySaveDto.getCompanyName());
        company.setCompanyAdd(companySaveDto.getCompanyAdd());
        company.setCompanyEmail(companySaveDto.getCompanyEmail());
        company.setCompanyNumber(companySaveDto.getCompanyNumber());
        company.setCompanyGstNumber(companySaveDto.getCompanyGstNumber());
        company.setActive(companySaveDto.isActive());
        return companyRepository.save(company);
    }
}
