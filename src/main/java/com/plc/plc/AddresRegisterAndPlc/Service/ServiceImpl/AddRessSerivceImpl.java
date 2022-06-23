package com.plc.plc.AddresRegisterAndPlc.Service.ServiceImpl;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import com.plc.plc.AddresRegisterAndPlc.Repository.AddresInRegisterRepository;
import org.springframework.stereotype.Service;

@Service
public class AddRessSerivceImpl {
    private final AddresInRegisterRepository addresInRegisterRepository;

    public AddRessSerivceImpl(AddresInRegisterRepository addresInRegisterRepository) {
        this.addresInRegisterRepository = addresInRegisterRepository;
    }
    public AddresRegisterTypeEntity addresRegisterTypeEntity(AddresRegisterTypeEntity addresRegisterTypeEntity)
    {
        return addresInRegisterRepository.save(addresRegisterTypeEntity);
    }
    public AddresRegisterTypeEntity findById(Long aId)
    {
        return addresInRegisterRepository.findById(aId)
                .orElseThrow(()->new RuntimeException("address Not Found"));
    }
}
