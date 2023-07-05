package game;

import map.ImageZone;
import map.Zone;
import player.Joueur;
import player.Personnage;
import tools.Devinette;
import tools.Enigme;
import tools.Objet;
import java.util.Random;
import java.util.Scanner;

import commande.Commande;

public class Partie {

	private int niveau = 0;
	private int duree;
	public int position;
	private Enigme enigmes = new Enigme();
	public Joueur joueur = new Joueur();
	public Zone[] zone;
	public Objet[] objets;
	public Personnage personnage = new Personnage();
	String[] img= new String[10];

	Scanner scanner = new Scanner(System.in);

	public Partie() {
		this.niveau = 1;
		this.duree = 0;
		this.position = 0;
		img[0]=new String("src/Cellule.png");
    	img[1]=new String("src/bureau.jpg");
    	img[2]=new String("src/Cantine.png");
    	img[3]=new String("src/SallePause.png");
    	img[4]=new String("src/Ponton.png");
    	img[5]=new String("src/Ponton.png");
    	img[6]=new String("src/Ponton.png");
    	img[7]=new String("src/Bateau.jpg");
    	img[8]=new String("src/foret.png");
    	img[9]=new String("src/hangard.png");

	}
	

	

	public void authentifierLeJoueur() {

		System.out.println(
				"Bienvenue dans le le jeu Escape Game!\nTapez 1 pour vous connecter \nTapez 2 pour vous enregistrer si vous n'avez pas de compte.");
		int choix_joueur = scanner.nextInt();
		if (choix_joueur == 2) {
			this.joueur.s_enregistrer();
		} else {
			this.joueur.s_authentifier();
		}
	}

	public void resolutionEnigme() {
		if (!this.joueur.repondre_enigme(this.niveau)) {
			System.out.println("Voulez-vous recommencer ? (oui/non)");
			String rep = scanner.nextLine();
			if (rep.equals("oui")) {
				this.position=0;
				this.jouer();
			} else {
				return;
			}
		}
	}

	public void passerAuNiveauSuivant() {
		this.niveau++;
		
	}

	public void passerALaZoneSuivante() {
		this.position++;
		
	}

