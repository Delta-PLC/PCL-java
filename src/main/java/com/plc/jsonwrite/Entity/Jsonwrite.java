package com.plc.jsonwrite.Entity;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Repository.CompanyRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Jsonwrite {
    private static final Logger log= LoggerFactory.getLogger(Jsonwrite.class);
    final CompanyRepository companyRepository;

    public Jsonwrite(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

        @GetMapping(value = "/jsonwrite/{ip}/{addres}/{rvalues}")
        public ResponseEntity<?> jsonwritedata(@PathVariable String ip,@PathVariable String addres,@PathVariable List<String> rvalues) throws SQLException {

        log.info("--------------ip:{}",ip);
        log.info("--------------addres:{}",addres);
        log.info("--------------{}",rvalues);



        try {
            String url = "jdbc:postgresql://localhost:5432/plc_project";
            String user = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, user, password);


            int i = 1;
            String select_sql = "select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port from tblmachine_details where machine_ip='"+ip+"'";
            PreparedStatement pstn = con.prepareStatement(select_sql);
            ResultSet rs = pstn.executeQuery();


            JSONArray obj = new JSONArray();

            FileWriter file = new FileWriter("/home/endloss/Desktop/datab.json");
            while (rs.next()) {

                int machine_id = rs.getInt("machine_id");
                String machine_ip = rs.getString("machine_ip");

                int dev_id = rs.getInt("dev_id");
                int machine_port = rs.getInt("machine_port");


                Map<String, Object> jsonObjectPayload = new LinkedHashMap<>();
                jsonObjectPayload.put("IP", machine_ip);
                jsonObjectPayload.put("unitid", String.valueOf(dev_id));
                jsonObjectPayload.put("port", String.valueOf(machine_port));

                JSONArray obj1 = new JSONArray();

                Map<String, Object> jsonObject1 = new LinkedHashMap<>();
                Map<String, Object> jsonObject2 = new LinkedHashMap<>();
                Map<String, Object> jsonObject3 = new LinkedHashMap<>();


                List<String> listStrings1 = new ArrayList<>();
                List<String> listStrings2 = new ArrayList<>();
                List<String> listStrings3 = new ArrayList<>();
                List<String> listStrings4 = new ArrayList<>();

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

                        String address1 = rssql1.getString("address");

                        if(addres.equals(address1)) {
                            if (plc_register.equals("Coil Register")) {
                                jsonObject1.put("method", plc_register);
                                listStrings1.add(address1);
                                jsonObject1.put("lport", listStrings1);

                                 listStrings4.add(String.valueOf(rvalues));
                                    jsonObject1.put("data", rvalues);

                            }
                        }
                        if(addres.equals(address1))
                        {
                        if (plc_register.equals("Holding Register")) {
                            jsonObject2.put("method", plc_register);
                            listStrings2.add(address1);
                            jsonObject2.put("lport", listStrings2);

                                listStrings4.add(String.valueOf(rvalues));
                                jsonObject2.put("data", rvalues);
                            }
                        }
                        if(addres.equals(address1))
                        {
                        if (plc_register.equals("Input Output Register")) {
                            jsonObject3.put("method", plc_register);
                            listStrings3.add(address1);

                            jsonObject3.put("lport", listStrings3);
                                listStrings4.add(String.valueOf(rvalues));
                                jsonObject3.put("data", rvalues);
                            }

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
        return new ResponseEntity<>(HttpStatus.OK);
    }
    }

