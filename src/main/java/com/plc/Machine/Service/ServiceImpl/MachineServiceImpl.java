package com.plc.Machine.Service.ServiceImpl;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.Machine;
import com.plc.Machine.Repository.MachineRepository;
import com.plc.Machine.Service.MachineService;
import com.plc.exception.ExceptionService.MachineNotFound;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
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

    @Override
    public Machine update(Long machineId, MachineSaveDto machineSaveDto) {
        Machine machine=machineRepository.findById(machineId).orElseThrow(()->new MachineNotFound("sorry ! Machine is Not Found"));
        if (machine.getMachine_id()!= null)
        {
            machine.setMachineName(machineSaveDto.getMachineName());
            machine.setMachineIp(machineSaveDto.getMachineIp());
            machine.setMachinePort(machineSaveDto.getMachinePort());
            machine.setDevId(machineSaveDto.getDevId());
            machine.setPermission(machineSaveDto.getPermission());
            machine.setActive(machineSaveDto.isActive());
        }
        return machineRepository.save(machine);
    }

    @Override
    public Object Delete(Long machineId) {
        machineRepository.deleteById(machineId);
        return null;
    }

    @Override
    public List<Machine> findAll() {
        return machineRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Machine::getMachine_id))
                .collect(Collectors.toList());
    }

    @Override
    public Machine findById(Long machineId) {
        return machineRepository.findById(machineId)
                .orElseThrow(()->new MachineNotFound("sorry ! Machine is Not Found"));
    }


}
