package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
@Tag(name = "Equipe", description = "Gestion des equipes")
//@CrossOrigin(origins = "http://192.168.33.10:4200")
public class EquipeRestController {
    @Autowired
    IEquipeService equipeService;

    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        return equipeService.retrieveAllEquipes();
    }



    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }


    @PostMapping("/add-equipe")
    @ResponseBody
    public EquipeDTO addEquipe(@RequestBody EquipeDTO equipeDTO) {
        // Convertissez EquipeDTO en Equipe si nécessaire
        Equipe equipe = new Equipe();
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipe.setNiveau(equipeDTO.getNiveau());
        // Appel de votre service avec Equipe
        Equipe equipeAdded = equipeService.addEquipe(equipe);

        // Convertissez Equipe en EquipeDTO si nécessaire
        EquipeDTO resultDTO = new EquipeDTO();
        resultDTO.setNomEquipe(equipeAdded.getNomEquipe());
        resultDTO.setNiveau(equipeAdded.getNiveau());

        return resultDTO;
    }


    @PutMapping("/update-equipe/{idEquipe}")
    @ResponseBody
    public EquipeDTO updateEquipe(@PathVariable("idEquipe") Integer idEquipe, @RequestBody EquipeDTO updatedEquipeDTO) {

        Equipe existingEquipe = equipeService.retrieveEquipe(idEquipe);
        existingEquipe.setNomEquipe(updatedEquipeDTO.getNomEquipe());
        existingEquipe.setNiveau(updatedEquipeDTO.getNiveau());


        Equipe updatedEquipeEntity = equipeService.updateEquipe(existingEquipe, idEquipe);

        EquipeDTO resultDTO = new EquipeDTO();
        resultDTO.setNomEquipe(updatedEquipeEntity.getNomEquipe());
        resultDTO.setNiveau(updatedEquipeEntity.getNiveau());

        return resultDTO;
    }







    @DeleteMapping("/remove-equipe/{idEquipe}")
    @ResponseBody
    public void removeEquipe(@PathVariable("idEquipe") Integer idEquipe) {
        equipeService.removeEquipe(idEquipe);
    }

    @GetMapping("/getAllEquipesTestRules")
    @ResponseBody
    private List<Equipe> getAllEquipesTestRules() {
        return equipeService.retrieveAllEquipes();
    }
    @GetMapping("/retrieve-equipe-by-nomEquipe/{nomEquipe}")
    @ResponseBody
    public Equipe getEquipeByNomEquipe( @PathVariable("nomEquipe") String nomEquipe) {
        return equipeService.getEquipeByNomEquipe(nomEquipe);
    }

}
