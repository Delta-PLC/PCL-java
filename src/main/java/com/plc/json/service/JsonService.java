package com.plc.json.service;

import com.plc.json.model.Jsondata;
import com.plc.json.repository.JsonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonService {
    private final JsonRepository jsonRepository;

    public JsonService(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public Iterable<Jsondata> list() {
        return jsonRepository.findAll();
    }
    public Jsondata save(Jsondata jsondata){
        return jsonRepository.save(jsondata);
    }

    public Iterable<Jsondata> save(List<Jsondata> jsondataList) {
        return jsonRepository.saveAll(jsondataList);
    }

}
