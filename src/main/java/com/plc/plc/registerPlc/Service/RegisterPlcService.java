package com.plc.plc.registerPlc.Service;

import com.plc.plc.registerPlc.Dto.RegisterPlcSaveDto;
import com.plc.plc.registerPlc.Entity.RegisterPlc;

import java.util.List;

public interface RegisterPlcService {
    RegisterPlc saveData(RegisterPlcSaveDto registerPlcSaveDto);
    RegisterPlc getById(Long registerPlcId);
    RegisterPlc updateRegisterPlcData(Long registerPlcId,RegisterPlcSaveDto registerPlcSaveDto);
    List<?> findAll();
    void deleteById(Long registerPlcId);
}
