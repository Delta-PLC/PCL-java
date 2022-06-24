package com.plc.plc.AddPanelWithRegisterType.Service;

import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;

import java.util.List;

public interface AddPanelWithRegisterTypeService {
    AddPanelWithRegisterType saveData(AddPanelWithRegisterType addPanelWithRegisterType);
    AddPanelWithRegisterType findById(Long id);
    List<AddPanelWithRegisterType> findAll();
    List<AddPanelWithRegisterType>  findByPanelDataAndRegisterType(Long machine_id,Long registerPlcId);


}
