package com.grocerybooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocerybooking.dto.AuthRequest;
import com.grocerybooking.entity.UserInfo;
import com.grocerybooking.security.JwtService;
import com.grocerybooking.security.UserInfoService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final UserInfoService service; 
    
	private final JwtService jwtService; 
    
    private final AuthenticationManager authenticationManager;
    
    AuthenticationController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) { 
        this.service = service; 
        this.jwtService = jwtService; 
        this.authenticationManager = authenticationManager; 
    }
    
    @PostMapping("/register") 
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) { 
        String response = service.addUser(userInfo); 
        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
    }
    
    @PostMapping("/login") 
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword())); 
        if (authentication.isAuthenticated()) {
        	System.out.println(authentication.getAuthorities());
            String token = jwtService.generateToken(authRequest.getUserName());
            return ResponseEntity.ok(token); 
        } else { 
            throw new UsernameNotFoundException("Invalid user request!"); 
        } 
    }
}
