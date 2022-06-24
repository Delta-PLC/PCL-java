package com.plc.json.repository;

import com.plc.json.model.Jsondata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JsonRepository extends JpaRepository<Jsondata,Integer> {


    Optional<?> findById(UUID id);

    @Query(value = "SELECT id,actual_timer,ip_address,set_timer,status FROM public.json_data ORDER BY id DESC limit 1", nativeQuery = true)
    Jsondata findTopByOrderByIdDesc();

}