package com.plc.Machine.Service.ServiceImpl;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.MachineEntity;
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
    public MachineEntity save(MachineSaveDto machineSaveDto) {
        MachineEntity machineEntity = new MachineEntity();
        machineEntity.setMachineName(machineSaveDto.getMachineName());
        machineEntity.setMachineIp(machineSaveDto.getMachineIp());
        machineEntity.setMachinePort(machineSaveDto.getMachinePort());
        machineEntity.setDevId(machineSaveDto.getDevId());
        machineEntity.setPermissionn(machineSaveDto.getPermissionn());
        machineEntity.setMachineActive(machineSaveDto.isMachineActive());
        return machineRepository.save(machineEntity);
    }

    @Override
    public MachineEntity update(Long machineId, MachineSaveDto machineSaveDto) {
        MachineEntity machineEntity =machineRepository.findById(machineId).orElseThrow(()->new MachineNotFound("sorry ! Machine is Not Found"));
        if (machineEntity.getMachine_id()!= null)
        {
            machineEntity.setMachineName(machineSaveDto.getMachineName());
            machineEntity.setMachineIp(machineSaveDto.getMachineIp());
            machineEntity.setMachinePort(machineSaveDto.getMachinePort());
            machineEntity.setDevId(machineSaveDto.getDevId());
            machineEntity.setPermissionn(machineSaveDto.getPermissionn());
            machineEntity.setMachineActive(machineSaveDto.isMachineActive());
        }
        return machineRepository.save(machineEntity);
    }

    @Override
    public Object Delete(Long machineId) {
        machineRepository.deleteById(machineId);
        return null;
    }

    @Override
    public List<MachineEntity> findAll() {
        return machineRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MachineEntity::getMachine_id))
                .collect(Collectors.toList());
    }

    @Override
    public MachineEntity findById(Long machineId) {
        return machineRepository.findById(machineId)
                .orElseThrow(()->new MachineNotFound("sorry ! Machine is Not Found"));
    }


}
