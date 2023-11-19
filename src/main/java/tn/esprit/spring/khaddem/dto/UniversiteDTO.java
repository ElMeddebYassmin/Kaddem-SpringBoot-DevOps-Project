package tn.esprit.spring.khaddem.dto;

import tn.esprit.spring.khaddem.entities.Statut;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;




public class UniversiteDTO {
    private String nomUniv;
    private String adresse;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public String getNomUniv() {
        return nomUniv;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
