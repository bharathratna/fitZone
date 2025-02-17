package org.exploreandlearn.auth.service;

import org.exploreandlearn.auth.repository.UserRepository;
import org.exploreandlearn.auth.entity.UserInfo;
import org.exploreandlearn.auth.entity.UserInfoDetails;
import org.exploreandlearn.auth.request.UserRequest;
import org.exploreandlearn.auth.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public String addUser(UserRequest userRequest){
        UserInfo user = new UserInfo();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRoles(userRequest.getRoles());
        user.setMobileNo(userRequest.getMobileNo());
        userRepository.save(user);
        return  "User is added Successfully";
    }
}
