package org.exploreandlearn.rakshithlearnSpringboot.repository;

import org.exploreandlearn.rakshithlearnSpringboot.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository  extends JpaRepository<Client, Integer> {
}
