package tn.esprit.spring.khaddem.repositories;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.khaddem.entities.Niveau;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class EquipeRepositoryTest {

    private static final String nomEquipe = "equipeDEV2";
    private static final Niveau niveau = Niveau.EXPERT;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private static final Logger logger = LoggerFactory.getLogger(EquipeRepositoryTest.class);


    /*@Test
    void whenFindByNomEquipe_thenReturnEquipe(){
        logger.info("Début du test whenFindByNomEquipe_thenReturnEquipe");

        final Equipe equipe = new Equipe();
        equipe.setNomEquipe(nomEquipe);
        equipe.setNiveau(niveau);
        testEntityManager.persist(equipe);
        testEntityManager.flush();

        final Equipe equipeBDD = equipeRepository.findEquipeByNomEquipe(nomEquipe);
        logger.info("Équipe récupérée depuis la base de données : " + equipeBDD.getNomEquipe() + ", " + equipeBDD.getNiveau() + "," );

        assertEquals(nomEquipe,equipeBDD.getNomEquipe());
        logger.info("Fin du test whenFindByNomEquipe_thenReturnEquipe");

    }
*/

}
