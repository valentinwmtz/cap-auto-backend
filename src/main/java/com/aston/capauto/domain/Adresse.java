package com.aston.capauto.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int codePostale;
    private String rue;
    private int numero;
    private String ville;
    private String pays;

    @OneToOne(mappedBy = "adresse")
    @JsonBackReference
    private Eleve eleve;


    public Adresse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Adresse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("codePostale=" + codePostale)
                .add("rue='" + rue + "'")
                .add("numero=" + numero)
                .add("ville='" + ville + "'")
                .add("pays='" + pays + "'")
                .add("eleve='" + eleve + "'")
                .toString();
    }
}
