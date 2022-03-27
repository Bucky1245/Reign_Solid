/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.tp_solid.reigns;

import static com.fges.tp_solid.reigns.Genre.REINE;
import static com.fges.tp_solid.reigns.Genre.ROI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author julie.jacques
 */
public class Jeu {

    private static Personnage personnage;
    private static ArrayList<Question> questions;

    public static void main(String args[]) {

        // début du jeu
        System.out.println("Bienvenue sur Reigns");

        initBanqueQuestions();

        System.out.println("Création du personnage...");

        initPersonnage();

        System.out.println(personnage.getGenre().longRegne()
                + " " + personnage.getNom());

        personnage.getJauges().forEach((key, element) -> element.afficheJauge());

        // tirage des questions
        int nbTours = 0;
        while(!finDuJeu()) {
            nbTours++;
            Question question = getQuestionAleatoire();
            reponseQuestion(question);
            personnage.getJauges().forEach((key, element) -> element.afficheJauge());
        }

        // fin du jeu
        System.out.println(
                personnage.getNom()
                        + " a perdu ! Son règne a duré "
                        + nbTours
                        + " tours");

    }

    private static void reponseQuestion(Question question) {
        question.afficheQuestion();
        // récupère la réponse
        Scanner scanner = new Scanner(System.in);
        String reponse = "";
        while (!reponse.equals("G") && !reponse.equals("D")) {
            System.out.println("Entrez la réponse (G ou D)");
            System.out.flush();
            reponse = scanner.nextLine();
        }
        // applique les malus
        if (reponse.equals("G")) {
            question.getEffetJaugeGauche().appliqueEffets(personnage);
        } else {
            question.getEffetJaugeDroite().appliqueEffets(personnage);
        }
    }

    private static void initPersonnage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du personnage: ");
        System.out.flush();
        String nom = scanner.nextLine();
        System.out.println(
                "Faut-il vous appeler Roi ou Reine ? (1 pour Roi, 2 pour Reine)");
        int genre = scanner.nextInt();
        Genre roiReine;
        if (genre == 1) {
            roiReine = ROI;
        } else {
            roiReine = REINE;
        }

        Map<TypeJauge, Jauge> jauges = new TreeMap<>();
        jauges.put(TypeJauge.CLERGE, new Jauge(TypeJauge.CLERGE, TypeJauge.CLERGE.toString()));
        jauges.put(TypeJauge.FINANCE, new Jauge(TypeJauge.FINANCE, TypeJauge.FINANCE.toString()));
        jauges.put(TypeJauge.PEUPLE, new Jauge(TypeJauge.PEUPLE, TypeJauge.PEUPLE.toString()));
        jauges.put(TypeJauge.ARMEE, new Jauge(TypeJauge.ARMEE, TypeJauge.ARMEE.toString()));

