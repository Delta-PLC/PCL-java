package com.plc.user.controller;

import com.plc.company.Entity.CompanyEntity;
import com.plc.company.Repository.CompanyRepository;
import com.plc.exception.ExceptionService.RoleNotFound;
import com.plc.exception.ExceptionService.UserNotFound;

import com.plc.jwt.JwtUtils;
import com.plc.payload.Request.LoginRequest;
import com.plc.payload.Request.SignupRequest;
import com.plc.payload.Response.JwtResponse;
import com.plc.payload.Response.MessageResponse;
import com.plc.payload.Response.PageResponse;
import com.plc.secutiry.UserDetailsImpl;
import com.plc.user.entity.Role;
import com.plc.user.entity.Roles;
import com.plc.user.entity.User;
import com.plc.user.repository.RoleRepository;
import com.plc.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/deltaplc/user")
public class AuthController {

    private final static Logger log= LoggerFactory.getLogger(AuthController.class);
    private final CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public AuthController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
       // log.info("Login Number {} ",loginRequest.getMobilenumber());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getMobilenumber(), loginRequest.getPassword()));
       // log.info("Login Number {} ",loginRequest.getMobilenumber());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        // System.out.println(jwt);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        //System.out.println(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), userDetails.getMobileNumber(),
                userDetails.getDatetime(), roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws Exception {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByMobileNumber(signUpRequest.getMobileNumber())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account encoder.encode(signUpRequest.getPassword())
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
                , signUpRequest.getMobileNumber(), signUpRequest.getCity(), signUpRequest.getAddress());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        System.out.println(signUpRequest.getRoles());
        if (strRoles == null) {
            Role moderatorRole = roleRepository.findByName(Roles.ROLE_MODERATOR)
                    .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
            System.out.println(moderatorRole.getName());
            roles.add(moderatorRole);
            System.out.println("moderator values");
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roles.add(adminRole);
                        System.out.println("admin values");

                        break;
                    case "USER":
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roles.add(userRole);
                        System.out.println("userRole values");

                        break;
                    case "SUPERADMIN":
                        Role superRole = roleRepository.findByName(Roles.ROLE_SUPERADMIN)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roles.add(superRole);
                        System.out.println("superRole values");
                        break;
                    case "COMPABYADMIN":
                        Role companyAdminRole = roleRepository.findByName(Roles.ROLE_COMPAYOWNER)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roles.add(companyAdminRole);
                        System.out.println("companyAdminRole values");
                        break;

                    default:
                        Role empRole = roleRepository.findByName(Roles.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roles.add(empRole);
                        System.out.println("empRole values");
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> FindById(@PathVariable Long id)  {

        return new ResponseEntity<> (userRepository.findById(id).orElseThrow(() -> new UserNotFound("user not found in this System")),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{userId}/update/{companyId}")
    public ResponseEntity<?> updateDat(@PathVariable Long userId,@PathVariable Long companyId)
    {
        User user=userRepository.findById(userId).get();
        CompanyEntity companyEntity=companyRepository.findById(companyId).get();
        user.updateCompanyId(companyEntity);
        userRepository.save(user);
        return new ResponseEntity<>(PageResponse.SuccessResponse(user),HttpStatus.OK);
    }


}
