package com.aston.capauto.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Eleve.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("prenom='" + prenom + "'")
                .add("nom='" + nom + "'")
                .add("dateNaissence=" + dateNaissence)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eleve eleve = (Eleve) o;
        return Objects.equals(id, eleve.id) &&
                Objects.equals(prenom, eleve.prenom) &&
                Objects.equals(nom, eleve.nom) &&
                Objects.equals(dateNaissence, eleve.dateNaissence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prenom, nom, dateNaissence);
    }
}
