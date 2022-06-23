package com.plc.Panel.Service;

import com.plc.Panel.Dto.PanelSaveDto;
import com.plc.Panel.Entity.PanelEntity;

import java.util.List;

public interface PanelService {
    PanelEntity save(PanelSaveDto panelSaveDto);
    PanelEntity update (Long machineId, PanelSaveDto panelSaveDto);
    Object Delete(Long machineId);
    List<PanelEntity> findAll();
    PanelEntity findById(Long machineId);

    PanelEntity updateCompanyId(Long machineId, Long companyId);
    void removeCompanyInMachine(Long companyId,Long machineId);
}
