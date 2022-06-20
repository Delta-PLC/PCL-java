package com.plc.plc.registerPlc.Service.ServiceImpl;

import com.plc.exception.ExceptionService.CompanyPlcNotFound;
import com.plc.exception.ExceptionService.ResourceNotFound;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.companyPlc.Repository.CompanyPlcRepository;
import com.plc.plc.registerPlc.Dto.RegisterPlcSaveDto;
import com.plc.plc.registerPlc.Entity.RegisterPlc;
import com.plc.plc.registerPlc.Repository.RegisterPlcRepository;
import com.plc.plc.registerPlc.Service.RegisterPlcService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterPlcServiceImpl implements RegisterPlcService {

    private final RegisterPlcRepository registerPlcRepository;
    private final CompanyPlcRepository companyPlcRepository;
    public RegisterPlcServiceImpl(RegisterPlcRepository registerPlcRepository, CompanyPlcRepository companyPlcRepository) {
        this.registerPlcRepository = registerPlcRepository;
        this.companyPlcRepository = companyPlcRepository;
    }

    @Override
    public RegisterPlc saveData(RegisterPlcSaveDto registerPlcSaveDto) {
        RegisterPlc registerPlc = new RegisterPlc();
        registerPlc.setPlcRegister(registerPlcSaveDto.getPlcRegister());
        registerPlc.setActive(registerPlcSaveDto.isActive());
        return registerPlcRepository.save(registerPlc);
    }

    @Override
    public RegisterPlc getById(Long registerPlcId) {
        return registerPlcRepository.findById(registerPlcId).orElseThrow(() -> new ResourceNotFound("sorry ! can not Found A Register Number"));
    }

    @Override
    public RegisterPlc updateRegisterPlcData(Long registerPlcId, RegisterPlcSaveDto registerPlcSaveDto) {
        RegisterPlc registerPlc = registerPlcRepository.findById(registerPlcId).orElseThrow(() -> new ResourceNotFound("sorry ! can not Found A Register Number"));
        if (registerPlc.getRegisterPlcId() != null) {
            registerPlc.setPlcRegister(registerPlcSaveDto.getPlcRegister());
            registerPlc.setActive(registerPlcSaveDto.isActive());

        }
        return registerPlcRepository.save(registerPlc);
    }

    @Override
    public List<?> findAll() {
        return registerPlcRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(RegisterPlc::getRegisterPlcId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long registerPlcId) {
        registerPlcRepository.deleteById(registerPlcId);
    }

    @Override
    public void updateCompanyPlcIdInRegisterPlc(Long companyPlcId, Long registerPlcId) {
        CompanyPlc companyPlc=companyPlcRepository.findById(companyPlcId)
                .orElseThrow(()->new CompanyPlcNotFound("Company Plc Not Found => "+companyPlcId));
        RegisterPlc registerPlc=registerPlcRepository.findById(registerPlcId)
                .orElseThrow(()->new ResourceNotFound("Register Not Found => "+registerPlcId));
        registerPlc.updateCompanyPlcId(companyPlc);
        registerPlcRepository.save(registerPlc);
    }

    @Override
    public void RemoveCompanyPlcIdInRegisterPlc(Long registerPlcId,Long companyPlcId ) {
        RegisterPlc registerPlc=registerPlcRepository.findById(registerPlcId)
                .orElseThrow(()->new ResourceNotFound("Register Not Found => "+registerPlcId));
        CompanyPlc companyPlc=companyPlcRepository.findById(companyPlcId)
                .orElseThrow(()->new CompanyPlcNotFound("Company Plc Not Found => "+companyPlcId));

        companyPlc.RemoveCompanyPlcId(registerPlc);
        companyPlcRepository.save(companyPlc);
    }
}
