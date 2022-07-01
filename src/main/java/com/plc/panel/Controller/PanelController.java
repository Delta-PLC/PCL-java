package com.plc.panel.Controller;

import com.plc.panel.Dto.PanelSaveDto;
import com.plc.panel.Service.ServiceImpl.PanelServiceImpl;
import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/machine/api")
public class PanelController {
    private static final Logger log= LoggerFactory.getLogger(PanelController.class);
    private final PanelServiceImpl machineServiceImpl;

    public PanelController(PanelServiceImpl machineServiceImpl) {
        this.machineServiceImpl = machineServiceImpl;
    }
    @PostMapping
    public ResponseEntity<?> saveMachine(@RequestBody PanelSaveDto panelSaveDto) throws SQLException {
        Object machineData=machineServiceImpl.save(panelSaveDto);


        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";

        Connection con= DriverManager.getConnection(url, user, password);
        //String select_sql= "select * from public.tblmachine_details;";
        String select_sql= "select tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port, company_details.company_name,tblplc_customer.reg_name, tbl_add_panel_with_register_tag.register_tag, addres_regisater_type.address from tblmachine_details left join company_details on tblmachine_details.c_id=company_details.company_id left join tblplc_customer on tblmachine_details.machine_id =tblplc_customer.m_id left join tbl_add_panel_with_register_tag on tblmachine_details.machine_id=tbl_add_panel_with_register_tag.penal_id left join plc_company on tblmachine_details.plc_id_=plc_company.plc_company_id left join addres_regisater_type on tblmachine_details.plc_id_=addres_regisater_type.company_plc_id";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        ResultSet rs = pstn.executeQuery();
        int i=1;
        try {
            JSONArray obj = new JSONArray();
            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {


                int dev_id=rs.getInt("dev_id");
                int machine_port=rs.getInt("machine_port");
                String machine_ip=rs.getString("machine_ip");
                String company_name=rs.getString("company_name");
                String reg_name=rs.getString("reg_name");
                String register_tag=rs.getString("register_tag");
                int address=rs.getInt("address");



                i++;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("IP",machine_ip);
                jsonObject.put("unitid",dev_id);
                jsonObject.put("port",machine_port);
               // jsonObject.put("company",company_name);
               // jsonObject.put("method",reg_name);
               // jsonObject.put("lport",register_tag);
               // jsonObject.put("2222",address);


                JSONArray work = new JSONArray();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("method",reg_name);
                jsonObject1.put("lport",register_tag);
                jsonObject1.put("2222",address);
                work.add(jsonObject1);

               //jsonObject.put("Work", work);

                obj.add(work);
                obj.add(jsonObject);


            }
            file.write(String.valueOf(obj));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        log.info("machine save Data =>{} ",machineData);
        return new ResponseEntity<>(PageResponse.SuccessResponse(machineData), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{machineId}")
    public ResponseEntity<?> updateMachine(@PathVariable Long machineId, @RequestBody PanelSaveDto panelSaveDto) throws SQLException {

        Object updateMachine=machineServiceImpl.update(machineId, panelSaveDto);

        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";
        int i=1;
        Connection con= DriverManager.getConnection(url, user, password);
        //System.out.println("connection done");
//        String select_sql= "select * from public.tblmachine_details;";
       // String select_sql= "select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port, company_details.company_name,tblplc_customer.reg_name, tbl_add_panel_with_register_tag.register_tag, addres_regisater_type.address from tblmachine_details left join company_details on tblmachine_details.c_id=company_details.company_id left join tblplc_customer on tblmachine_details.machine_id =tblplc_customer.m_id left join tbl_add_panel_with_register_tag on tblmachine_details.machine_id=tbl_add_panel_with_register_tag.penal_id left join plc_company on tblmachine_details.plc_id_=plc_company.plc_company_id left join addres_regisater_type on tblmachine_details.plc_id_=addres_regisater_type.company_plc_id where tblmachine_details.machine_id='"+i+"'";
        String select_sql= "select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port, company_details.company_name,tblplc_customer.reg_name, tbl_add_panel_with_register_tag.register_tag, addres_regisater_type.address,tbl_plcreg_type.plc_register from tblmachine_details left join company_details on tblmachine_details.c_id=company_details.company_id left join tblplc_customer on tblmachine_details.machine_id =tblplc_customer.m_id left join tbl_add_panel_with_register_tag on tblmachine_details.machine_id=tbl_add_panel_with_register_tag.penal_id left join plc_company on tblmachine_details.plc_id_=plc_company.plc_company_id left join addres_regisater_type on tblmachine_details.plc_id_=addres_regisater_type.company_plc_id left join tbl_plcreg_type on tblmachine_details.plc_id_=tbl_plcreg_type.register_plc_id";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        ResultSet rs = pstn.executeQuery();



        try {
            JSONArray obj = new JSONArray();

            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {

                //data from tblmachine_details

                int dev_id=rs.getInt("dev_id");
                int machine_port=rs.getInt("machine_port");
                String machine_ip=rs.getString("machine_ip");
                String company_name=rs.getString("company_name");



                String reg_name=rs.getString("reg_name");
                String register_tag=rs.getString("register_tag");
                int address=rs.getInt("address");
                String plc_register=rs.getString("plc_register");



                JSONObject jsonObject = new JSONObject();
                jsonObject.put("IP",machine_ip);
                jsonObject.put("unitid",dev_id);
                jsonObject.put("port",machine_port);



                List<String> listStrings = new ArrayList<>();
                listStrings.add(reg_name);
                listStrings.add(plc_register);
                listStrings.add(register_tag);
                //listStrings.add(String.valueOf(address));

                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("Method",listStrings);

                List<String> listStrings1 = new ArrayList<>();
                listStrings1.add(String.valueOf(address));
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("lport",listStrings1);

                JSONArray obj1 = new JSONArray();
                obj1.add(jsonObject1);
                obj1.add(jsonObject2);

                jsonObject.put("work",obj1);
                jsonObject.put("company",company_name);

                obj.add(jsonObject);

                i++;
            }

            file.write(String.valueOf(obj));
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        {
//            "machineName": "Machine2",
//                "machineIp": "192.168.14.2",
//                "machinePort": 502,
//                "devId": 2,
//                "permission": "write",
//                "active": false
//        }

        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine),HttpStatus.OK);

    }
    @GetMapping(value = "getDataById/{machineId}")
    public ResponseEntity<?> getById(@PathVariable Long machineId)
    {
        Object updateMachine=machineServiceImpl.findById(machineId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAll()
    {
        Object updateMachine=machineServiceImpl.findAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine),HttpStatus.OK);
    }
    @GetMapping(value = "deleteDataById/{machineId}")
    public ResponseEntity<?> deleteById(@PathVariable Long machineId)
    {
        Object updateMachine=machineServiceImpl.Delete(machineId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine),HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{machineId}/update/{companyId}")
    public ResponseEntity<?> companyRemoveIdMachine(@PathVariable Long machineId, @PathVariable Long companyId) {
        machineServiceImpl.removeCompanyInMachine(machineId, companyId);

        return new ResponseEntity<>(new MessageResponse("update successfully " +
                machineId), HttpStatus.ACCEPTED);
    }
}

