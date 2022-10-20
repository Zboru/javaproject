package com.zborowski.prisoncell;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrisonCellRepository extends CrudRepository<PrisonCell, Integer> {

    @Query("SELECT pc FROM PrisonCell pc WHERE pc.residentsNumber < pc.maxCapacity")
    List<PrisonCell> getCellsWithFreeSpace();
}
