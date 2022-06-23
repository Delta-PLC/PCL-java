package com.plc.plcconfiguration.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="plc_list")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddPlc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long plcid;

    @Column(name = "plccompany_name")
    private String plcCompanyName;

    @Column(name = "plcmodel_number")
    private String plcModelNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "addPlc", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("addPlc")
    private List<RegisterType> registerType = new ArrayList<>();

    //private String RegisterType;
    // already created: coil,input register, holding register

    @Column(name = "register_address")
    private String RegisterAddress;

    public long getPlcid() {
        return plcid;
    }

    public void setPlcid(long plcid) {
        this.plcid = plcid;
    }

    public String getPlcCompanyName() {
        return plcCompanyName;
    }

    public void setPlcCompanyName(String plcCompanyName) {
        this.plcCompanyName = plcCompanyName;
    }

    public String getPlcModelNumber() {
        return plcModelNumber;
    }

    public void setPlcModelNumber(String plcModelNumber) {
        this.plcModelNumber = plcModelNumber;
    }

    public List<RegisterType> getRegisterType() {
        return registerType;
    }

    public void setRegisterType(List<RegisterType> registerType) {
        this.registerType = registerType;
    }

    public String getRegisterAddress() {
        return RegisterAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        RegisterAddress = registerAddress;
    }
}
