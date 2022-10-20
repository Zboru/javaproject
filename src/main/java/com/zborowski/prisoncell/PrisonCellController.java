package com.zborowski.prisoncell;

import com.zborowski.inmate.Inmate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrisonCellController {
    @Autowired private PrisonCellService service;

    @GetMapping("/api/prisonCells")
    public List<PrisonCell> getPrisonCells() {
        return service.getCells();
    }

    @PostMapping("/api/prisonCells")
    public ResponseEntity<PrisonCell> addPrisonCell(@RequestBody PrisonCell prisonCell) {
        PrisonCell _prisonCell = service.save(prisonCell);
        return new ResponseEntity<>(_prisonCell, HttpStatus.CREATED);
    }

    @PatchMapping({"/api/prisonCells/{prisonCellId}"})
    public ResponseEntity<PrisonCell> updatePrisonCell(@PathVariable int prisonCellId, @RequestBody PrisonCell prisonCell) {
        service.update(prisonCellId, prisonCell);
        return new ResponseEntity<>(service.getPrisonCellById(prisonCellId), HttpStatus.OK);
    }

    @DeleteMapping({"/api/prisonCells/{prisonCellId}"})
    public ResponseEntity<Inmate> deletePrisonCell(@PathVariable int prisonCellId) {
        service.delete(prisonCellId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/prisonCells/free")
    public List<PrisonCell> getCellsWithFreeSpace() {
        return service.getCellsWithFreeSpace();
    }
}
