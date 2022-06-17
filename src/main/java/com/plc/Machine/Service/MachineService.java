package com.plc.Machine.Service;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.MachineEntity;

import java.util.List;

public interface MachineService {
    MachineEntity save(MachineSaveDto machineSaveDto);
    MachineEntity update (Long machineId, MachineSaveDto machineSaveDto);
    Object Delete(Long machineId);
    List<MachineEntity> findAll();
    MachineEntity findById(Long machineId);
}
