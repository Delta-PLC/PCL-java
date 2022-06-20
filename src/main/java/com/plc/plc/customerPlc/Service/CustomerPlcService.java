package com.plc.plc.customerPlc.Service;

import com.plc.plc.customerPlc.Dto.CustomerPlcSaveDto;
import com.plc.plc.customerPlc.Entity.CustomerPlc;

import java.util.List;

public interface CustomerPlcService {
    CustomerPlc saveData(CustomerPlcSaveDto customerPlcSaveDto);
    CustomerPlc getById(Long customerPlcId);
    CustomerPlc updateCustomerPlcData(Long customerPlcId,CustomerPlcSaveDto customerPlcSaveDto);
    List<?> findAllCustomerPlcData();
    void DeleteById(Long customerPlcId);
}
