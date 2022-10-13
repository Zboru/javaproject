package com.zborowski.prisoncell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrisonCellController {
    @Autowired private PrisonCellService service;

    @GetMapping("/api/prisonCells")
    public List<PrisonCell> getPrisonCells() {
        return service.getCells();
    }
}
