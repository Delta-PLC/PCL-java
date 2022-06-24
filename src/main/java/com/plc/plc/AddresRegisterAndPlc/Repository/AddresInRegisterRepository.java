package com.plc.plc.AddresRegisterAndPlc.Repository;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddresInRegisterRepository extends JpaRepository<AddresRegisterTypeEntity,Long> {
    @Query("select  a from  AddresRegisterTypeEntity a where a.companyPlcData.plcCompanyId =:companyPlcId ")
    List<AddresRegisterTypeEntity> findByCompanyPlcDataPlcCompanyId(Long companyPlcId);
}
