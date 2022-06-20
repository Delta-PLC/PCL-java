package com.plc.Machine.Service.ServiceImpl;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Entity.MachineEntity;
import com.plc.Machine.Repository.MachineRepository;
import com.plc.Machine.Service.MachineService;
import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Repository.CompanyRepository;
import com.plc.exception.ExceptionService.CompanyNotFound;
import com.plc.exception.ExceptionService.MachineNotFound;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;
    private final CompanyRepository companyRepository;
    public MachineServiceImpl(MachineRepository machineRepository, CompanyRepository companyRepository) {
        this.machineRepository = machineRepository;
        this.companyRepository = companyRepository;
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
                .orElseThrow(()->new MachineNotFound("sorry ! Machine  Not Found"));
    }

    @Override
    public MachineEntity updateCompanyId(Long machineId, Long companyId) {
        MachineEntity machine=machineRepository.findById(machineId).orElseThrow(()->new MachineNotFound("sorry ! Machine  Not Found"));
        CompanyEntity company=companyRepository.findById(companyId).
                orElseThrow(()->new CompanyNotFound("sorry ! Company  Not Found"));
        machine.companyIdUpdate(company);
        return machineRepository.save(machine);
    }

    @Override
    public void removeCompanyInMachine(Long companyId, Long machineId) {
        MachineEntity machine=machineRepository
                .findById(machineId)
                .orElseThrow(()->new MachineNotFound("sorry ! Machine  Not Found"));
        CompanyEntity company=companyRepository
                .findById(companyId)
                .orElseThrow(()->new CompanyNotFound("sorry ! Company  Not Found"));
        company.removeCompanyIdInMachine(machine);
        companyRepository.save(company);
    }


}
