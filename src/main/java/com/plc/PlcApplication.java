package com.plc;

import com.plc.user.entity.Role;
import com.plc.user.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class PlcApplication  {


	public static void main(String[] args) {
		SpringApplication.run(PlcApplication.class, args);
	}


}
