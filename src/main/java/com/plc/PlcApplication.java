package com.plc;

import com.plc.json.model.Jsondata;
import com.plc.json.repository.JsonRepository;
import com.plc.user.entity.Role;
import com.plc.user.entity.Roles;
import com.plc.user.entity.User;
import com.plc.user.repository.RoleRepository;
import com.plc.user.repository.UserRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@SpringBootApplication
public class PlcApplication  implements ApplicationRunner {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public static JsonRepository jsonRepository;
	public static final Logger log= LoggerFactory.getLogger(PlcApplication.class);

	public PlcApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JsonRepository jsonRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jsonRepository = jsonRepository;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, SQLException{
		SpringApplication.run(PlcApplication.class, args);


			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {

					Jsondata jsondata = new Jsondata();
					JSONParser parser = new JSONParser();
					JSONArray jsonArray = null;
					try {
						jsonArray = (JSONArray) parser.parse(new FileReader("/home/endloss/Downloads/data.json"));
						int i = 1;
						//   String n=null;
						int count =1;
						for (Object o : jsonArray) {

							System.out.println("connection done--------------");

							JSONObject person = (JSONObject) o;
							jsondata.setId(UUID.randomUUID());
							if (person.get("Server IP") == null) {

							} else {
								jsondata.setIpAddress((String) person.get("Server IP"));
								System.out.println("Server IP: " +jsondata.ip());

								if (person.get("Status") == null) {

									jsondata.setStatus(0);

									if (person.get("Actual Timer") == null)
									{
										jsondata.setActualTimer(0);

										if (person.get("Set Timer") == null)
										{
											jsondata.setSetTimer(0);

										} else
										{
											jsondata.setSetTimer((Integer) person.get("Set Timer"));
											System.out.println("Set Timer: " +jsondata.stt());
										}
									} else
									{
										jsondata.setActualTimer((Integer) person.get("Actual Timer"));
										System.out.println("Actual Timer: " +jsondata.att());
										if (person.get("Set Timer") == null) {
											jsondata.setSetTimer(0);

										} else {
											jsondata.setSetTimer((Integer) person.get("Set Timer"));
											System.out.println("Set Timer: " +jsondata.stt());
										}
									}

								} else {
									jsondata.setStatus((Integer) person.get("Status"));
									System.out.println("Status: " +jsondata.st());
									if (person.get("Actual Timer") == null) {
										jsondata.setActualTimer(0);

										if (person.get("Set Timer") == null) {
											jsondata.setSetTimer(0);


										} else {
											jsondata.setSetTimer((Integer) person.get("Set Timer"));
											System.out.println("Set Timer: " +jsondata.stt());
										}
									} else {
										jsondata.setActualTimer((Integer) person.get("Actual Timer"));
										System.out.println("Actual Timer: " +jsondata.att());

										if (person.get("Set Timer") == null) {
											jsondata.setSetTimer(0);

										} else {
											jsondata.setSetTimer((Integer) person.get("Set Timer"));
											System.out.println("Set Timer: " +jsondata.stt());
										}
									}
								}
							}

							jsonRepository.save(jsondata);
							log.info("save Data {}",jsonRepository.save(jsondata));
							i++;





					}
					} catch (ParseException e) {
						throw new RuntimeException(e);
					} catch (FileNotFoundException e) {
						throw new RuntimeException(e);
					}
					System.out.println("Hello World");
				}
			}, 0, 5000);

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
		Role roles4=new Role(Roles.ROLE_COMPAYOWNER);



		List<User> data=userRepository.findAll();
		if (data.isEmpty()) {
			roleRepository.save(roleSuperadmin);
			roleRepository.save(roles);
			roleRepository.save(roles1);
			roleRepository.save(roles2);
			roleRepository.save(roles3);
			roleRepository.save(roles4);

			user.getRoles().add(roleSuperadmin);
			user.getRoles().add(roles);
		//	log.info("{}",user.getRoles());

			userRepository.save(user);


		}
	}
}
