package fr.eni.encheres.BLL;

import java.time.LocalDate;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.DAL.ArticleDAO;
import fr.eni.encheres.DAL.DAOFactory;
import fr.eni.encheres.DAL.EnchereDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class EnchereManager {
	private static EnchereManager instance;
	private EnchereDAO enchereDAO;
	
	private EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if(instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void checkNoArticle(int idArticle, BusinessException businessException) {
		//noarticle null
		if(idArticle == 0) {
			businessException.addError(CodesResultatBLL.ARTICLE_NO_ARTICLE_NOT_VALID);
		}
	}
	
	public void checkNoUtilisateur(Utilisateur utilisateur, BusinessException businessException) {
		if(utilisateur.getNoUtilisateur() == 0) {
			businessException.addError(CodesResultatBLL.UTILISATEUR_NO_UTILISATEUR_NOT_VALID);
		}
	}
	
	public void checkDateEnchere(LocalDate dateEnchere, BusinessException businessException) {

		if(dateEnchere == null) {
			businessException.addError(CodesResultatBLL.NO_VENDEUR_NOT_VALID);
		}

	}
	
	public void checkMontantEnchere(int montantEnchere, BusinessException businessException) {
		if(montantEnchere == 0) {
			businessException.addError(CodesResultatBLL.NO_VENDEUR_NOT_VALID);
		}
	}
	
	

}
