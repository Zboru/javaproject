package com.zborowski.inmate;

import com.google.gson.Gson;
import com.zborowski.prisoncell.PrisonCell;
import com.zborowski.prisoncell.PrisonCellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InmateService {

    @Autowired private InmateRepository inmateRepository;
    @Autowired private PrisonCellRepository prisonCellRepository;


    private boolean checkCellHasDangerousInmate(int id) {
        List<Inmate> inmates = prisonCellRepository.getCellInmates(id);
        return inmates.stream().anyMatch(o -> o.getDangerState().equals(3));
    }

    private boolean checkIfCellHasInmates(int id, int inmateId) {
        List<Inmate> inmates = prisonCellRepository.getCellInmates(id);
        return inmates.stream().filter(o -> o.getId() != inmateId).toList().size() > 0;
    }

    public List<Inmate> getInmates() {
        return (List<Inmate>) inmateRepository.findAll();
    }

    public List<Inmate> getInmateByPrisonCellId(int id) {
        return inmateRepository.getInmateByPrisonCellId(id);
    }

    public ResponseEntity<?> save(Inmate inmate) {
        PrisonCell prisonCell = inmate.getPrisonCell();

        // Check if prison cell has already assigned inmates
        if (inmate.getDangerState() == 3 && this.checkIfCellHasInmates(prisonCell.getId(), inmate.getId())) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            response.put("type", "HIGH_RISK_ADD");
            response.put("message", "Nie można dodać groźnego więźnia do celi z innymi");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Check if prison cell has high risk inmates
        if (this.checkCellHasDangerousInmate(prisonCell.getId())) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            response.put("type", "HAS_HIGH_RISK");
            response.put("message", "Cell has high risk inmate, can't add more inmates");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // If not, increase number and save inmate
        int residents_number = prisonCell.getResidentsNumber();
        prisonCell.setResidentsNumber(residents_number + 1);
        prisonCellRepository.save(prisonCell);
        Inmate _inmate = inmateRepository.save(inmate);
        return new ResponseEntity<>(_inmate, HttpStatus.CREATED);
    }

    public ResponseEntity<?> update(int id, Inmate newInmate) {
        Inmate inmate = inmateRepository.findById(id).get();
        PrisonCell prisonCell = inmate.getPrisonCell();
        PrisonCell newPrisonCell = newInmate.getPrisonCell();

        boolean samePrisonCell = Objects.equals(newInmate.getPrisonCell().getId(), inmate.getPrisonCell().getId());

        // Check if prison cell has inmates
        if (newInmate.getDangerState() == 3 && this.checkIfCellHasInmates(newPrisonCell.getId(), inmate.getId())) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            response.put("type", "HIGH_RISK_ADD_TO_OCCUPIED_CELL");
            response.put("message", "Nie można dodać groźnego więźnia do celi z innymi");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Check if prison cell has high risk inmates
        if (this.checkCellHasDangerousInmate(newPrisonCell.getId()) && !samePrisonCell) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            response.put("type", "HAS_HIGH_RISK");
            response.put("message", "Do celi z groźnym więźniem nie można dodać innych");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // If prison cell ids are not equal, do increment/decrement thing
        // on its resident number
        if (!samePrisonCell) {
            // Decrement residents number of cell
            int residents_number = prisonCell.getResidentsNumber();
            prisonCell.setResidentsNumber(residents_number - 1);
            prisonCellRepository.save(prisonCell);

            // Increment residents number of changed cell
            prisonCell = newInmate.getPrisonCell();
            residents_number = prisonCell.getResidentsNumber();
            prisonCell.setResidentsNumber(residents_number + 1);
            prisonCellRepository.save(prisonCell);
        }

        inmate.setFirstname(newInmate.getFirstname());
        inmate.setLastname(newInmate.getLastname());
        inmate.setPrisonCell(newInmate.getPrisonCell());
        inmate.setDangerState(newInmate.getDangerState());
        return new ResponseEntity<>(inmateRepository.save(inmate), HttpStatus.CREATED);
    }

    public void delete(int id) {
        Inmate inmate = inmateRepository.findById(id).get();
        // Decrement residents number of cell
        PrisonCell prisonCell = inmate.getPrisonCell();
        int residents_number = prisonCell.getResidentsNumber();
        prisonCell.setResidentsNumber(residents_number - 1);

        inmateRepository.deleteById(id);
    }
}
