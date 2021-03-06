package com.plc.company.Service.ServiceImpl;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Repository.CompanyRepository;
import com.plc.company.Service.CompanyService;
import com.plc.company.dto.CompanySaveDto;
import com.plc.exception.ExceptionService.CompanyNotFound;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        //companyEntity.getPanelEntityList().add(companySaveDto.getPanelEntity());
        return companyRepository.save(companyEntity);
    }

    @Override
    public CompanyEntity findByCompanyId(Long CompanyId) {
        return companyRepository
                .findById(CompanyId)
                .orElseThrow(() -> new CompanyNotFound("Company Not Found => " + CompanyId));
    }

    @Override
    public List<?> findCompanyDataAll() {
        return companyRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(CompanyEntity::getCompany_id))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyEntity updateCompanyData(Long CompanyId, CompanySaveDto companySaveDto) {
        CompanyEntity companyEntity = companyRepository
                .findById(CompanyId)
                .orElseThrow(() -> new CompanyNotFound("Company Not Found => " + CompanyId));
        if (companyEntity.getCompany_id() != null) {
            companyEntity.setCompanyName(companySaveDto.getCompanyName());
            companyEntity.setCompanyAdd(companySaveDto.getCompanyAdd());
            companyEntity.setCompanyEmail(companySaveDto.getCompanyEmail());
            companyEntity.setCompanyNumber(companySaveDto.getCompanyNumber());
            companyEntity.setCompanyGstNumber(companySaveDto.getCompanyGstNumber());
            companyEntity.setCompanyActive(companySaveDto.isCompanyActive());
        }
        return companyRepository.save(companyEntity);
    }

    @Override
    public void DeleteCompanyData(Long CompanyId) {
        companyRepository.deleteById(CompanyId);
    }


}
