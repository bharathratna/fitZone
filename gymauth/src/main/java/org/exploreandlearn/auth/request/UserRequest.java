package org.exploreandlearn.auth.request;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class UserRequest {
    @Nonnull
    private String name;
    @Nonnull
    private String email;
    @Nonnull
    private Long mobileNo;
    @Nonnull
    private String password;
    private String gender;
    private String confirmPassword;
    private int indicator;
}
