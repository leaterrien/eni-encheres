package fr.eni.encheres.BLL;

import java.time.LocalDate;

import fr.eni.encheres.BO.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.Categorie;
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
	
	BusinessException businessException = new BusinessException();

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
		
		
		article.setDateDebutEncheres(ArticleCheckValid.startDateAuctionCheck(article.getDateDebutEncheres(), businessException));
		article.setDateFinEncheres(ArticleCheckValid.endDateAuctionCheck(article.getDateDebutEncheres(), article.getDateFinEncheres(), businessException));
		if (!businessException.hasErrors()) {
			this.articleDAO.insert(article, categorie);
		}
		return article;
	}
	
	
	
}
