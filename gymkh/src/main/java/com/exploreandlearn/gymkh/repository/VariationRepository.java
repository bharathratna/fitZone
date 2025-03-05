package com.exploreandlearn.gymkh.repository;

import com.exploreandlearn.gymkh.entity.VariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariationRepository extends JpaRepository<VariationEntity, Integer> {

    @Query(value = "SELECT * FROM variation where work_out_id = ?1", nativeQuery = true)
    List<VariationEntity> getByWorkOutID(int workOutId);
}
