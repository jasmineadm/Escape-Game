package player;
import java.util.ArrayList;

import tools.Objet;

public class Inventaire {
	
	private ArrayList<Objet> objets;
	public Inventaire() {
	   objets = new ArrayList<Objet>();
	}

	public void ajouterObjet(Objet objet) {
	    objets.add(objet);
	}

	public void afficherInventaire() {
	    System.out.println("Inventaire d'objets récupérés dans cette zone :");
	    for (Objet objet : objets) {
	        System.out.println("- " + objet.getNom() + " qui " + objet.getDescription());
	    }
	}


}