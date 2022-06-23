package com.plc.plc.AddresRegisterAndPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.registerPlc.Entity.RegisterPlc;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addres_regisater_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class AddresRegisterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_reg_id", nullable = false)
    private Long add_reg_id;

    private int address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "reg_id"),name = "reg_id",referencedColumnName = "registerPlcId")
    @JsonIgnoreProperties(value = {"addresRegisterTypeList","customerPlcData"})
    private RegisterPlc registerPlcData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "company_plc_id"),name = "company_plc_id",referencedColumnName = "plcCompanyId")
    @JsonIgnoreProperties(value = {"addresRegisterTypeDataList"})
    private CompanyPlc companyPlcData;

}
