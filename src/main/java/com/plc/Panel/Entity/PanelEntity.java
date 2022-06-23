package com.plc.Panel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.company.Entity.CompanyEntity;
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
public class PanelEntity extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
