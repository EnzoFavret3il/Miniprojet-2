package fr.ecole3il.rodez2023.carte.application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class AffichageChemin extends JFrame {
    public AffichageChemin(int a, int b) {
        super();
        this.setSize(400, 300);
        this.setTitle("Le chemin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Création du JPanel pour le centrage
        JPanel centerPanel = new JPanel(new GridBagLayout());
        
        // Création du JLabel avec le texte approprié
        JLabel label = new JLabel("Voici le chemin le plus court [" + a + " : " + b + "]");
        
        // Ajout du JLabel au centre du JPanel
        centerPanel.add(label);
        
        // Ajout du JPanel au centre du JFrame
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
       
        this.setVisible(true);
    }

    public AffichageChemin() {
        super();
        this.setSize(400, 300); // Taille par défaut
        this.setTitle("Le chemin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Création du JPanel pour le centrage
        JPanel centerPanel = new JPanel(new GridBagLayout());
        
        // Création du JLabel avec le texte approprié
        JLabel label = new JLabel("Aucun Chemin trouvé");
        
        // Ajout du JLabel au centre du JPanel
        centerPanel.add(label);
        
        // Ajout du JPanel au centre du JFrame
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
}
