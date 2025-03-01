package org.exploreandlearn.gymkh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "variation")
@Data
public class VariationEntity {
    @Id
    private Integer variationId;
    private String variationName;
    @ManyToOne
    @JoinColumn(name = "workOutId", nullable = false)
    private WorkOutEntity workOutId;
}
