package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Aymen Laroussi
 * @project 5TWIN6-DevGurus-Kaddem
 */

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContratServiceTest {

    @Mock
    ContratRepository contratRepository;

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    ContratServiceImpl contratService;
    Contrat contrat = Contrat.builder()
            .montantContrat(15)
            .dateDebutContrat(Date.from(LocalDate.of(2022, 1, 15)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()))
            .dateFinContrat(new Date())
            .archived(false)
            .specialite(Specialite.WEB)
            .etudiant(new Etudiant())
            .build();

    @Test
    void retrieveAllContratsTest(){
        contratRepository.save(contrat);
        List<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat);
        Mockito.when(contratRepository.findAll()).thenReturn(contratList);

        List<Contrat> contrats = contratService.retrieveAllContrats();
        assertNotNull(contrats);
        assertEquals(1, contrats.size());
        log.info(contrats.toString());
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    void updateContratTest() {
        contrat.setIdContrat(1);
        Contrat updatedContrat = new Contrat();
        updatedContrat.setSpecialite(Specialite.IA);
        updatedContrat.setArchived(true);
        updatedContrat.setMontantContrat(1750);
        updatedContrat.setEtudiant(new Etudiant());

        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
        when(contratRepository.save(contrat)).thenReturn(updatedContrat);
        Contrat result = contratService.updateContrat(updatedContrat, 1);
        assertNotNull(result);
        log.info(result.toString());
        verify(contratRepository, times(1)).findById(1);
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void retrieveContratTest() {
        contrat.setIdContrat(1);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(1);

        assertNotNull(result);
        assertEquals(1, result.getIdContrat());
        log.info(result.toString());

        verify(contratRepository, times(1)).findById(1);
    }

    @Test
    void removeContratTest() {
        contratService.removeContrat(1);
        log.info("Contrat supprimé!");
        verify(contratRepository, times(1)).deleteById(1);
    }

    @Test
    void addContratTest() {
        Contrat result = contratService.addContrat(contrat);
        assertNotNull(result);
        log.info(result.toString());
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void addAndAffectContratToEtudiantTest() {
        Contrat contrat = Contrat.builder()
                .montantContrat(15)
                .dateDebutContrat(Date.from(LocalDate.of(2022, 1, 15)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()))
                .dateFinContrat(new Date())
                .archived(false)
                .specialite(Specialite.WEB)
                .etudiant(new Etudiant())
                .build();
        Etudiant etudiant = mock(Etudiant.class);

        when(etudiantRepository.findByNomEAndPrenomE("Laroussi", "Aymen")).thenReturn(etudiant);
        when(etudiant.getContrats()).thenReturn(new ArrayList<>());

        Contrat result = contratService.addAndAffectContratToEtudiant(contrat, "Laroussi", "Aymen");

        verify(contratRepository, atMost(2)).save(contrat);
        verify(etudiantRepository).findByNomEAndPrenomE("Laroussi", "Aymen");
        verify(etudiant, times(1)).getContrats();
        assertEquals(contrat, result);
    }

    @Test
    void testNbContratsValides() {
        when(contratRepository.getnbContratsValides(contrat.getDateDebutContrat(), contrat.getDateFinContrat())).thenReturn(5);
        Integer result = contratService.nbContratsValides(contrat.getDateDebutContrat(), contrat.getDateFinContrat());

        assertEquals(5, result);
        log.info(result.toString());
        verify(contratRepository, times(1)).getnbContratsValides(contrat.getDateDebutContrat(), contrat.getDateFinContrat());
    }

}
