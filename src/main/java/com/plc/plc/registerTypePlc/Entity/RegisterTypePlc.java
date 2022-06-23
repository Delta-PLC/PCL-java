package com.plc.plc.registerTypePlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;
import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_plcreg_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegisterTypePlc extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registerPlcId;

    private String plcRegister;
    private boolean active;
    @OneToMany(mappedBy = "registerTypePlc",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"registerPlc"})
    private List<CustomerPlc> customerPlcData;

    @OneToMany(mappedBy = "registerTypePlcData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"registerPlcData"})
    private List<AddresRegisterTypeEntity> addresRegisterTypeList;

    @OneToMany(mappedBy = "registerTypeData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"registerTypeData"})
    private List<AddPanelWithRegisterType> panelWithRegisterTypeList;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "registerPlcId = " + registerPlcId + ", " +
                "plcRegister = " + plcRegister + ", " +
                "active = " + active + ")";
    }
}
