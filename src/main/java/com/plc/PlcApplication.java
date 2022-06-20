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


		User user=new User();
		log.info("user ROle {}",user.getRoles());
		user.setUsername("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setMobileNumber("1234567890");
		user.setAddress("karol bag");
		user.setCity("ahmedabad");

		Role roleSuperadmin=new Role(Roles.ROLE_SUPERADMIN);
		Role roles=new Role(Roles.ROLE_ADMIN);
		Role roles1=new Role(Roles.ROLE_EMPLOYEE);
		Role roles2=new Role(Roles.ROLE_MODERATOR);
		Role roles3=new Role(Roles.ROLE_USER);



		List<User> data=userRepository.findAll();
		if (data.isEmpty()) {
			roleRepository.save(roleSuperadmin);
			roleRepository.save(roles);
			roleRepository.save(roles1);
			roleRepository.save(roles2);
			roleRepository.save(roles3);


			user.getRoles().add(roleSuperadmin);
			user.getRoles().add(roles);
		//	log.info("{}",user.getRoles());

			userRepository.save(user);


		}
	}
}
