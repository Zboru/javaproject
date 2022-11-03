package com.zborowski;

import com.zborowski.inmate.Inmate;
import com.zborowski.inmate.InmateRepository;
import com.zborowski.prisoncell.PrisonCell;
import com.zborowski.prisoncell.PrisonCellRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InmateRepositoryTest {
    @Autowired
    private InmateRepository inmateRepository;

    @Autowired
    private PrisonCellRepository prisonCellRepository;

    @Test
    public void save() {
        PrisonCell prisonCell = new PrisonCell();
        prisonCell.setResidentsNumber(0);
        prisonCell.setCellName("Cela nr" + Math.random() * 500);
        prisonCell.setMaxCapacity(1);
        prisonCellRepository.save(prisonCell);

        Inmate inmate = new Inmate();
        inmate.setFirstname("T");
        inmate.setLastname("T");
        inmate.setPrisonCell(prisonCell);

        Inmate newInmate = inmateRepository.save(inmate);
        assertNotNull(newInmate);
    }

    @Test
    public void delete() {
        PrisonCell prisonCell = prisonCellRepository.findAll().iterator().next();

        Inmate inmate = new Inmate();
        inmate.setFirstname("T");
        inmate.setLastname("T");
        inmate.setPrisonCell(prisonCell);

        Inmate newInmate = inmateRepository.save(inmate);

        inmateRepository.deleteById(newInmate.getId());
        assertTrue(inmateRepository.findById(newInmate.getId()).isEmpty());
    }

    @Test
    public void update() {
        Inmate inmate = inmateRepository.findAll().iterator().next();
        inmate.setFirstname("X");
        inmate.setLastname("X");

        Inmate updatedInmate = inmateRepository.save(inmate);

        assertEquals(inmate.getFirstname(), updatedInmate.getFirstname());
    }
}
