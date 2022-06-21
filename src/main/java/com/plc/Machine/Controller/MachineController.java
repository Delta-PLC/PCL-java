package com.plc.Machine.Controller;

import com.plc.Machine.Dto.MachineSaveDto;
import com.plc.Machine.Service.ServiceImpl.MachineServiceImpl;
import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

@RestController
@RequestMapping(value = "/machine/api")
public class MachineController {
    private static final Logger log= LoggerFactory.getLogger(MachineController.class);
    private final MachineServiceImpl machineServiceImpl;

    public MachineController(MachineServiceImpl machineServiceImpl) {
        this.machineServiceImpl = machineServiceImpl;
    }
    @PostMapping
    public ResponseEntity<?> saveMachine(@RequestBody MachineSaveDto machineSaveDto) throws SQLException {
        Object machineData=machineServiceImpl.save(machineSaveDto);


        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";

        Connection con= DriverManager.getConnection(url, user, password);
        //System.out.println("connection done");
        String select_sql= "select * from public.tblmachine_details;";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        System.out.println(pstn);
        ResultSet rs = pstn.executeQuery();
        int i=1;
        try {
            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {

                int mid=rs.getInt("machine_id");
                String cby=rs.getString("created_by");
                String cdate=rs.getString("created_date");
                String mby=rs.getString("modified_by");
                String mdate=rs.getString("modified_date");
                boolean active=rs.getBoolean("machine_active");
                int did=rs.getInt("dev_id");
                String mip=rs.getString("machine_ip");
                String mname=rs.getString("machine_name");
                int mport=rs.getInt("machine_port");
                String per=rs.getString("permissionn");
                int cid=rs.getInt("c_id");

                System.out.println("machine_id: "+mid);
                System.out.println("created_by: "+cby);
                System.out.println("created_date: "+cdate);
                System.out.println("modified_by: "+mby);
                System.out.println("modified_date: "+mdate);
                System.out.println("machine_active: "+active);
                System.out.println("dev_id: "+did);
                System.out.println("machine_ip: "+mip);
                System.out.println("machine_name: "+mname);
                System.out.println("machine_port: "+mport);
                System.out.println("permission: "+per);
                System.out.println("c_id: "+cid);
                System.out.println("____________________________");

                i++;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("machine_id",mid);
//                jsonObject.put("created_by",cby);
//                jsonObject.put("created_date",cdate);
//                jsonObject.put("modified_by",mby);
//                jsonObject.put("modified_date",mdate);
                jsonObject.put("machine_active",active);
                jsonObject.put("dev_id",did);
                jsonObject.put("machine_ip",mip);
                jsonObject.put("machine_name",mname);
                jsonObject.put("machine_port",mport);
                jsonObject.put("permission",per);
                jsonObject.put("c_id",cid);

                file.write(jsonObject.toJSONString());

                System.out.println("JSON file created: "+jsonObject);
            }
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        log.info("machine save Data =>{} ",machineData);
        return new ResponseEntity<>(PageResponse.SuccessResponse(machineData), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{machineId}")
    public ResponseEntity<?> updateMachine(@PathVariable Long machineId, @RequestBody MachineSaveDto machineSaveDto) throws SQLException {

        Object updateMachine=machineServiceImpl.update(machineId,machineSaveDto);

        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";

        Connection con= DriverManager.getConnection(url, user, password);
        //System.out.println("connection done");
        String select_sql= "select * from public.tblmachine_details;";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        System.out.println(pstn);
        ResultSet rs = pstn.executeQuery();
        int i=1;
        try {
            FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
            while (rs.next()) {

                int mid=rs.getInt("machine_id");
                String cby=rs.getString("created_by");
                String cdate=rs.getString("created_date");
                String mby=rs.getString("modified_by");
                String mdate=rs.getString("modified_date");
                boolean active=rs.getBoolean("machine_active");
                int did=rs.getInt("dev_id");
                String mip=rs.getString("machine_ip");
                String mname=rs.getString("machine_name");
                int mport=rs.getInt("machine_port");
                String per=rs.getString("permissionn");
                int cid=rs.getInt("c_id");

                System.out.println("machine_id: "+mid);
                System.out.println("created_by: "+cby);
                System.out.println("created_date: "+cdate);
                System.out.println("modified_by: "+mby);
                System.out.println("modified_date: "+mdate);
                System.out.println("machine_active: "+active);
                System.out.println("dev_id: "+did);
                System.out.println("machine_ip: "+mip);
                System.out.println("machine_name: "+mname);
                System.out.println("machine_port: "+mport);
                System.out.println("permission: "+per);
                System.out.println("c_id: "+cid);
                System.out.println("____________________________");

                i++;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("machine_id",mid);
//                jsonObject.put("created_by",cby);
//                jsonObject.put("created_date",cdate);
//                jsonObject.put("modified_by",mby);
//                jsonObject.put("modified_date",mdate);
                jsonObject.put("machine_active",active);
                jsonObject.put("dev_id",did);
                jsonObject.put("machine_ip",mip);
                jsonObject.put("machine_name",mname);
                jsonObject.put("machine_port",mport);
                jsonObject.put("permission",per);
                jsonObject.put("c_id",cid);

                file.write(jsonObject.toJSONString());

                System.out.println("JSON file created: "+jsonObject);
            }
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
