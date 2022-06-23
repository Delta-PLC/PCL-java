package com.plc.panel.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PanelSaveDto {
    private Long machine_id;
    private String machineName;
    private String machineIp;
    private int machinePort;
    private int devId;
    private String permissionn;
    private boolean machineActive;
}
