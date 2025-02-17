package org.exploreandlearn.auth.repository;

import org.exploreandlearn.auth.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByName(String name);

    Optional<UserInfo> findByMobileNo(Long mobileNo);

}
