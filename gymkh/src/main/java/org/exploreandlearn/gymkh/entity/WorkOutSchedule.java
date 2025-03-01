package org.exploreandlearn.gymkh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class WorkOutSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer workOutScheduleId;
    private Integer day;
    private Integer set;
    private Integer reps;
    private String variation;
    private String workOutType;
    private String reference;
    private Integer userId;
}
