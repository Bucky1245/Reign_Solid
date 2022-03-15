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
public class Jauge {
    
    protected TypeJauge type;
    protected String nom;
    protected int valeur;

    public Jauge(TypeJauge type, String nom){
        this.type = type;
        this.nom = nom;
        this.valeur = (int) Math.random() * (35-15) + 15;
    }

    public void afficheJauge(){
        String resultat = "[";
        // valeur : ####
        for(int i=0;i<this.valeur;i++){
            resultat += "#";
        }
        // on complète avec ____
        for(int i=0;i<50-(this.valeur>0?this.valeur:0);i++){
            resultat += "_";
        }
        resultat += "] ";
        // affichage du nom
        resultat += this.nom;
        System.out.println(resultat);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public TypeJauge getType() {
        return type;
    }

    public void setType(TypeJauge type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Jauge [nom=" + nom + ", type=" + type + ", valeur=" + valeur + "]";
    }
}
