package com.plc.plc.MethodPlc.Entity;

import com.plc.AuditingAndResponse.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "register_method")
public class Method  extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long methodId;
    private String methodName;
    private boolean active;
}
