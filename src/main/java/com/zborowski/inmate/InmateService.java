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

    public Inmate getInmateById(int id) {
        return repository.findById(id).get();
    }

    public Inmate save(Inmate inmate) {
        return repository.save(inmate);
    }

    public Inmate update(int id, Inmate _inmate) {
        Inmate inmate = repository.findById(id).get();
        inmate.setFirstname(_inmate.getFirstname());
        inmate.setLastname(_inmate.getLastname());
        inmate.setPrisonCell(_inmate.getPrisonCell());
        return repository.save(inmate);
    }

    public void delete(int inmateId) {
        repository.deleteById(inmateId);
    }
}
