package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant")
@Tag(name = "Etudiant", description = "Gestion des etudiants")
//@CrossOrigin(origins = "http://192.168.33.10:4200")
public class EtudiantRestController {

    @Autowired
    IEtudiantService etudiantService;

//    @Timed(value = "retrieve-all.time", description = "Time taken to return the list of students")
//    @GetMapping("/retrieve-all-etudiants")
//    @ResponseBody
//    public List<Etudiant> getEtudiants() {
//        return etudiantService.retrieveAllEtudiants();
//    }

//    @Timed(value = "retrieve-one.time", description = "Time taken to return one student")
//    @GetMapping("/retrieve-etudiant/{etudiantId}")
//    @ResponseBody
//    public Etudiant retrieveContrat(@PathVariable("etudiantId") Integer etudiantId) {
//        Optional<Etudiant> etudiantOptional = etudiantService.retrieveEtudiant(etudiantId);
//
//        if (etudiantOptional.isPresent()) {
//            return etudiantOptional.get();
//        } else {
//            throw new EntityNotFoundException("Etudiant not found");
//        }
//    }

//    @PostMapping("/add-etudiant")
//    @ResponseBody
//    public Etudiant addEtudiant(@RequestBody Etudiant e) {
//        return etudiantService.addEtudiant(e);
//    }

//    @PutMapping("/update-etudiant/{idEtudiant}")
//    @ResponseBody
//    public Optional<Etudiant> updateEtudiant(@PathVariable("idEtudiant") Integer idEtudiant, @RequestBody Etudiant updatedEtudiant) {
//        Optional<Etudiant> existingEtudiantOptional = etudiantService.retrieveEtudiant(idEtudiant);
//
//        if (existingEtudiantOptional.isPresent()) {
//            var existingEtudiant = existingEtudiantOptional.get();
//            existingEtudiant.setPrenomE(updatedEtudiant.getPrenomE());
//            existingEtudiant.setNomE(updatedEtudiant.getNomE());
//            existingEtudiant.setOp(updatedEtudiant.getOp());
//            return etudiantService.updateEtudiant(existingEtudiant, idEtudiant);
//        } else {
//            throw new EntityNotFoundException("Etudiant not found");
//        }
//    }
//

    @DeleteMapping("/remove-etudiant/{idEtudiant}")
    @ResponseBody
    public void removeEtudiant(@PathVariable("idEtudiant") Integer idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
    }

    @PutMapping("/assignEtudiantToDepartement/{etudiantId}/{departementId}")
    @ResponseBody
    public void assignEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId
            ,@PathVariable("departementId") Integer departementId) {
        etudiantService.assignEtudiantToDepartement(etudiantId,departementId);
    }

    @GetMapping("/findByDepartement/{departement-id}")
    @ResponseBody
    public List<Etudiant> findByDepartement(@PathVariable("departement-id") Integer departementId) {
        return etudiantService.findByDepartementIdDepartement(departementId);
    }

    @GetMapping("/findByEquipesNiveau/{niveau}")
    @ResponseBody
    public List<Etudiant> findByEquipesNiveau(@PathVariable("niveau") Niveau niveau) {
        return etudiantService.findByEquipesNiveau(niveau);
    }

    @GetMapping("/retrieveEtudiantsByContratSpecialite/{specialite}")
    @ResponseBody
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(@PathVariable("specialite") Specialite specialite) {
        return etudiantService.retrieveEtudiantsByContratSpecialite(specialite);
    }

    @GetMapping("/retrieveEtudiantsByContratSpecialiteSQL/{specialite}")
    @ResponseBody
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(@PathVariable("specialite") String specialite) {
        return etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

//    @PostMapping("/addAndAssignEtudiantToEquipeAndContract/{equipeId}/{contratId}")
//    @ResponseBody
//    public void addAndAssignEtudiantToEquipeAndContract(@RequestBody Etudiant etudiant,@PathVariable("contratId") Integer contratId,@PathVariable("equipeId") Integer equipeId) {
//        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant,contratId,equipeId);
//    }

    @GetMapping("/getEtudiantsByDepartement/{idDepartement}")
    @ResponseBody
    public List<Etudiant> getEtudiantsByDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        return etudiantService.getEtudiantsByDepartement(idDepartement);
    }
}
