package org.exploreandlearn.auth.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedUser {
    private String username;
    private String role;
    private String id;
}
