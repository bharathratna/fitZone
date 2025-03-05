package com.exploreandlearn.gymkh.controller;

import com.exploreandlearn.gymkh.entity.WorkOutEntity;
import com.exploreandlearn.gymkh.model.WorkOutRequestResponse;
import com.exploreandlearn.gymkh.response.VariationResponse;
import com.exploreandlearn.gymkh.service.WorkOutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/v1/api/workout")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class WorkController {



    private final WorkOutServiceImpl workOutService;

    @GetMapping("/all")
    public List<WorkOutEntity> getAllWorkEntity(){
        return workOutService.getWorkOut();
    }

    @GetMapping("/variation")
    public List<VariationResponse> getVariationEntity(@RequestParam("id") int workOutId){
        return workOutService.getVariation(workOutId);
    }

    @PostMapping("/scheduleWorkOut")
    public ResponseEntity<String> scheduleWorkout(@RequestParam("userId")int userId, @RequestBody List<WorkOutRequestResponse> workOutRequestList){
        try{
            String response = workOutService.addWork(userId, workOutRequestList);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getWorkOut")
    public List<WorkOutRequestResponse> getList(@RequestParam("id") Integer userId){
        return workOutService.getScheduleWorkOut(userId);
    }

}
