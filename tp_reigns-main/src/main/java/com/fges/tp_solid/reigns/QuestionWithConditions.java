package com.fges.tp_solid.reigns;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithConditions extends Question{
    List<Condition> conditions;

    public QuestionWithConditions(String nomPersonnage, String question, String effetGauche, String effetDroite) {
        super(nomPersonnage, question, effetGauche, effetDroite);
        this.conditions = new ArrayList<>();
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}