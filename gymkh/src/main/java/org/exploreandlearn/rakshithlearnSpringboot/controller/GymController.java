package org.exploreandlearn.rakshithlearnSpringboot.controller;


import org.exploreandlearn.rakshithlearnSpringboot.model.BMIRequest;
import org.exploreandlearn.rakshithlearnSpringboot.entity.Client;
import org.exploreandlearn.rakshithlearnSpringboot.service.GymServiceImpl;
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
