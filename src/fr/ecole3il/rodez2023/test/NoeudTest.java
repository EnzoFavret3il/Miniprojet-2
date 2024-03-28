package fr.ecole3il.rodez2023.test;

import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoeudTest {

    @Test
    void testGetValeur() {
        Noeud<String> noeud = new Noeud<>("Test");
        assertEquals("Test", noeud.getValeur());
    }

    @Test
    void testGetVoisins() {
        Noeud<String> noeud1 = new Noeud<>("Test1");
        Noeud<String> noeud2 = new Noeud<>("Test2");
        noeud1.ajouterVoisin(noeud2);

        assertTrue(noeud1.getVoisins().contains(noeud2));
    }

    @Test
    void testAjouterVoisin() {
        Noeud<String> noeud1 = new Noeud<>("Test1");
        Noeud<String> noeud2 = new Noeud<>("Test2");
        noeud1.ajouterVoisin(noeud2);

        assertTrue(noeud1.getVoisins().contains(noeud2));
    }
}
