package fr.eni.encheres.DAL;

import java.sql.SQLException;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

public interface UtilisateurDAO {
	public Utilisateur selectByNickname(String pseudo) throws BusinessException;
	public Utilisateur selectByEmail(String email) throws BusinessException;
	public void insert(Utilisateur utilisateur) throws BusinessException;
}
