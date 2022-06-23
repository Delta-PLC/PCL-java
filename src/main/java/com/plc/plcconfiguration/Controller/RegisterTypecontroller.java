package com.plc.plcconfiguration.Controller;

import com.plc.plcconfiguration.Entity.RegisterType;
import com.plc.plcconfiguration.Repository.AddPlcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/register")
public class RegisterTypecontroller {
    @Autowired
    AddPlcRepository addPlcRepository;
    @ResponseBody
    @RequestMapping(value = "/reg")
    public List<?> getRegisterTypes() {
        List<?> registerType = (List<?>) addPlcRepository.findAll();
        return registerType;
    }
}
