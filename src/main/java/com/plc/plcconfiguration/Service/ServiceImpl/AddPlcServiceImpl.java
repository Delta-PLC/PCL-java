package com.plc.plcconfiguration.Service.ServiceImpl;

import com.plc.plcconfiguration.Entity.AddPlc;
import com.plc.plcconfiguration.Entity.RegisterType;
import com.plc.plcconfiguration.Repository.AddPlcRepository;
import com.plc.plcconfiguration.Service.AddPlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AddPlcServiceImpl implements AddPlcService {

    @Autowired
    private AddPlcRepository addPlcRepository;


    @Override
    public AddPlc saveAddPlc(AddPlc addPlc) {
        List<RegisterType> registerType = new ArrayList<>();

        RegisterType r1=new RegisterType();
        r1.setTypeName("Coil");

        RegisterType r2=new RegisterType();
        r2.setTypeName("Input Register");

        RegisterType r3=new RegisterType();
        r3.setTypeName("Holding Register");

        registerType.add(r1);
        registerType.add(r2);
        registerType.add(r3);
        // Prepare data for ManyToOne
        r1.setAddPlc(addPlc);
        r2.setAddPlc(addPlc);
        r3.setAddPlc(addPlc);
        addPlc.setRegisterType(registerType);
        addPlc = addPlcRepository.save(addPlc);
        return addPlc;
    }

    @Override
    public AddPlc findByAddPlcId(Long plcid) {
        AddPlc addPlc = addPlcRepository.findByPlcid(plcid);
        return addPlc;
    }
}
