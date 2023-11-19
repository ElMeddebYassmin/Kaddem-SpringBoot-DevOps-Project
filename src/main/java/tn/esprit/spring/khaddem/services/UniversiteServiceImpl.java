package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UniversiteServiceImpl implements IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    DepartementRepository departementRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        log.debug("u :" + u.getNomUniv());
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite updateUniversite(Universite u, Integer id) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(id);

        Universite uni = optionalUniversite.orElseThrow(() -> new NoSuchElementException("Universite not found for id: " + id));

        uni.setNomUniv(u.getNomUniv());
        Universite finalU = universiteRepository.save(uni);
        return finalU;
    }

    @Override
    public void removeUniversite(Integer idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }

    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        return optionalUniversite.orElseThrow(() -> new NoSuchElementException("Universite not found for id: " + idUniversite));
    }

    @Transactional
    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(universiteId);
        Optional<Departement> optionalDepartement = departementRepository.findById(departementId);

        Universite universite = optionalUniversite.orElseThrow(() -> new NoSuchElementException("Universite not found for id: " + universiteId));
        Departement departement = optionalDepartement.orElseThrow(() -> new NoSuchElementException("Departement not found for id: " + departementId));

        universite.getDepartements().add(departement);
        log.info("Departments number: " + universite.getDepartements().size());
    }
}
