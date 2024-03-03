package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.List;
/**@param <E> type générique des val du graphe*/
public interface AlgorithmeChemin<E> {
	/**@param => dans l'order:
	 * -	Graphe<E> graphe => graphe où la recherche est effectué
	 * -	Noeud<E> départ => noeud de départ
	 * -	Noeud<E> arrivee => noeud d'arrive
	 * 
	 * @return => renvoie un liste de noeud permettant de visualiser le chemin*/
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
    
    
    Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee);
}
