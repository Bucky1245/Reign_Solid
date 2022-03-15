/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.tp_solid.reigns;

import java.util.List;

/**
 *
 * @author julie.jacques
 */
public class Personnage {
    
    protected String nom;
    protected Genre genre;
    List<Jauge> jauges;
    
    public Personnage(String nom, Genre genre,List<Jauge> jauges){
        this.nom = nom;
        this.genre = genre;
        this.jauges = jauges;
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
