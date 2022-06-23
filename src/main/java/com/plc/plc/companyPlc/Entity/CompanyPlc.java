package com.plc.plc.companyPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import com.plc.plc.registerPlc.Entity.RegisterPlc;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "plc_company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  CompanyPlc extends Audit<String > {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plcCompanyId;
    private String plcName;
    private String plcMode;
    private boolean active;

    @OneToMany(mappedBy = "companyPlcData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"companyPlcData"})
    private List<AddresRegisterTypeEntity> addresRegisterTypeDataList;


}
