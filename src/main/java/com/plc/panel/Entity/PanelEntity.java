package com.plc.panel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.company.Entity.CompanyEntity;
import com.plc.plc.AddPanelWithRegisterType.Entity.AddPanelWithRegisterType;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tblmachine_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PanelEntity extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machine_id;
    private String machineName;
    private String machineIp;
    private int machinePort;
    private int devId;
    private String permissionn;
    private boolean machineActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "c_id"),name = "c_id",referencedColumnName = "company_id")
    @JsonIgnoreProperties(value = {"machineEntityList"})
    private CompanyEntity companyEntityList;

    @OneToMany(mappedBy = "panelEntityData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"machineEntityData"})
    private List<CustomerPlc> customerPlcList;

    @OneToMany(mappedBy = "paneldata",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"paneldata"})
    private List<AddPanelWithRegisterType> addPanelWithRegisterTypeList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "plc_id_"),name = "plc_id_",referencedColumnName =  "plcCompanyId")
    @JsonIgnoreProperties(value = {"panelEntityList","addresRegisterTypeDataList"})
    private CompanyPlc companyPlcdata;
    public void companyIdUpdate(CompanyEntity company) {
        this.companyEntityList=company;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "machine_id = " + machine_id + ", " +
                "machineName = " + machineName + ", " +
                "machineIp = " + machineIp + ", " +
                "machinePort = " + machinePort + ", " +
                "devId = " + devId + ", " +
                "permissionn = " + permissionn + ", " +
                "machineActive = " + machineActive + ")";
    }
}
