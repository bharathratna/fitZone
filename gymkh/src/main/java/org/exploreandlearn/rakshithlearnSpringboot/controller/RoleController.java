package org.exploreandlearn.rakshithlearnSpringboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.exploreandlearn.auth.utility.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/role")
public class RoleController {

    private final JwtUtil jwtUtil;

    public RoleController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/getRoles")
    public String roles(@RequestHeader(value = "Authorization") String token) throws JsonProcessingException {
        String jwtToken = token.substring(7);
        System.out.println(jwtToken);
        ObjectMapper objectMapper = new ObjectMapper();
        String roles = jwtUtil.getRoles(jwtToken);
        List<String> maps = objectMapper.readValue(roles, new TypeReference<List<String>>() {
        });
        System.out.println(maps);
        return roles;
    }
}
