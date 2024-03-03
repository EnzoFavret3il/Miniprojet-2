package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

	@Override
	public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
		// TODO Auto-generated method stub
		return null;
	}
	private Map<Noeud<E>, Double> initialiserCoutsEstimes(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
	
	}
	private double calculerDistanceCuiCui(Noeud<E> noeud1, Noeud<E> noeud2) {
		
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
