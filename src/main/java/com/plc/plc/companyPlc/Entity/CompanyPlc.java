package com.plc.plc.companyPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
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
public class CompanyPlc extends Audit<String > {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plcCompanyId;
    private String plcName;
    private String plcMode;
    private boolean active;
    @OneToMany(mappedBy = "companyPlcData",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"companyPlcData"})
    private List<RegisterPlc> registerPlcsList;

    public void RemoveCompanyPlcId(RegisterPlc registerPlc) {
        registerPlcsList.remove(registerPlc);
    }
}
