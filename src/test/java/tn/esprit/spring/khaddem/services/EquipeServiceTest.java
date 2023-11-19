package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Niveau;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class EquipeServiceTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private ContratRepository contratRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {

        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Equipe 1", Niveau.EXPERT));
        equipes.add(new Equipe("Equipe 2", Niveau.EXPERT));


        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);


        List<Equipe> result = equipeService.retrieveAllEquipes();


        assertEquipesListEquals(equipes, result);
    }

    @Test
    void testAddEquipe() {

        Equipe equipe = new Equipe( "Nouvelle Equipe", Niveau.JUNIOR);


        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);


        Equipe result = equipeService.addEquipe(equipe);


        assertEquipeEquals(equipe, result);
    }
    private void assertEquipeEquals(Equipe expected, Equipe actual) {

        Assertions.assertEquals(expected.getIdEquipe(), actual.getIdEquipe());
        Assertions.assertEquals(expected.getNomEquipe(), actual.getNomEquipe());
        Assertions.assertEquals(expected.getNiveau(), actual.getNiveau());
    }
    private void assertEquipesListEquals(List<Equipe> expected, List<Equipe> actual) {

        Assertions.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquipeEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    void testUpdateEquipe() {

        Equipe equipe = new Equipe(1,"Equipe 1", Niveau.EXPERT);


        Mockito.when(equipeRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(equipe));


        String updatedNomEquipe = "Equipe 1 Updated";
        Niveau updatedNiveau = Niveau.EXPERT;
        Equipe updatedEquipe=new Equipe(1,updatedNomEquipe,updatedNiveau);


        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        Mockito.when(equipeService.updateEquipe(updatedEquipe, 1)).thenReturn(updatedEquipe);
        Equipe result = equipeService.updateEquipe(updatedEquipe, 1);

        assertEquipeEquals(updatedEquipe, result);
    }


    @Test
    void testRetrieveEquipe() {

        Equipe equipe = new Equipe( "Equipe 1", Niveau.EXPERT);


        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));


        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);

        assertEquipeEquals(retrievedEquipe, equipe);
    }

    @Test
    void testRemoveEquipe() {

        equipeService.removeEquipe(1);


        Mockito.verify(equipeRepository, Mockito.times(1)).deleteById(1);
    }
}
