package fr.eni.encheres.BLL;

import java.time.LocalDate;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.BO.EtatVente;
import fr.eni.encheres.DAL.ArticleDAO;
import fr.eni.encheres.DAL.DAOFactory;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleManager {
	
	BusinessException businessException = new BusinessException();
	
	// On vérifie si la date de mise en vente n'est pas inférieure à la date du jour
	public LocalDate checkValidStartDate(LocalDate dateDebutEncheres) throws BusinessException {
		LocalDate debut = null;
		
		debut = DateNewSellValid.startDateAuctionCheck(dateDebutEncheres, businessException);
		return dateDebutEncheres;
	}
	
	// On vérifie si la date de mise en vente n'est pas inférieure à la date de fin de l'enchère
	public LocalDate checkValidEndDate(LocalDate dateDebutEncheres, LocalDate dateFinEncheres) throws BusinessException {
		LocalDate fin = null;
			
		fin = DateNewSellValid.endDateAuctionCheck(dateDebutEncheres, dateFinEncheres, businessException);
		return dateFinEncheres;
	}
	
	

	private static ArticleManager instance;
	private ArticleDAO articleDAO;

	private ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	
	public void checkArticle(Article article, BusinessException businessException) {
		checkNoArticle(article.getNoArticle(), businessException);
		checkNom(article.getNom(), businessException);
		checkDescription(article.getDescription(), businessException);
		checkNoVendeur(article.getVendeur(), businessException);
		checkNoCategorie(article.getCategorie(), businessException);
	}
	
	public void checkNoArticle(int idArticle, BusinessException businessException) {
		//noarticle null
		if(idArticle == 0) {
			businessException.addError(CodesResultatBLL.ARTICLE_NO_ARTICLE_NOT_VALID);
		}
	}
	
	public void checkNom(String nom, BusinessException businessException) {
		boolean valid = true;
		
		if(nom == null) {
			valid = false;
		}
		else if(nom.length()>30) {
			valid = false;
		}
		if(valid == false) {
			businessException.addError(CodesResultatBLL.ARTICLE_NAME_NOT_VALID);
		}
	}
	public void checkDescription(String description, BusinessException businessException) {
		boolean valid = true;
		
		if(description == null) {
			valid = false;
		}
		else if(description.length()>300) {
			valid = false;
		}
		if(valid == false) {
			businessException.addError(CodesResultatBLL.ARTICLE_DESCRIPTION_NOT_VALID);
		}
	}

	public void checkNoVendeur(Utilisateur vendeur, BusinessException businessException) {
		if(vendeur.getNoUtilisateur() == 0) {
			businessException.addError(CodesResultatBLL.ARTICLE_NO_VENDEUR_NOT_VALID);
		}
	}
	
	public void checkNoCategorie(Categorie categorie, BusinessException businessException) {
		if(categorie.getNoCategorie() == 0) {
			businessException.addError(CodesResultatBLL.ARTICLE_NO_CATEGORIE_NOT_VALID);
		}
	}

	public List<Article> getArticlesDeconnected(int noCategorie, String rechercheNom) throws BusinessException {
		BusinessException businessException = new BusinessException();
		List<Article> listeArticles = articleDAO.selectAllDeconnected(noCategorie, rechercheNom);

		// Gestion de l'attribut etatVente de chacun des articles
		for (Article article : listeArticles) {
			article.setEtatVente(defineEtatVente(article));
		}

		// TODO : check des données reçues

		// Throw de businessException si les données reçues ne sont pas correctes
		if (businessException.hasErrors()) {
			throw businessException;
		}
		return listeArticles;
	}

	/**
	 * Définission de l'attribut etatVente de l'article
	 * 
	 * @param article
	 * @return
	 */
	private EtatVente defineEtatVente(Article article) {
		EtatVente etatVente = EtatVente.EN_VENTE;
		if (article.getDateFinEncheres().isBefore(LocalDate.now())) {
			if (article.getAcheteur() != null) {
				etatVente = EtatVente.ACHETE;
			} else {
				etatVente = EtatVente.NON_VENDU;
			}
		}
		return etatVente;
	}

	//methode add article
}
