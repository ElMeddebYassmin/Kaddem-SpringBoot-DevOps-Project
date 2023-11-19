package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.DepartementDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.services.IDepartementService;

import java.util.List;

@RestController
@RequestMapping("/departement")
@Tag(name = "Departement", description = "Gestion des départements")
@CrossOrigin(origins = "${allowed.origin}")
public class DepartementRestController {

    @Autowired
    IDepartementService departementService;


    @GetMapping("/retrieve-all-departements")
    @ResponseBody
    public List<Departement> getDepartements() {
        return departementService.retrieveAllDepartements();

    }


    @GetMapping("/retrieve-departement/{departement-id}")
    @ResponseBody
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }


    @PostMapping("/add-departement")
    @ResponseBody
    public Departement addDepartement(@RequestBody DepartementDTO d) {
        Departement newDepartement = new Departement();
        newDepartement.setNomDepart(d.getNomDepart());
        return departementService.addDepartement(newDepartement);
    }

    @PutMapping("/update-departement/{id}")
    @ResponseBody
    public Departement updateDepartement(@PathVariable("idDepartement") Integer id, @RequestBody DepartementDTO updatedDepartement) {
        Departement existingDepartement = departementService.retrieveDepartement(id);
        existingDepartement.setNomDepart(updatedDepartement.getNomDepart());
        return departementService.updateDepartement(existingDepartement, id);
    }


    @DeleteMapping("/delete-departement/{idDepartement}")
    @ResponseBody
    public void removeDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        departementService.removeDepartement(idDepartement);
    }


    @GetMapping("/retrieveDepartementsByDepartement/{idDepartement}")
    @ResponseBody
    public List<Departement> retrieveDepartementsByDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        return departementService.retrieveDepartementsByUniversite(idDepartement);

    }


}
