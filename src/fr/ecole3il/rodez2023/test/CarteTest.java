package fr.ecole3il.rodez2023.test;


import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    @Test
    void testGetTuile() {
        Tuile[][] tuiles = new Tuile[2][2];
        tuiles[0][0] = Tuile.DESERT;
        tuiles[0][1] = Tuile.FORET;
        tuiles[1][0] = Tuile.MONTAGNES;
        tuiles[1][1] = Tuile.PLAINE;
        Carte carte = new Carte(tuiles);

        assertEquals(Tuile.DESERT, carte.getTuile(0, 0));
        assertEquals(Tuile.FORET, carte.getTuile(0, 1));
        assertEquals(Tuile.MONTAGNES, carte.getTuile(1, 0));
        assertEquals(Tuile.PLAINE, carte.getTuile(1, 1));
    }

    @Test
    void testGetLargeur() {
        Tuile[][] tuiles = new Tuile[3][2];
        Carte carte = new Carte(tuiles);

        assertEquals(3, carte.getLargeur());
    }

    @Test
    void testGetHauteur() {
        Tuile[][] tuiles = new Tuile[3][2];
        Carte carte = new Carte(tuiles);

        assertEquals(2, carte.getHauteur());
    }

    @Test
    void testGetCase() {
        Tuile[][] tuiles = new Tuile[2][2];
        tuiles[0][0] = Tuile.DESERT;
        tuiles[0][1] = Tuile.FORET;
        tuiles[1][0] = Tuile.MONTAGNES;
        tuiles[1][1] = Tuile.PLAINE;
        Carte carte = new Carte(tuiles);

        Case caseTest = carte.getCase(0, 0);
        assertEquals(Tuile.DESERT, caseTest.getTuile());
        assertEquals(0, caseTest.getX());
        assertEquals(0, caseTest.getY());
    }
}
