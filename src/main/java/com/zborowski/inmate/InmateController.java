package com.zborowski.inmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InmateController {
    @Autowired private InmateService service;

    @GetMapping("/api/inmates")
    public List<Inmate> showInmateList() {
        return service.getInmates();
    }

}
