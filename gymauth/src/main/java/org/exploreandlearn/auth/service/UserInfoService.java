package org.exploreandlearn.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.exploreandlearn.auth.repository.UserRepository;
import org.exploreandlearn.auth.entity.UserInfo;
import org.exploreandlearn.auth.entity.UserInfoDetails;
import org.exploreandlearn.auth.request.UserRequest;
import org.exploreandlearn.auth.response.LoggedUser;
import org.exploreandlearn.auth.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserInfoService  {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    @Autowired
    private  AuthenticationManager authenticationManager;

    public UserInfoService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(String userRequest) throws UsernameNotFoundException {
        Optional<UserInfoDetails> user;
        if(Character.isDigit(userRequest.charAt(0))){
            user = userRepository.findByMobileNo(Long.parseLong(userRequest))
                    .map(UserInfoDetails::new);
        }
        else {
            user = userRepository.findByEmail(userRequest)
                    .map(UserInfoDetails::new);
            if (user.isEmpty()) user = userRepository
                    .findByName(userRequest)
                    .map(UserInfoDetails::new);
        }
        if(user.isPresent()) {
            return jwtUtil.generateToken(user.get());
        }
        throw new UsernameNotFoundException("User not Found");
    }

    public Integer addUser(UserRequest userRequest){
        UserInfo user = new UserInfo();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRoles(getRoles(userRequest.getIndicator()));
        user.setMobileNo(userRequest.getMobileNo());
        UserInfo res = userRepository.save(user);
        return  res.getId();
    }

    private String getRoles(Integer number){
        if(number == 2) return "staff";
        else return "trainee";
    }

    public boolean checkUser(Long mobileNo) {
       return  userRepository.findByMobileNo(mobileNo).isPresent();
    }
    
    public LoggedUser getUser(String token){
        String jwtToken = token.substring(7);
        Claims claims = jwtUtil.extractClaim(jwtToken);
        String userId = claims.get("id").toString();
        String roles = userRepository.findById(Integer.parseInt(userId)).map(UserInfo::getRoles).orElse("");
        return LoggedUser.builder().id(userId).role(roles).username(claims.getSubject()).build();
    }
}
