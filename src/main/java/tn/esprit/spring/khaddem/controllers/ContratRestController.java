package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.khaddem.services.IContratService;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
@Tag(name = "Contrat", description = "Gestion des contrats")
//@CrossOrigin(origins = "http://localhost:4200")
public class ContratRestController {
    IContratService contratService;

    // http://localhost:8089/Kaddem/contrat/retrieve-all-contrats
//    @Timed(value = "contract.getAll", description = "Time taken to retrieve list of contracts")
//    @GetMapping("/retrieve-all-contrats")
//    @ResponseBody
//    public List<Contrat> getContrats() {
//        return contratService.retrieveAllContrats();
//    }

    // http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
//    @Timed(value = "contract.get", description = "Time taken to retrieve details of contract By{id}")
//    @GetMapping("/retrieve-contrat/{contrat-id}")
//    @ResponseBody
//    public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
//        return contratService.retrieveContrat(contratId);
//    }

    // http://localhost:8089/Kaddem/contrat/add-contrat
//    @Timed(value = "contract.add", description = "Time taken to add new contract")
//    @PostMapping("/add-contrat")
//    @ResponseBody
//    public Contrat addContrat(@RequestBody Contrat c) {
//        return contratService.addContrat(c);
//    }

//    @Timed(value = "contract.update", description = "Time taken to update contract By{id}")
//    @PutMapping("/update-contrat/{idContrat}")
//    @ResponseBody
//    public Contrat updateContrat(@PathVariable("idContrat") Integer idContrat, @RequestBody Contrat updatedContrat) {
//        Contrat existingContrat = contratService.retrieveContrat(idContrat);
//        existingContrat.setDateDebutContrat(updatedContrat.getDateDebutContrat());
//        existingContrat.setDateFinContrat(updatedContrat.getDateFinContrat());
//        existingContrat.setSpecialite(updatedContrat.getSpecialite());
//        existingContrat.setArchived(updatedContrat.getArchived());
//        existingContrat.setMontantContrat(updatedContrat.getMontantContrat());
//
//        return contratService.updateContrat(existingContrat, idContrat);
//    }

//    @Timed(value = "contract.delete", description = "Time taken to delete contract By{id}")
//    @DeleteMapping("/remove-contrat/{idContrat}")
//    @ResponseBody
//    public void removeContrat(@PathVariable("idContrat") Integer idContrat) {
//        contratService.removeContrat(idContrat);
//    }

//    @Timed(value = "contract.affect", description = "Time taken to add & affect contract")
//    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
//    @ResponseBody
//    public Contrat addAndAffectContratToEtudiant(@RequestBody Contrat contrat,@PathVariable("nomE") String nomE,@PathVariable("prenomE") String prenomE) {
//        return contratService.addAndAffectContratToEtudiant(contrat,nomE,prenomE);
//    }
//
//    @Timed(value = "contract.validate", description = "Time taken to retrieve valid contracts between two dates")
//    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
//    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
//                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
//
//        return contratService.nbContratsValides(startDate, endDate);
//    }

    @Scheduled(cron = "0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    @PutMapping(value = "/majStatusContrat")
    public void majStatusContrat() {
        contratService.retrieveAndUpdateStatusContrat();
    }

//    @Timed(value = "contract.calculate", description = "Time taken to calculate revenue between two dates")
//    @GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
//    @ResponseBody
//    public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
//                                                    @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
//        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
//    }

}
