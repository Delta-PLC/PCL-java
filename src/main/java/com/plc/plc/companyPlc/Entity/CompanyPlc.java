package com.plc.plc.companyPlc.Entity;

import com.plc.AuditingAndResponse.Audit;
import lombok.*;

import javax.persistence.*;

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

}
