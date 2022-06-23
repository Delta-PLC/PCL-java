package com.plc.Panel.Repository;

import com.plc.Panel.Entity.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanelRepository extends JpaRepository<PanelEntity,Long > {

}
