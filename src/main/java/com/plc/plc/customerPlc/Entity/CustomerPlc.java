package com.plc.plc.customerPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.Machine.Entity.MachineEntity;
import com.plc.plc.MethodPlc.Entity.Method;
import com.plc.plc.registerPlc.Entity.RegisterPlc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblplc_customer")
public class CustomerPlc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerPlcId;
    private String regName;
    private String regAni;
    private boolean customerActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "m_id"),name = "m_id",referencedColumnName = "machine_id")
    @JsonIgnoreProperties(value = "customerPlcList")
    private MachineEntity machineEntityData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "plc_reg_id"),name = "plc_reg_id",referencedColumnName = "registerPlcId")
    @JsonIgnoreProperties(value = {"customerPlcData"})
    private RegisterPlc registerPlc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "meth_id"),name = "meth_id",referencedColumnName = "methodId")
    @JsonIgnoreProperties(value = {"customerPlcListDAta"})
    private Method method;


    public void updateCustomerPlc(Method method) {
        this.method=method;
    }
}
