package org.exploreandlearn.rakshithlearnSpringboot.controller;

import org.exploreandlearn.rakshithlearnSpringboot.entity.WorkOutEntity;
import org.exploreandlearn.rakshithlearnSpringboot.response.VariationResponse;
import org.exploreandlearn.rakshithlearnSpringboot.service.WorkOutServiceImpl;
import lombok.RequiredArgsConstructor;
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

}
