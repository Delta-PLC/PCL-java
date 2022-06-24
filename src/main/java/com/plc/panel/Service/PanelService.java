package com.plc.panel.Service;

import com.plc.panel.Dto.PanelSaveDto;
import com.plc.panel.Entity.PanelEntity;

import java.util.List;

public interface PanelService {
    PanelEntity save(PanelEntity panelEntity);
    PanelEntity update (Long machineId, PanelSaveDto panelSaveDto);
    Object Delete(Long machineId);
    List<PanelEntity> findAll();
    PanelEntity findById(Long machineId);

    PanelEntity updateCompanyId(Long machineId, Long companyId);
    void removeCompanyInMachine(Long companyId,Long machineId);
}
