package org.exploreandlearn.rakshithlearnSpringboot.repository;

import org.exploreandlearn.rakshithlearnSpringboot.entity.WorkOutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOutRepository extends JpaRepository<WorkOutEntity, Integer> {
}
