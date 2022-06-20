package com.plc.plc.MethodPlc.Service;

import com.plc.plc.MethodPlc.Dto.MethodSaveDto;
import com.plc.plc.MethodPlc.Entity.Method;

import java.util.List;

public interface MethodService {
    Method saveData(MethodSaveDto methodSaveDto);
    Method findById(Long methodId);
    List<?> getAll();
    void deleteMethodById(Long methodId);
    Method updateData(Long methodId,MethodSaveDto methodSaveDto);
}
