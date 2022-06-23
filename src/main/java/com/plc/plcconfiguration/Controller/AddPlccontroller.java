package com.plc.plcconfiguration.Controller;

import com.plc.plcconfiguration.Entity.AddPlc;
import com.plc.plcconfiguration.Repository.AddPlcRepository;
import com.plc.plcconfiguration.Service.AddPlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addplc")
public class AddPlccontroller {

     @Autowired
     private AddPlcService addPlcService;
     @RequestMapping(value = "/saveplc", method = RequestMethod.POST)
     @ResponseBody
     public AddPlc saveaddPlc(@RequestBody AddPlc addPlc) {
          AddPlc apResponse = addPlcService.saveAddPlc(addPlc);
          return apResponse;
     }
     @RequestMapping(value = "/{plcid}", method = RequestMethod.GET)
     @ResponseBody
     public AddPlc getplcDetails(@PathVariable Long plcid) {
          AddPlc apResponse = addPlcService.findByAddPlcId(plcid);
          return apResponse;
     }
}
