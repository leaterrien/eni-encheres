package fr.eni.encheres.DAL;

import fr.eni.encheres.DAL.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

}
