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
import java.util.stream.Collectors;

public class AdaptateurAlgorithme {

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> arrivee = graphe.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);

        if (noeudsChemin == null || noeudsChemin.isEmpty()) {
            return new Chemin(new ArrayList<>());
        }
        return new Chemin(afficherChemin(noeudsChemin));
    }

    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = new Noeud<>(carte.getCase(x, y));
                graphe.ajouterNoeud(currentNoeud);
            }
        }
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = graphe.getNoeud(x, y);
                ajouterAretesVoisines(graphe, currentNoeud, x, y, largeur, hauteur);
            }
        }
        return graphe;
    }

    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> currentNoeud, int x, int y, int largeur,
                                              int hauteur) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                    Noeud<Case> voisinNoeud = graphe.getNoeud(newX, newY);
                    if (voisinNoeud != null) {
                        double cout = calculerCout(currentNoeud.getValeur(), voisinNoeud.getValeur());
                        graphe.ajouterArete(currentNoeud, voisinNoeud, cout);
                        currentNoeud.ajouterVoisin(voisinNoeud);
                    }
                }
            }
        }
    }

    private static double calculerCout(Case from, Case to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Les cases doivent être non nulles");
        }
        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    private static List<Case> afficherChemin(List<Noeud<Case>> chemin) {
        if (chemin.isEmpty()) {
            System.out.println("Aucun chemin trouvé !");
            return new ArrayList<>();
        }
        System.out.print("Chemin: ");
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : chemin) {
            Case caseNode = noeud.getValeur();
            cheminCases.add(caseNode);
            System.out.print("\n -> " + caseNode.toString());
        }
        System.out.println();

        return cheminCases;
    }
}
