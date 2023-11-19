package tn.esprit.spring.khaddem.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.services.IUniversiteService;
import java.util.List;

@Tag(name = "Gestion des universités")
@RestController
@RequestMapping("/universite")
@Tag(name = "Universite", description = "Gestion des universités")
//@CrossOrigin(origins = "http://localhost:4200")
public class UniversiteRestController {
    @Autowired
    IUniversiteService universiteService;

    // http://localhost:8089/Kaddem/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    @Operation(description = "récupérer la liste des universités")
    @ResponseBody
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();

    }

    // http://localhost:8089/Kaddem/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universiteId}")
    @Operation(description = "récupérer une université par son id")
    @ResponseBody
    public Universite retrieveUniversite(@PathVariable("universiteId") Integer universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    // http://localhost:8089/Kaddem/universite/add-universite
    @PostMapping("/add-universite")
    @ResponseBody
    public Universite addUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite u = new Universite();
        u.setNomUniv(universiteDTO.getNomUniv());
        u.setAdresse(universiteDTO.getAdresse());
        u.setDateCreation(universiteDTO.getDateCreation());
        u.setStatut(universiteDTO.getStatut());
        return universiteService.addUniversite(u);

    }


    @PutMapping("/update-universite/{idUniversite}")
    @ResponseBody
    public Universite updateUniversite(@PathVariable("idUniversite") Integer idUniversite, @RequestBody UniversiteDTO updatedUniversiteDTO) {
        Universite existingUniversite = universiteService.retrieveUniversite(idUniversite);
        existingUniversite.setNomUniv(updatedUniversiteDTO.getNomUniv());
        existingUniversite.setAdresse(updatedUniversiteDTO.getAdresse());
        existingUniversite.setStatut(updatedUniversiteDTO.getStatut());
        existingUniversite.setDateCreation(updatedUniversiteDTO.getDateCreation());

        return universiteService.updateUniversite(existingUniversite, idUniversite);

    }



    @DeleteMapping("/remove-universite/{idUniversite}")
    @Operation(description = "supprimer une université par ID")
    @ResponseBody
    public void removeUniversite(@PathVariable("idUniversite") Integer idUniversite) {
        universiteService.removeUniversite(idUniversite);
    }

    // http://localhost:8089/Kaddem/universite/assignUniversiteToDepartement/1/1
    @PutMapping("/assignUniversiteToDepartement/{universiteId}/{departementId}")
    @Operation(description = "assigner une université à un département")
    @ResponseBody
    public void assignUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId,@PathVariable("departementId") Integer departementId) {
        universiteService.assignUniversiteToDepartement(universiteId,departementId);
    }
}
