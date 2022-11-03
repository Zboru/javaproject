package com.zborowski.inmate;

import com.zborowski.prisoncell.PrisonCell;
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
    public ResponseEntity<?> addInmate(@RequestBody Inmate inmate) {
        return service.save(inmate);
    }

    @PatchMapping({"/api/inmates/{inmateId}"})
    public ResponseEntity<?> updateInmate(@PathVariable int inmateId, @RequestBody Inmate inmate) {
        return service.update(inmateId, inmate);
    }

    @DeleteMapping({"/api/inmates/{inmateId}"})
    public ResponseEntity<Inmate> deleteInmate(@PathVariable int inmateId) {
        service.delete(inmateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/inmates/byPrisonCell/{prisonCellId}")
    public List<Inmate> getCellsWithFreeSpace(@PathVariable int prisonCellId) {
        return service.getInmateByPrisonCellId(prisonCellId);
    }

}
