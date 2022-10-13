package com.zborowski.inmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InmateService {

    @Autowired private InmateRepository repository;

    public List<Inmate> getInmates() {
        return (List<Inmate>) repository.findAll();
    }
}
