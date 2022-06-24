package com.plc.plc.AddresRegisterAndPlc.Service.ServiceImpl;

import com.plc.exception.ExceptionService.ResourceNotFound;
import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import com.plc.plc.AddresRegisterAndPlc.Repository.AddresInRegisterRepository;
import com.plc.plc.AddresRegisterAndPlc.Service.AddresRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddRessSerivceImpl implements AddresRegisterService {
    public final Logger logger = LoggerFactory.getLogger(AddRessSerivceImpl.class);
    private final AddresInRegisterRepository addresInRegisterRepository;

    public AddRessSerivceImpl(AddresInRegisterRepository addresInRegisterRepository) {
        this.addresInRegisterRepository = addresInRegisterRepository;
    }

    public AddresRegisterTypeEntity addresRegisterTypeEntity(AddresRegisterTypeEntity addresRegisterTypeEntity) {
        return addresInRegisterRepository.save(addresRegisterTypeEntity);
    }

    public AddresRegisterTypeEntity findById(Long aId) {
        return addresInRegisterRepository.findById(aId)
                .orElseThrow(() -> new RuntimeException("address Not Found"));
    }

    @Override
    public List<AddresRegisterTypeEntity> findByCompanyPlcDataPlcCompanyId(Long companyPlcId) {
        return addresInRegisterRepository.findByCompanyPlcDataPlcCompanyId(companyPlcId)
                .stream()
                .distinct()
                .sorted(Comparator.comparing(AddresRegisterTypeEntity::getAdd_reg_id))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddresRegisterTypeEntity> findAll() {
        return addresInRegisterRepository.findAll()
                .stream()
                .distinct()
                .sorted(Comparator.comparing(AddresRegisterTypeEntity::getAdd_reg_id))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddresRegisterTypeEntity> findByAddress(Integer address) {
        List<AddresRegisterTypeEntity> addresRegisterTypeEntity = this.findAll();
        return addresRegisterTypeEntity.stream()
                .filter(a -> {
                    if (address.equals(a.getAddress())) {
                        this.addresInRegisterRepository.findByAddress(address);
                    } else {
                        throw new ResourceNotFound("Resource Not found");
                    }
                    return false;
                }).sorted(Comparator.comparing(AddresRegisterTypeEntity::getAdd_reg_id))
                .collect(Collectors.toList());

    }

    @Override
    public List<AddresRegisterTypeEntity> findByAddressAndRegisterPlc(Integer address, Long registerplcId) {


        return this.addresInRegisterRepository.findByAddressAndRegisterPlc(address, registerplcId)
                .stream()
                .sorted(Comparator.comparing(AddresRegisterTypeEntity::getAdd_reg_id))
                .collect(Collectors.toList());
    }

    @Override
    public AddresRegisterTypeEntity findByAddIdAndCompanyIdAndRegisterId(Long plcCompanyId, Long registerPlcId, Long add_reg_id) {
        return addresInRegisterRepository
                .findByAddIdAndCompanyIdAndRegisterId(plcCompanyId,registerPlcId,add_reg_id);
    }

    @Override
    public List<AddresRegisterTypeEntity> findByCompanyPlcIdAndRegisterPlcId(Long plcCompanyId, Long registerPlcId) {
        return addresInRegisterRepository
                .findByCompanyPlcIdAndRegisterPlcId(plcCompanyId, registerPlcId)
                .stream()
                .distinct()
                .sorted(Comparator.comparing(AddresRegisterTypeEntity::getAdd_reg_id))
                .collect(Collectors.toList());
    }


}
