package com.plc.company.Repository;

import com.plc.company.Entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

}
