package com.aston.capauto.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "eleve")
public class Eleve extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String prenom;

    @NotNull
    @Size(max = 100)
    private String nom;

    @NotNull
    private Date dateNaissence;

    @OneToOne
    @JoinColumn(name = "adresse_id")
    @JsonManagedReference
    private Adresse adresse;

    private String photo;

    public Eleve() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissence() {
        return dateNaissence;
    }

    public void setDateNaissence(Date dateNaissence) {
        this.dateNaissence = dateNaissence;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Eleve.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("prenom='" + prenom + "'")
                .add("nom='" + nom + "'")
                .add("dateNaissence=" + dateNaissence)
                .add("adresse=" + adresse)
                .toString();
    }
}
