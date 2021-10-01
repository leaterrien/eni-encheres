package fr.eni.encheres.DAL;

import fr.eni.encheres.DAL.jdbc.ArticleDAOJdbcImpl;
import fr.eni.encheres.DAL.jdbc.CategorieDAOJdbcImpl;
import fr.eni.encheres.DAL.jdbc.EnchereDAOJdbcImpl;
import fr.eni.encheres.DAL.jdbc.RetraitDAOJdbcImpl;
import fr.eni.encheres.DAL.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static RetraitDAO getretraitDAO() {
		return new RetraitDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

}
