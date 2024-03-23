package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

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

            if (noeudActuel.equals(arrivee)) {
                break;
            }

            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauDistance = distance.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauDistance < distance.get(voisin)) {
                    distance.put(voisin, nouveauDistance);
                    precedent.put(voisin, noeudActuel);
                    queue.offer(voisin);
                }
            }
        }

        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudActuel = arrivee;

        while (noeudActuel != null) {
            chemin.add(noeudActuel);
            noeudActuel = precedent.get(noeudActuel);
        }
        Collections.reverse(chemin);

        return chemin;
    }
}
