package com.plc.Machine.Repository;

import com.plc.Machine.Entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine ,Long > {

}
