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

//    @Query(value = "SELECT Cast(id as varchar)id,actual_timer,ip_address,set_timer,status,datetime FROM public.json_data ORDER BY datetime DESC ", nativeQuery = true)
@Query(value = "SELECT Cast(id as varchar)id,actual_timer,ip_address,set_timer,status,datetime FROM public.json_data ORDER BY actual_timer DESC ", nativeQuery = true)
    List<?> findByDescAll();

    @Query("select j from Jsondata  j where  j.ipAddress=:ipAddress")
    List<?> findByIpAddress(String ipAddress);

}