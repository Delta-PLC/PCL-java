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
    private static final Logger log = LoggerFactory.getLogger(PanelController.class);
    private final PanelServiceImpl machineServiceImpl;

    public PanelController(PanelServiceImpl machineServiceImpl) {
        this.machineServiceImpl = machineServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveMachine(@RequestBody PanelSaveDto panelSaveDto) throws SQLException, IOException {

        Object machineData = machineServiceImpl.save(panelSaveDto);
        log.info("machine save Data =>{} ", machineData);
//        log.info("machine save Data =>{} ",machineData);
        return new ResponseEntity<>(PageResponse.SuccessResponse(machineData), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{machineId}")
    public ResponseEntity<?> updateMachine(@PathVariable Long machineId, @RequestBody PanelSaveDto panelSaveDto) throws SQLException {

        Object updateMachine = machineServiceImpl.update(machineId, panelSaveDto);

        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, user, password);
        String select_sql = "select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port from tblmachine_details";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        ResultSet rs = pstn.executeQuery();


        try {
            JSONArray obj = new JSONArray();

            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {

                int machine_id = rs.getInt("machine_id");
                String machine_ip = rs.getString("machine_ip");
                log.info("--------------machinneip :{}",machine_ip);
                int dev_id = rs.getInt("dev_id");
                int machine_port = rs.getInt("machine_port");


                Map<String, Object> jsonObjectPayload = new LinkedHashMap<>();
                jsonObjectPayload.put("IP", machine_ip);
                jsonObjectPayload.put("unitid", String.valueOf(dev_id));
                jsonObjectPayload.put("port", String.valueOf(machine_port));


//						JSONObject jsonObject = new JSONObject();
//
//						jsonObject.put("IP", machine_ip);
//						jsonObject.put("unitid", String.valueOf(dev_id));
//						jsonObject.put("port", String.valueOf(machine_port));

                JSONArray obj1 = new JSONArray();

                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();
                JSONObject jsonObject3 = new JSONObject();

                List<String> listStrings1 = new ArrayList<>();
                List<String> listStrings2 = new ArrayList<>();
                List<String> listStrings3 = new ArrayList<>();

                String sql = "select addres_regisater_type.address , addres_regisater_type.add_reg_id,tbl_add_panel_with_register_tag.add_reg_id from addres_regisater_type left join tbl_add_panel_with_register_tag on addres_regisater_type.add_reg_id=tbl_add_panel_with_register_tag.add_reg_id where penal_id='" + machine_id + "'";
                PreparedStatement pstnsql = con.prepareStatement(sql);
                ResultSet rssql = pstnsql.executeQuery();
                while (rssql.next()) {


                    String address = rssql.getString("address");


                    String sql1 = "select addres_regisater_type.address,tbl_plcreg_type.plc_register from addres_regisater_type left join tbl_plcreg_type on addres_regisater_type.reg_id=tbl_plcreg_type.register_plc_id where address='" + address + "'";
                    PreparedStatement pstnsql1 = con.prepareStatement(sql1);
                    ResultSet rssql1 = pstnsql1.executeQuery();
                    while (rssql1.next()) {


                        String plc_register = rssql1.getString("plc_register");
                        //System.out.println("plc_register: " + plc_register);
                        String address1 = rssql1.getString("address");
                        //System.out.println("address1: " + address1);


                        if (plc_register.equals("Coil Register")) {
                            jsonObject1.put("method", plc_register);
                            listStrings1.add(address1);
                            jsonObject1.put("lport", listStrings1);

                        }

                        if (plc_register.equals("Holding Register")) {
                            jsonObject2.put("method", plc_register);
                            listStrings2.add(address1);
                            jsonObject2.put("lport", listStrings2);
                        }

                        if (plc_register.equals("Input Output Register")) {
                            jsonObject3.put("method", plc_register);
                            listStrings3.add(address1);
                            //System.out.println("listStrings3: " + listStrings3);
                            jsonObject3.put("lport", listStrings3);

                        }


                    }

                }
                if (jsonObject1.isEmpty()) {

                } else {
                    obj1.add(jsonObject1);
                }
                if (jsonObject2.isEmpty()) {

                } else {
                    obj1.add(jsonObject2);
                }
                if (jsonObject3.isEmpty()) {

                } else {
                    obj1.add(jsonObject3);
                }


                jsonObjectPayload.put("work", obj1);
                obj.add(jsonObjectPayload);
            }

            file.write(String.valueOf(obj));
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine), HttpStatus.OK);

    }

    @GetMapping(value = "getDataById/{machineId}")
    public ResponseEntity<?> getById(@PathVariable Long machineId) {
        Object updateMachine = machineServiceImpl.findById(machineId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Object updateMachine = machineServiceImpl.findAll();
        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine), HttpStatus.OK);
    }

    @GetMapping(value = "deleteDataById/{machineId}")
    public ResponseEntity<?> deleteById(@PathVariable Long machineId) {
        Object updateMachine = machineServiceImpl.Delete(machineId);
        return new ResponseEntity<>(PageResponse.SuccessResponse(updateMachine), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{machineId}/update/{companyId}")
    public ResponseEntity<?> companyRemoveIdMachine(@PathVariable Long machineId, @PathVariable Long companyId) {
        machineServiceImpl.removeCompanyInMachine(machineId, companyId);

        return new ResponseEntity<>(new MessageResponse("update successfully " +
                machineId), HttpStatus.ACCEPTED);
    }
}




