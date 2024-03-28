package fr.ecole3il.rodez2023.test;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrapheTest {

    @Test
    void testAjouterNoeud() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> noeud = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
        graphe.ajouterNoeud(noeud);

        assertTrue(graphe.getNoeuds().contains(noeud));
    }

    @Test
    void testAjouterArete() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> noeud1 = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
        Noeud<Case> noeud2 = new Noeud<>(new Case(Tuile.FORET, 1, 1));
        graphe.ajouterArete(noeud1, noeud2, 1.0);

        assertEquals(1.0, graphe.getCoutArete(noeud1, noeud2));
    }

    @Test
    void testGetNoeud() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> noeud = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
        graphe.ajouterNoeud(noeud);

        assertEquals(noeud, graphe.getNoeud(0, 0));
    }

    @Test
    void testGetVoisins() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> noeud1 = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
        Noeud<Case> noeud2 = new Noeud<>(new Case(Tuile.FORET, 1, 1));
        graphe.ajouterArete(noeud1, noeud2, 1.0);

        assertTrue(graphe.getVoisins(noeud1).contains(noeud2));
    }

    @Test
    void testAjoutPenalite() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> noeud = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
        graphe.ajouterNoeud(noeud);

        assertEquals(Tuile.DESERT.getPenalite(), graphe.ajoutPenalite(noeud));
    }
}