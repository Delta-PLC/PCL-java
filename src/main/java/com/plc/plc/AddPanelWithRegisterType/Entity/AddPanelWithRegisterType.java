package com.plc.plc.AddPanelWithRegisterType.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.panel.Entity.PanelEntity;
import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
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
@Table(name = "tbl_Add_Panel_With_Register_tag")
public class AddPanelWithRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registerTag;
    private String permissionn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "add_reg_id"),name = "add_reg_id",referencedColumnName = "add_reg_id")
    @JsonIgnoreProperties(value = {"addresRegisterTypeList","customerPlcData",
                                    "companyPlcData",
                                "panelWithRegisterTypeList","addPanelWithRegisterTypeData"})
    private AddresRegisterTypeEntity registerTypeDataList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "penal_id"),name = "penal_id",referencedColumnName = "machine_id")
    @JsonIgnoreProperties(value = {"addPanelWithRegisterTypeList","addresRegisterTypeList","companyPlcdata","panelWithRegisterTypeList"
                                        ,"customerPlcList","companyEntityList"})
    private PanelEntity paneldata;
}
