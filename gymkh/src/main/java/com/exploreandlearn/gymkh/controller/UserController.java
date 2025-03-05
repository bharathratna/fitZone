package com.exploreandlearn.gymkh.controller;

import com.exploreandlearn.gymkh.model.UserResponse;
import com.exploreandlearn.gymkh.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/getUser")
    public UserResponse getUser(@RequestParam("memberId") Integer memberId){
        return userService.getUser(memberId);
    }
}
