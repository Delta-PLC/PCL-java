package com.plc.plcconfiguration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AddPlcdto {

    private long id;
    private String plcCompanyName;
    private String plcModelNumber;
    private String plcType;
    private String plcNumber;
}
