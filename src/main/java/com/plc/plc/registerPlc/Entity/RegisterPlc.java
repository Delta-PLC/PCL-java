package com.plc.plc.registerPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.plc.companyPlc.Entity.CompanyPlc;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tblplcreg")
public class RegisterPlc extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registerPlcId;
    private String plcRegister;
    private boolean active;
    @OneToMany(mappedBy = "registerPlc",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"registerPlc"})
    private List<CustomerPlc> customerPlcData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "plc_id"),name = "plc_id",referencedColumnName = "plcCompanyId")
    @JsonIgnoreProperties(value = {"registerPlcsList"})
    private CompanyPlc companyPlcData;


    public void updateCompanyPlcId(CompanyPlc companyPlc) {
        this.companyPlcData=companyPlc;
    }

}
