package com.exploreandlearn.gymkh.repository;

import com.exploreandlearn.gymkh.entity.WorkOutSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOutScheduleRepository extends
        JpaRepository<WorkOutSchedule, Integer> {

    List<WorkOutSchedule> findByUserId(Integer userId);
}
