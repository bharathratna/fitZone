package com.exploreandlearn.gymkh.controller;


import com.exploreandlearn.gymkh.model.BMIRequest;
import com.exploreandlearn.gymkh.entity.Client;
import com.exploreandlearn.gymkh.service.GymServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/api/gym")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class GymController {

    private final GymServiceImpl gymService;

    @PostMapping("/create")
    public Integer createClient(@RequestBody Client client){
        return gymService.registerClient(client);
    }

    @PostMapping("/calculate")
    public Double calculateBMI(@RequestBody BMIRequest bmiRequest){
        return gymService.calculateBMI(bmiRequest);
    }
}
