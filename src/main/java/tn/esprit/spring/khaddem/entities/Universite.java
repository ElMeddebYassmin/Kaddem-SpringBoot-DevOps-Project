package tn.esprit.spring.khaddem.entities;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
@Entity
@NoArgsConstructor
public class Universite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniversite;
    private String nomUniv;
    private String adresse;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Integer getIdUniversite() {
        return idUniversite;
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

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

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

    public List<Departement> getDepartements() {
        return departements;
    }

    @OneToMany(cascade = CascadeType.ALL)
    //  @JsonIgnore
    private List<Departement>departements;


    public Universite(Integer idUniversite, String nomUniv, String adresse, Date dateCreation, Statut statut) {
        this.idUniversite = idUniversite;
        this.nomUniv = nomUniv;
        this.adresse = adresse;
        this.dateCreation = dateCreation;
        this.statut = statut;
    }

}
