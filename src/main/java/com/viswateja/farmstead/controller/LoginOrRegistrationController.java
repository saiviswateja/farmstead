package com.viswateja.farmstead.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.viswateja.farmstead.DTO.Requests.LoginRequest;
import com.viswateja.farmstead.DTO.Responses.LoginResponse;
import com.viswateja.farmstead.entity.User;
import com.viswateja.farmstead.helper.JWTHelper;
import com.viswateja.farmstead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginOrRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTHelper jwtHelper;

    @GetMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        String email =  loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if(email.isEmpty() && password.isEmpty()) {
            throw new Exception("Please Provide the valid credentials");
        }
        User user = userService.getUserWithEmailAddress(email);
        if(user==null) {
            throw new Exception("User Not Found");
        }
        if(user.getPassword().equals(loginRequest.getPassword())) {
            String accessToken = "";
            try {
                accessToken = jwtHelper.createJWT(user.getUserPk());
            } catch (JWTCreationException jwtCreationException) {
                throw new Exception("Error while creating JWT Token");
            }
            LoginResponse loginResponse = LoginResponse.builder()
                    .accessToken(accessToken)
                    .userName(user.getUsername())
                    .userPk(user.getUserPk())
                    .build();
            return loginResponse;
        }
        throw new Exception("Invalid Credentials");
    }

    @PostMapping("/register")
    public LoginResponse registerUser(@RequestBody User user) throws Exception {
        if(userService.getUserWithEmailAddress(user.getEmailAddress())!=null) {
            throw new Exception("Email Id already taken");
        }
        User registeredUser =  userService.saveUser(user);
        if(registeredUser==null) {
            throw new Exception("Error while Signing up the user");
        }
        return LoginResponse.builder()
                .accessToken(user.getEmailAddress())
                .userName(user.getUsername())
                .userPk(user.getUserPk())
                .build();
    }
}
