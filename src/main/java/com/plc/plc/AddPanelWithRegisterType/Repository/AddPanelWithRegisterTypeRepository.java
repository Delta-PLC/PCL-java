package com.plc.plc.AddPanelWithRegisterType.Repository;

import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddPanelWithRegisterTypeRepository extends JpaRepository<AddPanelWithRegisterType,Long> {
//    @Query("select a from AddPanelWithRegisterType  a where a.paneldata.machine_id=:machine_id and a.registerTypeData.registerPlcId=:registerPlcId")
//    List<AddPanelWithRegisterType>  findByPanelDataAndRegisterType(Long machine_id,Long registerPlcId);
}
