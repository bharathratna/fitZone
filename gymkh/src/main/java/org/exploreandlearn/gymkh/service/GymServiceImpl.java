package org.exploreandlearn.gymkh.service;

import org.exploreandlearn.gymkh.model.BMIRequest;
import org.exploreandlearn.gymkh.entity.Client;
import org.exploreandlearn.gymkh.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GymServiceImpl {

    private final GymRepository gymRepository;

    public Integer registerClient(Client client){
        Client registry = gymRepository.save(client);
        return registry.getClientId();
    }

    public double calculateBMI(BMIRequest request){
        double height = (double) request.getHeight() / 100 ;
        return request.getWeight()/(height * height);
    }
}
