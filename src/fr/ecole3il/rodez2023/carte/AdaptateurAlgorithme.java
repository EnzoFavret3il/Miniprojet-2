package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);

        Tuile tuileDepart = carte.getTuile(xDepart, yDepart);
        Case depart = new Case(tuileDepart, xDepart, yDepart);

        Tuile tuileArrivee = carte.getTuile(xArrivee, yArrivee);
        Case arrivee = new Case(tuileArrivee, xArrivee, yArrivee);

        List<Noeud<Case>> chemin = algorithme.trouverChemin(graphe, new Noeud<>(depart), new Noeud<>(arrivee));

        return afficherChemin(chemin);
    }

    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Tuile tuileCourante = carte.getTuile(x, y);
                Case caseCourante = new Case(tuileCourante, x, y);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));
                ajouterAretesVoisines(graphe, caseCourante, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Ignorer le cas où i et j sont tous les deux égaux à zéro (pas de déplacement)
                if (i == 0 && j == 0) {
                    continue;
                }

                int newX = x + i;
                int newY = y + j;

                // Vérifier si les coordonnées sont valides
                if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                    Case voisin = new Case(currentCase.getTuile(), newX, newY);
                    graphe.ajouterArete(new Noeud<>(currentCase), new Noeud<>(voisin), calculerCout(currentCase, voisin));
                }
            }
        }
    }


    static double calculerCout(Case from, Case to) {
        if (from == null || to == null)
            return Double.POSITIVE_INFINITY;

        return 1.0;
    }

    static Chemin afficherChemin(List<Noeud<Case>> chemin) {
        try {
            if (chemin.isEmpty()) {
                System.out.println("No path found!");
                return new Chemin(new ArrayList<>());
            }

            System.out.print("Path: ");
            List<Case> cheminCases = new ArrayList<>();
            for (int i = 0; i < chemin.size(); i++) {
                Case caseNode = chemin.get(i).getValeur();
                cheminCases.add(caseNode);
                if (i != 0) {
                    System.out.print(" -> ");
                }
                System.out.print("Case [x=" + caseNode.getX() + ", y=" + caseNode.getY() + "]");
            }
            System.out.println();

            return new Chemin(cheminCases);
        } catch (Exception e) {
            System.out.println("An error occurred while displaying the path: " + e.getMessage());
            e.printStackTrace();
            return new Chemin(new ArrayList<>());
        }
    }





}