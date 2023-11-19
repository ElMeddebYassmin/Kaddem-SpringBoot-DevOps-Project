package tn.esprit.spring.khaddem.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.khaddem.entities.Departement;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DepartementRepositoryTest {

    @Autowired
    DepartementRepository departementRepository;

    private Departement departementTest;

    @BeforeEach
    public void setUp() {
        departementTest = new Departement();
    }

    @Test
    @Order(0)
    public void testAjouterDepartement() {
        departementTest = departementRepository.save(departementTest);
        log.info(departementTest.toString());
        Assertions.assertNotNull(departementTest.getIdDepartement());
    }

    @Test
    @Order(1)
    public void testModifierDepartement() {
        departementTest.setNomDepart("Departement n°12");
        departementTest = departementRepository.save(departementTest);
        log.info(departementTest.toString());
        Assertions.assertEquals("Departement n°12", departementTest.getNomDepart());
    }

    @Test
    @Order(2)
    public void testSupprimerDepartement() {
        departementRepository.delete(departementTest);
        Departement deletedDepartement = departementRepository.findById(departementTest.getIdDepartement()).orElse(null);
        Assertions.assertNull(deletedDepartement);
    }

    @Test
    @Order(3)
    public void testCompterDepartements() {
        long count = departementRepository.count();
        Assertions.assertEquals(count, departementRepository.findAll().size());
    }


}
