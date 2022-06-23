package com.plc.plc.AddresRegisterAndPlc.Repository;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresInRegisterRepository extends JpaRepository<AddresRegisterTypeEntity,Long> {
}
