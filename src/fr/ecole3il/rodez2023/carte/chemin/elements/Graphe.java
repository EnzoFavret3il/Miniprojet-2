package fr.ecole3il.rodez2023.carte.chemin.elements;
import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**@author EnzoFAVRET*/
public class Graphe<E> {
		/**@param Map<Noeud<E>> --> initalisation de la collection de type map
		 * @param matriceAdj
		 * @param listeNoeuds --> List des noeuds => ai-je vraiment besoin de le préciser ??*/
	    private Map<Noeud<E>, Map<Noeud<E>, Double>> matriceAdj;
	    private List<Noeud<E>> listeNoeuds;

	    /**@Constructeur --> constructeur du graphe*/
	    public Graphe() {
	        matriceAdj = new HashMap<>();
	        listeNoeuds = new ArrayList<>();
	    }
	    
	    /**@méthode ajouterNoeud --> ajoute un noeud à la matrice adjacente et à la liste*/
	    public void ajouterNoeud(Noeud<E> noeud) {
	        if (!listeNoeuds.contains(noeud)) {
	            listeNoeuds.add(noeud);
	            matriceAdj.put(noeud, new HashMap<>());
	        }
	    }
	    
	    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
	        //Verif noeud départ ou arrivee existe
	    	if (!matriceAdj.containsKey(depart)) {
	            ajouterNoeud(depart);
	        }
	        if (!matriceAdj.containsKey(arrivee)) {
	            ajouterNoeud(arrivee);
	        }
	        //Iniatilise 
	        matriceAdj.get(depart).put(arrivee, cout);
	    }

	    /**@methode return la valeur */
	    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
	        // Vérif si  nœud de départ existe
	        if (matriceAdj.containsKey(depart)) {
	            // recup map des arretes du noeud départ
	            Map<Noeud<E>, Double> aretesAdjacentes = matriceAdj.get(depart);
	            
	            // Verrif si arrete existe
	            if (aretesAdjacentes.containsKey(arrivee)) {
		                // renvoie le cout de l'arrete
	                return aretesAdjacentes.get(arrivee);
	            }
	        }
	        ///Si val nul, return 0
	        return 0;
	    }

	    /**@getter --> Renvoie la liste des noeuds du graphe*/
	    public List<Noeud<E>> getNoeuds() {
	        return new ArrayList<>(listeNoeuds);
	    }
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
	    /**@getter --> renvoie la liste de voisins d'un noeud*/
	    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
	        if (matriceAdj.containsKey(noeud)) {
	            return new ArrayList<>(matriceAdj.get(noeud).keySet());
	        } else {
	            return new ArrayList<>();
	        }
	    }

	public int ajoutPenalite(Noeud<E> noeud) {
		if (noeud.getValeur() instanceof Case) {
			Case caseValue = (Case) noeud.getValeur();
			return caseValue.getTuile().getPenalite();
		}
		return 0;
	}
}


