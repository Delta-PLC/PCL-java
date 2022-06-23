package com.plc.plc.registerTypePlc.Dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RegisterTypePlcSaveDto {
    private String plcRegister;
    private boolean active;
}
