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
        Noeud<Case> depart = graphe.getNoeuds().get(yDepart * carte.getLargeur() + xDepart);
        Noeud<Case> arrivee = graphe.getNoeuds().get(yArrivee * carte.getLargeur() + xArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
        List<Case> cheminCases = convertirNoeudsEnCases(cheminNoeuds);
        return new Chemin(cheminCases);
    }

    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        for (int x = 0; x < carte.getLargeur(); x++) {
            for (int y = 0; y < carte.getHauteur(); y++) {
                Case currentCase = new Case(carte.getTuile(x, y), y, x);
                graphe.ajouterNoeud(new Noeud<>(currentCase));
                ajouterAretesVoisines(graphe, currentCase, x, y, carte.getLargeur(), carte.getHauteur());
            }
        }
        return graphe;
    }

    private static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        // Ajouter les arêtes vers les cases voisines
        if (x > 0) {
            Case left = new Case(null, y, x - 1);
            graphe.ajouterArete(graphe.getNoeuds().get(y * largeur + x), graphe.getNoeuds().get(y * largeur + (x - 1)), calculerCout(currentCase, left));
        }
        if (x < largeur - 1) {
            Case right = new Case(null, y, x + 1);
            graphe.ajouterArete(graphe.getNoeuds().get(y * largeur + x), graphe.getNoeuds().get(y * largeur + (x + 1)), calculerCout(currentCase, right));
        }
        if (y > 0) {
            Case up = new Case(null, y - 1, x);
            graphe.ajouterArete(graphe.getNoeuds().get(y * largeur + x), graphe.getNoeuds().get((y - 1) * largeur + x), calculerCout(currentCase, up));
        }
        if (y < hauteur - 1) {
            Case down = new Case(null, y + 1, x);
            graphe.ajouterArete(graphe.getNoeuds().get(y * largeur + x), graphe.getNoeuds().get((y + 1) * largeur + x), calculerCout(currentCase, down));
        }
    }

    private static double calculerCout(Case from, Case to) {
        // Simple calcul de distance euclidienne pour le coût
        return Math.sqrt(Math.pow(to.getX() - from.getX(), 2) + Math.pow(to.getY() - from.getY(), 2));
    }

    private static List<Case> convertirNoeudsEnCases(List<Noeud<Case>> cheminNoeuds) {
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }
        return cheminCases;
    }
}
