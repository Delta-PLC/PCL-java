package com.plc.Machine.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.company.Entity.CompanyEntity;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "tblmachine_details")
public class MachineEntity extends Audit<String> {
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

    @OneToMany(mappedBy = "machineEntityData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"machineEntityData"})
    private List<CustomerPlc> customerPlcList;

    public void companyIdUpdate(CompanyEntity company) {
        this.companyEntityList=company;
    }


}
