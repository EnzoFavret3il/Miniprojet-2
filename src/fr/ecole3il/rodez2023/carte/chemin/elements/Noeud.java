package fr.ecole3il.rodez2023.carte.chemin.elements;
import java.util.ArrayList;
import java.util.List;

/**@author EnzoFAVRET*/
public class Noeud<E> {
	/**@param valeur --> noeud de type E
	 * @param voisins --> liste de noeud de type E*/
	private E valeur;
	private List<Noeud<E>> voisins;
	
	/**@constructor Noeud --> constructeur de base prenant en param l'attribut valeur*/
	public Noeud(E valeur) {
		
		this.valeur = valeur;
		this.voisins = voisins;
	}
	/**@getter E --> renvoie la valeur du getter E*/
	public E getValeur() {
		return valeur;
	}
	/**@getter List<Noeud<E>> --> retourne la liste des noeuds*/
	public List<Noeud<E>> getVoisins(){
		return voisins;
	}
	
	/**@methode ajouterVoisin--> Methode permettant d'ajouter un noeud voisin à la liste des voisins du noeud actuel*/
	public void ajouterVoisin(Noeud<E> voisin) {
		voisins.add(voisin);
	}
	
	
}
