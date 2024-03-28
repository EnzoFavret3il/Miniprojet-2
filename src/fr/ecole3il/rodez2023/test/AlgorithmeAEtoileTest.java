package fr.ecole3il.rodez2023.test;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmeAEtoileTest {

    @Test
    void testTrouverChemin() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> noeud1 = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
        Noeud<Case> noeud2 = new Noeud<>(new Case(Tuile.FORET, 1, 1));
        Noeud<Case> noeud3 = new Noeud<>(new Case(Tuile.MONTAGNES, 2, 2));
        graphe.ajouterArete(noeud1, noeud2, 1.0);
        graphe.ajouterArete(noeud2, noeud3, 1.0);

        AlgorithmeAEtoile<Case> algorithme = new AlgorithmeAEtoile<>();
        List<Noeud<Case>> chemin = algorithme.trouverChemin(graphe, noeud1, noeud3);

        assertEquals(3, chemin.size());
        assertEquals(noeud1, chemin.get(0));
        assertEquals(noeud2, chemin.get(1));
        assertEquals(noeud3, chemin.get(2));
    }
}
