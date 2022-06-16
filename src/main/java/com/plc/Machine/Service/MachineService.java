package com.plc.Machine.Service;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.Machine;

public interface MachineService {
    Machine save(MachineSaveDto machineSaveDto);
}
