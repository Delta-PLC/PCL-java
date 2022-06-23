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





















