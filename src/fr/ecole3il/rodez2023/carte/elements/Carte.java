package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Carte représente une carte composée de tuiles disposées en une grille bidimensionnelle.
 * 
 * @author proussille
 */
public class Carte {
    private Tuile[][] tuiles; // Matrice de tuiles représentant la carte

    /**
     * Construit une nouvelle carte avec les tuiles spécifiées.
     * @param tuiles La matrice de tuiles représentant la carte.
     */
    public Carte(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }

    /**
     * Récupère la tuile située aux coordonnées spécifiées.
     * @param x La coordonnée x de la tuile.
     * @param y La coordonnée y de la tuile.
     * @return La tuile située aux coordonnées spécifiées.
     */

    public Tuile getTuile(int x, int y) {
        return tuiles[x][y];
    }
    public Case getCase(int x, int y) {
        return new Case(getTuile(x, y), x, y);
    }
    /**
     * Récupère la largeur de la carte (nombre de colonnes).
     * @return La largeur de la carte.
     */
    public int getLargeur() {
        return tuiles.length;
    }

    /**
     * Récupère la hauteur de la carte (nombre de lignes).
     * @return La hauteur de la carte.
     */
    public int getHauteur() {
        return tuiles[0].length;
    }
    public List<Case> getVoisins(Case c) {
        List<Case> voisins = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Skip the current case itself
                if (dx == 0 && dy == 0) continue;

                int nx = c.getX() + dx;
                int ny = c.getY() + dy;

                // Check if the coordinates are within the map
                if (nx >= 0 && nx < getLargeur() && ny >= 0 && ny < getHauteur()) {
                    voisins.add(getCase(nx, ny));
                }
            }
        }
        return voisins;
    }
        public boolean isWalkable(Case c) {
            // Implement this method based on your game's rules
            // For example, you might check the type of the tile at the case's coordinates
            Tuile tuile = getTuile(c.getX(), c.getY());
            // Assuming Tuile has a method isWalkable
            return tuile.isWalkable();
        }

}
