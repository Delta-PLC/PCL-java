package com.plc.panel.Service.ServiceImpl;

import com.plc.panel.Dto.PanelSaveDto;
import com.plc.panel.Entity.PanelEntity;
import com.plc.panel.Repository.PanelRepository;
import com.plc.panel.Service.PanelService;
import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Repository.CompanyRepository;
import com.plc.exception.ExceptionService.CompanyNotFound;
import com.plc.exception.ExceptionService.MachineNotFound;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PanelServiceImpl implements PanelService {
    private final PanelRepository panelRepository;
    private final CompanyRepository companyRepository;
    public PanelServiceImpl(PanelRepository panelRepository, CompanyRepository companyRepository) {
        this.panelRepository = panelRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public PanelEntity save(PanelEntity panelEntity) {
        Optional<CompanyEntity> company=companyRepository.findById(panelEntity.getCompanyEntityList().getCompany_id());
        panelEntity.setCompanyEntityList(company.get());
        return panelRepository.save(panelEntity);
    }

    @Override
    public PanelEntity update(Long machineId, PanelSaveDto panelSaveDto) {
        PanelEntity panelEntity = panelRepository.findById(machineId).orElseThrow(()->new MachineNotFound("sorry ! Machine is Not Found"));
        if (panelEntity.getMachine_id()!= null)
        {
            panelEntity.setMachineName(panelSaveDto.getMachineName());
            panelEntity.setMachineIp(panelSaveDto.getMachineIp());
            panelEntity.setMachinePort(panelSaveDto.getMachinePort());
            panelEntity.setDevId(panelSaveDto.getDevId());
            panelEntity.setPermissionn(panelSaveDto.getPermissionn());
            panelEntity.setMachineActive(panelSaveDto.isMachineActive());
        }
        return panelRepository.save(panelEntity);
    }

    @Override
    public Object Delete(Long machineId) {
        panelRepository.deleteById(machineId);
        return null;
    }

    @Override
    public List<PanelEntity> findAll() {
        return panelRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(PanelEntity::getMachine_id))
                .collect(Collectors.toList());
    }

    @Override
    public PanelEntity findById(Long machineId) {
        return panelRepository.findById(machineId)
                .orElseThrow(()->new MachineNotFound("sorry ! Machine  Not Found"));
    }

    @Override
    public PanelEntity updateCompanyId(Long machineId, Long companyId) {
        PanelEntity machine= panelRepository.findById(machineId).orElseThrow(()->new MachineNotFound("sorry ! Machine  Not Found"));
        CompanyEntity company=companyRepository.findById(companyId).
                orElseThrow(()->new CompanyNotFound("sorry ! Company  Not Found"));
        machine.companyIdUpdate(company);
        return panelRepository.save(machine);
    }

    @Override
    public void removeCompanyInMachine(Long companyId, Long machineId) {
        PanelEntity machine= panelRepository
                .findById(machineId)
                .orElseThrow(()->new MachineNotFound("sorry ! Machine  Not Found"));
        CompanyEntity company=companyRepository
                .findById(companyId)
                .orElseThrow(()->new CompanyNotFound("sorry ! Company  Not Found"));
        company.removeCompanyIdInMachine(machine);
        companyRepository.save(company);
    }


}
