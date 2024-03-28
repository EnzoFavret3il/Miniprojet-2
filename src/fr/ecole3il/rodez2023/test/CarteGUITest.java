package fr.ecole3il.rodez2023.test;

import fr.ecole3il.rodez2023.carte.application.CarteGUI;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class CarteGUITest {

    @Test
    void testDessinerCarte() {
        GenerateurCarte gen = new GenerateurCarte();
        Carte carte = gen.genererCarte(20, 20);
        CarteGUI carteGUI = new CarteGUI(carte);

        BufferedImage image = new BufferedImage(carte.getLargeur() * 32, carte.getHauteur() * 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        carteGUI.dessinerCarte(g);

        // Add assertions to verify the correctness of the drawn map
    }

    @Test
    void testTrouverChemin() {
        GenerateurCarte gen = new GenerateurCarte();
        Carte carte = gen.genererCarte(20, 20);
        CarteGUI carteGUI = new CarteGUI(carte);

        carteGUI.caseDepart = new Case(carte.getTuile(0, 0), 0, 0);
        carteGUI.caseArrivee = new Case(carte.getTuile(19, 19), 19, 19);
        carteGUI.trouverChemin();

        // Add assertions to verify the correctness of the found path
    }

    @Test
    void testGetTuileImage() {
        GenerateurCarte gen = new GenerateurCarte();
        Carte carte = gen.genererCarte(20, 20);
        CarteGUI carteGUI = new CarteGUI(carte);

        BufferedImage image = carteGUI.getTuileImage(Tuile.DESERT);
        Color color = new Color(image.getRGB(0, 0));

        assertEquals(Color.YELLOW.getRGB(), color.getRGB());
    }
}
