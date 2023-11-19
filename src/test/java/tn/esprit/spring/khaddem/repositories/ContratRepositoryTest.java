package tn.esprit.spring.khaddem.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.entities.Specialite;
import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Aymen Laroussi
 * @project 5TWIN6-DevGurus-Kaddem
 */

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContratRepositoryTest  {
    Contrat contrat = new Contrat();
    @Autowired
    ContratRepository contratRepository;
    Etudiant etudiant = new Etudiant();
    @Autowired
    EtudiantRepository etudiantRepository;
    @Test
    @Order(0)
    void addContratTest() {
        contrat = contratRepository.save(contrat);
        log.info(contrat.toString());
        Assertions.assertNotNull(contrat.getIdContrat());
    }

    @Test
    @Order(1)
    void getContartTest() {
        contrat.setIdContrat(2);
        Optional<Contrat> c = contratRepository.findById(contrat.getIdContrat());

        assertTrue(c.isPresent());
        Contrat getContrat = c.get();
        assertEquals(contrat.getIdContrat(), getContrat.getIdContrat());
        assertEquals(contrat.getMontantContrat(), getContrat.getMontantContrat());
        log.info(contrat.toString());
    }

    @Test
    @Order(2)
    void updateContratTest() {
        contrat.setSpecialite(Specialite.WEB);
        contrat = contratRepository.save(contrat);
        log.info(contrat.toString());
        Assertions.assertNotNull(contrat.getIdContrat());
    }

    @Test
    @Order(3)
    void deleteContratTest() {
        contrat.setIdContrat(2);
        contrat = contratRepository.save(contrat);
        contratRepository.delete(contrat);
        Optional<Contrat> deletedContrat = contratRepository.findById(contrat.getIdContrat());
        log.info(contrat.toString());
        assertFalse(deletedContrat.isPresent());
    }

    @Test
    @Order(4)
    void getnbContratsValidesTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Integer result = contratRepository.getnbContratsValides(dateFormat.parse("15-01-2022"), new Date());
        log.info(result.toString());
        assertEquals(0, result);
    }

    @Test
    @Order(5)
    void getByEtudiantIdEtudiantTest() {
        etudiant.setPrenomE("Aymen");
        etudiant.setNomE("Laroussi");
        etudiant.setOp(Option.SE);
        etudiant = etudiantRepository.save(etudiant);
        List<Contrat> contrats = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());
        log.info(contrats.toString());
        assertNotNull(contrats);
    }

}
