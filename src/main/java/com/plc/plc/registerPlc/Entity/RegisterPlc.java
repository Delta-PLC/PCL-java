package com.plc.plc.registerPlc.Entity;

import com.plc.AuditingAndResponse.Audit;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "plcreg")
public class RegisterPlc extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registerPlcId;
    private String plcRegister;
    private boolean active;
}
