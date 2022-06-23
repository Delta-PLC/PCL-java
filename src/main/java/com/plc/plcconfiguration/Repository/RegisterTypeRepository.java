package com.plc.plcconfiguration.Repository;

import com.plc.plcconfiguration.Entity.AddPlc;
import com.plc.plcconfiguration.Entity.RegisterType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegisterTypeRepository extends JpaRepository<RegisterType,Long> {

}
