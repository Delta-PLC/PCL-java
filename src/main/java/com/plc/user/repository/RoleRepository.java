package com.plc.user.repository;

import com.plc.Machine.Entity.MachineEntity;
import com.plc.user.entity.Role;
import com.plc.user.entity.Roles;
import com.plc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role,Long> {
    Optional<Role> findByName(Roles roles);


}
