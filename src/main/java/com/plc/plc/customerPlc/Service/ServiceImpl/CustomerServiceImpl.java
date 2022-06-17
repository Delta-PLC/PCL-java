package com.plc.plc.customerPlc.Service.ServiceImpl;

import com.plc.exception.ExceptionService.ResourceNotFound;
import com.plc.plc.customerPlc.Dto.CustomerPlcSaveDto;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import com.plc.plc.customerPlc.Repository.CustomerPlcRepository;
import com.plc.plc.customerPlc.Service.CustomerPlcService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerPlcService {
    private final CustomerPlcRepository customerPlcRepository;

    public CustomerServiceImpl(CustomerPlcRepository customerPlcRepository) {
        this.customerPlcRepository = customerPlcRepository;
    }

    @Override
    public CustomerPlc saveData(CustomerPlcSaveDto customerPlcSaveDto) {
        CustomerPlc customerPlc = new CustomerPlc();
        customerPlc.setRegName(customerPlcSaveDto.getRegName());
        customerPlc.setRegAni(customerPlcSaveDto.getRegAni());
        customerPlc.setCustomerActive(customerPlcSaveDto.isCustomerActive());
        return customerPlcRepository.save(customerPlc);
    }

    @Override
    public CustomerPlc getById(Long customerPlcId) {
        return customerPlcRepository.findById(customerPlcId)
                .orElseThrow(() -> new ResourceNotFound("sorry !  can not found Any this type Data"));
    }

    @Override
    public CustomerPlc updateCustomerPlcData(Long customerPlcId, CustomerPlcSaveDto customerPlcSaveDto) {
        CustomerPlc customerPlc = customerPlcRepository.findById(customerPlcId)
                .orElseThrow(() -> new ResourceNotFound("sorry !  can not found Any this type Data"));
        if (customerPlc.getCustomerPlcId() != null) {
            customerPlc.setRegName(customerPlcSaveDto.getRegName());
            customerPlc.setRegAni(customerPlcSaveDto.getRegAni());
            customerPlc.setCustomerActive(customerPlcSaveDto.isCustomerActive());
        }
        return customerPlcRepository.save(customerPlc);
    }

    @Override
    public List<?> findAllCustomerPlcData() {
        return customerPlcRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(CustomerPlc::getCustomerPlcId))
                .collect(Collectors.toList());
    }

    @Override
    public void DeleteById(Long customerPlcId) {
        customerPlcRepository.findById(customerPlcId);

    }
}
