package org.exploreandlearn.rakshithlearnSpringboot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Client {
    //name,age height, weight  BMI, Goal
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer clientId;
    private String name;
    private int age;
    private int height;
    private double weight;
    private double bodyBmi;
    private Integer mobile;
}
