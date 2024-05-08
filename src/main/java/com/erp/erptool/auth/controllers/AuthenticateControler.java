package com.erp.erptool.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.erptool.auth.models.APILoginReturnModel;
import com.erp.erptool.auth.models.JwtRequestModel;
import com.erp.erptool.auth.models.UserLoginDetailsModel;
import com.erp.erptool.config.JwtUtils;
import com.erp.erptool.setup.models.APIReturnModel;
import com.erp.erptool.users.models.UsersModel;
import com.erp.erptool.users.services.UserDetailsServiceImpl;
import com.erp.erptool.users.services.UsersService;

@RestController
@CrossOrigin
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UsersService userService;

    @Autowired
    private JwtUtils jwtUtils;

    // Generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateTokenValue(@RequestBody JwtRequestModel jwtRequest) throws Exception {

        String message = "";
        String token = "";
        APILoginReturnModel apiLoginReturnModel = new APILoginReturnModel();
        apiLoginReturnModel.setStatus("fail");
        try {
            message = authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());

        } catch (UsernameNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            message = "User not found" + e.getMessage();
            // throw new Exception("User not found");
        }
        System.out.println("message " + message);
        UserLoginDetailsModel userLoginDetail = new UserLoginDetailsModel();
        if (message == "success") {
            final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
            token = this.jwtUtils.generateToken(userDetails);
            System.out.println("Generated token " + token);
            apiLoginReturnModel.setStatus("Success");
            message = "Login Successfully";
            UsersModel user = this.userService.getUserByName(userDetails.getUsername());
            System.out.println("userId "+user.getUserId());
            System.out.println("userName "+user.getUserName());
            System.out.println("firstName "+user.getFirstName());
            System.out.println("middleName "+user.getMiddleName());
            System.out.println("lastName "+user.getLastName());
            System.out.println("emailId "+user.getEmail());
            System.out.println("phoneNo "+user.getPhoneNo());

            userLoginDetail.setUserId(user.getUserId());
            userLoginDetail.setUserName(user.getUserName());
            userLoginDetail.setFirstName(user.getFirstName());
            userLoginDetail.setMiddleName(user.getMiddleName());
            userLoginDetail.setLastName(user.getLastName());
            userLoginDetail.setEmailId(user.getEmail());
            userLoginDetail.setPhoneNo(user.getPhoneNo());
            userLoginDetail.setFullName((user.getFirstName()+""+( user.getMiddleName()==""?"":user.getMiddleName()+" "  )+""+user.getLastName()).trim());

        }
        apiLoginReturnModel.setMessage(message);
        apiLoginReturnModel.setToken(token);
        apiLoginReturnModel.setUser(userLoginDetail);

        return ResponseEntity.ok(apiLoginReturnModel);
    }

    private String authenticate(String userName, String password) throws Exception {
        String message = "";
        try {
            message = "success";
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        } catch (DisabledException e) {
            message = "User is disabled";
            // throw new Exception("User Disabled "+e.getMessage());
        } catch (BadCredentialsException e) {
            // TODO: handle exception
            message = "Invalid Credentials";
            // throw new Exception("Invalid Credentials "+e.getMessage());
        }
        return message;

    }
    @GetMapping("/test")
    private String test() {
        return "Welcome to ERPTOOL.\n Server is working fine.";
    }
}