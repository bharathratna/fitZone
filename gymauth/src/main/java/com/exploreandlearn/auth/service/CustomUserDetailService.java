package com.exploreandlearn.auth.service;

import com.exploreandlearn.auth.entity.UserInfo;
import com.exploreandlearn.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user;
        if(Character.isDigit(username.charAt(0))) {
            user = userRepository.findByName(username);
        }
        else{
            user = userRepository.findByName(username);
            if(user.isEmpty()){
                user = userRepository.findByEmail(username);
            }
        }
        if(user.isPresent()) return new User(
                user.get().getName(),
                user.get().getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.get().getRoles())));
        else throw new UsernameNotFoundException("User not found !!!");
    }
}
