package com.fges.tp_solid.reigns;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Effets {
    private Map<TypeJauge, Integer> effets;

    public Effets() {
        this.effets = new TreeMap<>();
    }

    /**
     * exemple : jauge armée : -5 ; jauge peuple : +5
     * @return 
     */
    public String afficheEffets(){
        String result = "";
        for(Entry<TypeJauge,Integer> effet : effets.entrySet()){
            result += "; jauge "+effet.getKey().toString()+" : ";
            if(effet.getValue()>0)
                result += "+";
            result += effet.getValue();
        }
        return result.substring(1);
    }
    
    public void appliqueEffets(Personnage personnage){
        this.appliqueEffets(effets, personnage);
    }
    
    public void appliqueEffets(Map<TypeJauge,Integer> effets, 
                                Personnage personnage){
        for(Entry<TypeJauge,Integer> effet : effets.entrySet()){
            switch(effet.getKey()){
                    case ARMEE:
                        personnage.getJauges().get(TypeJauge.ARMEE).setValeur(
                                personnage.getJauges().get(TypeJauge.ARMEE).getValeur()
                                        +effet.getValue());
                        break;
                    case CLERGE:
                        personnage.getJauges().get(TypeJauge.CLERGE).setValeur(
                                personnage.getJauges().get(TypeJauge.CLERGE).getValeur()
                                        +effet.getValue());
                        break;
                    case FINANCE:
                        personnage.getJauges().get(TypeJauge.FINANCE).setValeur(
                                personnage.getJauges().get(TypeJauge.FINANCE).getValeur()
                                        +effet.getValue());
                        break;
                    case PEUPLE:
                        personnage.getJauges().get(TypeJauge.PEUPLE).setValeur(
                                personnage.getJauges().get(TypeJauge.PEUPLE).getValeur()
                                        +effet.getValue());
                        break;
            }
        }
    }
    
    public void ajouteEffet(TypeJauge jauge,
                                   int valeur){
        effets.put(jauge,valeur);
    }
    
    public Map<TypeJauge, Integer> getEffets() {
        return effets;
    }

    public void setEffets(Map<TypeJauge, Integer> effets) {
        this.effets = effets;
    }
}
