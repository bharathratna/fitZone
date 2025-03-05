package com.exploreandlearn.gymkh.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkOutRequestResponse {
    private Integer day;
    private Integer set;
    private String variation;
    private String workout;
    private String reference;
    private Integer reps;
}
