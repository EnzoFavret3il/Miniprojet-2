package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.*;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve le chemin le plus court entre deux noeuds dans un graphe pondéré en utilisant l'algorithme A*.
     *
     * @param graphe   le graphe dans lequel chercher le chemin.
     * @param debut    le noeud de départ.
     * @param cible    le noeud d'arrivée.
     * @return         une liste de noeuds représentant le chemin le plus court de départ à arrivée.
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> debut, Noeud<E> cible) {
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> precedent = new HashMap<>();
        Set<Noeud<E>> explore = new HashSet<>();
        PriorityQueue<Noeud<E>> filePrioritaire = new PriorityQueue<>(Comparator.comparingDouble(coutTotalEstime::get));

        // Initialisation des coûts
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            precedent.put(noeud, null);
        }
        coutActuel.put(debut, 0.0);
        coutTotalEstime.put(debut, heuristique(debut, cible));
        filePrioritaire.add(debut);

        // Algorithme A*
        while (!filePrioritaire.isEmpty()) {
            Noeud<E> courant = filePrioritaire.poll();
            if (courant.equals(cible))
                break;
            explore.add(courant);

            // Exploration des voisins
            for (Noeud<E> voisin : graphe.getVoisins(courant)) {
                if (explore.contains(voisin))
                    continue;

                double nouveauCout = coutActuel.get(courant) + graphe.getCoutArete(courant, voisin);
                if (nouveauCout < coutActuel.get(voisin)) {
                    precedent.put(voisin, courant);
                    coutActuel.put(voisin, nouveauCout);
                    coutTotalEstime.put(voisin, coutActuel.get(voisin) + heuristique(voisin, cible));
                    if (!filePrioritaire.contains(voisin))
                        filePrioritaire.add(voisin);
                }
            }
        }

        // Reconstruction du chemin
        LinkedList<Noeud<E>> chemin = contructionDuChemin(precedent, cible, debut);
        Collections.reverse(chemin);

        return new ArrayList<>(chemin);

    }

    /**
     * Calcule l'heuristique entre deux noeuds dans le graphe.
     *
     * @param n      le noeud actuel.
     * @param cible  le noeud cible.
     * @return       la valeur de l'heuristique.
     */
    private double heuristique(Noeud<E> n, Noeud<E> cible) {
        // Calcul de l'heuristique basée sur la distance euclidienne entre les deux noeuds
        return 0.0; // Remplacer par la formule appropriée
    }

    /**
     * Reconstruit le chemin à partir des prédécesseurs.
     *
     * @param precedent  la map des prédécesseurs.
     * @param cible      le noeud cible.
     * @param debut      le noeud de départ.
     * @return           le chemin reconstruit.
     */
    private LinkedList<Noeud<E>> contructionDuChemin(Map<Noeud<E>, Noeud<E>> precedent, Noeud<E> cible,
                                                     Noeud<E> debut) {
        LinkedList<Noeud<E>> chemin = new LinkedList<>();
        Noeud<E> courant = cible;
        while (courant != null) {
            chemin.add(courant);
            courant = precedent.get(courant);
        }
        Collections.reverse(chemin);

        return chemin;
    }

}
