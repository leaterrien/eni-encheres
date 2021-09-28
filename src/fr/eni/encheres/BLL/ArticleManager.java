package fr.eni.encheres.BLL;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BO.Article;
import fr.eni.encheres.BO.EtatVente;
import fr.eni.encheres.DAL.ArticleDAO;
import fr.eni.encheres.DAL.DAOFactory;
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

}
