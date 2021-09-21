package fr.eni.encheres.DAL;

import java.sql.SQLException;

import fr.eni.encheres.BO.Utilisateur;

public class TestDAL {
	

	public static void main(String[] args) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		Utilisateur u1 = new Utilisateur("JeanDup", "Dupont", "Jean", "jean.dupont@campus-eni.fr", "0666666666", "21 avenue Pasteur", "92500", "Rueil","password",150, false);
		try {
			utilisateurDAO.insert(u1);
			System.out.println("Utilisateur ajouté : " + u1.toString());
			utilisateurDAO.selectByNickname("JeanDup", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
