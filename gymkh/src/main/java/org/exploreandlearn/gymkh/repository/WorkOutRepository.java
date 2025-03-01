package org.exploreandlearn.gymkh.repository;

import org.exploreandlearn.gymkh.entity.WorkOutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOutRepository extends JpaRepository<WorkOutEntity, Integer> {
}
