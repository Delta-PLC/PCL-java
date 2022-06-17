package com.plc.plc.customerPlc.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerPlcSaveDto {
    private String regName;
    private String regAni;
    private boolean customerActive;
}
