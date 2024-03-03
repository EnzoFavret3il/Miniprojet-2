package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
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
	}

}
