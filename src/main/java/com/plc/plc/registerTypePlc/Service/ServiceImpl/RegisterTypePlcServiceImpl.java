package com.plc.plc.registerTypePlc.Service.ServiceImpl;

import com.plc.exception.ExceptionService.ResourceNotFound;
import com.plc.plc.registerTypePlc.Dto.RegisterTypePlcSaveDto;
import com.plc.plc.registerTypePlc.Entity.RegisterTypePlc;
import com.plc.plc.registerTypePlc.Repository.RegisterTypePlcRepository;
import com.plc.plc.registerTypePlc.Service.RegisterTypePlcService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterTypePlcServiceImpl implements RegisterTypePlcService {
    private final RegisterTypePlcRepository registerTypePlcRepository;

    public RegisterTypePlcServiceImpl(RegisterTypePlcRepository registerTypePlcRepository) {
        this.registerTypePlcRepository = registerTypePlcRepository;
    }

    @Override
    public RegisterTypePlc saveData(RegisterTypePlcSaveDto registerTypePlcSaveDto) {
        RegisterTypePlc registerTypePlc = new RegisterTypePlc();
        registerTypePlc.setPlcRegister(registerTypePlcSaveDto.getPlcRegister());
        registerTypePlc.setActive(registerTypePlcSaveDto.isActive());
        return registerTypePlcRepository.save(registerTypePlc);
    }

    @Override
    public RegisterTypePlc getById(Long registerPlcId) {
        return registerTypePlcRepository.findById(registerPlcId).orElseThrow(() -> new ResourceNotFound("sorry ! can not Found A Register Number"));
    }

    @Override
    public RegisterTypePlc updateRegisterPlcData(Long registerPlcId, RegisterTypePlcSaveDto registerTypePlcSaveDto) {
        RegisterTypePlc registerTypePlc = registerTypePlcRepository.findById(registerPlcId).orElseThrow(() -> new ResourceNotFound("sorry ! can not Found A Register Number"));
        if (registerTypePlc.getRegisterPlcId() != null) {
            registerTypePlc.setPlcRegister(registerTypePlcSaveDto.getPlcRegister());
            registerTypePlc.setActive(registerTypePlcSaveDto.isActive());

        }
        return registerTypePlcRepository.save(registerTypePlc);
    }

    @Override
    public List<?> findAll() {
        return registerTypePlcRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(RegisterTypePlc::getRegisterPlcId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long registerPlcId) {
        registerTypePlcRepository.deleteById(registerPlcId);
    }
}
