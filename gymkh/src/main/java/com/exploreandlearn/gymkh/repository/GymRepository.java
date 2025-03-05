package com.exploreandlearn.gymkh.repository;

import com.exploreandlearn.gymkh.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository  extends JpaRepository<Client, Integer> {
}
