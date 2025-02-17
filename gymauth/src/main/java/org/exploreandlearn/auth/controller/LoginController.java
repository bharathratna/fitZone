package org.exploreandlearn.auth.controller;

import org.exploreandlearn.auth.request.AuthRequest;
import org.exploreandlearn.auth.request.UserRequest;
import org.exploreandlearn.auth.service.UserInfoService;
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
    public String registerUser(@RequestBody UserRequest userRequest) {
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
}
