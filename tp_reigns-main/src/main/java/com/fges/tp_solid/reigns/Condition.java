package com.fges.tp_solid.reigns;

public class Condition {
    TypeJauge jauge;
    String signe;
    int score;

    public Condition(TypeJauge jauge, String signe, int score) {
        this.jauge = jauge;
        this.signe = signe;
        this.score = score;
    }

    public TypeJauge getJauge() {
        return jauge;
    }

    public void setJauge(TypeJauge jauge) {
        this.jauge = jauge;
    }

    public String getSigne() {
        return signe;
    }

    public void setSigne(String signe) {
        this.signe = signe;
    }

    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
