package com.zborowski.inmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InmateController {
    @Autowired private InmateService service;

    @GetMapping("/api/inmates")
    public List<Inmate> showInmateList() {
        return service.getInmates();
    }

    @PostMapping("/api/inmates")
    public ResponseEntity<Inmate> addInmate(@RequestBody Inmate inmate) {
        Inmate _inmate = service.save(inmate);
        return new ResponseEntity<Inmate>(_inmate, HttpStatus.CREATED);
    }

    @PutMapping({"/api/inmates/{inmateId}"})
    public ResponseEntity<Inmate> updateInmate(@PathVariable int inmateId, @RequestBody Inmate inmate) {
        service.update(inmateId, inmate);
        return new ResponseEntity<>(service.getInmateById(inmateId), HttpStatus.OK);
    }

    @DeleteMapping({"/api/inmates/{inmateId}"})
    public ResponseEntity<Inmate> deleteInmate(@PathVariable int inmateId) {
        service.delete(inmateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
