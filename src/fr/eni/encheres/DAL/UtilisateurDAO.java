package fr.eni.encheres.DAL;

import java.sql.SQLException;

import fr.eni.encheres.BO.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur selectByNickname(String pseudo, String motDePasse) throws SQLException;
	public void insert(Utilisateur utilisateur) throws SQLException;
}
