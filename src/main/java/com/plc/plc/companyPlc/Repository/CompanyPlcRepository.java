package com.plc.plc.companyPlc.Repository;

import com.plc.plc.companyPlc.Entity.CompanyPlc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyPlcRepository extends JpaRepository<CompanyPlc,Long> {
    @Query("select DISTINCT a.plcName from CompanyPlc a")
    List<String> findDistinctByPlcName();
    List<CompanyPlc> findByPlcName(String name);
}
