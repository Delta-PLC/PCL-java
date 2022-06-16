package com.plc.plc.registerPlc.Dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RegisterPlcSaveDto {
    private String plcRegister;
    private boolean active;
}
