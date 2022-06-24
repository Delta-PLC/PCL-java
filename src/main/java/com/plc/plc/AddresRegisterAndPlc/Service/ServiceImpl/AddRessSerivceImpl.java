package com.plc.plc.AddresRegisterAndPlc.Service.ServiceImpl;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import com.plc.plc.AddresRegisterAndPlc.Repository.AddresInRegisterRepository;
import com.plc.plc.AddresRegisterAndPlc.Service.AddresRegisterService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddRessSerivceImpl implements AddresRegisterService {
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

    @Override
    public List<AddresRegisterTypeEntity> findByCompanyPlcDataPlcCompanyId(Long companyPlcId) {
        return addresInRegisterRepository.findByCompanyPlcDataPlcCompanyId(companyPlcId)
                .stream()
                .distinct()
                .sorted(Comparator.comparing(AddresRegisterTypeEntity::getAdd_reg_id))
                .collect(Collectors.toList());
    }
}
