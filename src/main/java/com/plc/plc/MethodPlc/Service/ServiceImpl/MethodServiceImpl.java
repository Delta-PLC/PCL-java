package com.plc.plc.MethodPlc.Service.ServiceImpl;

import com.plc.exception.ExceptionService.CompanyPlcNotFound;
import com.plc.exception.ExceptionService.ResourceNotFound;
import com.plc.plc.MethodPlc.Dto.MethodSaveDto;
import com.plc.plc.MethodPlc.Entity.Method;
import com.plc.plc.MethodPlc.Repository.MethodPlcRepository;
import com.plc.plc.MethodPlc.Service.MethodService;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import com.plc.plc.customerPlc.Repository.CustomerPlcRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class MethodServiceImpl implements MethodService {
    private final MethodPlcRepository methodPlcRepository;
    private final CustomerPlcRepository customerPlcRepository;
    public MethodServiceImpl(MethodPlcRepository methodPlcRepository, CustomerPlcRepository customerPlcRepository) {
        this.methodPlcRepository = methodPlcRepository;
        this.customerPlcRepository = customerPlcRepository;
    }

    @Override
    public Method saveData(MethodSaveDto methodSaveDto) {
        Method method=new Method();
        method.setMethodName(methodSaveDto.getMethodName());
        method.setActive(methodSaveDto.isActive());
        return methodPlcRepository.save(method);
    }

    @Override
    public Method findById(Long methodId) {
        return methodPlcRepository.findById(methodId)
                .orElseThrow(()->new ResourceNotFound("Method Not Found"));
    }

    @Override
    public List<?> getAll() {
        return methodPlcRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Method::getMethodId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMethodById(Long methodId) {
        methodPlcRepository.deleteById(methodId);
    }

    @Override
    public Method updateData(Long methodId, MethodSaveDto methodSaveDto) {
     Method method=   methodPlcRepository.findById(methodId)
                .orElseThrow(()->new ResourceNotFound("Method Not Found"));
     if (method.getMethodId()!=null)
     {
         method.setMethodName(methodSaveDto.getMethodName());
         method.setActive(methodSaveDto.isActive());
     }
        return methodPlcRepository.save(method);
    }

    @Override
    public void UpdateMethodIdInCustomerPlc(Long methodId, Long customerPlcId) {
        Method method=   methodPlcRepository.findById(methodId)
                .orElseThrow(()->new ResourceNotFound("Method Not Found"));
        CustomerPlc customerPlc=customerPlcRepository.findById(customerPlcId)
                .orElseThrow(()->new CompanyPlcNotFound("Company Plc Not Found"));
        customerPlc.updateCustomerPlc(method);
        customerPlcRepository.save(customerPlc);
    }

    @Override
    public void RemoveMethodIdInCustomerPlc(Long methodId, Long customerPlcId) {
        Method method=   methodPlcRepository.findById(methodId)
                .orElseThrow(()->new ResourceNotFound("Method Not Found"));
        CustomerPlc customerPlc=customerPlcRepository.findById(customerPlcId)
                .orElseThrow(()->new CompanyPlcNotFound("Company Plc Not Found"));
        method.RemoveCustomerPlc(customerPlc);
        methodPlcRepository.save(method);
    }
}
