package com.zborowski.inmate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InmateRepository extends CrudRepository<Inmate, Integer> {
    @Query("FROM Inmate inm WHERE inm.prisonCell.id = :id")
    List<Inmate> getInmateByPrisonCellId(int id);
}
