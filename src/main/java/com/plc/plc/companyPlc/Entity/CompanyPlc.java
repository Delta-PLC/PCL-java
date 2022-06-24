package com.plc.plc.companyPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.panel.Entity.PanelEntity;
import com.plc.plc.AddresRegisterAndPlc.Entity.AddresRegisterTypeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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


    @OneToMany(mappedBy = "companyPlcdata",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"companyPlcdata"})
    private List<PanelEntity> panelEntityList;

    public Long getPlcCompanyId() {
        return plcCompanyId;
    }

    public void setPlcCompanyId(Long plcCompanyId) {
        this.plcCompanyId = plcCompanyId;
    }

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }
}
