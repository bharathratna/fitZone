package org.exploreandlearn.gymkh.repository;

import org.exploreandlearn.gymkh.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository  extends JpaRepository<Client, Integer> {
}
