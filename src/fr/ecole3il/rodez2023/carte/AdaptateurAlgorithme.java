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

    public static Chemin trouverChemin(AlgorithmeChemin algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        // Convert the map to a graph
        Graphe<Case> graphe = convertirCarteEnGraphe(carte);

        // Create the start and end nodes
        Noeud<Case> noeudDepart = new Noeud<>(carte.getCase(xDepart, yDepart));
        Noeud<Case> noeudArrivee = new Noeud<>(carte.getCase(xArrivee, yArrivee));

        // Find the shortest path
        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);

        // Convert the nodes back to cases
        List<Case> casesChemin = noeudsChemin.stream()
                .map(Noeud::getValeur) // Use getValeur instead of getDonnees
                .collect(Collectors.toList());

        // Return the path
        return new Chemin(casesChemin);
    }

    private static Graphe<Case> convertirCarteEnGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // First, add all nodes to the graph
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> noeud = new Noeud<>(carte.getCase(x, y));
                graphe.ajouterNoeud(noeud);
            }
        }

        // Then, add edges between adjacent nodes
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> noeudCourant = graphe.getNoeud(carte.getCase(x, y));
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        // Skip the current node itself
                        if (dx == 0 && dy == 0) continue;

                        int nx = x + dx;
                        int ny = y + dy;

                        // Check if the coordinates are within the map
                        if (nx >= 0 && nx < largeur && ny >= 0 && ny < hauteur) {
                            Noeud<Case> noeudVoisin = graphe.getNoeud(carte.getCase(nx, ny));
                            // Add an edge with a cost of 1.0. You can adjust this cost as needed.
                            graphe.ajouterArete(noeudCourant, noeudVoisin, 1.0);
                        }
                    }
                }
            }
        }

        return graphe;
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
                // Ignore the case where i and j are both equal to zero (no movement)
                if (i == 0 && j == 0) {
                    continue;
                }

                int newX = x + i;
                int newY = y + j;

                // Check if the coordinates are valid
                if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                    Case neighborCase = new Case(currentCase.getTuile(), newX, newY);
                    Noeud<Case> currentNoeud = graphe.getNoeud(currentCase);
                    Noeud<Case> neighborNoeud = graphe.getNoeud(neighborCase);
                    if (currentNoeud != null && neighborNoeud != null) {
                        graphe.ajouterArete(currentNoeud, neighborNoeud, calculerCout(currentCase, neighborCase));
                    }
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