package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import game.Partie;

public class SourceDeDonnees {
	Connection connexion;
	Partie partie;
	public void connexionBD() {
		try {
			// Charger le driver JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Établir la connexion à la base de données
			String url = "jdbc:mysql://localhost:3306/personnage";
			String utilisateur = "root";
			String motDePasse = "";
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

			// Tester la connexion
			if (connexion != null) {
				System.out.println("Connexion à la base de données réussie !");
			} else {
				System.out.println("Erreur lors de la connexion à la base de données.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouter_joueur(String prenom, String nom, String pseudo, String mdp) throws SQLException {

		this.connexionBD();

		String req = "INSERT INTO personnage(nom, password, niveau, pseudo, prenom) values(?,?,?,?,?)";
		PreparedStatement ps = connexion.prepareStatement(req);
		ps.setString(5, prenom);
		ps.setString(1, nom);
		ps.setString(4, pseudo);
		ps.setString(2, mdp);
		ps.setInt(3, this.partie.position);

		ps.executeUpdate();
	}

	public boolean verifier_joueur(String pseudo, String mdp) {

		this.connexionBD();

		boolean res = false;
		try {
			Statement stmt = connexion.createStatement();
			String req = "SELECT * FROM personnage WHERE pseudo='" + pseudo + "' and password='" + mdp + "'";
			ResultSet rs = stmt.executeQuery(req);
			res = rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}