package com.plc.plc.customerPlc.Repository;

import com.plc.plc.customerPlc.Entity.CustomerPlc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPlcRepository extends JpaRepository<CustomerPlc,Long> {
}
