package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.elements.Case;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente un graphe générique.
 *
 * @param <E> le type d'élément contenu dans les nœuds du graphe.
 * @author EnzoFAVRET
 */
public class Graphe<E> {

	private Map<Noeud<E>, Map<Noeud<E>, Double>> matriceAdj;
	private List<Noeud<E>> listeNoeuds;

	/**
	 * Constructeur du graphe.
	 */
	public Graphe() {
		matriceAdj = new HashMap<>();
		listeNoeuds = new ArrayList<>();
	}

	/**
	 * Ajoute un nœud au graphe.
	 *
	 * @param noeud le nœud à ajouter.
	 */
	public void ajouterNoeud(Noeud<E> noeud) {
		if (!listeNoeuds.contains(noeud)) {
			listeNoeuds.add(noeud);
			matriceAdj.put(noeud, new HashMap<>());
		}
	}

	/**
	 * Ajoute une arête entre deux nœuds avec un coût spécifié.
	 *
	 * @param depart  le nœud de départ de l'arête.
	 * @param arrivee le nœud d'arrivée de l'arête.
	 * @param cout    le coût de l'arête.
	 */
	public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
		if (!matriceAdj.containsKey(depart)) {
			ajouterNoeud(depart);
		}
		if (!matriceAdj.containsKey(arrivee)) {
			ajouterNoeud(arrivee);
		}
		matriceAdj.get(depart).put(arrivee, cout);
	}

	/**
	 * Récupère le coût d'une arête entre deux nœuds spécifiés.
	 *
	 * @param depart  le nœud de départ.
	 * @param arrivee le nœud d'arrivée.
	 * @return le coût de l'arête entre les deux nœuds.
	 */
	public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
		if (matriceAdj.containsKey(depart)) {
			Map<Noeud<E>, Double> aretesAdjacentes = matriceAdj.get(depart);
			if (aretesAdjacentes.containsKey(arrivee)) {
				return aretesAdjacentes.get(arrivee);
			}
		}
		return 0;
	}

	/**
	 * Récupère la liste des nœuds du graphe.
	 *
	 * @return la liste des nœuds.
	 */
	public List<Noeud<E>> getNoeuds() {
		return new ArrayList<>(listeNoeuds);
	}

	/**
	 * Récupère le nœud situé aux coordonnées spécifiées.
	 *
	 * @param x la coordonnée X du nœud.
	 * @param y la coordonnée Y du nœud.
	 * @return le nœud trouvé, ou null s'il n'existe pas.
	 */
	public Noeud<E> getNoeud(int x, int y) {
		for (Noeud<E> noeud : listeNoeuds) {
			if (noeud.getValeur() instanceof Case) {
				Case caseValue = (Case) noeud.getValeur();
				if (caseValue.getX() == x && caseValue.getY() == y) {
					return noeud;
				}
			}
		}
		return null;
	}

	/**
	 * Récupère la liste des voisins d'un nœud spécifié.
	 *
	 * @param noeud le nœud dont on veut récupérer les voisins.
	 * @return la liste des voisins du nœud.
	 */
	public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
		if (matriceAdj.containsKey(noeud)) {
			return new ArrayList<>(matriceAdj.get(noeud).keySet());
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Ajoute la pénalité de la case associée au nœud spécifié.
	 *
	 * @param noeud le nœud dont on veut ajouter la pénalité.
	 * @return la pénalité associée à la case du nœud.
	 */
	public int ajoutPenalite(Noeud<E> noeud) {
		if (noeud.getValeur() instanceof Case) {
			Case caseValue = (Case) noeud.getValeur();
			return caseValue.getTuile().getPenalite();
		}
		return 0;
	}
}
