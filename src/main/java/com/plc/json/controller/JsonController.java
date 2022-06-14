package com.plc.json.controller;

import com.plc.json.model.Jsondata;
import com.plc.json.repository.JsonRepository;
import com.plc.json.service.JsonService;
import com.plc.payload.Response.MessageResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.hibernate.internal.util.ReflectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/json")
public class JsonController {

    public static final Logger log = LoggerFactory.getLogger(JsonController.class);

    private final JsonService jsonService;

    private final JsonRepository jsonRepository;

    public JsonController(JsonService jsonService, JsonRepository jsonRepository) {
        this.jsonService = jsonService;
        this.jsonRepository = jsonRepository;
    }

    @GetMapping("/writeJson")
    public ResponseEntity<?> WithModellist() throws FileNotFoundException, IOException, SQLException, ParseException {


        Jsondata jsondata = new Jsondata();


        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("/home/endloss/Downloads/data.json"));


        int i = 1;
       //   String n=null;
        int count =1;
        for (Object o : jsonArray) {

            System.out.println("connection done--------------");

            JSONObject person = (JSONObject) o;
            jsondata.setId(UUID.randomUUID());
            if (person.get("Server IP") == null) {

            } else {
                jsondata.setIpAddress((String) person.get("Server IP"));
             System.out.println("Server IP: " +jsondata.ip());

                if (person.get("Status") == null) {

                    jsondata.setStatus(0);

                    if (person.get("Actual Timer") == null)
                    {
                        jsondata.setActualTimer(0);

                        if (person.get("Set Timer") == null)
                        {
                            jsondata.setSetTimer(0);

                        } else
                            {
                                jsondata.setSetTimer((Integer) person.get("Set Timer"));
                                System.out.println("Set Timer: " +jsondata.stt());
                            }
                    } else
                    {
                        jsondata.setActualTimer((Integer) person.get("Actual Timer"));
                        System.out.println("Actual Timer: " +jsondata.att());
                        if (person.get("Set Timer") == null) {
                          jsondata.setSetTimer(0);

                        } else {
                            jsondata.setSetTimer((Integer) person.get("Set Timer"));
                            System.out.println("Set Timer: " +jsondata.stt());
                        }
                    }

                } else {
                    jsondata.setStatus((Integer) person.get("Status"));
                    System.out.println("Status: " +jsondata.st());
                    if (person.get("Actual Timer") == null) {
                       jsondata.setActualTimer(0);

                        if (person.get("Set Timer") == null) {
                            jsondata.setSetTimer(0);


                        } else {
                            jsondata.setSetTimer((Integer) person.get("Set Timer"));
                            System.out.println("Set Timer: " +jsondata.stt());
                        }
                    } else {
                        jsondata.setActualTimer((Integer) person.get("Actual Timer"));
                        System.out.println("Actual Timer: " +jsondata.att());

                        if (person.get("Set Timer") == null) {
                          jsondata.setSetTimer(0);

                        } else {
                            jsondata.setSetTimer((Integer) person.get("Set Timer"));
                            System.out.println("Set Timer: " +jsondata.stt());
                        }
                    }
                }
            }

            jsonRepository.save(jsondata);
            log.info("save Data {}",jsonRepository.save(jsondata));
            i++;

        }


        return new ResponseEntity<>(new MessageResponse("done"), HttpStatus.ACCEPTED);

    }

    @GetMapping("/readJson")
    public ResponseEntity<List<Jsondata>> showUser() {
        log.info("Data", jsonRepository.findAll());
        return ResponseEntity.ok(jsonRepository.findAll());
    }

    @GetMapping("/putJson")
    public ResponseEntity<List<Jsondata>> putData() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";

        Connection con= DriverManager.getConnection(url, user, password);
        //System.out.println("connection done");
        String select_sql= "select * from public.json_data;";
        PreparedStatement pstn = con.prepareStatement(select_sql);
        System.out.println(pstn);
        ResultSet rs = pstn.executeQuery();
        int i=1;
        try {
            FileWriter file = new FileWriter("/home/endloss/Desktop/data_db1_json.json");
            while (rs.next()) {
                String ip=rs.getString("ip_address");
                int status=rs.getInt("status");
                int at=rs.getInt("actual_timer");
                int st=rs.getInt("set_timer");

                System.out.println("Server_IP: "+ip);
                System.out.println("Status: "+status);
                System.out.println("Actual_Timer: "+at);
                System.out.println("Set_Timer: "+st);

                System.out.println("____________________________");
//        	System.out.println(i);
                i++;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Server_IP",ip);
                jsonObject.put("Status",status);
                jsonObject.put("Actual_Timer",at);
                jsonObject.put("Set_Timer",st);


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


        log.info("Data", jsonRepository.findAll());
        return ResponseEntity.ok(jsonRepository.findAll());
    }


}





















