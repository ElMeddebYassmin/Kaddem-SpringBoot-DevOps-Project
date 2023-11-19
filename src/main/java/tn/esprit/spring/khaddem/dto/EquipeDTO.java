package tn.esprit.spring.khaddem.dto;

import tn.esprit.spring.khaddem.entities.Niveau;

public class EquipeDTO {
    private String nomEquipe;
    private Niveau niveau;

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
