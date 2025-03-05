package com.exploreandlearn.gymkh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "work_out")
@Data
public class WorkOutEntity {
    @Id
    private Integer workOutId;
    private String workOutName;
}
