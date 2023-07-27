package com.demos.librairiecine7arche.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livre")
@PrimaryKeyJoinColumn(name="id")
public class Livre extends Article{
    private String titre;
    private String numeroIsbn;
    private int nbPage;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    @ManyToMany
    private List<Editeur> editeurs;

    @ManyToMany
    private List<Genre> genres;

    // constructeur,

    public Livre() {
    }

    public Livre(String numeroIsbn, int nbPage, Auteur auteur, List<Editeur> editeurs, List<Genre> genres,String titre) {
        this.numeroIsbn = numeroIsbn;
        this.nbPage = nbPage;
        this.auteur = auteur;
        this.editeurs = editeurs;
        this.genres = genres;
        this.titre = titre;
    }




// getters et setters


    public String getNumeroIsbn() {
        return numeroIsbn;
    }

    public void setNumeroIsbn(String numeroIsbn) {
        this.numeroIsbn = numeroIsbn;
    }

    public int getNbPage() {
        return nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public List<Editeur> getEditeurs() {
        return editeurs;
    }

    public void setEditeurs(List<Editeur> editeurs) {
        this.editeurs = editeurs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


}
