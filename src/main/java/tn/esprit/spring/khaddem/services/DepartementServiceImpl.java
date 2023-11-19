package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements IDepartementService {
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        departementRepository.save(d);
        return d;
    }

    @Override
    public Departement updateDepartement(Departement d, Integer id) {
        Optional<Departement> optionalDepartement = departementRepository.findById(id);

        if (optionalDepartement.isPresent()) {
            Departement dep = optionalDepartement.get();

            dep.setNomDepart(d.getNomDepart());
            Departement finalD = departementRepository.save(dep);
            return finalD;
        } else {
            throw new NoSuchElementException("Departement not found for id: " + id);
        }
    }


    @Override
    public void removeDepartement(Integer idDepart) {
        departementRepository.deleteById(idDepart);
    }

    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

        if (optionalDepartement.isPresent()) {
            return optionalDepartement.get();
        } else {
            throw new NoSuchElementException("Departement not found for id: " + idDepart);
        }
    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();
            return universite.getDepartements();
        } else {
            throw new NoSuchElementException("Universite not found for id: " + idUniversite);
        }
    }
}
