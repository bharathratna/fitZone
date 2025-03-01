package org.exploreandlearn.gymkh.controller;


import org.exploreandlearn.gymkh.model.BMIRequest;
import org.exploreandlearn.gymkh.entity.Client;
import org.exploreandlearn.gymkh.service.GymServiceImpl;
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
