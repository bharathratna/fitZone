package com.exploreandlearn.gymkh.service;

import com.exploreandlearn.gymkh.repository.WorkOutUserRepository;
import lombok.RequiredArgsConstructor;
import com.exploreandlearn.gymkh.model.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final WorkOutUserRepository workOutUserRepository;

    public UserResponse getUser(Integer id){
        return workOutUserRepository.findById(id).map(user -> UserResponse
                .builder().name(user.getName()).mobileNo(user.getMobileNo())
                .userId(user.getId()).build()).orElse(UserResponse.builder().build());
    }
}
