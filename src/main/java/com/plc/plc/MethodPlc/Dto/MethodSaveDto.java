package com.plc.plc.MethodPlc.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MethodSaveDto {
    private String methodName;
    private boolean active;
}
