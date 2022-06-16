package com.plc.plc.companyPlc.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CompanyPlcSaveDto {
    private String plcName;
    private String plcMode;
    private boolean active;
}
