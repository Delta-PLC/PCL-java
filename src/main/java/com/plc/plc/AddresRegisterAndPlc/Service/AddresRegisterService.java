package com.plc.plc.AddresRegisterAndPlc.Service;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddresRegisterService {
    List<AddresRegisterTypeEntity> findByCompanyPlcDataPlcCompanyId(Long companyPlcId);
    List<AddresRegisterTypeEntity> findAll();
    List<AddresRegisterTypeEntity> findByAddress(Integer address);
    List<AddresRegisterTypeEntity> findByCompanyPlcIdAndRegisterPlcId(Long companyPlcId,Long RegisterPlcId);
    List<AddresRegisterTypeEntity> findByAddressAndRegisterPlc(Integer address,Long registerplcId);
    AddresRegisterTypeEntity findByAddIdAndCompanyIdAndRegisterId(Long plcCompanyId, Long registerPlcId ,Long add_reg_id);

}
