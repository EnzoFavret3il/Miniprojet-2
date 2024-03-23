package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.List;


class AlgoCheminTest {

	@Test
	void testFindDaWay() {
		//Graphe fictif pour les simuler les tests
		Graphe<String> graphe = new Graphe<>();
        Noeud<String> depart = new Noeud<>("Bar");
        Noeud<String> arrivee = new Noeud<>("Maison");
        graphe.ajouterNoeud(depart);
        graphe.ajouterNoeud(arrivee);
        graphe.ajouterArete(depart, arrivee, 1.0);
        
     // algo de test
        AlgorithmeChemin<String> algorithmeChemin = new AlgorithmeChemin<String>() {
        	
        	// return que deux valeurs pour simplifier les tests
            @Override
            public List<Noeud<String>> trouverChemin(Graphe<String> graphe, Noeud<String> depart, Noeud<String> arrivee) {
                return List.of(depart, arrivee);
            }

        };
        
        List<Noeud<String>> cheminTrouve = algorithmeChemin.trouverChemin(graphe, depart, arrivee);
        
        //Verif des resultats => retrouvons nous le chemin après le bar ?
        assertEquals(2, cheminTrouve.size());
        assertEquals(depart, cheminTrouve.get(0));
        assertEquals(arrivee, cheminTrouve.get(1));
	}

}
