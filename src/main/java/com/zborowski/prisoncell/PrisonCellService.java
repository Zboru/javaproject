package com.zborowski.prisoncell;

import com.zborowski.inmate.Inmate;
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

    public PrisonCell getPrisonCellById(int id) {
        return repository.findById(id).get();
    }

    public PrisonCell save(PrisonCell prisonCell) {
        prisonCell.setResidentsNumber(0);
        return repository.save(prisonCell);
    }

    public void update(int id, PrisonCell _inmate) {
        PrisonCell prisonCell = repository.findById(id).get();
        prisonCell.setCellName(_inmate.getCellName());
        prisonCell.setMaxCapacity(_inmate.getMaxCapacity());
        repository.save(prisonCell);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<PrisonCell> getCellsWithFreeSpace() {
        return repository.getCellsWithFreeSpace();
    }
}
