package com.exploreandlearn.gymkh.repository;

import com.exploreandlearn.auth.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOutUserRepository extends JpaRepository<UserInfo, Integer> {
}
