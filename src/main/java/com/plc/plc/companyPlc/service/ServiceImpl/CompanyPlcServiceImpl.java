package com.plc.plc.companyPlc.service.ServiceImpl;

import com.plc.exception.ExceptionService.CompanyPlcNotFound;
import com.plc.plc.companyPlc.Dto.CompanyPlcSaveDto;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.companyPlc.Repository.CompanyPlcRepository;
import com.plc.plc.companyPlc.service.CompanyPlcService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyPlcServiceImpl implements CompanyPlcService {
    private final CompanyPlcRepository companyPlcRepository;

    public CompanyPlcServiceImpl(CompanyPlcRepository companyPlcRepository) {
        this.companyPlcRepository = companyPlcRepository;
    }

    @Override
    public CompanyPlc saveData(CompanyPlcSaveDto companyPlcSaveDto) {
        CompanyPlc companyPlc=new CompanyPlc();
        companyPlc.setPlcName(companyPlcSaveDto.getPlcName());
        companyPlc.setPlcMode(companyPlcSaveDto.getPlcMode());
        companyPlc.setActive(companyPlcSaveDto.isActive());
        return companyPlcRepository.save(companyPlc);
    }

    @Override
    public CompanyPlc getById(Long companyPlcId) {
        return companyPlcRepository.findById(companyPlcId)
                .orElseThrow(()->new CompanyPlcNotFound("PLC Not found In this Company"));
    }

    @Override
    public List<CompanyPlc> findAll() {
        return companyPlcRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(CompanyPlc::getPlcCompanyId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long companyPlcId) {
         companyPlcRepository.deleteById(companyPlcId);
    }


    @Override
    public CompanyPlc updateCompanyPlc(Long companyPlcId, CompanyPlcSaveDto companyPlcSaveDto) {
       CompanyPlc companyPlc= companyPlcRepository.findById(companyPlcId)
                .orElseThrow(()->new CompanyPlcNotFound("PLC Not found In this Company"));
       if (companyPlc.getPlcCompanyId()!=null)
       {
           companyPlc.setPlcName(companyPlcSaveDto.getPlcName());
           companyPlc.setPlcMode(companyPlcSaveDto.getPlcMode());
           companyPlc.setActive(companyPlcSaveDto.isActive());
       }
        return companyPlcRepository.save(companyPlc);
    }


}
