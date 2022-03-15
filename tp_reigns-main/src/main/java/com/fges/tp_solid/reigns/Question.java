/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.tp_solid.reigns;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author julie.jacques
 */
public class Question {
    
    // nom du perso qui pose la question
    protected String nomPersonnage;
    protected String question;
    protected String effetGauche;
    protected String effetDroite;
    protected Effets effetJaugeGauche;
    protected Effets effetJaugeDroite;
    
    public Question(String nomPersonnage, 
                    String question,
                    String effetGauche,
                    String effetDroite){
        this.nomPersonnage = nomPersonnage;
        this.question = question;
        this.effetGauche = effetGauche;
        this.effetDroite = effetDroite;
        this.effetJaugeGauche = new Effets();
        this.effetJaugeDroite = new Effets();
    }
    
    public void afficheQuestion(){
        String result = "["+nomPersonnage+"] "
                + question
                + "[G: "+effetGauche
                + ",D: "+effetDroite
                + "]";
        System.out.println(result);
        System.out.println("Effet G:"+this.effetJaugeGauche.afficheEffets());
        System.out.println("Effet D:"+this.effetJaugeDroite.afficheEffets());
        System.out.flush();
        
    }

    public String getNomPersonnage() {
        return nomPersonnage;
    }

    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Effets getEffetJaugeGauche() {
        return effetJaugeGauche;
    }

    public void setEffetJaugeGauche(Effets effetJaugeGauche) {
        this.effetJaugeGauche = effetJaugeGauche;
    }

    public Effets getEffetJaugeDroite() {
        return effetJaugeDroite;
    }

    public void setEffetJaugeDroite(Effets effetJaugeDroite) {
        this.effetJaugeDroite = effetJaugeDroite;
    }
    
    
}
