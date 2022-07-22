package com.plc;


import com.plc.json.model.Jsondata;
import com.plc.json.repository.JsonRepository;
import com.plc.panel.Controller.PanelController;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class PlcApplication implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public static JsonRepository jsonRepository;

//	private static GsonService service=new GsonService();

    public static final Logger log = LoggerFactory.getLogger(PlcApplication.class);


    public PlcApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JsonRepository jsonRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jsonRepository = jsonRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(PlcApplication.class, args);


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                Jsondata jsondata = new Jsondata();
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = null;
                try {


                    File file = new File("/home/endloss/Desktop/data.json");

                    if (file.length() == 0L) {
                        System.out.println("File is empty");
                        //edit on 20 july
                        BufferedWriter writer = Files.newBufferedWriter(Paths.get("/home/endloss/Desktop/data.json"));
                        writer.write("[{}]");
                        writer.flush();
                    } else {


                        String content = new Scanner(new File("/home/endloss/Desktop/data.json")).next();
//							System.out.println("--------------------------------------------"+content+"----------------------------------------");
//							String[] textStr = content.split("\n");
//							String aa = textStr[0];

                        String aa = content;
                        String bb = "[{}]";

                        if (aa.equals(bb)) {
                            System.out.println("file Empty contains only [{}]");
                        } else {


                            //C:/Users/Endlos/Downloads/data.json
                            jsonArray = (JSONArray) parser.parse(new FileReader("/home/endloss/Desktop/data.json"));

                            int i = 1;
                            //   String n=null;
                            int count = 1;
                            for (Object o : jsonArray) {

                                JSONObject person = (JSONObject) o;
                                jsondata.setId(UUID.randomUUID());
                                if (person.get("Server IP") == null) {

                                } else {
                                    jsondata.setIpAddress((String) person.get("Server IP"));

                                    if (person.get("Status") == null) {

                                        jsondata.setStatus(0);

                                        if (person.get("Actual Timer") == null) {
                                            jsondata.setActualTimer(0);

                                            if (person.get("Set Timer") == null) {
                                                jsondata.setSetTimer(0);

                                            } else {
                                                jsondata.setSetTimer((Integer) person.get("Set Timer"));
                                            }
                                        } else {
                                            jsondata.setActualTimer((Integer) person.get("Actual Timer"));
                                            if (person.get("Set Timer") == null) {
                                                jsondata.setSetTimer(0);

                                            } else {
                                                jsondata.setSetTimer((Integer) person.get("Set Timer"));
                                            }
                                        }

                                    } else {
                                        jsondata.setStatus((Integer) person.get("Status"));
                                        if (person.get("Actual Timer") == null) {
                                            jsondata.setActualTimer(0);

                                            if (person.get("Set Timer") == null) {
                                                jsondata.setSetTimer(0);


                                            } else {
                                                jsondata.setSetTimer((Integer) person.get("Set Timer"));
                                            }
                                        } else {
                                            jsondata.setActualTimer((Integer) person.get("Actual Timer"));

                                            if (person.get("Set Timer") == null) {
                                                jsondata.setSetTimer(0);

                                            } else {
                                                jsondata.setSetTimer((Integer) person.get("Set Timer"));
                                            }
                                        }
                                    }
                                    jsonRepository.save(jsondata);
                                }


                                //log.info("save Data {}",jsonRepository.save(jsondata));
                                i++;
                                BufferedWriter writer = Files.newBufferedWriter(Paths.get("/home/endloss/Desktop/data.json"));
                                writer.write("[{}]");
                                writer.flush();
                            }
                        }
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1000000);

        Timer t1 = new Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String url = "jdbc:postgresql://localhost:5432/plc_project";
                    String user = "postgres";
                    String password = "postgres";
                    Connection con = DriverManager.getConnection(url, user, password);


                    int i = 1;
                    String select_sql = "select tblmachine_details.machine_id,tblmachine_details.machine_ip, tblmachine_details.dev_id, tblmachine_details.machine_port from tblmachine_details";
                    PreparedStatement pstn = con.prepareStatement(select_sql);
                    ResultSet rs = pstn.executeQuery();


                    JSONArray obj = new JSONArray();

                    FileWriter file = new FileWriter("/home/endloss/Desktop/machine.json");
                    while (rs.next()) {

                        int machine_id = rs.getInt("machine_id");
                        String machine_ip = rs.getString("machine_ip");
                      //  log.info("--------------machinneip :{}",machine_ip);
                        int dev_id = rs.getInt("dev_id");
                        int machine_port = rs.getInt("machine_port");


                        Map<String, Object> jsonObjectPayload = new LinkedHashMap<>();
                        jsonObjectPayload.put("IP", machine_ip);
                        jsonObjectPayload.put("unitid", String.valueOf(dev_id));
                        jsonObjectPayload.put("port", String.valueOf(machine_port));


//						JSONObject jsonObject = new JSONObject();
//
//						jsonObject.put("IP", machine_ip);
//						jsonObject.put("unitid", String.valueOf(dev_id));
//						jsonObject.put("port", String.valueOf(machine_port));

                        JSONArray obj1 = new JSONArray();

                        JSONObject jsonObject1 = new JSONObject();
                        JSONObject jsonObject2 = new JSONObject();
                        JSONObject jsonObject3 = new JSONObject();

                        List<String> listStrings1 = new ArrayList<>();
                        List<String> listStrings2 = new ArrayList<>();
                        List<String> listStrings3 = new ArrayList<>();

                        String sql = "select addres_regisater_type.address , addres_regisater_type.add_reg_id,tbl_add_panel_with_register_tag.add_reg_id from addres_regisater_type left join tbl_add_panel_with_register_tag on addres_regisater_type.add_reg_id=tbl_add_panel_with_register_tag.add_reg_id where penal_id='" + machine_id + "'";
                        PreparedStatement pstnsql = con.prepareStatement(sql);
                        ResultSet rssql = pstnsql.executeQuery();
                        while (rssql.next()) {


                            String address = rssql.getString("address");


                            String sql1 = "select addres_regisater_type.address,tbl_plcreg_type.plc_register from addres_regisater_type left join tbl_plcreg_type on addres_regisater_type.reg_id=tbl_plcreg_type.register_plc_id where address='" + address + "'";
                            PreparedStatement pstnsql1 = con.prepareStatement(sql1);
                            ResultSet rssql1 = pstnsql1.executeQuery();
                            while (rssql1.next()) {


                                String plc_register = rssql1.getString("plc_register");
                                //System.out.println("plc_register: " + plc_register);
                                String address1 = rssql1.getString("address");


                                if (plc_register.equals("Coil Register")) {
                                    jsonObject1.put("method", plc_register);
                                    listStrings1.add(address1);
                                    jsonObject1.put("lport", listStrings1);

                                }

                                if (plc_register.equals("Holding Register")) {
                                    jsonObject2.put("method", plc_register);
                                    listStrings2.add(address1);
                                    jsonObject2.put("lport", listStrings2);
                                }

                                if (plc_register.equals("Input Output Register")) {
                                    jsonObject3.put("method", plc_register);
                                    listStrings3.add(address1);
                                    //System.out.println("listStrings3: " + listStrings3);
                                    jsonObject3.put("lport", listStrings3);

                                }


                            }

                        }
                        if (jsonObject1.isEmpty()) {

                        } else {
                            obj1.add(jsonObject1);
                        }
                        if (jsonObject2.isEmpty()) {

                        } else {
                            obj1.add(jsonObject2);
                        }
                        if (jsonObject3.isEmpty()) {

                        } else {
                            obj1.add(jsonObject3);
                        }


                        jsonObjectPayload.put("work", obj1);
                        obj.add(jsonObjectPayload);

                    }

                    file.write(String.valueOf(obj));
                    file.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }, 0, 1000000);//10000 MEANS 10 second


    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        User user = new User();
        //log.info("user ROle {}",user.getRoles());
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setMobileNumber("1234567890");
        user.setAddress("karol bag");
        user.setCity("ahmedabad");

        Role roleSuperadmin = new Role(Roles.ROLE_SUPERADMIN);
        Role roles = new Role(Roles.ROLE_ADMIN);
        Role roles1 = new Role(Roles.ROLE_EMPLOYEE);
        Role roles2 = new Role(Roles.ROLE_MODERATOR);
        Role roles3 = new Role(Roles.ROLE_USER);
        Role roles4 = new Role(Roles.ROLE_COMPANYOWNER);


        List<User> data = userRepository.findAll();
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