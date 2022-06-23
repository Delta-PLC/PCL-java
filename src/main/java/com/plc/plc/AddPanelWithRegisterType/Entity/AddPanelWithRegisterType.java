package com.plc.plc.AddPanelWithRegisterType.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.panel.Entity.PanelEntity;
import com.plc.plc.registerTypePlc.Entity.RegisterTypePlc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tbl_Add_Panel_With_Register_Type")
public class AddPanelWithRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registerTag;
    private String permissionn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "reg_id"),name = "reg_id",referencedColumnName = "registerPlcId")
    @JsonIgnoreProperties(value = {"addresRegisterTypeList","customerPlcData","panelWithRegisterTypeList"})
    private RegisterTypePlc registerTypeData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "penal_id"),name = "penal_id",referencedColumnName = "machine_id")
    @JsonIgnoreProperties(value = {"addPanelWithRegisterTypeList","addresRegisterTypeList","customerPlcData","panelWithRegisterTypeList"})
    private PanelEntity paneldata;
}
