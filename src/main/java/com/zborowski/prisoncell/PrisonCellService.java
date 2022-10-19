package com.zborowski.prisoncell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrisonCellService {
    @Autowired
    private PrisonCellRepository repository;

    public List<PrisonCell> getCells() {
        return (List<PrisonCell>) repository.findAll();
    }

    public List<PrisonCell> getCellsWithFreeSpace() {
        return repository.getCellsWithFreeSpace();
    }
}
