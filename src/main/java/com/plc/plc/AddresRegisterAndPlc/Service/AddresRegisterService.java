package com.plc.plc.AddresRegisterAndPlc.Service;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;

import java.util.List;

public interface AddresRegisterService {
    List<AddresRegisterTypeEntity> findByCompanyPlcDataPlcCompanyId(Long companyPlcId);
}
