package org.exploreandlearn.rakshithlearnSpringboot.service;

import org.exploreandlearn.rakshithlearnSpringboot.entity.VariationEntity;
import org.exploreandlearn.rakshithlearnSpringboot.entity.WorkOutEntity;
import org.exploreandlearn.rakshithlearnSpringboot.repository.VariationRepository;
import org.exploreandlearn.rakshithlearnSpringboot.repository.WorkOutRepository;
import org.exploreandlearn.rakshithlearnSpringboot.response.VariationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class WorkOutServiceImpl {

    private final WorkOutRepository workOutRepository;
    private final VariationRepository variationRepository;

    public List<WorkOutEntity> getWorkOut(){
        return workOutRepository.findAll();
    }

    public List<VariationResponse> getVariation(Integer workoutId){
        List<VariationEntity> byWorkOutID = variationRepository.getByWorkOutID(workoutId);
        if(byWorkOutID.isEmpty()) return Collections.emptyList();
        return byWorkOutID.stream()
                .map(mapper()).toList();
    }
    private Function<VariationEntity, VariationResponse> mapper(){
        return (entity) -> VariationResponse.builder()
                .variationId(entity.getVariationId())
                .variationName(entity.getVariationName())
                .build();
    }
}
