package com.exploreandlearn.gymkh.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class UserResponse {
    private Integer userId;
    private String name;
    private long mobileNo;
}
