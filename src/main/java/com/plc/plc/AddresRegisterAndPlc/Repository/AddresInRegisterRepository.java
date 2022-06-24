package com.plc.plc.AddresRegisterAndPlc.Repository;

import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddresInRegisterRepository extends JpaRepository<AddresRegisterTypeEntity, Long> {
    @Query("select  a from  AddresRegisterTypeEntity a where a.companyPlcData.plcCompanyId =:companyPlcId ")
    List<AddresRegisterTypeEntity> findByCompanyPlcDataPlcCompanyId(Long companyPlcId);

    @Query("select a from AddresRegisterTypeEntity  a where  a.address=:address")
    List<AddresRegisterTypeEntity> findByAddress(int address);

    @Query("select a from AddresRegisterTypeEntity a where a.companyPlcData.plcCompanyId=:plcCompanyId AND a.registerTypePlcData.registerPlcId=:registerPlcId")
    List<AddresRegisterTypeEntity> findByCompanyPlcIdAndRegisterPlcId(Long plcCompanyId, Long registerPlcId);

    @Query("select a from AddresRegisterTypeEntity a where a.address=:address AND a.registerTypePlcData.registerPlcId=:registerPlcId")
    List<AddresRegisterTypeEntity> findByAddressAndRegisterPlc(Integer address,Long registerPlcId);
    @Query("select a from AddresRegisterTypeEntity a where a.companyPlcData.plcCompanyId=:plcCompanyId AND a.registerTypePlcData.registerPlcId=:registerPlcId and a.add_reg_id=:add_reg_id")
    AddresRegisterTypeEntity findByAddIdAndCompanyIdAndRegisterId(Long plcCompanyId, Long registerPlcId ,Long add_reg_id);
}
