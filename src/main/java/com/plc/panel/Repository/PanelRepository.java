package com.plc.panel.Repository;

import com.plc.panel.Entity.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanelRepository extends JpaRepository<PanelEntity,Long > {

}
