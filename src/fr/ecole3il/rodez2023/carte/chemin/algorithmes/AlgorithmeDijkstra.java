package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> precedent = new HashMap<>();
        Map<Noeud<E>, Double> distance = new HashMap<>();
        PriorityQueue<Noeud<E>> queue = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            distance.put(noeud, Double.POSITIVE_INFINITY);
            precedent.put(noeud, null);
        }
        distance.put(depart, 0.0);
        queue.add(depart);

        while (!queue.isEmpty()) {
            Noeud<E> noeudActuel = queue.poll();

            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                // Check if the neighbor is walkable
                if (!((Case) voisin.getValeur()).getTuile().isWalkable()) {
                    continue; // Skip this neighbor if it's not walkable
                }

                double nouveauDistance = distance.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauDistance < distance.get(voisin)) {
                    distance.put(voisin, nouveauDistance);
                }
                // Update the precedent map every time a node is visited
                precedent.put(voisin, noeudActuel);
                queue.offer(voisin);
            }
        }

        // Construct the shortest path
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = precedent.get(noeud);
        }
        Collections.reverse(chemin);

        // Check if the destination was reached
        if (!chemin.contains(arrivee)) {
            return Collections.emptyList(); // Indicates that there is no path
        }

        return chemin;
    }
}
