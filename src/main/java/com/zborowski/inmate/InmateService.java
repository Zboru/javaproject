package com.zborowski.inmate;

import com.zborowski.prisoncell.PrisonCell;
import com.zborowski.prisoncell.PrisonCellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InmateService {

    @Autowired private InmateRepository repository;
    @Autowired private PrisonCellRepository pc_repository;

    public List<Inmate> getInmates() {
        return (List<Inmate>) repository.findAll();
    }

    public Inmate getInmateById(int id) {
        return repository.findById(id).get();
    }

    public Inmate save(Inmate inmate) {
        // Increment residents number of cell
        PrisonCell prisonCell = inmate.getPrisonCell();
        int residents_number = prisonCell.getResidentsNumber();
        prisonCell.setResidentsNumber(residents_number + 1);
        pc_repository.save(prisonCell);

        return repository.save(inmate);
    }

    public void update(int id, Inmate _inmate) {
        Inmate inmate = repository.findById(id).get();
        // Decrement residents number of cell
        PrisonCell prisonCell = inmate.getPrisonCell();
        int residents_number = prisonCell.getResidentsNumber();
        prisonCell.setResidentsNumber(residents_number - 1);
        pc_repository.save(prisonCell);

        // Increment residents number of changed cell
        prisonCell = _inmate.getPrisonCell();
        residents_number = prisonCell.getResidentsNumber();
        prisonCell.setResidentsNumber(residents_number + 1);
        pc_repository.save(prisonCell);

        inmate.setFirstname(_inmate.getFirstname());
        inmate.setLastname(_inmate.getLastname());
        inmate.setPrisonCell(_inmate.getPrisonCell());
        repository.save(inmate);
    }

    public void delete(int id) {
        Inmate inmate = repository.findById(id).get();
        // Decrement residents number of cell
        PrisonCell prisonCell = inmate.getPrisonCell();
        int residents_number = prisonCell.getResidentsNumber();
        prisonCell.setResidentsNumber(residents_number - 1);

        repository.deleteById(id);
    }
}