	public void jouer() {
		
		System.out.println("Cela fait 2 ans que Mark, qui est un héros et un ancien soldat de l’armée de l’air, est emprisonné injustement sur\n"
				+ "l'île de Frioul à Marseille. Avant son arrestation, il avait caché un sac contenant des objets précieux qu’il devait\n"
				+ "vendre à un client de Thanos, un puissant malfaiteur. Par précaution, il avait remis une carte codée dans laquelle\n"
				+ "il avait noté la localisation de ce sac à son ami Martin. Récemment, Mark se retrouve menacé par Thanos, qui a\n"
				+ "pris sa fille en otage. En contrepartie, il veut que Mark lui remette le sac.\n"
				+ " Pour récupérer sa fille, il doit donc trouver un moyen de s’évader de prison afin de retrouver le sac et le donner à\n"
				+ "Thanos. Pour ce faire, Mark fait appel à Martin pour lui demander de lui ramener la carte et un téléphone.\n"
				+ "Cependant, Mark ne peut pas simplement demander à son ami de lui remettre ces objets directement, car il est en\n"
				+ "prison et donc sous surveillance constante. Ainsi, il demande à son ami de cacher la carte et le téléphone dans une\n"
				+ "zone du Vieux-Port de Marseille, où il pourra les récupérer une fois qu'il aura réussi à s'échapper de prison.");
		
		System.out.println("\n \n--------------------C'EST LE DEBUT DE L'AVANTURE-----------------\n \n");
		// initialisation de tous les variables à 0

		//appel de la méthode pour s'authentifier 
		//this.authentifierLeJoueur();

		// ajouter un texte qui récapitule le scénario et les règles du jeu.

		// début du jeu dans la cellule
		zone = Zone.initZones();
		System.out.println("\n Tu es actuellement dans  " + zone[this.position].getNomZone() + "\n");
		String val= this.img[position];
		
		ImageZone img=new ImageZone(val);
		img.displayImage(700, 500);

		// les objets à récupérer dans cette zone
		if (zone[this.position].getObjets().length != 0) {
			for (Objet objet : zone[this.position].getObjets()) {
				System.out.println("Dans cette zone vous devez récupérer les objets suivants:\n-" + objet.getNom()
						+ " qui " + objet.getDescription() + ".\n");
			}
		}

		System.out.println("Résous l'énigme ci-dessous pour pouvoir passer à la zone suivante.");
		this.resolutionEnigme();
		this.personnage.recupeObjet(this.position);

		System.out.println("Bravo! Tu as réussi à sortir de " + zone[this.position].getNomZone() + ".");

		this.passerALaZoneSuivante();
		 

		// A la sortie de la cellule
		if (this.position >= 1 && this.position <= 3) {
			System.out.println(
					"Maintenant il faut récupérer les trois clés qui te permettront de t'évader de cette prison.\nChacune de ces clés est cachée dans une zone de la prison.\n\nChoisis la zone dans laquelle tu veux chercher en premier.");

			for (int i = 0; i < 3; i++) {
				this.joueur.choisirLaZoneOuChercherLesCles(this.position);
				val= this.img[this.position];
				 img=new ImageZone(val); 
				img.displayImage(700, 500);
				System.out.println("Résous l'énigme ci-dessous pour pouvoir passer à la zone suivante.");

				this.resolutionEnigme();
				this.personnage.recupeObjet(this.position);
				if (i != 2) {
					System.out.println(
							"Bravo! Tu as réussi à récupérer la clé.Maintenant choisis la prochaine zone où chercher.");

				}
				this.passerALaZoneSuivante();
			}
			this.passerAuNiveauSuivant();

			// arrivée au vieux-port
			boolean res = false;
			while ((this.position >= 4 && this.position <= 6) && !res == true) {
				
				if (this.position == 4) {
					System.out.println(
							"Félicitation ! Tu as réussi à t'évader de prison.\nMaintenant que tu es sorti de prison, tu dois récupérer la carte codée pour déchiffrer la localisation du sac et le portable pour te rendre à cette adresse.\n "
									+ "Pour cela il te faut trouver le bon ponton parmi les 3 pontons du vieux-port.\nIndice : ...\n");

				}
				val= this.img[this.position];
				 img=new ImageZone(val); 
				img.displayImage(700, 500);
				
				res = this.joueur.trouverPonton();
				if (!res) {
					System.out.println("voulez vous recommencer ? (oui/non)");
					String rep1 = scanner.nextLine();
					if (rep1.equals("oui")) {
						this.jouer();
					} else {
						return;
					}

				}
				this.passerALaZoneSuivante();
			}

			// passer directement à la zone 7 si le jour réussi du premier coup
			while (this.position != 7) {
				this.passerALaZoneSuivante();
			}

			// zone 7
			if (this.position == 7) {
				val= this.img[this.position];
				 img=new ImageZone(val); 
				img.displayImage(700, 500);
				System.out.println(
						"Tu es maintenant dans le bateau. Pour récupérer la carte et le téléphone, tu dois deviner un nombre entre 1 et 100.");
				Devinette.jeuDeDevinette();
				System.out.println(
						"Bravo! Tu a récupéré la carte et le téléphone. Maintenant il faut déchiffrer le code de la carte. Pour cela résous l'énigme ci-dessous.");
				this.resolutionEnigme();
			}
			this.personnage.recupeObjet(this.position);
			this.zone[this.position].explorer();
			this.passerALaZoneSuivante();

			// zone 8
			if (this.position == 8) {
				val= this.img[this.position];
				 img=new ImageZone(val); 
				img.displayImage(700, 500);
				this.passerAuNiveauSuivant();
				System.out.println(
						"Bravo! tu a réussi à te téléporter j'usqu'à la forêt. Il y'a 3 objets à récupérer ici : la pelle, la boisson énergisante et le sac.Pour cela tu dois résoudre les énigme suivantes.\n");
				this.resolutionEnigme();
				this.resolutionEnigme();
				this.resolutionEnigme();
				this.personnage.recupeObjet(this.position);
				System.out.println(
						"Tu a réussi à récupérer le dernier objet qui est le sac. Dépêche toi pour te rendre au hangar afin de libérer ta fille.");
				this.passerALaZoneSuivante();
				// Commande.se_deplacer();

				// zone 9
				
				val= this.img[this.position];
				 img=new ImageZone(val); 
				img.displayImage(700, 500);
				System.out.println("Bravo! tu es arrivé à la dernière étape. Dans ce hangar se trouve votre fille. Résous l'énigme ci-dessous pour la récupérer et gagner la partie.\n");

				this.resolutionEnigme();
				this.personnage.recupeObjet(this.position);
				System.out.println("FÉLICATATION!!! TU A GAGNÉ LA PARTIE.");
			}
		}
	}

}