package com.plc.plc.registerTypePlc.Service;

import com.plc.plc.registerTypePlc.Dto.RegisterTypePlcSaveDto;
import com.plc.plc.registerTypePlc.Entity.RegisterTypePlc;

import java.util.List;

public interface RegisterTypePlcService {
    RegisterTypePlc saveData(RegisterTypePlcSaveDto registerTypePlcSaveDto);
    RegisterTypePlc getById(Long registerPlcId);
    RegisterTypePlc updateRegisterPlcData(Long registerPlcId, RegisterTypePlcSaveDto registerTypePlcSaveDto);
    List<?> findAll();
    void deleteById(Long registerPlcId);
}
