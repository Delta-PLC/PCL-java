package com.plc.plcconfiguration.Repository;

import com.plc.plcconfiguration.Entity.AddPlc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddPlcRepository extends JpaRepository<AddPlc,Long> {

    AddPlc findByPlcid(Long plcid);
}
