package com.plc.plc.MethodPlc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.AuditingAndResponse.Audit;
import com.plc.plc.customerPlc.Entity.CustomerPlc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "register_method")
public class Method  extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long methodId;
    private String methodName;
    private boolean active;
    @OneToMany(mappedBy = "method",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"method"})
    private List<CustomerPlc> customerPlcListDAta;



    public void RemoveCustomerPlc(CustomerPlc customerPlc) {
        customerPlcListDAta.remove(customerPlc);
    }
}
