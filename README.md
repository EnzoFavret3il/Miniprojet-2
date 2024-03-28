# DESCRIPTION
Ce projet avait pour but d'implementer les classes adéquates pour les algorithmes de Dijkstra et A Etoiles pour trouver le chemin le plus court sur une carte en fonction des terrains disponibles.

# CE QUE J'AI PU REALISER DU PROJET

L'ensemble du projet est fonctionnel et les algorithmes fonctionnes sans soucis.

## TestUnitaire

Une majorité des classes sont testés dans le package test. Les tests sont des tests unitaires.

Le projet compile sans soucis !




# REPONSE AUX QUESTIONS
## RELATION ENTRE LES NOEUDS

La structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes est une collection de type MAP car elle permet d'associer une clef et une valeur. 

La clef pourrait représenter un voisin du noeud et la valeur serait le coût entre l'arrête du noeud et son voisin

Les classes Noeud et Graphe ont été définies avec des paramêtres génériques afin de rendre le code plus flexible à la modification, réutilisable et compatible.

## AlgorithmChemin

La création d'une interface est une bonne pratique dans ce contexte car cela rend le code plus flexible aux modifications sans impacter le reste du code, cela améliore la conception du code en augementant la cohésion en regroupant les méthodes dans cette interface et enfin, cela facilite les tests 

# RETOUR SUR LE PROJET

Comme prévu, le projet était très intéréssant mais assez compliqué à réaliser. Le sujet était clair mais la mise en place des algorithmes a été le plus compliqué à cause, majoritairement, de mes capacités à mettre en place ce genre d'algorithme.
