package com.plc.plc.AddPanelWithRegisterType.Service.serviceImpl;

import com.plc.exception.ExceptionService.ResourceNotFound;
import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;
import com.plc.plc.AddPanelWithRegisterType.Repository.AddPanelWithRegisterTypeRepository;
import com.plc.plc.AddPanelWithRegisterType.Service.AddPanelWithRegisterTypeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddPanelWithRegisterTypeServiceImpl implements AddPanelWithRegisterTypeService {
    private final AddPanelWithRegisterTypeRepository addPanelWithRegisterTypeRepository;

    public AddPanelWithRegisterTypeServiceImpl(AddPanelWithRegisterTypeRepository addPanelWithRegisterTypeRepository) {
        this.addPanelWithRegisterTypeRepository = addPanelWithRegisterTypeRepository;
    }

    public AddPanelWithRegisterType saveData(AddPanelWithRegisterType addPanelWithRegisterType) {
        return this.addPanelWithRegisterTypeRepository.save(addPanelWithRegisterType);
    }

    public AddPanelWithRegisterType findById(Long id) {
        return this.addPanelWithRegisterTypeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("resource Not Found"));
    }

    @Override
    public List<AddPanelWithRegisterType> findAll() {
        return this.addPanelWithRegisterTypeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(AddPanelWithRegisterType::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddPanelWithRegisterType> findByPanelDataAndRegisterType(Long machine_id, Long registerPlcId) {
        return this.findByPanelDataAndRegisterType(machine_id, registerPlcId)
                .stream()
                .sorted(Comparator.comparing(AddPanelWithRegisterType::getId))
                .collect(Collectors.toList());
    }
}
