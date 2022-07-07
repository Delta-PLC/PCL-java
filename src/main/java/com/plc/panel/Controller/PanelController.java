package com.plc.panel.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        String select_sql="select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port,tbl_plcreg_type.plc_register from tblmachine_details left join tbl_plcreg_type on tblmachine_details.plc_id_=tbl_plcreg_type.register_plc_id";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        ResultSet rs = pstn.executeQuery();
        int i=1;
        try {
            JSONArray obj = new JSONArray();
            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {

                int machine_id = rs.getInt("machine_id");
                String machine_ip = rs.getString("machine_ip");
                int dev_id = rs.getInt("dev_id");
                int machine_port = rs.getInt("machine_port");
                String plc_register = rs.getString("plc_register");
                //  String company_name = rs.getString("company_name");
                //int address = rs.getInt("address");
                //String plc_name = rs.getString("plc_name");
                //String plc_mode = rs.getString("plc_mode");
                //String reg_name = rs.getString("reg_name");
                //String register_tag = rs.getString("register_tag");
                //String plc_register = rs.getString("plc_register");

                i++;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("IP",machine_ip);
                jsonObject.put("unitid",dev_id);
                jsonObject.put("port",machine_port);

                JSONObject jsonObject1 = new JSONObject();
                JSONArray obj1 = new JSONArray();

                List<String> listStrings = new ArrayList<>();
                listStrings.add(plc_register);

                jsonObject1.put("Method",listStrings);
                List<String> listStrings1 = new ArrayList<>();
                String select_sql1="select addres_regisater_type.address from addres_regisater_type left join tblmachine_details on addres_regisater_type.company_plc_id=tblmachine_details.c_id where machine_id='"+machine_id+"'";
                PreparedStatement pstn1 = con.prepareStatement(select_sql1);
                ResultSet rs1 = pstn1.executeQuery();
                while (rs1.next())
                {
                    int address = rs1.getInt("address");
                    listStrings1.add(String.valueOf(address));
                }
                //rs1.close();
                jsonObject1.put("lport",listStrings1);

                obj1.add(jsonObject1);
                jsonObject.put("work",obj1);
               // jsonObject.put("company",company_name);
                obj.add(jsonObject);
               // System.out.println("json array:"+obj);

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

        Object updateMachine = machineServiceImpl.update(machineId, panelSaveDto);

        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";
        int i = 1;
        Connection con = DriverManager.getConnection(url, user, password);

        //String select_sql="select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port,addres_regisater_type.address,plc_company.plc_name,plc_company.plc_mode,tbl_add_panel_with_register_tag.register_tag, tbl_plcreg_type.plc_register from tblmachine_details left join addres_regisater_type on tblmachine_details.plc_id_=addres_regisater_type.company_plc_id left join plc_company on tblmachine_details.plc_id_=plc_company.plc_company_id left join tbl_add_panel_with_register_tag on tblmachine_details.machine_id=tbl_add_panel_with_register_tag.penal_id left join tbl_plcreg_type on tblmachine_details.plc_id_=tbl_plcreg_type.register_plc_id";
        //String select_sql = "select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port, company_details.company_name,tblplc_customer.reg_name, tbl_add_panel_with_register_tag.register_tag, addres_regisater_type.address,tbl_plcreg_type.plc_register from tblmachine_details left join company_details on tblmachine_details.c_id=company_details.company_id left join tblplc_customer on tblmachine_details.machine_id =tblplc_customer.m_id left join tbl_add_panel_with_register_tag on tblmachine_details.machine_id=tbl_add_panel_with_register_tag.penal_id left join plc_company on tblmachine_details.plc_id_=plc_company.plc_company_id left join addres_regisater_type on tblmachine_details.plc_id_=addres_regisater_type.company_plc_id left join tbl_plcreg_type on tblmachine_details.plc_id_=tbl_plcreg_type.register_plc_id";

        String select_sql="select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port,tbl_plcreg_type.plc_register from tblmachine_details left join tbl_plcreg_type on tblmachine_details.plc_id_=tbl_plcreg_type.register_plc_id ";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        ResultSet rs = pstn.executeQuery();


        try {
            JSONArray obj = new JSONArray();

            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {

                int machine_id = rs.getInt("machine_id");
                String machine_ip = rs.getString("machine_ip");
                int dev_id = rs.getInt("dev_id");
                int machine_port = rs.getInt("machine_port");
                String plc_register = rs.getString("plc_register");
                System.out.println("----------------------:"+plc_register);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("IP", machine_ip);
                jsonObject.put("unitid", String.valueOf(dev_id));
                jsonObject.put("port", String.valueOf(machine_port));
                JSONObject jsonObject1 = new JSONObject();
                JSONArray obj1 = new JSONArray();

                List<String> listStrings = new ArrayList<>();
                listStrings.add(plc_register);
                jsonObject1.put("Method",listStrings);
                System.out.println("=====================:"+listStrings);

                List<String> listStrings1 = new ArrayList<>();
                String select_sql1="select addres_regisater_type.address from addres_regisater_type left join tblmachine_details on addres_regisater_type.company_plc_id=tblmachine_details.c_id where machine_id='"+machine_id+"'";
                PreparedStatement pstn1 = con.prepareStatement(select_sql1);
                ResultSet rs1 = pstn1.executeQuery();
                while (rs1.next())
                {
                    int address = rs1.getInt("address");
                    System.out.println("address: "+address);
                    listStrings1.add(String.valueOf(address));
                }
                //rs1.close();
                jsonObject1.put("lport",listStrings1);

                obj1.add(jsonObject1);
                jsonObject.put("work",obj1);
                // jsonObject.put("company",company_name);
                obj.add(jsonObject);
                // System.out.println("json array:"+obj);

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
//    "machineName":"machine1",
//    "machineIp":"10.60.82.01",
//    "machinePort":1,
//    "devId":1,
//    "permissionn":"read",
//    "machineActive":true,
//    "companyEntityList": {
//            "company_id": 1
//    }
//}
//{
//    "machineName":"machine47",
//   "machineIp":"10.60.82.47",
//    "machinePort":47,
//   "devId":47,
//    "permissionn":"write",
//    "machineActive":true,
//   "companyEntityList": {
//            "company_id": 1
//    }
//}

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

