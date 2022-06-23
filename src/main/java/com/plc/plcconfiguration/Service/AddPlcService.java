package com.plc.plcconfiguration.Service;

import com.plc.plcconfiguration.Entity.AddPlc;
import org.springframework.stereotype.Component;

@Component
public interface AddPlcService {

    public AddPlc saveAddPlc(AddPlc addPlc);
    public AddPlc findByAddPlcId(Long plcid);

}
