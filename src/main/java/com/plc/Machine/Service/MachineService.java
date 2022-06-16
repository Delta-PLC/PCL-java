package com.plc.Machine.Service;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.Machine;

import java.util.List;

public interface MachineService {
    Machine save(MachineSaveDto machineSaveDto);
    Machine update (Long machineId,MachineSaveDto machineSaveDto);
    Object Delete(Long machineId);
    List<Machine> findAll();
    Machine findById(Long machineId);
}
