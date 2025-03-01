package org.exploreandlearn.gymkh.controller;

import lombok.RequiredArgsConstructor;
import org.exploreandlearn.gymkh.model.UserResponse;
import org.exploreandlearn.gymkh.service.UserServiceImpl;
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
