package com.plc.plc.customerPlc.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_plc")
public class CustomerPlc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerPlcId;
    private String regName;
    private String regAni;
    private boolean active;


}
