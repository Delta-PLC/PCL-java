package com.plc.json.repository;

import com.plc.json.model.Jsondata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonRepository extends JpaRepository<Jsondata,Long> {


}
