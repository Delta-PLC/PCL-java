package com.plc.Machine.Service.ServiceImpl;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.Machine;
import com.plc.Machine.Repository.MachineRepository;
import com.plc.Machine.Service.MachineService;

public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Machine save(MachineSaveDto machineSaveDto) {
        Machine machine = new Machine();
        machine.setMachineName(machineSaveDto.getMachineName());
        machine.setMachineIp(machineSaveDto.getMachineIp());
        machine.setMachinePort(machineSaveDto.getMachinePort());
        machine.setDevId(machineSaveDto.getDevId());
        machine.setPermission(machineSaveDto.getPermission());
        machine.setActive(machineSaveDto.isActive());
        return machineRepository.save(machine);
    }
}
