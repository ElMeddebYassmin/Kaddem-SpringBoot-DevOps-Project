package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ContratServiceImpl implements IContratService {


    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        log.info("debut methode retrieveAllContrats");
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(Contrat c, Integer id) {
        Optional<Contrat> optionalContrat = contratRepository.findById(id);

        if (optionalContrat.isPresent()) {
            Contrat contrat = optionalContrat.get();
            contrat.setDateDebutContrat(c.getDateDebutContrat());
            contrat.setDateFinContrat(c.getDateFinContrat());
            contrat.setSpecialite(c.getSpecialite());
            contrat.setArchived(c.getArchived());
            contrat.setMontantContrat(c.getMontantContrat());
            contrat.setEtudiant(c.getEtudiant());
            return contratRepository.save(contrat);
        } else
            throw new NoSuchElementException("Contrat not found for id: " + id);
        }


    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        log.info("debut methode retrieveContrat");
        Optional<Contrat> optionalContrat = contratRepository.findById(idContrat);

        if (optionalContrat.isPresent()) {
            return optionalContrat.get();
        } else {
            throw new NoSuchElementException("Contrat not found for id: " + idContrat);
        }
    }

    @Override
    public void removeContrat(Integer idContrat) {
        log.info("debut methode removeContrat");
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat addContrat(Contrat c) {
        contratRepository.save(c);
        return c;
    }

    @Transactional
    public Contrat addAndAffectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Long startDate = new Date().getTime();
        log.info("startDate: " + startDate);
        log.info("debut methode addAndAffectContratToEtudiant");
        Etudiant etudiant = etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
        log.info("etudiant: " + etudiant.getNomE() + " " + etudiant.getPrenomE());
        // nb contrats actifs
        Integer nbContratsActifs = etudiant.getContrats().size();
        if (nbContratsActifs > 5) {
            log.info("nombre de contrats autorisés est atteint");
            Long endDate = new Date().getTime();
            Long executionTime = endDate - startDate;
            log.info("endDate: " + startDate);
            log.info("executionTime: " + executionTime + " ms");
            return ce;
        }
        log.info("nb Contrats en cours: " + nbContratsActifs);
        contratRepository.save(ce);
        ce.setEtudiant(etudiant);
        log.info("fin methode addAndAffectContratToEtudiant");
        Long endDate = new Date().getTime();
        Long executionTime = endDate - startDate;

        log.info("endDate: " + startDate);
        log.info("executionTime: " + executionTime + " ms");

        return ce;
    }

    public Integer nbContratsValides(Date startDate, Date endDate) {
        return contratRepository.getnbContratsValides(startDate, endDate);
    }

    @Override
    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
        return 0;
    }

    @Override
    public void retrieveAndUpdateStatusContrat() {

    }

//    public void retrieveAndUpdateStatusContrat() {
//        log.info("debut methode retrieveAndUpdateStatusContrat");
//        List<Contrat> contrats = contratRepository.findAll();
//        log.info("total contrats :" + contrats.size());
//
//        for (Contrat contrat : contrats) {
//            log.info("id: " + contrat.getIdContrat());
//            log.info("date fin" + contrat.getDateFinContrat());
//            log.info("archived " + contrat.getArchived());
//
//            Date dateSysteme = new Date();
//
//            if (contrat.getArchived() == null || !contrat.getArchived()) {
//                long difference_In_Time = contrat.getDateFinContrat().getTime() - dateSysteme.getTime();
//                long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
//                // il est préférable d'utiliser des méthodes prédéfinis de comparaison
//                log.info("difference in days : " + difference_In_Days);
//                //     if (difference_In_Days>=0 && difference_In_Days<=15){  // entre 0 et  15 jours exactement
//                if (difference_In_Days == 15) {  // pour 15 jours exactement
//                    log.info(" Contrat Commencant le : " + contrat.getDateDebutContrat() + "pour l'etudiant " + contrat.getEtudiant().getNomE() +
//                            " " + contrat.getEtudiant().getPrenomE() + "  va bientot s achever le "
//                            + contrat.getDateFinContrat());
//                }
//                if (difference_In_Days == 0) {
//                    log.info("jour j: " + contrat.getIdContrat());
//                    contrat.setArchived(true);
//                    contratRepository.save(contrat);
//                }
//            }
//
//            log.info("debut methode retrieveAndUpdateStatusContrat");
//        }
//    }
//
//    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
//        float difference_In_Time = endDate.getTime() - startDate.getTime();
//        float difference_In_Days = (float) (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
//        float difference_In_months = difference_In_Days / 30;
//        List<Contrat> contrats = contratRepository.findAll();
//        float chiffreAffaireEntreDeuxDates = 0;
//        float chiffreAffaireEntreDeuxDatesIA = 0;
//        float chiffreAffaireEntreDeuxDatesCloud = 0;
//        float chiffreAffaireEntreDeuxDatesReseau = 0;
//        float chiffreAffaireEntreDeuxDatesSecurite = 0;
//
//        for (Contrat contrat : contrats) {
//            if (contrat.getSpecialite() == Specialite.IA) {
//                chiffreAffaireEntreDeuxDates += (difference_In_months * contrat.getMontantContrat());
//                chiffreAffaireEntreDeuxDatesIA += (difference_In_months * contrat.getMontantContrat());
//            } else if (contrat.getSpecialite() == Specialite.CLOUD) {
//                chiffreAffaireEntreDeuxDates += (difference_In_months * contrat.getMontantContrat());
//                chiffreAffaireEntreDeuxDatesCloud += (difference_In_months * contrat.getMontantContrat());
//            } else if (contrat.getSpecialite() == Specialite.RESEAU) {
//                chiffreAffaireEntreDeuxDates += (difference_In_months * contrat.getMontantContrat());
//                chiffreAffaireEntreDeuxDatesReseau += (difference_In_months * contrat.getMontantContrat());
//            } else if (contrat.getSpecialite() == Specialite.SECURITE) {
//                chiffreAffaireEntreDeuxDates += (difference_In_months * contrat.getMontantContrat());
//                chiffreAffaireEntreDeuxDatesSecurite += (difference_In_months * contrat.getMontantContrat());
//            }
//        }
//
//        log.info("chiffreAffaireEntreDeuxDates: " + chiffreAffaireEntreDeuxDates);
//        log.info("chiffreAffaireEntreDeuxDatesIA:" + chiffreAffaireEntreDeuxDatesIA);
//        log.info("chiffreAffaireEntreDeuxDatesCloud " + chiffreAffaireEntreDeuxDatesCloud);
//        log.info("chiffreAffaireEntreDeuxDatesReseau " + chiffreAffaireEntreDeuxDatesReseau);
//        log.info("chiffreAffaireEntreDeuxDatesSecurite " + chiffreAffaireEntreDeuxDatesSecurite);
//
//        return chiffreAffaireEntreDeuxDates;
//    }


}