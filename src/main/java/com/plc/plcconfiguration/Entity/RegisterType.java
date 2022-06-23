package com.plc.plcconfiguration.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.company.Entity.CompanyEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "register_type_list")
@Getter
@Setter
@ToString
public class RegisterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register_name")
    private String typeName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "plc_id", referencedColumnName = "plcid")
    @JsonIgnoreProperties("registerType")
    private AddPlc addPlc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddPlc getAddPlc() {
        return addPlc;
    }

    public void setAddPlc(AddPlc addPlc) {
        this.addPlc = addPlc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
