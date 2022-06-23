package com.plc.plc.companyPlc.Repository;

import com.plc.plc.companyPlc.Entity.CompanyPlc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyPlcRepository extends JpaRepository<CompanyPlc,Long> {
    List<CompanyPlc> findDistinctByPlcName();
}
