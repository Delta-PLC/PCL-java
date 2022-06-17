package com.plc.Machine.Repository;

import com.plc.Machine.Entity.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<MachineEntity,Long > {

}
