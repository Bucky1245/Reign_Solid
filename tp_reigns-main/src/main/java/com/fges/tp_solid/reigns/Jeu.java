/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.tp_solid.reigns;

import static com.fges.tp_solid.reigns.Genre.REINE;
import static com.fges.tp_solid.reigns.Genre.ROI;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

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
    }

    private static Question getQuestionAleatoire() {
        int numQuestion = (int) (Math.random() * questions.size());
        return questions.get(numQuestion);
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
