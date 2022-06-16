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
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/writedb")
    public ResponseEntity<?> writeindb() throws FileNotFoundException, IOException, SQLException, ParseException {


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



    @GetMapping("/read")
    public ResponseEntity<List<Jsondata>> showData() {
        log.info("Data", jsonRepository.findAll());
        return ResponseEntity.ok(jsonRepository.findAll());
    }



    @GetMapping("/read/{id}")
    public ResponseEntity<?> getIpById(@PathVariable("id") UUID id) {
        Optional<?> ipData = jsonRepository.findById(id);
        if (ipData.isPresent()) {
            return new ResponseEntity<>(ipData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}





















