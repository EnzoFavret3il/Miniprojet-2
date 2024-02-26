package fr.ecole3il.rodez2023.carte.chemin.elements;
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
	        // Verif si les noeuds depart et arrivee existe, sinon, ajouter au graphe via ajouterNoeud
	        if (!relations.containsKey(depart)) {
	            ajouterNoeud(depart);
	        }
	        if (!relations.containsKey(arrivee)) {
	            ajouterNoeud(arrivee);
	        }
	        
	    }


	    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
	        if (matriceAdj.containsKey(depart) && matriceAdj.get(depart).containsKey(arrivee)) {
	            return matriceAdj.get(depart).get(arrivee);
	        } else {
	            return Double.POSITIVE_INFINITY;
	        }
	    }

	    public List<Noeud<E>> getNoeuds() {
	        return new ArrayList<>(listeNoeuds);
	    }

	    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
	        if (matriceAdj.containsKey(noeud)) {
	            return new ArrayList<>(matriceAdj.get(noeud).keySet());
	        } else {
	            return new ArrayList<>();
	        }
	    }
}
