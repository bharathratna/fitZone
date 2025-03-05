package com.exploreandlearn.auth.controller;

import com.exploreandlearn.auth.service.UserInfoService;
import com.exploreandlearn.auth.request.AuthRequest;
import com.exploreandlearn.auth.request.UserRequest;
import com.exploreandlearn.auth.response.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserInfoService userInfoService;

    @PostMapping("/register")
    public Integer registerUser(@RequestBody UserRequest userRequest) {
        return userInfoService.addUser(userRequest);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest authRequest){
        try {
            return ResponseEntity.ok(
                    Map.of("token",
                            userInfoService.generateToken(authRequest.getUsername())
                    ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("token", e.getMessage()));
        }
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String, String>> checkUserExist(@RequestParam("mobileNo") Long mobile){
        boolean checkUser = userInfoService.checkUser(mobile);
        if(!checkUser) return ResponseEntity.ok(Map.of("message","Ok", "status", "200"));
        else return ResponseEntity.ok(Map.of("message","User Already Present", "status", "404"));
    }

    @GetMapping("/getUserInfo")
    public LoggedUser getUserInfo(@RequestHeader("Authorization") String token){
       return userInfoService.getUser(token);

    }
}
