package com.plc.company.dto;

import com.plc.panel.Entity.PanelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CompanySaveDto {
    private Long company_id;
    private String companyName;
    private String companyAdd;
    private String companyEmail;
    private String companyNumber;
    private String companyGstNumber;
    private boolean companyActive;
    private PanelEntity panelEntity;
}
