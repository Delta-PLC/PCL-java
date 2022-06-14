package com.plc;

import com.plc.user.entity.Role;
import com.plc.user.entity.Roles;
import com.plc.user.entity.User;
import com.plc.user.repository.RoleRepository;
import com.plc.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class PlcApplication  implements ApplicationRunner {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	public static final Logger log= LoggerFactory.getLogger(PlcApplication.class);

	public PlcApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(PlcApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		Role roles=new Role(Roles.ROLE_ADMIN);
		User user=new User();
		user.setUsername("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setMobileNumber("123456789");
		user.setAddress("karol bag");
		user.setCity("ahmedabad");




		List<User> data=userRepository.findAll();
		if (data.isEmpty()) {
			roleRepository.save(roles);
			//user.getRoles().add(roles);
		//	log.info("{}",user.getRoles());

			userRepository.save(user);


		}
	}
}
