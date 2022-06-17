package com.plc.company.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.Machine.Entity.MachineEntity;
import com.plc.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "company_details")
public class CompanyEntity extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long company_id;
    private String companyName;
    private String companyAdd;
    private String companyEmail;
    private String companyNumber;
    private String companyGstNumber;
    private boolean companyActive;
    @OneToMany(mappedBy = "companyEntityData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"companyEntityData"})
    private List<User> userList;

    @OneToMany(mappedBy = "companyEntityList",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"companyEntityList"})
    private List<MachineEntity> machineEntityList;



}
