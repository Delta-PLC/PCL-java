package com.plc.plc.registerPlc.Service.ServiceImpl;

import com.plc.exception.ExceptionService.ResourceNotFound;
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

    public RegisterPlcServiceImpl(RegisterPlcRepository registerPlcRepository) {
        this.registerPlcRepository = registerPlcRepository;
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
}
