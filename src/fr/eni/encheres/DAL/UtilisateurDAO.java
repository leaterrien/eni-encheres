package fr.eni.encheres.DAL;


import java.util.List;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

public interface UtilisateurDAO {
	public Utilisateur selectByNickname(String pseudo) throws BusinessException;
	public Utilisateur selectByEmail(String email) throws BusinessException;
	public Utilisateur selectById(int noUtilisateur) throws BusinessException;
	public List<Utilisateur> selectAll() throws BusinessException;
	public Utilisateur insert(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur update(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur updatePassword(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur updateCredit(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur updateAdmin(Utilisateur utilisateur) throws BusinessException;
	public void delete(int noUtilisateur) throws BusinessException;
}