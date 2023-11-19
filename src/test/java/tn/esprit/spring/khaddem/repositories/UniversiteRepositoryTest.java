package tn.esprit.spring.khaddem.repositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.khaddem.entities.Statut;
import tn.esprit.spring.khaddem.entities.Universite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UniversiteRepositoryTest {

    @Autowired
    private UniversiteRepository universiteRepository;

    private Universite universite;

    @BeforeEach
    void setUp() {
        universite = new Universite();
        universite.setNomUniv("universite1");
        universite.setAdresse("Ariana");
        universite.setStatut(Statut.PRIVEE);
        universite.setDateCreation(new Date());
    }

    @Test
    @Order(0)
    void testSave() {
        Universite savedUniversite = universiteRepository.save(universite);
        assertNotNull(savedUniversite.getIdUniversite());
        assertEquals(universite, savedUniversite);
    }

    @Test
    @Order(1)
    void testFindById() {
        Universite savedUniversite = universiteRepository.save(universite);
        Universite foundUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);
        assertNotNull(foundUniversite);
        assertEquals(savedUniversite.getIdUniversite(), foundUniversite.getIdUniversite());
        assertEquals(savedUniversite.getNomUniv(), foundUniversite.getNomUniv());
        assertEquals(savedUniversite.getAdresse(), foundUniversite.getAdresse());
        assertEquals(savedUniversite.getStatut(), foundUniversite.getStatut());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String savedDateStr = dateFormat.format(savedUniversite.getDateCreation());
        String foundDateStr = dateFormat.format(foundUniversite.getDateCreation());
        assertEquals(savedDateStr, foundDateStr);
    }


    @Test
    @Order(2)
    void testDeleteById() {
        Universite savedUniversite = universiteRepository.save(universite);
        universiteRepository.deleteById(savedUniversite.getIdUniversite());
        assertFalse(universiteRepository.existsById(savedUniversite.getIdUniversite()));
    }

    @Test
    @Order(3)
    void testFindAll() {
        Iterable<Universite> universites = universiteRepository.findAll();
        assertNotNull(universites);
        List<Universite> universiteList = new ArrayList<>();
        universites.forEach(universiteList::add);
        assertTrue(universiteList.isEmpty());
    }
}
