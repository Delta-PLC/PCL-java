package com.plc.json.controller;

import com.plc.json.model.Jsondata;
import com.plc.json.service.JsonService;
import com.plc.payload.Response.MessageResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

@RestController
@RequestMapping("/json")
public class JsonController {

    private final JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping("/write")
    public ResponseEntity<?> list() throws FileNotFoundException, IOException, SQLException, net.minidev.json.parser.ParseException {

        String url = "jdbc:postgresql://localhost:5432/plc_project";
        String user = "postgres";
        String password = "postgres";

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("/home/endloss/Downloads/data.json"));


        int i=1;
        for (Object o : jsonArray) {

            System.out.println("vlaue of  i:"+i);
            Connection con= DriverManager.getConnection(url,user,password);
             System.out.println("connection done--------------");
            String insert_sql= "INSERT INTO json VALUES (?, ?, ?, ?);";
            PreparedStatement pstn = con.prepareStatement(insert_sql);

            JSONObject person = (JSONObject) o;


            if(person.get("Server IP") == null)
            {

            }
            else
            {
                String ip = (String) person.get("Server IP");
                System.out.println("Server IP: " + ip);


                pstn.setString(1, ip);


                if(person.get("Status") == null)
                {
                    pstn.setNull(2, Types.INTEGER);
                    if(person.get("Actual Timer") == null)
                    {


                        pstn.setNull(3, Types.INTEGER);

                        if(person.get("Set Timer") == null)
                        {
                            pstn.setNull(4, Types.INTEGER);

                        }
                        else
                        {
                            long st = (long) person.get("Set Timer");
                            System.out.println("Set Timer: " + st);
                            pstn.setLong(4,st);
                        }
                    }
                    else
                    {
                        Object at =person.get("Actual Timer");
                        System.out.println("Actual Timer: " + at);
                        pstn.setInt(3,(Integer)at);

                        if(person.get("Set Timer") == null)
                        {
                            pstn.setNull(4, Types.INTEGER);

                        }
                        else
                        {
                            Object st = person.get("Set Timer");
                            pstn.setInt(4,(Integer)st);
                            System.out.println("Set Timer: " + st);
                        }
                    }

                }
                else
                {

                    Object Status=person.get("Status");
                    System.out.println("Status: " + Status);
                    pstn.setInt(2, (Integer) Status);

                    if(person.get("Actual Timer") == null)
                    {


                        pstn.setNull(3, Types.INTEGER);

                        if(person.get("Set Timer") == null)
                        {
                            pstn.setNull(4, Types.INTEGER);

                        }
                        else
                        {
                            Object st = person.get("Set Timer");
                            System.out.println("Set Timer: " + st);
                            pstn.setInt(4, (Integer) st);
                        }
                    }
                    else
                    {
                        Object at=person.get("Actual Timer");
                        System.out.println("Actual Timer: " + at);
                        pstn.setInt(3, (Integer) at);

                        if(person.get("Set Timer") == null)
                        {
                            pstn.setNull(4, Types.INTEGER);

                        }
                        else
                        {
                            Object st =person.get("Set Timer");
                            pstn.setInt(4,(Integer) st);
                            System.out.println("Set Timer: " + st);
                        }
                    }
                }
            }
            System.out.println("........................");
            pstn.executeUpdate();
            i++;
        }
        return new ResponseEntity<>(new MessageResponse("Successfully..."),HttpStatus.ACCEPTED);
    }

    @GetMapping("/read")
    public Iterable<Jsondata> list1(){
        return jsonService.list();
    }
}