        Jeu.personnage = new Personnage(nom, roiReine, jauges);
    }

    private static void initBanqueQuestions() {
        questions = new ArrayList<>();
        Question question1 = new Question(
                "Main du roi",
                "Le peuple souhaite libérer les prisonniers",
                "Oui",
                "Non");
        question1.getEffetJaugeGauche().ajouteEffet(TypeJauge.ARMEE, -5);
        question1.getEffetJaugeGauche().ajouteEffet(TypeJauge.PEUPLE, +5);
        question1.getEffetJaugeDroite().ajouteEffet(TypeJauge.PEUPLE, -7);
        questions.add(question1);
        Question question2 = new Question(
                "Paysan",
                "Il n'y a plus rien à manger",
                "Importer de la nourriture",
                "Ne rien faire");
        question2.getEffetJaugeGauche().ajouteEffet(TypeJauge.FINANCE, -5);
        question2.getEffetJaugeGauche().ajouteEffet(TypeJauge.PEUPLE, +5);
        question2.getEffetJaugeDroite().ajouteEffet(TypeJauge.PEUPLE, -5);
        questions.add(question2);
        Question question3 = new Question(
                "Prêtre",
                "Les dieux sont en colère",
                "Faire un sacrifice",
                "Ne rien faire");
        question3.getEffetJaugeGauche().ajouteEffet(TypeJauge.CLERGE, +5);
        question3.getEffetJaugeGauche().ajouteEffet(TypeJauge.PEUPLE, -3);
        question3.getEffetJaugeDroite().ajouteEffet(TypeJauge.CLERGE, -5);
        questions.add(question3);
        Question question4 = new Question(
                "Main du roi",
                "Le roi Baratheon rassemble son armée",
                "Le soutenir",
                "Rester neutre");
        question4.getEffetJaugeGauche().ajouteEffet(TypeJauge.ARMEE, +3);
        question4.getEffetJaugeGauche().ajouteEffet(TypeJauge.FINANCE, -3);
        question4.getEffetJaugeGauche().ajouteEffet(TypeJauge.CLERGE, -3);
        question4.getEffetJaugeDroite().ajouteEffet(TypeJauge.PEUPLE, +3);
        questions.add(question4);
        Question question5 = new Question(
                "Paysan",
                "Abondance de récoltes cette année",
                "Taxer énormément",
                "Taxer un tout petit peu");
        question5.getEffetJaugeGauche().ajouteEffet(TypeJauge.FINANCE, +10);
        question5.getEffetJaugeGauche().ajouteEffet(TypeJauge.PEUPLE, -5);
        question5.getEffetJaugeDroite().ajouteEffet(TypeJauge.FINANCE, +1);
        question5.getEffetJaugeDroite().ajouteEffet(TypeJauge.PEUPLE, -3);
        questions.add(question5);
        QuestionWithConditions question6 = new QuestionWithConditions("Main du roi", "Les caisses sont vides...", "Augmenter les taxes", "Emprunter");
        question6.getEffetJaugeGauche().ajouteEffet(TypeJauge.FINANCE, +10);
        question6.getEffetJaugeGauche().ajouteEffet(TypeJauge.PEUPLE, -5);
        question6.getEffetJaugeDroite().ajouteEffet(TypeJauge.FINANCE, +7);
        question6.getEffetJaugeDroite().ajouteEffet(TypeJauge.PEUPLE, -3);
        question6.getConditions().add(new Condition(TypeJauge.FINANCE, ">", 10));
        questions.add(question6);
        QuestionWithConditions question7 = new QuestionWithConditions("Prêtre", "J'aimerai qu'on nous considère en tant que tel", "Construire un monastère", "Ecouter sans rien faire");
        question7.getEffetJaugeGauche().ajouteEffet(TypeJauge.CLERGE, +5);
        question7.getEffetJaugeGauche().ajouteEffet(TypeJauge.FINANCE, -5);
        question7.getEffetJaugeDroite().ajouteEffet(TypeJauge.CLERGE, -5);
        question7.getConditions().add(new Condition(TypeJauge.CLERGE, "<", 10));
        question7.getConditions().add(new Condition(TypeJauge.FINANCE, ">", 30));
        questions.add(question7);
    }

    private static Question getQuestionAleatoire() {
        List<Question> questionsAleatoires = new ArrayList<>();
        int numQuestion = (int) (Math.random() * questions.size());
        for(Question question : questions)
        {
            if(question instanceof QuestionWithConditions)
            {
                Map<TypeJauge, Jauge> jauges = personnage.getJauges();
                boolean isOk = false;
                for(Condition condition : ((QuestionWithConditions) question).getConditions())
                {
                    if(">".equals(condition.getSigne()))
                    {
                        if(condition.getScore() < jauges.get(condition.getJauge()).getValeur())
                        {
                            isOk = true;
                        }
                        else
                        {
                            isOk = false;
                        }
                    }
                    else
                    {
                        if(condition.getScore() > jauges.get(condition.getJauge()).getValeur())
                        {
                            isOk = true;
                        }
                        else
                        {
                            isOk = false;
                        }
                    }
                }
                if(isOk)
                {
                    questionsAleatoires.add(question);
                }
            }
            else
                questionsAleatoires.add(question);
        }
        return questionsAleatoires.get(numQuestion);
    }

    public static boolean finDuJeu(){
        Boolean bool = false;
        for(Entry<TypeJauge, Jauge> jauge : personnage.getJauges().entrySet()) {
            if(jauge.getValue().getValeur() <= 50 || jauge.getValue().getValeur() >= 0){
                bool = true;
            }
        }
        return bool;
    }
}
