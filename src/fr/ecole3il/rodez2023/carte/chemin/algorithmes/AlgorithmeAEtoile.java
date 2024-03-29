package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

	//Methode qui trouve le chemin good
	@Override
	/**@param graphe   le graphe pour la recherche
	 *@param depart   le nœud de départ
	 *@param arrivee  le nœud d'arrivée
	 *@return liste de noeud représentant le chemin le plus court eentre depart/arrivee
	 */
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
                double nouveauDistance = distance.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauDistance < distance.get(voisin)) {
                    distance.put(voisin, nouveauDistance);
                    precedent.put(voisin, noeudActuel);
                    queue.offer(voisin);
                }
            }
        }

        List<Noeud<E>> chemin = new ArrayList<>();
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            if (precedent.get(noeud) != null) {
                chemin.add(noeud);
            }
        }
        Collections.reverse(chemin);

        return chemin;
    }


    //Initialise les couts
	/**
	* @param graphe   le graphe dans lequel la recherche est effectuée.
    * @param depart   le nœud de départ.
    * @param arrivee  le nœud d'arrivée.
    * @return une map contenant les coûts estimés pour chaque nœud.
    */
   private Map<Noeud<E>, Double> initialiserCoutsEstimes(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
       Map<Noeud<E>, Double> coutsEstimes = new HashMap<>();
       for (Noeud<E> noeud : graphe.getNoeuds()) {
           // Utilisation de la distance à vol d'oiseau comme heuristique pour les coûts estimés
           double distance = calculerDistanceCuiCui(noeud, arrivee);
           coutsEstimes.put(noeud, distance);
       }
       return coutsEstimes;
   }
   //Methode calcul distance vol oiseau, random pour l'instant car je ne sais pas comment faire
	private double calculerDistanceCuiCui(Noeud<E> noeud1, Noeud<E> noeud2) {
		Random ran= new Random();
		return ran.nextDouble();
	}
	/**Initialisation Priorité
	*@param coutsEstimes une map contenant les coûts estimés pour chaque nœud
    * @return une file de priorité initialisée avec les coûts estimés des nœuds
    */
   private PriorityQueue<Noeud<E>> initialiserFilePriorite(Map<Noeud<E>, Double> coutsEstimes) {
       return new PriorityQueue<>(Comparator.comparingDouble(coutsEstimes::get));
   }
   
 //Methode de contruction du chemin en fonction du chemin le plus court
   /**@param arrivee        le nœud d'arrivee
    *@param predecesseurs  une map contenant les prédécesseurs de chaque nœud
    *@return une liste de nœuds représentant le chemin le plus court
    */
   private List<Noeud<E>> construireChemin(Noeud<E> arrivee, Map<Noeud<E>, Noeud<E>> predecesseurs) {
       List<Noeud<E>> chemin = new ArrayList<>();
       Noeud<E> noeud = arrivee;
       while (noeud != null) {
           chemin.add(noeud);
           noeud = predecesseurs.get(noeud);
       }
       //inversion de la collection pour récupérer le chemin dans le bonne ordre
       Collections.reverse(chemin);
       return chemin;
   }

}
