package com.plc.Machine.Entity;

import com.plc.AuditingAndResponse.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "machine_name")
public class Machine extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long machine_id;
    private String machineName;
    private String machineIp;
    private int machinePort;
    private int devId;
    private String permission;
    private boolean active;
}
