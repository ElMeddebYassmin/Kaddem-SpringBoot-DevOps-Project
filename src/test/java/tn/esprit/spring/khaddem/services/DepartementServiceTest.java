package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;

import java.util.HashSet;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DepartementServiceTest {

    @Mock
    DepartementRepository departementRepository;

    @InjectMocks
    DepartementServiceImpl departementService;

    @Test
    void createDepartementTest() {
        Departement departement = new Departement();
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement savedDepartement = departementService.addDepartement(departement);

        verify(departementRepository, times(1)).save(departement);
        Assertions.assertEquals(departement, savedDepartement);
    }

    @Test
    void updateDepartementTest() {
        int departementId = 1;
        Departement departement = new Departement(departementId, "departement123", new HashSet<>());

        String updatedName = "New Department Name";
        departement.setNomDepart(updatedName);

        Departement updatedDepartement = departementService.addDepartement(departement);

        verify(departementRepository, times(1)).save(departement);
        Assertions.assertEquals(updatedName, updatedDepartement.getNomDepart());
    }


    @Test
    void deleteDepartementTest() {
        int departementId = 1;
        Departement departement = new Departement(departementId, "departement123", new HashSet<>());

        departementService.removeDepartement(departementId);

        verify(departementRepository, times(1)).deleteById(departementId);
    }

}
