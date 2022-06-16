package com.plc.company.Entity;

import com.plc.AuditingAndResponse.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "company_details")
public class Company extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long company_id;
    private String companyName;
    private String companyAdd;
    private String companyEmail;
    private String companyNumber;
    private String companyGstNumber;
    private boolean active;




}
