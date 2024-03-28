package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;

/**
 * Cette classe implémente l'algorithme de Dijkstra pour trouver le chemin le plus court
 * entre deux noeuds dans un graphe pondéré.
 *
 * @param <E> le type des éléments contenus dans les noeuds du graphe.
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    /**
     * Trouve le chemin le plus court entre deux noeuds dans un graphe pondéré.
     *
     * @param graphe   le graphe dans lequel chercher le chemin.
     * @param depart   le noeud de départ.
     * @param arrivee  le noeud d'arrivée.
     * @return         une liste de noeuds représentant le chemin le plus court de départ à arrivée.
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> predecesseur = new HashMap<>();
        Map<Noeud<E>, Double> distance = new HashMap<>();
        PriorityQueue<Noeud<E>> filePrioritaire = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            distance.put(noeud, Double.POSITIVE_INFINITY);
            predecesseur.put(noeud, null);
        }
        distance.put(depart, 0.0);
        filePrioritaire.add(depart);
        while (!filePrioritaire.isEmpty()) {
            Noeud<E> noeudActuel = filePrioritaire.poll();
            if (noeudActuel.equals(arrivee)) {
                break;
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouvelleDistance = distance.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                    if (nouvelleDistance < distance.get(voisin)) {
                    distance.put(voisin, nouvelleDistance);
                    predecesseur.put(voisin, noeudActuel);
                    filePrioritaire.offer(voisin);
                }
            }
        }

        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudActuel = arrivee;

        while (noeudActuel != null) {
            chemin.add(noeudActuel);
            noeudActuel = predecesseur.get(noeudActuel);
        }
Collections.reverse(chemin);

        return chemin;
    }
}
