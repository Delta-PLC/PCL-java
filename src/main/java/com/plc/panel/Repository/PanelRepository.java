package com.plc.panel.Repository;

import com.plc.panel.Entity.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PanelRepository extends JpaRepository<PanelEntity,Long > {

}
