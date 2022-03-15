/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.tp_solid.reigns;


/**
 *
 * @author julie.jacques
 */
public class Personnage {
    
    protected String nom;
    protected Genre genre;
    
    public Personnage(String nom, Genre genre){
        this.nom = nom;
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void  setGenre(Genre genre) {
        this.genre = genre;
    }
}
