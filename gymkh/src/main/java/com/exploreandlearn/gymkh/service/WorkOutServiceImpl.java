package com.exploreandlearn.gymkh.service;

import com.exploreandlearn.gymkh.response.VariationResponse;
import lombok.RequiredArgsConstructor;
import com.exploreandlearn.gymkh.entity.VariationEntity;
import com.exploreandlearn.gymkh.entity.WorkOutEntity;
import com.exploreandlearn.gymkh.entity.WorkOutSchedule;
import com.exploreandlearn.gymkh.model.WorkOutRequestResponse;
import com.exploreandlearn.gymkh.repository.VariationRepository;
import com.exploreandlearn.gymkh.repository.WorkOutRepository;
import com.exploreandlearn.gymkh.repository.WorkOutScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class WorkOutServiceImpl {

    private final WorkOutRepository workOutRepository;
    private final VariationRepository variationRepository;
    private final WorkOutScheduleRepository workOutScheduleRepository;

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

    public String addWork(Integer userId, List<WorkOutRequestResponse> workOutRequest) throws Exception {
        if(workOutRequest.isEmpty()) throw new Exception("Work Out cannot be empty !!");
        List<WorkOutSchedule> list = workOutRequest.
                stream().map(req -> entityMap(userId, req)).toList();
        workOutScheduleRepository.saveAllAndFlush(list);
        return "WorkOut Scheduled Successfully";
    }

    private WorkOutSchedule entityMap(Integer userId, WorkOutRequestResponse request) {
        WorkOutSchedule workOutSchedule = new WorkOutSchedule();
        workOutSchedule.setWorkOutType(request.getWorkout());
        workOutSchedule.setReference(request.getReference());
        workOutSchedule.setSet(request.getSet());
        workOutSchedule.setUserId(userId);
        workOutSchedule.setReps(request.getReps());
        workOutSchedule.setVariation(request.getVariation());
        workOutSchedule.setDay(request.getDay());
        return workOutSchedule;
    }

    public List<WorkOutRequestResponse> getScheduleWorkOut(Integer userId) {
        List<WorkOutSchedule> byUserId = workOutScheduleRepository.findByUserId(userId);
        if(byUserId.isEmpty()) return Collections.emptyList();
        else return byUserId.stream().map(this::getWorkOut).toList();
    }

    private WorkOutRequestResponse getWorkOut(WorkOutSchedule workOutSchedule) {
        return WorkOutRequestResponse.builder()
                .day(workOutSchedule.getDay())
                .set(workOutSchedule.getSet())
                .reps(workOutSchedule.getReps())
                .workout(workOutSchedule.getWorkOutType())
                .reference(workOutSchedule.getReference())
                .variation(workOutSchedule.getVariation()).build();
    }

}
