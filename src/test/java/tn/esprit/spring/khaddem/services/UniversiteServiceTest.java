package tn.esprit.spring.khaddem.services;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Statut;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


@SpringBootTest
class UniversiteServiceTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1, "Universite 1","Tunis",new Date(), Statut.PRIVEE));
        universites.add(new Universite(2, "Universite 2","Bizerte",new Date(), Statut.PRIVEE));
        Mockito.when(universiteRepository.findAll()).thenReturn(universites);
        List<Universite> result = universiteService.retrieveAllUniversites();
        assertEquals(universites, result);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite(1, "Universite 1","Tunis",new Date(), Statut.PRIVEE);
        Mockito.when(universiteRepository.save(universite)).thenReturn(universite);
        Universite result = universiteService.addUniversite(universite);
        assertEquals(universite, result);
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite(1, "Universite 1", "Tunis", new Date(), Statut.PRIVEE);
        Universite updatedUniversite = new Universite(1, "Universite2", "Sousse", new Date(), Statut.PUBLIQUE);
        Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        Mockito.when(universiteService.updateUniversite(updatedUniversite, 1)).thenReturn(updatedUniversite);
        Universite result = universiteService.updateUniversite(updatedUniversite, 1);
        assertEquals(updatedUniversite, result);
    }

    @Test
    void testRemoveUniversite() {
        universiteService.removeUniversite(1);
        Mockito.verify(universiteRepository).deleteById(1);
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite(1, "Universite 1","Tunis",new Date(), Statut.PRIVEE);
        Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        Universite result = universiteService.retrieveUniversite(1);
        assertEquals(universite, result);
    }
    @Test
    void testRetrieveUniversite2() {
        Universite universite = new Universite(1, "Universite 2","Tunis",new Date(), Statut.PRIVEE);
        Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        Universite result = universiteService.retrieveUniversite(1);
        assertEquals(universite, result);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = new Universite(1, "Universite 1","Tunis",new Date(), Statut.PRIVEE);
        universite.setDepartements(new ArrayList<>());

        Set<Etudiant> etudiants = null;
        Departement departement = new Departement(1, "Informatique", etudiants);
        Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        Mockito.when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        universiteService.assignUniversiteToDepartement(1, 1);
        assertEquals(1, universite.getDepartements().size());
        assertEquals(departement, universite.getDepartements().get(0));
    }


}

