package com.plc.plc.AddresRegisterAndPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.registerTypePlc.Entity.RegisterTypePlc;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addres_regisater_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddresRegisterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_reg_id", nullable = false)
    private Long add_reg_id;

    private int address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "reg_id"),name = "reg_id",referencedColumnName = "registerPlcId")
    @JsonIgnoreProperties(value = {"addresRegisterTypeList","customerPlcData","panelWithRegisterTypeList"})
    private RegisterTypePlc registerTypePlcData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "company_plc_id"),name = "company_plc_id",referencedColumnName = "plcCompanyId")
    @JsonIgnoreProperties(value = {"addresRegisterTypeDataList","panelEntityList"})
    private CompanyPlc companyPlcData;


    @OneToMany(mappedBy = "registerTypeDataList",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "registerTypeDataList")
    private List<AddPanelWithRegisterType> addPanelWithRegisterTypeData;
    @Override
    public String toString() {
        return "AddresRegisterTypeEntity{" +
                "add_reg_id=" + add_reg_id +
                ", address=" + address +
                ", registerTypePlcData=" + registerTypePlcData +
                ", companyPlcData=" + companyPlcData +
                '}';
    }
}
