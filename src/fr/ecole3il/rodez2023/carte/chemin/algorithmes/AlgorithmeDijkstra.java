package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
    	
    	//Initialisation des couts et de la file en fonction des couts
    	/**@param graphe   le graphe pour la recherche
    	 *@param depart   le nœud de départ
    	 *@param arrivee  le nœud d'arrivée
    	 *@return liste de noeud représentant le chemin le plus court eentre depart/arrivee
    	 */
        Map<Noeud<E>, Double> couts = initialiserCouts(graphe, depart);
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = initialiserFilePriorite(couts);

        //Recherche dans la file et mise à jours des coûts et des predécésseurs
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break;
            }
            updateCoutPredecesseur(graphe, noeudActuel, couts, predecesseurs, filePriorite);
        }

        return construireChemin(arrivee, predecesseurs);
    }
    //methode d'initalisation des couts
    /**@param graphe le graphe dans lequel la recherche est effectuée
     *@param depart le nœud de depart
     *@return une map contenant les coûts de base pour chaque nœud
     */
    private Map<Noeud<E>, Double> initialiserCouts(Graphe<E> graphe, Noeud<E> depart) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
        }
        couts.put(depart, 0.0);
        return couts;
    }
    //methode d'initialisation permettant d'initialiser la file de priorité
    /**@param couts une map contenant les coûts actuels des nœuds
     *@return une file de priorité initialisé avec les coûts des nœuds
     */
    private PriorityQueue<Noeud<E>> initialiserFilePriorite(Map<Noeud<E>, Double> couts) {
        return new PriorityQueue<>(Comparator.comparingDouble(couts::get));
    }

    //méthode mettant à jours les couts et les predecesseurs
    /**@param graphe         le graphe dans lequel la recherche est effectuée
     *@param noeudActuel    le nœud entrain d'être exploré
     *@param couts          une map contenant les coûts actuels des nœuds
     *@param predecesseurs  une map contenant les prédécesseurs de chaque nœud
     *@param filePriorite   la file de priorité contenant les nœuds 
     */
    private void updateCoutPredecesseur(Graphe<E> graphe, Noeud<E> noeudActuel, Map<Noeud<E>, Double> couts, Map<Noeud<E>, Noeud<E>> predecesseurs,
                                                 PriorityQueue<Noeud<E>> filePriorite) {
        for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
            double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
            if (nouveauCout < couts.get(voisin)) {
                couts.put(voisin, nouveauCout);
                predecesseurs.put(voisin, noeudActuel);
                filePriorite.add(voisin);
            }
        }
    }

    //Methode de contruction du chemin en fonction du chemin le plus court
    /**@param arrivee le nœud d'arrivee
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
    @Override
    public Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        return null;
    }



}
