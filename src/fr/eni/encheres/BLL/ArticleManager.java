package fr.eni.encheres.BLL;

import java.time.LocalDate;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
import fr.eni.encheres.BO.Enchere;
import fr.eni.encheres.BO.EtatVente;
import fr.eni.encheres.BO.Retrait;
import fr.eni.encheres.DAL.ArticleDAO;
import fr.eni.encheres.DAL.DAOFactory;
import fr.eni.encheres.DAL.RetraitDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleManager {
	
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
	

	public Article getArticle(int noArticle) throws BusinessException{
		BusinessException businessException = new BusinessException();
		
		checkNoArticle(noArticle, businessException);
		if(businessException.hasErrors()) {
			throw businessException;
		}
		//récupération de l'article 
		Article article = articleDAO.selectById(noArticle);
		if(article == null) {
			businessException.addError(CodesResultatBLL.ARTICLE_GET_ARTICLE_RECEIVE_NULL);
		} else {
			checkArticle(article, businessException);
		}
		//throw de businessException si les données reçues ne sont pas correctes
		if (businessException.hasErrors()) {
			throw businessException;
		}
		return article;
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
			businessException.addError(CodesResultatBLL.NO_VENDEUR_NOT_VALID);
		}
	}
	
	public void checkNoCategorie(Categorie categorie, BusinessException businessException) {
		if(categorie.getNoCategorie() == 0) {
			businessException.addError(CodesResultatBLL.NO_CATEGORIE_NOT_VALID);
		}
	}


	/**
	 * Récupération des articles pour un utilisateur déconnecté
	 * 
	 * @param noCategorie
	 * @param rechercheNom
	 * @return
	 * @throws BusinessException
	 */
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
	 * Récupération des articles pour un utilisateur connecté
	 * 
	 * @param noCategorie
	 * @param rechercheNom
	 * @return
	 * @throws BusinessException
	 */
	public List<Article> getArticlesConnected(int noCategorie, String rechercheNom, boolean achatSelected,
			List<String> conditions, int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		List<Article> listeArticles = articleDAO.selectAllConnected(noCategorie, rechercheNom, achatSelected,
				conditions, noUtilisateur);

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
		if (article.getDateDebutEncheres().isAfter(LocalDate.now())) {
			etatVente = EtatVente.NOUVEAU;
		}
		if (article.getDateFinEncheres().isBefore(LocalDate.now())) {
			if (article.getAcheteur() != null) {
				etatVente = EtatVente.ACHETE;
			} else {
				etatVente = EtatVente.NON_VENDU;
			}
		}
		return etatVente;
	}
	

	
    
	//add article
	public Article addArticle(Article article, int categorie) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		article.setDateDebutEncheres(ArticleCheckValid.startDateAuctionCheck(article.getDateDebutEncheres(), businessException));
		article.setDateFinEncheres(ArticleCheckValid.endDateAuctionCheck(article.getDateDebutEncheres(), article.getDateFinEncheres(), businessException));
		if (!businessException.hasErrors()) {
			this.articleDAO.insert(article, categorie);
		}
		return article;
	}
	

	public Enchere getMaxEnchere(Article article) {
		Enchere enchereMax = null;
		for (Enchere enchere : article.getListEncheres()) {
			if (enchereMax == null || enchere.getMontant() > enchereMax.getMontant()) {
				enchereMax = enchere;
			}
		}
		return enchereMax;
	}

}
