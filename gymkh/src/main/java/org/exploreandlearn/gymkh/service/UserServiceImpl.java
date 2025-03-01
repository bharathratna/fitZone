package org.exploreandlearn.gymkh.service;

import lombok.RequiredArgsConstructor;
import org.exploreandlearn.gymkh.model.UserResponse;
import org.exploreandlearn.gymkh.repository.WorkOutUserRepository;
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
