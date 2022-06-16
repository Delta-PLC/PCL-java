package com.plc.company.Repository;

import com.plc.company.Entity.Company;
import com.plc.company.dto.CompanySaveDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company ,Long> {

}
